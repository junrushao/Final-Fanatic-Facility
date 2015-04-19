grammar Compiler2015;

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
				Environment.classNames.defineStructOrUnion($uId, $structOrUnion.isUnion, $su.members, $su.anonymousMembers);
				Environment.exitScope(true);
				--inStructDepth;
			}
	| structOrUnion Identifier
			{
				$name = $Identifier.text;
				Environment.classNames.declareStructOrUnion($name, $structOrUnion.isUnion);
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
				true,
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
				true,
				$parameters.hasVaList
				);
		}
	| plainDeclarator[$innerType] ('[' constantExpression { $list.add($constantExpression.ret); } ']')*
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
	: expressionStatement
	| compoundStatement
	| selectionStatement
	| iterationStatement
	| jumpStatement
	;

expressionStatement returns [Statement ret]
	: expression? ';'
	;

compoundStatement returns [CompoundStatement ret]
	: '{' declaration* statement* '}'
	;

selectionStatement returns [IfStatement ret]
	: 'if' '(' expression ')' statement ('else' statement)?
	;

iterationStatement returns [Statement ret]
	: 'while' '(' expression ')' statement
	| 'for' '(' expression? ';' expression? ';' expression? ')' statement
	;

jumpStatement returns [Statement ret]
	: 'continue' ';'
	| 'break' ';'
	| 'return' expression? ';'
	;

/* Expressions  */
expression returns [CommaExpression ret]
	: assignmentExpression (',' assignmentExpression)*
	;

assignmentExpression returns [Expression ret]
	: logicalOrExpression
	| unaryExpression assignmentOperator assignmentExpression
	;

assignmentOperator
	: '=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
	;

constantExpression returns [Expression ret]
	: logicalOrExpression
	;

logicalOrExpression returns [Expression ret]
	: logicalAndExpression ('||' logicalAndExpression)*
	;

logicalAndExpression returns [Expression ret]
	: inclusiveOrExpression ('&&' inclusiveOrExpression)*
	;

inclusiveOrExpression returns [Expression ret]
	: exclusiveOrExpression ('|' exclusiveOrExpression)*
	;

exclusiveOrExpression returns [Expression ret]
	: andExpression ('^' andExpression)*
	;

andExpression returns [Expression ret]
	: equalityExpression ('&' equalityExpression)*
	;

equalityExpression returns [Expression ret]
	: relationalExpression (equalityOperator relationalExpression)*
	;

equalityOperator
	: '==' | '!='
	;

relationalExpression returns [Expression ret]
	: shiftExpression (relationalOperator shiftExpression)*
	;

relationalOperator returns [Expression ret]
	: '<' | '>' | '<=' | '>='
	;

shiftExpression returns [Expression ret]
	: additiveExpression (shiftOperator additiveExpression)*
	;

shiftOperator returns [Expression ret]
	: '<<' | '>>'
	;

additiveExpression returns [Expression ret]
	: multiplicativeExpression (additiveOperator multiplicativeExpression)*
	;

additiveOperator
	: '+' | '-'
	;

multiplicativeExpression returns [Expression ret]
	: castExpression (multiplicativeOperator castExpression)*
	;

multiplicativeOperator
	: '*' | '/' | '%'
	;

castExpression returns [Expression ret]
	: unaryExpression
	| '(' typeName ')' castExpression
	;

typeName returns [Type type]
	: typeSpecifier '*'*
	;

unaryExpression returns [Expression ret]
	: postfixExpression
	| '++' unaryExpression
	| '--' unaryExpression
	| unaryOperator castExpression
	| 'sizeof' unaryExpression
	| 'sizeof' '(' typeName ')'
	;

unaryOperator
	: '&' | '*' | '+' | '-' | '~' | '!'
	;

postfixExpression returns [Expression ret]
	: primaryExpression
	| postfixExpression '[' expression ']'
	| postfixExpression '(' arguments? ')'
	| postfixExpression '.' Identifier
	| postfixExpression '->' Identifier
	| postfixExpression '++'
	| postfixExpression '--'
	;

arguments returns [ArrayList<Expression> ret]
	: assignmentExpression (',' assignmentExpression)*
	;

primaryExpression returns [Expression ret]
	: Identifier
	| constant
	| StringLiteral
	| '(' expression ')'
	;

constant returns [Expression ret]
	: DecimalConstant
	| OctalConstant
	| HexadecimalConstant
	| CharacterConstant
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
	:	'\'' CCharSequence '\''
	;

fragment
CCharSequence
	:	CChar+
	;

fragment
CChar
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
	:	'"' SCharSequence? '"'
	;

fragment
SCharSequence
	:	SChar+
	;

fragment
SChar
	:	~["\\\r\n]
	|	EscapeSequence
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
