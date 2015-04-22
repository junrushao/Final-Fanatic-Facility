grammar Compiler2015;

/* TODO:
 * 0. FunctionCall should support (FunctionType | FunctionPointerType)
 * 1. Scope in structures seems not correct;
 * 2. Need to declare a constant function pointer once a function is declared;
 * 3. Check whether a type is well defined after AST is built;
 * 4. Check function main exists;
 * 5. Consider functions without return types(default return int)
 * 6. In declaration, array size should be determined, except that it has initializer list.
 * 7. ArrayPointerType in parameter type should first be converted into
 */
@parser::header {
import Compiler2015.AST.*;
import Compiler2015.AST.Statement.*;
import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.*;
import Compiler2015.AST.Statement.ExpressionStatement.*;
import Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression.*;
import Compiler2015.AST.Declaration.*;
import Compiler2015.AST.Type.*;
import Compiler2015.Environment.*;
import Compiler2015.Exception.*;
import Compiler2015.Utility.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.Stack;
}

@parser::members {
}

/* Top level */

compilationUnit
	: (functionDefinition | declaration | ';')* EOF
	;

// TODO : change initDeclaratorList -> declaratorList
declaration
locals [ ArrayList<Type> types, ArrayList<String> names, ArrayList<Initializer> inits, int n]
@after {
	TypeAnalyser.exit();
}
	:	'typedef'
		typeSpecifier
			{
				TypeAnalyser.enter($typeSpecifier.ret);
			}
		(initDeclaratorList
			{
				$types = $initDeclaratorList.types;
				$names = $initDeclaratorList.names;
				$inits = $initDeclaratorList.inits;
				$n = $types.size();
				for (int i = 0; i < $n; ++i) {
					if ($inits.get(i) != null)
						throw new CompilationError("typedef cannot be used with initializers.");
					Environment.symbolNames.defineTypedefName($names.get(i), $types.get(i));
				}
			}
		)?
		';'
	|	typeSpecifier
			{
				TypeAnalyser.enter($typeSpecifier.ret);
			}
		(initDeclaratorList
			{
				$types = $initDeclaratorList.types;
				$names = $initDeclaratorList.names;
				$inits = $initDeclaratorList.inits;
				$n = $types.size();
				for (int i = 0; i < $n; ++i) {
					Environment.symbolNames.defineVariable($names.get(i), $types.get(i), $inits.get(i));
				}
			}
		)? ';'
	;

functionDefinition
locals [ Type type, String name, Statement s = null, ArrayList<Type> parameterTypes, ArrayList<String> parameterNames, boolean hasVaList = false, int uId = -1]
@after {
	TypeAnalyser.exit();
}
	:	typeSpecifier { TypeAnalyser.enter($typeSpecifier.ret); }
		declarator
		{
			$type = TypeAnalyser.analyse();
		}
		'('(parameterTypeList
			{
				$parameterTypes = $parameterTypeList.types;
				$parameterNames = $parameterTypeList.names;
				$hasVaList = $parameterTypeList.hasVaList;
			}
		)?')'
		{
			$type = new FunctionType($type, $parameterTypes, $parameterNames, $hasVaList);
			$uId = Environment.symbolNames.defineVariable($name, $type, null);
		}
		compoundStatement
		{
			$s = $compoundStatement.ret;
			Environment.symbolNames.defineVariable($uId, $type, $s);
		}
	;

initDeclaratorList returns [ArrayList<Type> types = new ArrayList<Type>(), ArrayList<String> names = new ArrayList<String>(), ArrayList<Initializer> inits = new ArrayList<Initializer>()]
	: initDeclarator
		{
			$types.add($initDeclarator.type);
			$names.add($initDeclarator.name);
			$inits.add($initDeclarator.init);
		}
		(',' initDeclarator
			{
				$types.add($initDeclarator.type);
				$names.add($initDeclarator.name);
				$inits.add($initDeclarator.init);
			}
		)*
	;

initDeclarator returns [Type type, String name, Initializer init = null]
	: declarator
		{
			$type = TypeAnalyser.analyse();
			$name = $declarator.name;
		}
		('=' initializer
			{
				$init = $initializer.ret;
			}
		)?
	;

typeSpecifier returns [Type ret]
	:	'void' { $ret = new VoidType(); }
	|	'char' { $ret = new CharType(); }
	|	'int' { $ret = new IntType();  }
	|	typedefName { $ret = $typedefName.ret; }
	|	structOrUnionSpecifier { $ret = $structOrUnionSpecifier.ret; }
	;

structOrUnionSpecifier returns [Type ret]
locals [boolean isUnion, String name]
@init {
	$name = "";
}
	:	structOrUnion
		{
			$isUnion = $structOrUnion.isUnion;
		}
		(Identifier { $name = $Identifier.text; } )?
		'{' { StructBuilder.enter($name, $isUnion); }
			(structDeclaration
				{
					StructBuilder.addAttributes($structDeclaration.types, $structDeclaration.names);
				}
			)*
		'}' { $ret = StructBuilder.exit(); }
	|	structOrUnion { $isUnion = $structOrUnion.isUnion; }
		Identifier
		{
			$name = $Identifier.text;
			$ret = StructBuilder.decalreDirectly($name, $isUnion);
		}
	;

structOrUnion returns [boolean isUnion]
	:	'struct' { $isUnion = false; }
	|	'union'  { $isUnion = true; }
	;

/**
 * declaration inside struct / union
 */
structDeclaration returns [ArrayList<Type> types, ArrayList<String> names]
@init {
	$types = new ArrayList<Type>();
	$names = new ArrayList<String>();
}
@after {
	for (Type t : $types)
		if (t instanceof FunctionType)
			throw new CompilationError("Could not declare / define functions inside struct / union.");
	TypeAnalyser.exit();
}
	:	typeSpecifier { TypeAnalyser.enter($typeSpecifier.ret); }
		(
			d1 = declarator
			{
				$types.add(TypeAnalyser.analyse());
				$names.add($d1.name);
			}
			(
				',' d2 = declarator
				{
					$types.add(TypeAnalyser.analyse());
					$names.add($d2.name);
				}
			)*
		)? ';'
	;

declarator returns [String name]
locals [int n = 0]
@after {
	for (int i = 0; i < $n; ++i)
		TypeAnalyser.addStar();
}
	:	('*' { ++$n; } )*
		directDeclarator { $name = $directDeclarator.name; }
	;

directDeclarator returns [String name]
	:	Identifier { $name = $Identifier.text; }
	|	'(' declarator ')' { $name = $declarator.name; }
	|	d1 = directDeclarator { $name = $d1.name; }
		'[' constantExpression ']' { TypeAnalyser.addArray($constantExpression.ret); }
	|	d2 = directDeclarator { $name = $d2.name;  }
		'[' ']' { TypeAnalyser.addArray(null); }
	|	d3 = directDeclarator { $name = $d3.name; }
		'(' ')' { TypeAnalyser.addParameter(null, false); }
	|	d4 = directDeclarator { $name = $d4.name; }
		'(' parameterTypeList ')' { TypeAnalyser.addParameter($parameterTypeList.types, $parameterTypeList.hasVaList); }
	;

parameterTypeList returns [ArrayList<Type> types, ArrayList<String> names, boolean hasVaList = false]
	:	parameterList
		{
			$types = $parameterList.types;
			$names = $parameterList.names;
		}
		(',' '...' { $hasVaList = true; } )?
	;

parameterList returns [ArrayList<Type> types = new ArrayList<Type>(), ArrayList<String> names = new ArrayList<String>()]
	:	p1 = parameterDeclaration
		{
			$types.add($p1.type);
			$names.add($p1.name);
		}
		(',' p2 = parameterDeclaration
			{
				$types.add($p1.type);
				$names.add($p1.name);
			}
		)?
	;

parameterDeclaration returns [Type type, String name]
@after {
	TypeAnalyser.exit();
}
	:	typeSpecifier
		{
			TypeAnalyser.enter($typeSpecifier.ret);
			$type = $typeSpecifier.ret;
			$name = "";
		}
	|	typeSpecifier { TypeAnalyser.enter($typeSpecifier.ret); }
		declarator
		{
			$type = TypeAnalyser.analyse();
			$name = $declarator.name;
		}
	|	typeSpecifier { TypeAnalyser.enter($typeSpecifier.ret); }
		abstractDeclarator
		{
			$type = TypeAnalyser.analyse();
			$name = "";
		}
	;

abstractDeclarator
locals [int n = 0]
@after {
	for (int i = 0; i < $n; ++i)
		TypeAnalyser.addStar();
}
	:	('*' { ++$n; } )+
	|	('*' { ++$n; } )*
		directAbstractDeclarator
	;

directAbstractDeclarator
	:	'(' abstractDeclarator ')'
	|	'[' ']'
			{ TypeAnalyser.addArray(null); }
	|	'[' constantExpression ']'
			{ TypeAnalyser.addArray($constantExpression.ret); }
	|	'(' ')'
			{ TypeAnalyser.addParameter(null, false); }
	|	'(' parameterTypeList ')'
			{ TypeAnalyser.addParameter($parameterTypeList.types, $parameterTypeList.hasVaList); }
	|	directAbstractDeclarator '[' ']'
	|	directAbstractDeclarator '[' constantExpression ']'
			{ TypeAnalyser.addArray($constantExpression.ret); }
	|	directAbstractDeclarator '(' ')'
			{ TypeAnalyser.addParameter(null, false); }
	|	directAbstractDeclarator '(' parameterTypeList? ')'
			{ TypeAnalyser.addParameter($parameterTypeList.types, $parameterTypeList.hasVaList); }
	;

typedefName returns [Type ret]
	:	{ Environment.isTypedefName($Identifier.text) }? Identifier
	;

initializer returns [Initializer ret]
	: assignmentExpression { $ret = new Initializer($assignmentExpression.ret); }
	| '{' i1 = initializer
			{
				$ret = new Initializer(new ArrayList<Initializer>());
				$ret.list.add($i1.ret);
			}
		(',' i2 = initializer { $ret.list.add($i2.ret); })* '}'
	;

/* Statements */
statement returns [Statement ret]
	: expressionStatement	{ $ret = $expressionStatement.ret; }
	| compoundStatement		{ $ret = $compoundStatement.ret; }
	| selectionStatement	{ $ret = $selectionStatement.ret;  }
	| iterationStatement	{ $ret = $iterationStatement.ret; }
	| jumpStatement			{ $ret = $jumpStatement.ret; }
	;

expressionStatement returns [Statement ret = null]
	: (expression { $ret = $expression.ret; })? ';'
	;

compoundStatement returns [CompoundStatement ret]
locals [ ArrayList<Statement> statements = new ArrayList<Statement>(); ]
	: '{' 					{ Environment.enterScope(); }
		(
			declaration
		|
			(statement { $statements.add($statement.ret); } )
		)*
	  '}'
			{
				$ret = new CompoundStatement(Environment.symbolNames.getVariablesInCurrentScope(), $statements);
				Environment.exitScope();
			}
	;

selectionStatement returns [IfStatement ret]
locals [ Expression e1 = null, Statement s1 = null, Statement s2 = null ]
	: 'if' '(' expression { $e1 = $expression.ret; } ')' st1 = statement { $s1 = $st1.ret; } ('else' st2 = statement { $s2 = $st2.ret; } )?
		{
			$ret = new IfStatement($e1, $s1, $s2);
		}
	;

iterationStatement returns [Statement ret]
	: 'while' '(' expression ')' statement
			{ $ret = new WhileStatement($expression.ret, $statement.ret); }
	| 'for' '(' ex1 = expression? ';' ex2 = expression? ';' ex3 = expression? ')' statement
			{ $ret = new ForStatement($ex1.ret, $ex2.ret, $ex3.ret, $statement.ret); }
	;

jumpStatement returns [Statement ret]
locals [ Expression e = null ]
	: 'continue' ';'				{ $ret = new ContinueStatement(); }
	| 'break' ';'					{ $ret = new BreakStatement(); }
	| 'return' (expression {$e = $expression.ret;} )? ';'
									{ $ret = new ReturnStatement($e); }
	;

/* Expressions  */
expression returns [Expression ret]
	: a1 = assignmentExpression   { $ret = $a1.ret; }
		(',' a2 = assignmentExpression
			{ $ret = CommaExpression.getExpression($ret, $a2.ret);  }
		)*
	;

assignmentExpression returns [Expression ret]
	: logicalOrExpression { $ret = $logicalOrExpression.ret; }
	| unaryExpression assignmentOperator assignmentExpression
				{ $ret = AssignClass.getExpression($unaryExpression.ret, $assignmentExpression.ret, $assignmentOperator.text); }
	;

assignmentOperator
	: '=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
	;

constantExpression returns [Expression ret]
	: logicalOrExpression
		{
			$ret = $logicalOrExpression.ret;
			if (!($ret instanceof Constant))
				throw new CompilationError("Not constant.");
		}
	;

logicalOrExpression returns [Expression ret]
	: a1 = logicalAndExpression { $ret = $a1.ret; }
		('||' a2 = logicalAndExpression
			{ $ret = LogicalOr.getExpression($ret, $a2.ret); }
		)*
	;

logicalAndExpression returns [Expression ret]
	: a1 = inclusiveOrExpression  { $ret = $a1.ret; }
		('&&' a2 = inclusiveOrExpression
			{ $ret = LogicalAnd.getExpression($ret, $a2.ret);  }
		)*
	;

inclusiveOrExpression returns [Expression ret]
	: a1 = exclusiveOrExpression   { $ret = $a1.ret; }
		('|' a2 = exclusiveOrExpression
			{ $ret = BitwiseOr.getExpression($ret, $a2.ret); }
		)*
	;

exclusiveOrExpression returns [Expression ret]
	: a1 = andExpression   { $ret = $a1.ret; }
		('^' a2 = andExpression
			{ $ret = BitwiseXOR.getExpression($ret, $a2.ret); }
		)*
	;

andExpression returns [Expression ret]
	: a1 = equalityExpression   { $ret = $a1.ret; }
		('&' a2 = equalityExpression
			{ $ret = BitwiseAnd.getExpression($ret, $a2.ret); }
		)*
	;

equalityExpression returns [Expression ret]
	: a1 = relationalExpression   { $ret = $a1.ret; }
		(op = equalityOperator a2 = relationalExpression
			{
				if ($op.text.equals("=="))
					$ret = EqualTo.getExpression($ret, $a2.ret);
				else
					$ret = NotEqualTo.getExpression($ret, $a2.ret);
			}
		)*
	;

equalityOperator
	: '==' | '!='
	;

relationalExpression returns [Expression ret]
	: a1 = shiftExpression   { $ret = $a1.ret; }
		(op = relationalOperator a2 = shiftExpression
			{
				if ($op.text.equals("<"))
					$ret = LessThan.getExpression($ret, $a2.ret);
				else if ($op.text.equals(">"))
					$ret = GreaterThan.getExpression($ret, $a2.ret);
				else if ($op.text.equals("<="))
					$ret = LE.getExpression($ret, $a2.ret);
				else
					$ret = GE.getExpression($ret, $a2.ret);
			}
		)*
	;

relationalOperator
	: '<' | '>' | '<=' | '>='
	;

shiftExpression returns [Expression ret]
	: a1 = additiveExpression { $ret = $a1.ret; }
		(op = shiftOperator a2 = additiveExpression
			{
				if ($op.text.equals("<<"))
					$ret = ShiftLeft.getExpression($ret, $a2.ret);
				else
					$ret = ShiftRight.getExpression($ret, $a2.ret);
			}
		)*
	;

shiftOperator
	: '<<' | '>>'
	;

additiveExpression returns [Expression ret]
	: a1 = multiplicativeExpression { $ret = $a1.ret; }
		(op = additiveOperator a2 = multiplicativeExpression
			{
				if ($op.text.equals("+"))
					$ret = Add.getExpression($ret, $a2.ret);
				else
					$ret = Subtract.getExpression($ret, $a2.ret);
			}
		)*
	;

additiveOperator
	: '+' | '-'
	;

multiplicativeExpression returns [Expression ret]
	: a1 = castExpression { $ret = $castExpression.ret; }
		(op = multiplicativeOperator a2 = castExpression
			{
				if ($op.text.equals("*"))
					$ret = Multiply.getExpression($ret, $a2.ret);
				else if ($op.text.equals("/"))
					$ret = Divide.getExpression($ret, $a2.ret);
				else
					$ret = Modulo.getExpression($ret, $a2.ret);
			}
		)*
	;

multiplicativeOperator
	: '*' | '/' | '%'
	;

castExpression returns [Expression ret]
	: unaryExpression { $ret = $unaryExpression.ret; }
	| '(' typeName ')' castExpression
		{
			$ret = CastExpression.getExpression($typeName.ret, $castExpression.ret);
		}
	;

typeName returns [Type ret]
@after {
	TypeAnalyser.exit();
}
	:	typeSpecifier
		{
			TypeAnalyser.enter($typeSpecifier.ret);
			$ret = $typeSpecifier.ret;
		}
	|	typeSpecifier { TypeAnalyser.enter($typeSpecifier.ret); }
		(abstractDeclarator
			{
				$ret = TypeAnalyser.analyse();
			}
		)?
	;

unaryExpression returns [Expression ret]
	: postfixExpression { $ret = $postfixExpression.ret; }
	| '++' unaryExpression { $ret = PrefixSelfInc.getExpression($unaryExpression.ret); }
	| '--' unaryExpression { $ret = PrefixSelfDec.getExpression($unaryExpression.ret); }
	| op = unaryOperator a2 = castExpression
		{
			if ($op.text.equals("&"))
				$ret = AddressFetch.getExpression($a2.ret);
			else if ($op.text.equals("*"))
				$ret = AddressAccess.getExpression($a2.ret);
			else if ($op.text.equals("+"))
				$ret = Positive.getExpression($a2.ret);
			else if ($op.text.equals("-"))
				$ret = Negative.getExpression($a2.ret);
			else if ($op.text.equals("~"))
				$ret = BitwiseNot.getExpression($a2.ret);
			else if ($op.text.equals("!"))
				$ret = LogicalNot.getExpression($a2.ret);
		}
	| 'sizeof' unaryExpression { $ret = new Sizeof($unaryExpression.ret); }
	| 'sizeof' '(' typeName ')' { $ret = new IntConstant($typeName.ret.sizeof()); }
	;

unaryOperator
	: '&' | '*' | '+' | '-' | '~' | '!'
	;

postfixExpression returns [Expression ret]
locals [ ArrayList<Expression> arg = null ]
	: primaryExpression { $ret = $primaryExpression.ret; }
	| p = postfixExpression '[' expression ']' { $ret = ArrayAccess.getExpression($p.ret, $expression.ret); }
	| p = postfixExpression '(' (arguments { $arg = $arguments.ret; } )? ')' { $ret = FunctionCall.getExpression($p.ret, $arg); }
	| p = postfixExpression '.' Identifier { $ret = MemberAccess.getExpression($p.ret, $Identifier.text); }
	| p = postfixExpression '->' Identifier  { $ret = PointerMemberAccess.getExpression($p.ret, $Identifier.text); }
	| p = postfixExpression '++' { $ret = PostfixSelfInc.getExpression($p.ret); }
	| p = postfixExpression '--' { $ret = PostfixSelfDec.getExpression($p.ret); }
	;

arguments returns [ArrayList<Expression> ret = new ArrayList<Expression>() ]
	: a1 = assignmentExpression { $ret.add($a1.ret); }
		(',' a2 = assignmentExpression
			{
				$ret.add($a2.ret);
			}
		)*
	;

primaryExpression returns [Expression ret]
locals [ ArrayList<String> s = new ArrayList<String>() ]
	: { Environment.isVariable($Identifier.text) }? Identifier
		{
			$ret = IdentifierExpression.getExpression($Identifier.text);
		}
	| constant
		{
			$ret = $constant.ret;
		}
	| (StringLiteral
		{
			$s.add($StringLiteral.text);
		}
	  )+
		{
			$ret = StringConstant.getExpression($s);
		}
	| '(' expression ')'
		{
			$ret = $expression.ret;
		}
	;

constant returns [Expression ret]
	: DecimalConstant { $ret = IntConstant.getExpression($DecimalConstant.text, 10); }
	| OctalConstant { $ret = IntConstant.getExpression($OctalConstant.text, 8); }
	| HexadecimalConstant { $ret = IntConstant.getExpression($HexadecimalConstant.text, 16);  }
	| CharacterConstant { $ret = CharConstant.getExpression($CharacterConstant.text); }
	;

// Lexer

Identifier
	:	IdentifierNondigit
		(	IdentifierNondigit
		|	Digit
		)*
	;

fragment
IdentifierNondigit
	:	Nondigit
	;

fragment
Nondigit
	:	[a-zA-Z_$]
	;

fragment
Digit
	:	[0-9]
	;

fragment
HexQuad
	:	HexadecimalDigit HexadecimalDigit HexadecimalDigit HexadecimalDigit
	;

DecimalConstant
	:	NonzeroDigit Digit*
	;

OctalConstant
	:	'0' OctalDigit*
	;

HexadecimalConstant
	:	HexadecimalPrefix HexadecimalDigit+
	;

fragment
HexadecimalPrefix
	:	'0' [xX]
	;

fragment
NonzeroDigit
	:	[1-9]
	;

fragment
OctalDigit
	:	[0-7]
	;

fragment
HexadecimalDigit
	:	[0-9a-fA-F]
	;

fragment
Sign
	:	'+' | '-'
	;

fragment
DigitSequence
	:	Digit+
	;

CharacterConstant
	:	'\'' CharSequence '\''
	;

fragment
CharSequence
	:	Char+
	;

fragment
Char
	:	~['\\\r\n]
	|	EscapeSequence
	;

fragment
EscapeSequence
	:	SimpleEscapeSequence
	|	OctalEscapeSequence
	|	HexadecimalEscapeSequence
	;

fragment
SimpleEscapeSequence
	:	'\\' ['"?abfnrtv\\]
	;

fragment
OctalEscapeSequence
	:	'\\' OctalDigit
	|	'\\' OctalDigit OctalDigit
	|	'\\' OctalDigit OctalDigit OctalDigit
	;

fragment
HexadecimalEscapeSequence
	:	'\\x' HexadecimalDigit+
	;

StringLiteral
	:	'"' CharSequence? '"'
	;

Preprocessing
	:	'#' ~[\r\n]* ('\r' | '\n' | ('\r''\n'))
		-> skip
	;

Whitespace
	:	[ \t]+
		-> skip
	;

Newline
	:	(	'\r' '\n'?
		|	'\n'
		)
		-> skip
	;

BlockComment
	:	'/*' .*? '*/'
		-> skip
	;

LineComment
	:	'//' ~[\r\n]*
		-> skip
	;
