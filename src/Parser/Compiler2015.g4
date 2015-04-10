grammar Compiler2015;

@header {

package Compiler2015.Parser;
import Compiler2015.AST.*;

}

// Parser

primaryExpression
	:	Identifier
	|	Constant
	|	StringLiteral+
	|	'(' expression ')'
	;

postfixExpression
	:	primaryExpression
	|	postfixExpression '[' expression ']'
	|	postfixExpression '(' argumentExpressionList? ')'
	|	postfixExpression '.' Identifier
	|	postfixExpression '->' Identifier
	|	postfixExpression '++'
	|	postfixExpression '--'
	;

argumentExpressionList
	:	assignmentExpression
	|	argumentExpressionList ',' assignmentExpression
	;

unaryExpression
	:	postfixExpression
	|	'++' unaryExpression
	|	'--' unaryExpression
	|	unaryOperator castExpression
	|	'sizeof' unaryExpression
	|	'sizeof' '(' typeName ')'
	;

unaryOperator
	:	'&' | '*' | '+' | '-' | '~' | '!'
	;

castExpression
	:	unaryExpression
	|	'(' typeName ')' castExpression
	;

multiplicativeExpression
	:	castExpression
	|	multiplicativeExpression '*' castExpression
	|	multiplicativeExpression '/' castExpression
	|	multiplicativeExpression '%' castExpression
	;

additiveExpression
	:	multiplicativeExpression
	|	additiveExpression '+' multiplicativeExpression
	|	additiveExpression '-' multiplicativeExpression
	;

shiftExpression
	:	additiveExpression
	|	shiftExpression '<<' additiveExpression
	|	shiftExpression '>>' additiveExpression
	;

relationalExpression
	:	shiftExpression
	|	relationalExpression '<' shiftExpression
	|	relationalExpression '>' shiftExpression
	|	relationalExpression '<=' shiftExpression
	|	relationalExpression '>=' shiftExpression
	;

equalityExpression
	:	relationalExpression
	|	equalityExpression '==' relationalExpression
	|	equalityExpression '!=' relationalExpression
	;

andExpression
	:	equalityExpression
	|	andExpression '&' equalityExpression
	;

exclusiveOrExpression
	:	andExpression
	|	exclusiveOrExpression '^' andExpression
	;

inclusiveOrExpression
	:	exclusiveOrExpression
	|	inclusiveOrExpression '|' exclusiveOrExpression
	;

logicalAndExpression
	:	inclusiveOrExpression
	|	logicalAndExpression '&&' inclusiveOrExpression
	;

logicalOrExpression
	:	logicalAndExpression
	|	logicalOrExpression '||' logicalAndExpression
	;

conditionalExpression
	:	logicalOrExpression ('?' expression ':' conditionalExpression)?
	;

assignmentExpression
	:	conditionalExpression
	|	unaryExpression assignmentOperator assignmentExpression
	;

assignmentOperator
	:	'=' | '*=' | '/=' | '%=' | '+=' | '-=' | '<<=' | '>>=' | '&=' | '^=' | '|='
	;

expression
	:	assignmentExpression
	|	expression ',' assignmentExpression
	;

constantExpression
	:	conditionalExpression
	;

declaration
	:	declarationSpecifiers initDeclaratorList? ';'
	;

declarationSpecifiers
	:	declarationSpecifier+
	;

declarationSpecifiers2
	:	declarationSpecifier+
	;

declarationSpecifier
	:	'typedef'
	|	typeSpecifier
	;

initDeclaratorList
	:	initDeclarator
	|	initDeclaratorList ',' initDeclarator
	;

initDeclarator
	:	declarator
	|	declarator '=' initializer
	;

typeSpecifier
	:	'void'
	|	'char'
	|	'int'
	|	structOrUnionSpecifier
	|	typedefName
	;

structOrUnionSpecifier
	:	structOrUnion Identifier? '{' structDeclarationList '}'
	|	structOrUnion Identifier
	;

structOrUnion
	:	'struct'
	|	'union'
	;

structDeclarationList
	:	structDeclaration
	|	structDeclarationList structDeclaration
	;

structDeclaration
	:	typeSpecifier+ structDeclaratorList? ';'
	;

//specifierQualifierList
//	:	typeSpecifier specifierQualifierList?
//	;

structDeclaratorList
	:	structDeclarator
	|	structDeclaratorList ',' structDeclarator
	;

structDeclarator
	:	declarator
	;

declarator
	:	pointer? directDeclarator
	;

directDeclarator
	:	Identifier
	|	'(' declarator ')'
	|	directDeclarator '[' assignmentExpression? ']'
	|	directDeclarator '(' parameterTypeList ')'
	|	directDeclarator '(' identifierList? ')'
	;

pointer
	:	'*'
	|	'*' pointer
	;

parameterTypeList
	:	parameterList
	|	parameterList ',' '...'
	;

parameterList
	:	parameterDeclaration
	|	parameterList ',' parameterDeclaration
	;

parameterDeclaration
	:	declarationSpecifiers declarator
	|	declarationSpecifiers2 abstractDeclarator?
	;

identifierList
	:	Identifier
	|	identifierList ',' Identifier
	;

typeName
	:	typeSpecifier+ abstractDeclarator?
	;

abstractDeclarator
	:	pointer
	|	pointer? directAbstractDeclarator
	;

directAbstractDeclarator
	:	'(' abstractDeclarator ')'
	|	'[' assignmentExpression? ']'
	|	'[' '*' ']'
	|	'(' parameterTypeList? ')'
	|	directAbstractDeclarator '[' ']'
	|	directAbstractDeclarator '[' '*' ']'
	|	directAbstractDeclarator '(' parameterTypeList? ')'
	;

typedefName
	:	Identifier
	;

initializer
	:	assignmentExpression
	|	'{' initializerList '}'
	|	'{' initializerList ',' '}'
	;

initializerList
	:	designation? initializer
	|	initializerList ',' designation? initializer
	;

designation
	:	designatorList '='
	;

designatorList
	:	designator
	|	designatorList designator
	;

designator
	:	'[' constantExpression ']'
	|	'.' Identifier
	;

statement
	:	labeledStatement
	|	compoundStatement
	|	expressionStatement
	|	selectionStatement
	|	iterationStatement
	|	jumpStatement
	;

labeledStatement
	:	Identifier ':' statement
	;

compoundStatement
	:	'{' blockItemList? '}'
	;

blockItemList
	:	blockItem
	|	blockItemList blockItem
	;

blockItem
	:	declaration
	|	statement
	;

expressionStatement
	:	expression? ';'
	;

selectionStatement
	:	'if' '(' expression ')' statement ('else' statement)?
	;

iterationStatement
	:	'while' '(' expression ')' statement
	|	'for' '(' expression? ';' expression? ';' expression? ')' statement
	;

jumpStatement
	:	'continue' ';'
	|	'break' ';'
	|	'return' expression? ';'
	;

compilationUnit
	:	translationUnit? EOF
	;

translationUnit
	:	externalDeclaration
	|	translationUnit externalDeclaration
	;

externalDeclaration
	:	functionDefinition
	|	declaration
	|	';' // stray ;
	;

functionDefinition
	:	declarationSpecifiers? declarator declarationList? compoundStatement
	;

declarationList
	:	declaration
	|	declarationList declaration
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

Constant
	:	IntegerConstant
	|	CharacterConstant
	;

fragment
IntegerConstant
	:	DecimalConstant
	|	OctalConstant
	|	HexadecimalConstant
	;

DecimalConstant
	:	NonzeroDigit Digit*
	;

fragment
OctalConstant
	:	'0' OctalDigit*
	;

fragment
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

fragment
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
