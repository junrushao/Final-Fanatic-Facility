grammar Compiler2015;

/* TODO:
 * 1. Scope in structures seems not correct;
 * 2. Need to declare a constant function pointer once a function is declared;
 * 3. Check whether a type is well defined after AST is built;
 * 4. Check function main exists;
 * 5. Consider functions without return types(default return int)
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
}

@parser::members {
public int inStructDepth = 0;
}

/* Top level */

compilationUnit
	: (declaration | functionDefinition)* EOF
	;

declaration
locals [ ArrayList<Type> types = null, ArrayList<String> names = null, ArrayList<Initializer> inits = null, int n = 0 ]
	: 'typedef' typeSpecifier declarators[$typeSpecifier.ret] ';'
		{
			$types = $declarators.types;
			$names = $declarators.names;
			$n = $types.size();
			for (int i = 0; i < $n; ++i)
				Environment.symbolNames.defineTypedefName($names.get(i), $types.get(i));
		}
	| typeSpecifier initDeclarators[$typeSpecifier.ret]? ';'
		{
			$types = $initDeclarators.types;
			$names = $initDeclarators.names;
			$inits = $initDeclarators.inits;
			$n = $types.size();
			for (int i = 0; i < $n; ++i) {
				Environment.symbolNames.defineVariable($names.get(i), $types.get(i), $inits.get(i));
			}
		}
	;

functionDefinition
locals [ Type returnType, String name, ArrayList<Type> types = null, ArrayList<String> names = null, boolean hasVaList = false, Statement s = null ]
@after {
	Environment.symbolNames.defineFunction(
		$name,
		$returnType,
		$types,
		$names,
		$hasVaList,
		$s
	);
}
	: typeSpecifier
		plainDeclarator[$typeSpecifier.ret]
			{
				$returnType = $plainDeclarator.type;
				$name = $plainDeclarator.name;
			}
		'(' (parameters
				{
					$types = $parameters.types;
					$names = $parameters.names;
					$hasVaList = $parameters.hasVaList;
				}
			) ? ')'
		compoundStatement
			{
				$s = $compoundStatement.ret;
			}
	;

/**
 * something like:
 *     int a, boolean b, struct X c, ...
 */
parameters returns [ArrayList<Type> types = new ArrayList<Type>(), ArrayList<String> names = new ArrayList<String>(), boolean hasVaList = false]
locals [int n = 0, Type type, String name]
@init {
	HashSet<String> existNames = new HashSet<String>();
}
	: p1 = plainDeclaration {
			$type = $p1.type;
			$name = $p1.name;
			if (existNames.contains($name))
				throw new CompilationError("Name appears more than once.");
			existNames.add($name);
			$types.add($type);
			$names.add($name);
		}
		(',' p2 = plainDeclaration {
			$type = $p2.type;
			$name = $p2.name;
			if (existNames.contains($name))
				throw new CompilationError("Name appears more than once.");
			existNames.add($name);
			$types.add($type);
			$names.add($name);
		} )*
		(',' '...' { $hasVaList = true; } )?
	;

declarators[Type innerType] returns [ArrayList<Type> types = new ArrayList<Type>(), ArrayList<String> names = new ArrayList<String>()]
	: declarator[innerType] { $types.add($declarator.type); $names.add($declarator.name); } (',' declarator[innerType] { $types.add($declarator.type); $names.add($declarator.name); } )*
	;

initDeclarators[Type innerType] returns [ArrayList<Type> types = new ArrayList<Type>(), ArrayList<String> names = new ArrayList<String>(), ArrayList<Initializer> inits = new ArrayList<Initializer>()]
	: initDeclarator[innerType]
		{
			$types.add($initDeclarator.type);
			$names.add($initDeclarator.name);
			$inits.add($initDeclarator.init);
		}
		(',' initDeclarator[innerType]
			{
				$types.add($initDeclarator.type);
				$names.add($initDeclarator.name);
				$inits.add($initDeclarator.init);
			}
		)*
	;

initDeclarator[Type innerType] returns [Type type, String name, Initializer init = null]
	: declarator[innerType] {$type = $declarator.type; $name = $declarator.name; } ('=' initializer {$init = $initializer.ret; } )?
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

/**
 * something like:
 *     void
 *     struct x
 *     struct x { int a, b; }
 */
typeSpecifier returns [Type ret]
locals [String name = null, StructOrUnionDeclaration su = null, int uId = -1]
	: 'void' { $ret = new VoidType(); }
	| 'char' { $ret = new CharType(); }
	| 'int'  { $ret = new IntType();  }
	| typedefName { $ret = $typedefName.ret; }
	| structOrUnion (Identifier { $name = $Identifier.text; } )?
		'{'
			{
				$uId = Environment.classNames.declareStructOrUnion($name, $structOrUnion.isUnion);
				Environment.enterScope();
				$su = new StructOrUnionDeclaration(-1, $structOrUnion.isUnion, new HashMap<String, Type>(), new ArrayList<StructOrUnionDeclaration>());
				++inStructDepth;
			}
			(t2 = typeSpecifier declarators[$t2.ret] ';'
				{
					$su.addAttributes($declarators.types, $declarators.names);
				}
			)+
		'}'
			{
				$ret = (Type) (Environment.classNames.table.get($uId).ref);
				Environment.classNames.defineStructOrUnion($uId, $structOrUnion.isUnion, $su.members, $su.anonymousMembers);
				Environment.exitScope(true);
				--inStructDepth;
			}
	| structOrUnion Identifier
			{
				$name = $Identifier.text;
				$uId = Environment.classNames.declareStructOrUnion($name, $structOrUnion.isUnion);
				$ret = (Type) (Environment.classNames.table.get($uId).ref);
			}
	;

typedefName returns [Type ret]
	: { Environment.isTypedefName($Identifier.text) }? Identifier
	;

structOrUnion returns [boolean isUnion]
	: 'struct' { $isUnion = false; }
	| 'union'  { $isUnion = true; }
	;

/**
 * something like:
 *     int a
 *     char **a
 */
plainDeclaration returns [Type type, String name]
	: typeSpecifier declarator[$typeSpecifier.ret]
		{
			$type = $declarator.type;
			$name = $declarator.name;
		}
	;

/**
 * something like:
 *     **a(int a, int b)
 *     *a[1][2][3]
 */
declarator[Type innerType] returns [Type type, String name]
locals [ArrayList<Expression> list = new ArrayList<Expression>() ]
	: { inStructDepth == 0 }? plainDeclarator[$innerType] '(' ')'
		{
			$type = new FunctionPointerType(
				$plainDeclarator.type,
				new ArrayList<Type>(),
				new ArrayList<String>(),
				false
			);
			$name = $plainDeclarator.name;
		}
	| { inStructDepth == 0 }? plainDeclarator[$innerType] '(' parameters ')'
		{
			$type = new FunctionPointerType(
				$plainDeclarator.type,
				$parameters.types,
				$parameters.names,
				$parameters.hasVaList
				);
		}
	| plainDeclarator[$innerType]
		{
			$type = $plainDeclarator.type;
			$name = $plainDeclarator.name;
		}
	| plainDeclarator[$innerType]
		'[' ']' { $list.add(null); } // the first dimension could be empty
		('[' constantExpression { $list.add($constantExpression.ret); } ']')*
		{
			$type = new ArrayPointerType($plainDeclarator.type, $list);
			$name = $plainDeclarator.name;
		}
	| plainDeclarator[$innerType] ('[' constantExpression { $list.add($constantExpression.ret); } ']')+
		{
			$type = new ArrayPointerType($plainDeclarator.type, $list);
			$name = $plainDeclarator.name;
		}
	;

/**
 * something like:
 *     **a
 */
plainDeclarator[Type innerType] returns [Type type, String name]
locals [int n = 0]
	: (s = '*' { ++$n; } )* Identifier
		{
			$type = $innerType;
			for (int i = 0; i < $n; ++i)
				$type = new VariablePointerType($type);
			$name = $Identifier.text;
		}
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
				Environment.exitScope(false);
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
	: typeSpecifier { $ret = $typeSpecifier.ret; }
		('*'
			{
				$ret = new VariablePointerType($ret);
			}
		)*
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
