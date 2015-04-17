grammar Compiler2015;

@parser::header {
import Compiler2015.AST.ASTNode;
import Compiler2015.AST.ASTRoot;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Utility.Tokens;
import Compiler2015.AST.Type.Type;
import Compiler2015.AST.Type.VariablePointerType;

}

@parser::members {

Type innerType = null;
/*
Type embedPointer(int count, Type t) {
	Type ret = t;
	for ( ; count > 0; --count)
		ret = new VariablePointerType(ret);
	return ret;
}
*/
}

primaryExpression returns [Expression ret]
	:	{ Environment.isVariable($Identifier.text) }?
		Identifier
									#primaryExpression1
	|	constant					#primaryExpression2
	|	StringLiteral+				#primaryExpression3
	|	'(' expression ')'			#primaryExpression4
	;

postfixExpression returns [Expression ret]
	:	primaryExpression									#postfixExpression1
	|	postfixExpression '[' expression ']'				#postfixExpression2
	|	postfixExpression '(' argumentExpressionList? ')'	#postfixExpression3
	|	postfixExpression '.' Identifier					#postfixExpression4
	|	postfixExpression '->' Identifier					#postfixExpression5
	|	postfixExpression '++'								#postfixExpression6
	|	postfixExpression '--'								#postfixExpression7
	;

argumentExpressionList returns [ArrayList<Expression> ret]
	:	assignmentExpression								#argumentExpressionList1
	|	argumentExpressionList ',' assignmentExpression		#argumentExpressionList2
	;

unaryExpression returns [Expression ret]
	:	postfixExpression			#unaryExpression1
	|	'++' unaryExpression		#unaryExpression2
	|	'--' unaryExpression		#unaryExpression3
	|	'&' castExpression			#unaryExpression4
	|	'*' castExpression			#unaryExpression5
	|	'+' castExpression			#unaryExpression6
	|	'-' castExpression			#unaryExpression7
	|	'~' castExpression			#unaryExpression8
	|	'!' castExpression			#unaryExpression9
	|	'sizeof' unaryExpression	#unaryExpression10
	|	'sizeof' '(' typeName ')'	#unaryExpression11
	;

castExpression returns [Expression ret]
	:	unaryExpression						#castExpression1
	|	'(' typeName ')' castExpression		#castExpression2
	;

multiplicativeExpression returns [Expression ret]
	:	castExpression									#multiplicativeExpression1
	|	multiplicativeExpression '*' castExpression		#multiplicativeExpression2
	|	multiplicativeExpression '/' castExpression		#multiplicativeExpression3
	|	multiplicativeExpression '%' castExpression		#multiplicativeExpression4
	;

additiveExpression returns [Expression ret]
	:	multiplicativeExpression							#additiveExpression1
	|	additiveExpression '+' multiplicativeExpression		#additiveExpression2
	|	additiveExpression '-' multiplicativeExpression		#additiveExpression3
	;

shiftExpression returns [Expression ret]
	:	additiveExpression							#shiftExpression1
	|	shiftExpression '<<' additiveExpression		#shiftExpression2
	|	shiftExpression '>>' additiveExpression		#shiftExpression3
	;

relationalExpression returns [Expression ret]
	:	shiftExpression								#relationalExpression1
	|	relationalExpression '<' shiftExpression	#relationalExpression2
	|	relationalExpression '>' shiftExpression	#relationalExpression3
	|	relationalExpression '<=' shiftExpression	#relationalExpression4
	|	relationalExpression '>=' shiftExpression	#relationalExpression5
	;

equalityExpression returns [Expression ret]
	:	relationalExpression							#equalityExpression1
	|	equalityExpression '==' relationalExpression	#equalityExpression2
	|	equalityExpression '!=' relationalExpression	#equalityExpression3
	;

andExpression returns [Expression ret]
	:	equalityExpression						#andExpression1
	|	andExpression '&' equalityExpression	#andExpression2
	;

exclusiveOrExpression returns [Expression ret]
	:	andExpression								#exclusiveOrExpression1
	|	exclusiveOrExpression '^' andExpression		#exclusiveOrExpression2
	;

inclusiveOrExpression returns [Expression ret]
	:	exclusiveOrExpression								#inclusiveOrExpression1
	|	inclusiveOrExpression '|' exclusiveOrExpression		#inclusiveOrExpression2
	;

logicalAndExpression returns [Expression ret]
	:	inclusiveOrExpression								#logicalAndExpression1
	|	logicalAndExpression '&&' inclusiveOrExpression		#logicalAndExpression2
	;

logicalOrExpression returns [Expression ret]
	:	logicalAndExpression							#logicalOrExpression1
	|	logicalOrExpression '||' logicalAndExpression	#logicalOrExpression2
	;

conditionalExpression returns [Expression ret]
	:	logicalOrExpression												#conditionalExpression1
	|	logicalOrExpression '?' expression ':' conditionalExpression	#conditionalExpression2
	;

assignmentExpression returns [Expression ret]
	:	conditionalExpression						#assignmentExpression1
	|	unaryExpression '=' assignmentExpression	#assignmentExpression2
	|	unaryExpression '*=' assignmentExpression	#assignmentExpression3
	|	unaryExpression '/=' assignmentExpression	#assignmentExpression4
	|	unaryExpression '%=' assignmentExpression	#assignmentExpression5
	|	unaryExpression '+=' assignmentExpression	#assignmentExpression6
	|	unaryExpression '-=' assignmentExpression	#assignmentExpression7
	|	unaryExpression '<<=' assignmentExpression	#assignmentExpression8
	|	unaryExpression '>>=' assignmentExpression	#assignmentExpression9
	|	unaryExpression '&=' assignmentExpression	#assignmentExpression10
	|	unaryExpression '^=' assignmentExpression	#assignmentExpression11
	|	unaryExpression '|=' assignmentExpression	#assignmentExpression12
	;

expression returns [Expression ret]
	:	assignmentExpression					#expression1
	|	expression ',' assignmentExpression		#expression2
	;

// TODO
declaration returns [ASTNode ret]
	:	'typedef' typeSpecifier initDeclaratorList? ';'		#declaration1
	|	typeSpecifier initDeclaratorList? ';'				#declaration2
	;

// TODO
initDeclaratorList returns [ASTNode ret]
	:	initDeclarator							#initDeclaratorList1
	|	initDeclaratorList ',' initDeclarator	#initDeclaratorList2
	;

// TODO
initDeclarator returns [ASTNode ret]
	:	declarator					#initDeclarator1
	|	declarator '=' initializer	#initDeclarator2
	;

// TODO
typeSpecifier returns [ASTNode ret]
	:	'void'					#typeSpecifier1
	|	'char'					#typeSpecifier2
	|	'int'					#typeSpecifier3
	|	structOrUnionSpecifier	#typeSpecifier4
	|	typedefName				#typeSpecifier5
	;

// TODO
structOrUnionSpecifier returns [ASTNode ret]
	:	structOrUnion Identifier? '{' structDeclaration* '}'	#structOrUnionSpecifier1
	|	structOrUnion Identifier								#structOrUnionSpecifier2
	;

structOrUnion returns [Tokens s]
	:	'struct' { $s = Tokens.STRUCT; }
	|	'union'  { $s = Tokens.UNION; }
	;

// TODO
structDeclaration returns [ASTNode ret]
	:	typeSpecifier ';'						#structDeclaration1
	|	typeSpecifier structDeclaratorList ';'	#structDeclaration2
	;

// TODO
structDeclaratorList returns [ArrayList<ASTNode> ret]
	:	declarator (',' declarator)*
	;

// TODO
declarator returns [ASTNode ret]
	:	pointer directDeclarator
	|	directDeclarator
	;

// TODO
directDeclarator returns [String name]
	:	Identifier										#directDeclarator1
	|	'(' declarator ')'								#directDeclarator2
	|	directDeclarator '[' assignmentExpression? ']'	#directDeclarator4
	|	directDeclarator '(' parameterTypeList? ')'		#directDeclarator5
	;

pointer returns [int count]
	:	'*'				{ $count = 1; }
	|	'*' pointer		{ $count = $pointer.count + 1; }
	;

// TODO
parameterTypeList returns [ArrayList<ASTNode> ret, boolean isFlexible]
	:	parameterList				#parameterTypeList1
	|	parameterList ',' '...'		#parameterTypeList2
	;

// TODO
parameterList returns [ArrayList<ASTNode> ret]
	:	parameterDeclaration					#parameterList1
	|	parameterList ',' parameterDeclaration	#parameterList2
	;

// TODO
parameterDeclaration returns [ASTNode ret]
	:	typeSpecifier declarator // may be typedef should be applied?
											#parameterDeclaration1
	|	typeSpecifier abstractDeclarator?	#parameterDeclaration2
	;

// TODO
identifierList returns [ArrayList<ASTNode> ret]
	:	Identifier
	|	identifierList ',' Identifier
	;

// TODO
typeName returns [ASTNode ret]
	:	typeSpecifier abstractDeclarator?
	;

// TODO
abstractDeclarator returns [ASTNode ret]
	:	pointer									#abstractDeclarator1
	|	pointer? directAbstractDeclarator		#abstractDeclarator2
	;

// TODO
directAbstractDeclarator returns [ASTNode ret]
	:	'(' abstractDeclarator ')'								#directAbstractDeclarator1
	|	'[' assignmentExpression? ']'							#directAbstractDeclarator2
	|	'(' parameterTypeList? ')'								#directAbstractDeclarator3
	|	directAbstractDeclarator '[' ']'						#directAbstractDeclarator4
	|	directAbstractDeclarator '(' parameterTypeList? ')'		#directAbstractDeclarator5
	;

// TODO
typedefName returns [String ret]
	:	{ Environment.isTypedefName($Identifier.text) }? Identifier
	;

// TODO
initializer returns [ASTNode ret]
	:	assignmentExpression
	|	'{' initializerList '}'
	|	'{' initializerList ',' '}'
	;

// TODO
initializerList returns [ArrayList<Object> ret]
	:	initializer
	|	initializerList ',' initializer
	;

// TODO
statement returns [ASTNode ret]
	:	compoundStatement
	|	expressionStatement
	|	selectionStatement
	|	iterationStatement
	|	jumpStatement
	;

// TODO
compoundStatement returns [ASTNode ret]
	:	'{' blockItem* '}'
	;

// TODO
blockItem returns [ASTNode ret]
	:	declaration				#blockItem1
	|	functionDefinition		#blockItem2
	|	statement				#blockItem3
	;

// TODO
expressionStatement returns [ASTNode ret]
	:	expression? ';'
	;

// TODO
selectionStatement returns [ASTNode ret]
	:	'if' '(' expression ')' statement ('else' statement)?
	;

// TODO
iterationStatement returns [ASTNode ret]
	:	'while' '(' expression ')' statement
									#iterationStatement1
	|	'for' '(' expression? ';' expression? ';' expression? ')' statement
									#iterationStatement2
	;

// TODO
jumpStatement returns [ASTNode ret]
	:	'continue' ';'				#jumpStatement1
	|	'break' ';'					#jumpStatement2
	|	'return' expression? ';'	#jumpStatement3
	;

// TODO
compilationUnit returns [ASTRoot ret]
	: externalDeclaration* EOF
	;

// TODO
externalDeclaration returns [ASTNode ret]
	:	functionDefinition			#externalDeclaration1
	|	declaration					#externalDeclaration2
	|	';' 						#externalDeclaration3
	;

// TODO
functionDefinition returns [ASTNode ret]
//	:	typeSpecifier? declarator declaration* compoundStatement old-style definition is not allowed
	:	typeSpecifier declarator compoundStatement
	;

constant returns [Expression ret]
	:	DecimalConstant				#constant1
	|	OctalConstant				#constant2
	|	HexadecimalConstant			#constant3
	|	CharacterConstant			#constant4
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
