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
int inStructDepth = 0;
}

/* Top level */

// TODO: Interact with Environment

// Done
compilationUnit
	: (declaration | functionDefinition)* EOF
	;

/*
program returns [ASTRoot ret]
@init {
	ArrayList<ASTNode> list = new ArrayList<ASTNode>();
}
@after {
	$ret = new ASTRoot(list);
}
	: (declaration { list.add($declaration.ret); } | functionDefinition { list.add($functionDefinition.ret); } )+
	;
*/

// Done
declaration
	: 'typedef' typeSpecifier declarators[$typeSpecifier.ret] ';'
		{
			ArrayList<Type> types = $declarators.types;
			ArrayList<String> names = $declarators.names;
			int n = types.size();
			for (int i = 0; i < n; ++i)
				Environment.symbolNames.defineTypedefName(names.get(i), types.get(i));
		}
	| typeSpecifier initDeclarators[$typeSpecifier.ret]? ';'
		{
			ArrayList<Type> types = $initDeclarators.types;
			ArrayList<String> names = $initDeclarators.names;
			ArrayList<Initializer> inits = $initDeclarators.inits;
			int n = types.size();
			for (int i = 0; i < n; ++i) {
				Environment.symbolNames.defineVariable(names.get(i), new VariableDefinition(types.get(i), inits.get(i)));
			}
		}
	;

// Done
functionDefinition returns [ASTNode ret]
locals [ Type returnType, String name, ArrayList<Type> types = null, ArrayList<String> names = null, boolean hasVaList = false, Statement s ]
@after {
	$ret = new FunctionDeclaration(
		-1,
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
// Done
parameters returns [ArrayList<Type> types, ArrayList<String> names, boolean hasVaList]
locals [int n = 0, Type type, String name]
@init {
	$hasVaList = false;
	$types = new ArrayList<Type>();
	$names = new ArrayList<String>();
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

// Done
declarators[Type innerType] returns [ArrayList<Type> types, ArrayList<String> names]
@init {
	$types = new ArrayList<Type>();
	$names = new ArrayList<String>();
}
	: declarator[innerType] { $types.add($declarator.type); $names.add($declarator.name); } (',' declarator[innerType] { $types.add($declarator.type); $names.add($declarator.name); } )*
	;

// Done
initDeclarators[Type innerType] returns [ArrayList<Type> types, ArrayList<String> names, ArrayList<Initializer> inits]
@init {
	$types = new ArrayList<Type>();
	$names = new ArrayList<String>();
	$inits = new ArrayList<Initializer>();
}
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

// Done
initDeclarator[Type innerType] returns [Type type, String name, Initializer init = null]
	: declarator[innerType] {$type = $declarator.type; $name = $declarator.name; } ('=' initializer {$init = $initializer.ret; } )?
	;

// Done
initializer returns [Initializer ret]
	: assignmentExpression { $ret = new Initializer($assignmentExpression.ret); }
	| '{' i1 = initializer
			{
				$ret = new Initializer(new ArrayList<Initializer>());
				$ret.list.add($i1.ret);
			}
		(',' i2 = initializer { $ret.list.add($i2.ret); })* '}'
	;

// Done
/**
 * something like:
 *     void
 *     struct x
 *     struct x { int a, b; }
 */
typeSpecifier returns [Type ret]
locals [String name, StructOrUnionDeclaration su]
@init {
	$name = null;
	$su = null;
}
	: 'void' { $ret = new VoidType(); }
	| 'char' { $ret = new CharType(); }
	| 'int'  { $ret = new IntType();  }
	| typedefName { $ret = $typedefName.ret; }
	| structOrUnion (Identifier { $name = $Identifier.text; } )?
		'{'
			{
				Environment.enterScope();
				$su = new StructOrUnionDeclaration($structOrUnion.isUnion, new HashMap<String, Type>(), new ArrayList<StructOrUnionDeclaration>());
				++inStructDepth;
			}
			(t2 = typeSpecifier declarators[$t2.ret] ';'
				{
					$su.addAttributes($declarators.types, $declarators.names);
				}
			)+
		'}'
			{
				Environment.classNames.defineStructOrUnion($name, $su);
				Environment.exitScope(true);
				--inStructDepth;
			}
	| structOrUnion Identifier
			{
				$name = $Identifier.text;
				Environment.classNames.declareStructOrUnion($name, $structOrUnion.isUnion);
			}
	;

// Done
typedefName returns [Type ret]
	: { Environment.isTypedefName($Identifier.text) }? Identifier
	;

// Done
structOrUnion returns [boolean isUnion]
	: 'struct' { $isUnion = false; }
	| 'union'  { $isUnion = true; }
	;

// Done
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

// Done
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

// Done
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
