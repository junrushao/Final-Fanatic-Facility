// Generated from Compiler2015.g4 by ANTLR 4.5
package Compiler2015.Parser;

import Compiler2015.AST.SimpleInitializerList;
import Compiler2015.AST.Statement.*;
import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.*;
import Compiler2015.AST.Statement.ExpressionStatement.*;
import Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression.*;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;
import Compiler2015.Utility.Panel;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Compiler2015Parser extends Parser {
	public static final int
			T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
			T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
			T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
			T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, T__28 = 29, T__29 = 30, T__30 = 31,
			T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, T__37 = 38,
			T__38 = 39, T__39 = 40, Typedef = 41, Semi = 42, Comma = 43, L1 = 44, R1 = 45, L2 = 46,
			R2 = 47, L3 = 48, R3 = 49, EQ = 50, STAR = 51, If = 52, Else = 53, While = 54, For = 55,
			OrOr = 56, AndAnd = 57, Or = 58, Caret = 59, And = 60, SizeOf = 61, Identifier = 62,
			DecimalConstant = 63, OctalConstant = 64, HexadecimalConstant = 65, CharacterConstant = 66,
			StringLiteral = 67, Preprocessing = 68, Whitespace = 69, Newline = 70, BlockComment = 71,
			LineComment = 72;
	public static final int
			RULE_compilationUnit = 0, RULE_declaration = 1, RULE_functionDefinition = 2,
			RULE_initDeclaratorList = 3, RULE_initDeclarator = 4, RULE_typeSpecifier = 5,
			RULE_structOrUnionSpecifier = 6, RULE_structOrUnion = 7, RULE_structDeclaration = 8,
			RULE_declarator = 9, RULE_plainDeclarator = 10, RULE_declaratorList = 11,
			RULE_directDeclarator = 12, RULE_parameterTypeList = 13, RULE_parameterList = 14,
			RULE_parameterDeclaration = 15, RULE_abstractDeclarator = 16, RULE_directAbstractDeclarator = 17,
			RULE_typedefName = 18, RULE_initializer = 19, RULE_statement = 20, RULE_expressionStatement = 21,
			RULE_compoundStatement = 22, RULE_selectionStatement = 23, RULE_iterationStatement = 24,
			RULE_jumpStatement = 25, RULE_expression = 26, RULE_assignmentExpression = 27,
			RULE_assignmentOperator = 28, RULE_constantExpression = 29, RULE_logicalOrExpression = 30,
			RULE_logicalAndExpression = 31, RULE_inclusiveOrExpression = 32, RULE_exclusiveOrExpression = 33,
			RULE_andExpression = 34, RULE_equalityExpression = 35, RULE_equalityOperator = 36,
			RULE_relationalExpression = 37, RULE_relationalOperator = 38, RULE_shiftExpression = 39,
			RULE_shiftOperator = 40, RULE_additiveExpression = 41, RULE_additiveOperator = 42,
			RULE_multiplicativeExpression = 43, RULE_multiplicativeOperator = 44,
			RULE_castExpression = 45, RULE_typeName = 46, RULE_unaryExpression = 47,
			RULE_unaryOperator = 48, RULE_postfixExpression = 49, RULE_arguments = 50,
			RULE_primaryExpression = 51, RULE_lambdaExpression = 52, RULE_constant = 53;
	public static final String[] ruleNames = {
			"compilationUnit", "declaration", "functionDefinition", "initDeclaratorList",
			"initDeclarator", "typeSpecifier", "structOrUnionSpecifier", "structOrUnion",
			"structDeclaration", "declarator", "plainDeclarator", "declaratorList",
			"directDeclarator", "parameterTypeList", "parameterList", "parameterDeclaration",
			"abstractDeclarator", "directAbstractDeclarator", "typedefName", "initializer",
			"statement", "expressionStatement", "compoundStatement", "selectionStatement",
			"iterationStatement", "jumpStatement", "expression", "assignmentExpression",
			"assignmentOperator", "constantExpression", "logicalOrExpression", "logicalAndExpression",
			"inclusiveOrExpression", "exclusiveOrExpression", "andExpression", "equalityExpression",
			"equalityOperator", "relationalExpression", "relationalOperator", "shiftExpression",
			"shiftOperator", "additiveExpression", "additiveOperator", "multiplicativeExpression",
			"multiplicativeOperator", "castExpression", "typeName", "unaryExpression",
			"unaryOperator", "postfixExpression", "arguments", "primaryExpression",
			"lambdaExpression", "constant"
	};
	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	public static final String _serializedATN =
			"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3J\u034b\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
					",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
					"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\2\7\2r\n\2\f\2\16\2u\13\2" +
					"\3\2\3\2\3\3\3\3\3\3\3\3\5\3}\n\3\3\3\3\3\3\3\3\3\3\3\5\3\u0084\n\3\3" +
					"\3\3\3\5\3\u0088\n\3\3\4\3\4\3\4\3\4\7\4\u008e\n\4\f\4\16\4\u0091\13\4" +
					"\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0099\n\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5" +
					"\7\5\u00a3\n\5\f\5\16\5\u00a6\13\5\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00ae\n" +
					"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00be\n" +
					"\7\3\b\3\b\3\b\3\b\5\b\u00c4\n\b\3\b\3\b\3\b\3\b\3\b\7\b\u00cb\n\b\f\b" +
					"\16\b\u00ce\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00d8\n\b\3\t\3\t" +
					"\3\t\3\t\5\t\u00de\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u00e8\n\n\f" +
					"\n\16\n\u00eb\13\n\5\n\u00ed\n\n\3\n\3\n\3\13\3\13\7\13\u00f3\n\13\f\13" +
					"\16\13\u00f6\13\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\7\r\u0101\n" +
					"\r\f\r\16\r\u0104\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u010e" +
					"\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
					"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0128\n\16" +
					"\f\16\16\16\u012b\13\16\3\17\3\17\3\17\3\17\3\17\5\17\u0132\n\17\3\20" +
					"\3\20\3\20\3\20\3\20\3\20\7\20\u013a\n\20\f\20\16\20\u013d\13\20\3\21" +
					"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u014c" +
					"\n\21\3\22\3\22\6\22\u0150\n\22\r\22\16\22\u0151\3\22\3\22\7\22\u0156" +
					"\n\22\f\22\16\22\u0159\13\22\3\22\5\22\u015c\n\22\3\23\3\23\3\23\3\23" +
					"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23" +
					"\3\23\3\23\3\23\5\23\u0173\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23" +
					"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0186\n\23\3\23\3\23" +
					"\7\23\u018a\n\23\f\23\16\23\u018d\13\23\3\24\3\24\3\24\3\24\3\25\3\25" +
					"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u019d\n\25\f\25\16\25\u01a0" +
					"\13\25\3\25\3\25\5\25\u01a4\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3" +
					"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01b5\n\26\3\27\3\27\3\27" +
					"\5\27\u01ba\n\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u01c5" +
					"\n\30\f\30\16\30\u01c8\13\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3" +
					"\31\3\31\3\31\3\31\3\31\3\31\5\31\u01d8\n\31\3\31\3\31\3\32\3\32\3\32" +
					"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u01e9\n\32\3\32" +
					"\3\32\3\32\3\32\5\32\u01ef\n\32\3\32\3\32\3\32\3\32\5\32\u01f5\n\32\3" +
					"\32\3\32\3\32\3\32\3\32\5\32\u01fc\n\32\3\33\3\33\3\33\3\33\3\33\3\33" +
					"\3\33\3\33\3\33\3\33\5\33\u0208\n\33\3\33\3\33\5\33\u020c\n\33\3\34\3" +
					"\34\3\34\3\34\3\34\3\34\7\34\u0214\n\34\f\34\16\34\u0217\13\34\3\35\3" +
					"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5" +
					"\35\u0228\n\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \7 \u0235\n " +
					"\f \16 \u0238\13 \3!\3!\3!\3!\3!\3!\7!\u0240\n!\f!\16!\u0243\13!\3\"\3" +
					"\"\3\"\3\"\3\"\3\"\7\"\u024b\n\"\f\"\16\"\u024e\13\"\3#\3#\3#\3#\3#\3" +
					"#\7#\u0256\n#\f#\16#\u0259\13#\3$\3$\3$\3$\3$\3$\7$\u0261\n$\f$\16$\u0264" +
					"\13$\3%\3%\3%\3%\3%\3%\7%\u026c\n%\f%\16%\u026f\13%\3&\3&\3\'\3\'\3\'" +
					"\3\'\3\'\3\'\7\'\u0279\n\'\f\'\16\'\u027c\13\'\3(\3(\3)\3)\3)\3)\3)\3" +
					")\7)\u0286\n)\f)\16)\u0289\13)\3*\3*\3+\3+\3+\3+\3+\3+\7+\u0293\n+\f+" +
					"\16+\u0296\13+\3,\3,\3-\3-\3-\3-\3-\3-\7-\u02a0\n-\f-\16-\u02a3\13-\3" +
					".\3.\3/\3/\3/\3/\3/\3/\3/\3/\3/\5/\u02b0\n/\3\60\3\60\3\60\3\60\3\60\3" +
					"\60\3\60\3\60\5\60\u02ba\n\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61" +
					"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61" +
					"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61" +
					"\3\61\3\61\5\61\u02e2\n\61\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63" +
					"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\5\63\u02f5\n\63\3\63\3\63\3\63" +
					"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\7\63" +
					"\u0307\n\63\f\63\16\63\u030a\13\63\3\64\3\64\3\64\3\64\3\64\3\64\7\64" +
					"\u0312\n\64\f\64\16\64\u0315\13\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65" +
					"\3\65\6\65\u031f\n\65\r\65\16\65\u0320\3\65\3\65\3\65\3\65\3\65\3\65\3" +
					"\65\3\65\3\65\5\65\u032c\n\65\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u0334" +
					"\n\66\3\66\3\66\3\66\3\66\3\66\5\66\u033b\n\66\3\66\3\66\3\66\3\66\3\67" +
					"\3\67\3\67\3\67\3\67\3\67\3\67\3\67\5\67\u0349\n\67\3\67\2\5\32$d8\2\4" +
					"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNP" +
					"RTVXZ\\^`bdfhjl\2\t\4\2\16\27\64\64\3\2\30\31\3\2\32\35\3\2\36\37\3\2" +
					" !\4\2\"#\65\65\6\2 !\'(\65\65>>\u037a\2s\3\2\2\2\4\u0087\3\2\2\2\6\u0089" +
					"\3\2\2\2\b\u009f\3\2\2\2\n\u00a7\3\2\2\2\f\u00bd\3\2\2\2\16\u00d7\3\2" +
					"\2\2\20\u00dd\3\2\2\2\22\u00df\3\2\2\2\24\u00f4\3\2\2\2\26\u00fa\3\2\2" +
					"\2\30\u00fd\3\2\2\2\32\u010d\3\2\2\2\34\u012c\3\2\2\2\36\u0133\3\2\2\2" +
					" \u014b\3\2\2\2\"\u015b\3\2\2\2$\u0172\3\2\2\2&\u018e\3\2\2\2(\u01a3\3" +
					"\2\2\2*\u01b4\3\2\2\2,\u01b9\3\2\2\2.\u01bd\3\2\2\2\60\u01cc\3\2\2\2\62" +
					"\u01fb\3\2\2\2\64\u020b\3\2\2\2\66\u020d\3\2\2\28\u0227\3\2\2\2:\u0229" +
					"\3\2\2\2<\u022b\3\2\2\2>\u022e\3\2\2\2@\u0239\3\2\2\2B\u0244\3\2\2\2D" +
					"\u024f\3\2\2\2F\u025a\3\2\2\2H\u0265\3\2\2\2J\u0270\3\2\2\2L\u0272\3\2" +
					"\2\2N\u027d\3\2\2\2P\u027f\3\2\2\2R\u028a\3\2\2\2T\u028c\3\2\2\2V\u0297" +
					"\3\2\2\2X\u0299\3\2\2\2Z\u02a4\3\2\2\2\\\u02af\3\2\2\2^\u02b9\3\2\2\2" +
					"`\u02e1\3\2\2\2b\u02e3\3\2\2\2d\u02e5\3\2\2\2f\u030b\3\2\2\2h\u032b\3" +
					"\2\2\2j\u032d\3\2\2\2l\u0348\3\2\2\2nr\5\6\4\2or\5\4\3\2pr\7,\2\2qn\3" +
					"\2\2\2qo\3\2\2\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3" +
					"\2\2\2vw\7\2\2\3w\3\3\2\2\2xy\7+\2\2yz\5\f\7\2z|\b\3\1\2{}\5\30\r\2|{" +
					"\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\177\7,\2\2\177\u0088\3\2\2\2\u0080\u0081" +
					"\5\f\7\2\u0081\u0083\b\3\1\2\u0082\u0084\5\b\5\2\u0083\u0082\3\2\2\2\u0083" +
					"\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7,\2\2\u0086\u0088\3\2" +
					"\2\2\u0087x\3\2\2\2\u0087\u0080\3\2\2\2\u0088\5\3\2\2\2\u0089\u008a\5" +
					"\f\7\2\u008a\u008f\b\4\1\2\u008b\u008c\7\65\2\2\u008c\u008e\b\4\1\2\u008d" +
					"\u008b\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2" +
					"\2\2\u0090\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0093\b\4\1\2\u0093" +
					"\u0094\5\32\16\2\u0094\u0098\7.\2\2\u0095\u0096\5\34\17\2\u0096\u0097" +
					"\b\4\1\2\u0097\u0099\3\2\2\2\u0098\u0095\3\2\2\2\u0098\u0099\3\2\2\2\u0099" +
					"\u009a\3\2\2\2\u009a\u009b\7/\2\2\u009b\u009c\b\4\1\2\u009c\u009d\5.\30" +
					"\2\u009d\u009e\b\4\1\2\u009e\7\3\2\2\2\u009f\u00a4\5\n\6\2\u00a0\u00a1" +
					"\7-\2\2\u00a1\u00a3\5\n\6\2\u00a2\u00a0\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4" +
					"\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\t\3\2\2\2\u00a6\u00a4\3\2\2\2" +
					"\u00a7\u00a8\5\24\13\2\u00a8\u00ad\b\6\1\2\u00a9\u00aa\7\64\2\2\u00aa" +
					"\u00ab\5(\25\2\u00ab\u00ac\b\6\1\2\u00ac\u00ae\3\2\2\2\u00ad\u00a9\3\2" +
					"\2\2\u00ad\u00ae\3\2\2\2\u00ae\13\3\2\2\2\u00af\u00b0\7\3\2\2\u00b0\u00be" +
					"\b\7\1\2\u00b1\u00b2\7\4\2\2\u00b2\u00be\b\7\1\2\u00b3\u00b4\7\5\2\2\u00b4" +
					"\u00be\b\7\1\2\u00b5\u00b6\7\6\2\2\u00b6\u00be\b\7\1\2\u00b7\u00b8\5&" +
					"\24\2\u00b8\u00b9\b\7\1\2\u00b9\u00be\3\2\2\2\u00ba\u00bb\5\16\b\2\u00bb" +
					"\u00bc\b\7\1\2\u00bc\u00be\3\2\2\2\u00bd\u00af\3\2\2\2\u00bd\u00b1\3\2" +
					"\2\2\u00bd\u00b3\3\2\2\2\u00bd\u00b5\3\2\2\2\u00bd\u00b7\3\2\2\2\u00bd" +
					"\u00ba\3\2\2\2\u00be\r\3\2\2\2\u00bf\u00c0\5\20\t\2\u00c0\u00c3\b\b\1" +
					"\2\u00c1\u00c2\7@\2\2\u00c2\u00c4\b\b\1\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4" +
					"\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\7\62\2\2\u00c6\u00cc\b\b\1\2" +
					"\u00c7\u00c8\5\22\n\2\u00c8\u00c9\b\b\1\2\u00c9\u00cb\3\2\2\2\u00ca\u00c7" +
					"\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd" +
					"\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7\63\2\2\u00d0\u00d1\b" +
					"\b\1\2\u00d1\u00d8\3\2\2\2\u00d2\u00d3\5\20\t\2\u00d3\u00d4\b\b\1\2\u00d4" +
					"\u00d5\7@\2\2\u00d5\u00d6\b\b\1\2\u00d6\u00d8\3\2\2\2\u00d7\u00bf\3\2" +
					"\2\2\u00d7\u00d2\3\2\2\2\u00d8\17\3\2\2\2\u00d9\u00da\7\7\2\2\u00da\u00de" +
					"\b\t\1\2\u00db\u00dc\7\b\2\2\u00dc\u00de\b\t\1\2\u00dd\u00d9\3\2\2\2\u00dd" +
					"\u00db\3\2\2\2\u00de\21\3\2\2\2\u00df\u00e0\5\f\7\2\u00e0\u00ec\b\n\1" +
					"\2\u00e1\u00e2\5\24\13\2\u00e2\u00e9\b\n\1\2\u00e3\u00e4\7-\2\2\u00e4" +
					"\u00e5\5\24\13\2\u00e5\u00e6\b\n\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e3\3" +
					"\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea" +
					"\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00e1\3\2\2\2\u00ec\u00ed\3\2" +
					"\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\7,\2\2\u00ef\23\3\2\2\2\u00f0\u00f1" +
					"\7\65\2\2\u00f1\u00f3\b\13\1\2\u00f2\u00f0\3\2\2\2\u00f3\u00f6\3\2\2\2" +
					"\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f7\3\2\2\2\u00f6\u00f4" +
					"\3\2\2\2\u00f7\u00f8\5\32\16\2\u00f8\u00f9\b\13\1\2\u00f9\25\3\2\2\2\u00fa" +
					"\u00fb\5\24\13\2\u00fb\u00fc\b\f\1\2\u00fc\27\3\2\2\2\u00fd\u0102\5\26" +
					"\f\2\u00fe\u00ff\7-\2\2\u00ff\u0101\5\26\f\2\u0100\u00fe\3\2\2\2\u0101" +
					"\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\31\3\2\2" +
					"\2\u0104\u0102\3\2\2\2\u0105\u0106\b\16\1\2\u0106\u0107\7@\2\2\u0107\u010e" +
					"\b\16\1\2\u0108\u0109\7.\2\2\u0109\u010a\5\24\13\2\u010a\u010b\7/\2\2" +
					"\u010b\u010c\b\16\1\2\u010c\u010e\3\2\2\2\u010d\u0105\3\2\2\2\u010d\u0108" +
					"\3\2\2\2\u010e\u0129\3\2\2\2\u010f\u0110\f\6\2\2\u0110\u0111\b\16\1\2" +
					"\u0111\u0112\7\60\2\2\u0112\u0113\5<\37\2\u0113\u0114\7\61\2\2\u0114\u0115" +
					"\b\16\1\2\u0115\u0128\3\2\2\2\u0116\u0117\f\5\2\2\u0117\u0118\b\16\1\2" +
					"\u0118\u0119\7\60\2\2\u0119\u011a\7\61\2\2\u011a\u0128\b\16\1\2\u011b" +
					"\u011c\f\4\2\2\u011c\u011d\b\16\1\2\u011d\u011e\7.\2\2\u011e\u011f\7/" +
					"\2\2\u011f\u0128\b\16\1\2\u0120\u0121\f\3\2\2\u0121\u0122\b\16\1\2\u0122" +
					"\u0123\7.\2\2\u0123\u0124\5\34\17\2\u0124\u0125\7/\2\2\u0125\u0126\b\16" +
					"\1\2\u0126\u0128\3\2\2\2\u0127\u010f\3\2\2\2\u0127\u0116\3\2\2\2\u0127" +
					"\u011b\3\2\2\2\u0127\u0120\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2" +
					"\2\2\u0129\u012a\3\2\2\2\u012a\33\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012d" +
					"\5\36\20\2\u012d\u0131\b\17\1\2\u012e\u012f\7-\2\2\u012f\u0130\7\t\2\2" +
					"\u0130\u0132\b\17\1\2\u0131\u012e\3\2\2\2\u0131\u0132\3\2\2\2\u0132\35" +
					"\3\2\2\2\u0133\u0134\5 \21\2\u0134\u013b\b\20\1\2\u0135\u0136\7-\2\2\u0136" +
					"\u0137\5 \21\2\u0137\u0138\b\20\1\2\u0138\u013a\3\2\2\2\u0139\u0135\3" +
					"\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c" +
					"\37\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u013f\5\f\7\2\u013f\u0140\b\21\1" +
					"\2\u0140\u014c\3\2\2\2\u0141\u0142\5\f\7\2\u0142\u0143\b\21\1\2\u0143" +
					"\u0144\5\24\13\2\u0144\u0145\b\21\1\2\u0145\u014c\3\2\2\2\u0146\u0147" +
					"\5\f\7\2\u0147\u0148\b\21\1\2\u0148\u0149\5\"\22\2\u0149\u014a\b\21\1" +
					"\2\u014a\u014c\3\2\2\2\u014b\u013e\3\2\2\2\u014b\u0141\3\2\2\2\u014b\u0146" +
					"\3\2\2\2\u014c!\3\2\2\2\u014d\u014e\7\65\2\2\u014e\u0150\b\22\1\2\u014f" +
					"\u014d\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2" +
					"\2\2\u0152\u015c\3\2\2\2\u0153\u0154\7\65\2\2\u0154\u0156\b\22\1\2\u0155" +
					"\u0153\3\2\2\2\u0156\u0159\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2" +
					"\2\2\u0158\u015a\3\2\2\2\u0159\u0157\3\2\2\2\u015a\u015c\5$\23\2\u015b" +
					"\u014f\3\2\2\2\u015b\u0157\3\2\2\2\u015c#\3\2\2\2\u015d\u015e\b\23\1\2" +
					"\u015e\u015f\7.\2\2\u015f\u0160\5\"\22\2\u0160\u0161\7/\2\2\u0161\u0173" +
					"\3\2\2\2\u0162\u0163\7\60\2\2\u0163\u0164\7\61\2\2\u0164\u0173\b\23\1" +
					"\2\u0165\u0166\7\60\2\2\u0166\u0167\5<\37\2\u0167\u0168\7\61\2\2\u0168" +
					"\u0169\b\23\1\2\u0169\u0173\3\2\2\2\u016a\u016b\7.\2\2\u016b\u016c\7/" +
					"\2\2\u016c\u0173\b\23\1\2\u016d\u016e\7.\2\2\u016e\u016f\5\34\17\2\u016f" +
					"\u0170\7/\2\2\u0170\u0171\b\23\1\2\u0171\u0173\3\2\2\2\u0172\u015d\3\2" +
					"\2\2\u0172\u0162\3\2\2\2\u0172\u0165\3\2\2\2\u0172\u016a\3\2\2\2\u0172" +
					"\u016d\3\2\2\2\u0173\u018b\3\2\2\2\u0174\u0175\f\6\2\2\u0175\u0176\7\60" +
					"\2\2\u0176\u0177\7\61\2\2\u0177\u018a\b\23\1\2\u0178\u0179\f\5\2\2\u0179" +
					"\u017a\7\60\2\2\u017a\u017b\5<\37\2\u017b\u017c\7\61\2\2\u017c\u017d\b" +
					"\23\1\2\u017d\u018a\3\2\2\2\u017e\u017f\f\4\2\2\u017f\u0180\7.\2\2\u0180" +
					"\u0181\7/\2\2\u0181\u018a\b\23\1\2\u0182\u0183\f\3\2\2\u0183\u0185\7." +
					"\2\2\u0184\u0186\5\34\17\2\u0185\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186" +
					"\u0187\3\2\2\2\u0187\u0188\7/\2\2\u0188\u018a\b\23\1\2\u0189\u0174\3\2" +
					"\2\2\u0189\u0178\3\2\2\2\u0189\u017e\3\2\2\2\u0189\u0182\3\2\2\2\u018a" +
					"\u018d\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c%\3\2\2\2" +
					"\u018d\u018b\3\2\2\2\u018e\u018f\6\24\n\2\u018f\u0190\7@\2\2\u0190\u0191" +
					"\b\24\1\2\u0191\'\3\2\2\2\u0192\u0193\58\35\2\u0193\u0194\b\25\1\2\u0194" +
					"\u01a4\3\2\2\2\u0195\u0196\7\62\2\2\u0196\u0197\5(\25\2\u0197\u019e\b" +
					"\25\1\2\u0198\u0199\7-\2\2\u0199\u019a\5(\25\2\u019a\u019b\b\25\1\2\u019b" +
					"\u019d\3\2\2\2\u019c\u0198\3\2\2\2\u019d\u01a0\3\2\2\2\u019e\u019c\3\2" +
					"\2\2\u019e\u019f\3\2\2\2\u019f\u01a1\3\2\2\2\u01a0\u019e\3\2\2\2\u01a1" +
					"\u01a2\7\63\2\2\u01a2\u01a4\3\2\2\2\u01a3\u0192\3\2\2\2\u01a3\u0195\3" +
					"\2\2\2\u01a4)\3\2\2\2\u01a5\u01a6\5,\27\2\u01a6\u01a7\b\26\1\2\u01a7\u01b5" +
					"\3\2\2\2\u01a8\u01a9\5.\30\2\u01a9\u01aa\b\26\1\2\u01aa\u01b5\3\2\2\2" +
					"\u01ab\u01ac\5\60\31\2\u01ac\u01ad\b\26\1\2\u01ad\u01b5\3\2\2\2\u01ae" +
					"\u01af\5\62\32\2\u01af\u01b0\b\26\1\2\u01b0\u01b5\3\2\2\2\u01b1\u01b2" +
					"\5\64\33\2\u01b2\u01b3\b\26\1\2\u01b3\u01b5\3\2\2\2\u01b4\u01a5\3\2\2" +
					"\2\u01b4\u01a8\3\2\2\2\u01b4\u01ab\3\2\2\2\u01b4\u01ae\3\2\2\2\u01b4\u01b1" +
					"\3\2\2\2\u01b5+\3\2\2\2\u01b6\u01b7\5\66\34\2\u01b7\u01b8\b\27\1\2\u01b8" +
					"\u01ba\3\2\2\2\u01b9\u01b6\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bb\3\2" +
					"\2\2\u01bb\u01bc\7,\2\2\u01bc-\3\2\2\2\u01bd\u01be\7\62\2\2\u01be\u01c6" +
					"\b\30\1\2\u01bf\u01c5\5\4\3\2\u01c0\u01c1\5*\26\2\u01c1\u01c2\b\30\1\2" +
					"\u01c2\u01c5\3\2\2\2\u01c3\u01c5\5\6\4\2\u01c4\u01bf\3\2\2\2\u01c4\u01c0" +
					"\3\2\2\2\u01c4\u01c3\3\2\2\2\u01c5\u01c8\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6" +
					"\u01c7\3\2\2\2\u01c7\u01c9\3\2\2\2\u01c8\u01c6\3\2\2\2\u01c9\u01ca\7\63" +
					"\2\2\u01ca\u01cb\b\30\1\2\u01cb/\3\2\2\2\u01cc\u01cd\7\66\2\2\u01cd\u01ce" +
					"\7.\2\2\u01ce\u01cf\5\66\34\2\u01cf\u01d0\b\31\1\2\u01d0\u01d1\7/\2\2" +
					"\u01d1\u01d2\5*\26\2\u01d2\u01d7\b\31\1\2\u01d3\u01d4\7\67\2\2\u01d4\u01d5" +
					"\5*\26\2\u01d5\u01d6\b\31\1\2\u01d6\u01d8\3\2\2\2\u01d7\u01d3\3\2\2\2" +
					"\u01d7\u01d8\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01da\b\31\1\2\u01da\61" +
					"\3\2\2\2\u01db\u01dc\78\2\2\u01dc\u01dd\7.\2\2\u01dd\u01de\5\66\34\2\u01de" +
					"\u01df\7/\2\2\u01df\u01e0\b\32\1\2\u01e0\u01e1\5*\26\2\u01e1\u01e2\b\32" +
					"\1\2\u01e2\u01fc\3\2\2\2\u01e3\u01e4\79\2\2\u01e4\u01e8\7.\2\2\u01e5\u01e6" +
					"\5\66\34\2\u01e6\u01e7\b\32\1\2\u01e7\u01e9\3\2\2\2\u01e8\u01e5\3\2\2" +
					"\2\u01e8\u01e9\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01ee\7,\2\2\u01eb\u01ec" +
					"\5\66\34\2\u01ec\u01ed\b\32\1\2\u01ed\u01ef\3\2\2\2\u01ee\u01eb\3\2\2" +
					"\2\u01ee\u01ef\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f4\7,\2\2\u01f1\u01f2" +
					"\5\66\34\2\u01f2\u01f3\b\32\1\2\u01f3\u01f5\3\2\2\2\u01f4\u01f1\3\2\2" +
					"\2\u01f4\u01f5\3\2\2\2\u01f5\u01f6\3\2\2\2\u01f6\u01f7\7/\2\2\u01f7\u01f8" +
					"\b\32\1\2\u01f8\u01f9\5*\26\2\u01f9\u01fa\b\32\1\2\u01fa\u01fc\3\2\2\2" +
					"\u01fb\u01db\3\2\2\2\u01fb\u01e3\3\2\2\2\u01fc\63\3\2\2\2\u01fd\u01fe" +
					"\7\n\2\2\u01fe\u01ff\7,\2\2\u01ff\u020c\b\33\1\2\u0200\u0201\7\13\2\2" +
					"\u0201\u0202\7,\2\2\u0202\u020c\b\33\1\2\u0203\u0207\7\f\2\2\u0204\u0205" +
					"\5\66\34\2\u0205\u0206\b\33\1\2\u0206\u0208\3\2\2\2\u0207\u0204\3\2\2" +
					"\2\u0207\u0208\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u020a\7,\2\2\u020a\u020c" +
					"\b\33\1\2\u020b\u01fd\3\2\2\2\u020b\u0200\3\2\2\2\u020b\u0203\3\2\2\2" +
					"\u020c\65\3\2\2\2\u020d\u020e\58\35\2\u020e\u0215\b\34\1\2\u020f\u0210" +
					"\7-\2\2\u0210\u0211\58\35\2\u0211\u0212\b\34\1\2\u0212\u0214\3\2\2\2\u0213" +
					"\u020f\3\2\2\2\u0214\u0217\3\2\2\2\u0215\u0213\3\2\2\2\u0215\u0216\3\2" +
					"\2\2\u0216\67\3\2\2\2\u0217\u0215\3\2\2\2\u0218\u0219\5> \2\u0219\u021a" +
					"\b\35\1\2\u021a\u0228\3\2\2\2\u021b\u021c\5`\61\2\u021c\u021d\5:\36\2" +
					"\u021d\u021e\58\35\2\u021e\u021f\b\35\1\2\u021f\u0228\3\2\2\2\u0220\u0221" +
					"\7\r\2\2\u0221\u0222\7.\2\2\u0222\u0223\7@\2\2\u0223\u0224\7-\2\2\u0224" +
					"\u0225\7@\2\2\u0225\u0226\7/\2\2\u0226\u0228\b\35\1\2\u0227\u0218\3\2" +
					"\2\2\u0227\u021b\3\2\2\2\u0227\u0220\3\2\2\2\u02289\3\2\2\2\u0229\u022a" +
					"\t\2\2\2\u022a;\3\2\2\2\u022b\u022c\5> \2\u022c\u022d\b\37\1\2\u022d=" +
					"\3\2\2\2\u022e\u022f\5@!\2\u022f\u0236\b \1\2\u0230\u0231\7:\2\2\u0231" +
					"\u0232\5@!\2\u0232\u0233\b \1\2\u0233\u0235\3\2\2\2\u0234\u0230\3\2\2" +
					"\2\u0235\u0238\3\2\2\2\u0236\u0234\3\2\2\2\u0236\u0237\3\2\2\2\u0237?" +
					"\3\2\2\2\u0238\u0236\3\2\2\2\u0239\u023a\5B\"\2\u023a\u0241\b!\1\2\u023b" +
					"\u023c\7;\2\2\u023c\u023d\5B\"\2\u023d\u023e\b!\1\2\u023e\u0240\3\2\2" +
					"\2\u023f\u023b\3\2\2\2\u0240\u0243\3\2\2\2\u0241\u023f\3\2\2\2\u0241\u0242" +
					"\3\2\2\2\u0242A\3\2\2\2\u0243\u0241\3\2\2\2\u0244\u0245\5D#\2\u0245\u024c" +
					"\b\"\1\2\u0246\u0247\7<\2\2\u0247\u0248\5D#\2\u0248\u0249\b\"\1\2\u0249" +
					"\u024b\3\2\2\2\u024a\u0246\3\2\2\2\u024b\u024e\3\2\2\2\u024c\u024a\3\2" +
					"\2\2\u024c\u024d\3\2\2\2\u024dC\3\2\2\2\u024e\u024c\3\2\2\2\u024f\u0250" +
					"\5F$\2\u0250\u0257\b#\1\2\u0251\u0252\7=\2\2\u0252\u0253\5F$\2\u0253\u0254" +
					"\b#\1\2\u0254\u0256\3\2\2\2\u0255\u0251\3\2\2\2\u0256\u0259\3\2\2\2\u0257" +
					"\u0255\3\2\2\2\u0257\u0258\3\2\2\2\u0258E\3\2\2\2\u0259\u0257\3\2\2\2" +
					"\u025a\u025b\5H%\2\u025b\u0262\b$\1\2\u025c\u025d\7>\2\2\u025d\u025e\5" +
					"H%\2\u025e\u025f\b$\1\2\u025f\u0261\3\2\2\2\u0260\u025c\3\2\2\2\u0261" +
					"\u0264\3\2\2\2\u0262\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263G\3\2\2\2" +
					"\u0264\u0262\3\2\2\2\u0265\u0266\5L\'\2\u0266\u026d\b%\1\2\u0267\u0268" +
					"\5J&\2\u0268\u0269\5L\'\2\u0269\u026a\b%\1\2\u026a\u026c\3\2\2\2\u026b" +
					"\u0267\3\2\2\2\u026c\u026f\3\2\2\2\u026d\u026b\3\2\2\2\u026d\u026e\3\2" +
					"\2\2\u026eI\3\2\2\2\u026f\u026d\3\2\2\2\u0270\u0271\t\3\2\2\u0271K\3\2" +
					"\2\2\u0272\u0273\5P)\2\u0273\u027a\b\'\1\2\u0274\u0275\5N(\2\u0275\u0276" +
					"\5P)\2\u0276\u0277\b\'\1\2\u0277\u0279\3\2\2\2\u0278\u0274\3\2\2\2\u0279" +
					"\u027c\3\2\2\2\u027a\u0278\3\2\2\2\u027a\u027b\3\2\2\2\u027bM\3\2\2\2" +
					"\u027c\u027a\3\2\2\2\u027d\u027e\t\4\2\2\u027eO\3\2\2\2\u027f\u0280\5" +
					"T+\2\u0280\u0287\b)\1\2\u0281\u0282\5R*\2\u0282\u0283\5T+\2\u0283\u0284" +
					"\b)\1\2\u0284\u0286\3\2\2\2\u0285\u0281\3\2\2\2\u0286\u0289\3\2\2\2\u0287" +
					"\u0285\3\2\2\2\u0287\u0288\3\2\2\2\u0288Q\3\2\2\2\u0289\u0287\3\2\2\2" +
					"\u028a\u028b\t\5\2\2\u028bS\3\2\2\2\u028c\u028d\5X-\2\u028d\u0294\b+\1" +
					"\2\u028e\u028f\5V,\2\u028f\u0290\5X-\2\u0290\u0291\b+\1\2\u0291\u0293" +
					"\3\2\2\2\u0292\u028e\3\2\2\2\u0293\u0296\3\2\2\2\u0294\u0292\3\2\2\2\u0294" +
					"\u0295\3\2\2\2\u0295U\3\2\2\2\u0296\u0294\3\2\2\2\u0297\u0298\t\6\2\2" +
					"\u0298W\3\2\2\2\u0299\u029a\5\\/\2\u029a\u02a1\b-\1\2\u029b\u029c\5Z." +
					"\2\u029c\u029d\5\\/\2\u029d\u029e\b-\1\2\u029e\u02a0\3\2\2\2\u029f\u029b" +
					"\3\2\2\2\u02a0\u02a3\3\2\2\2\u02a1\u029f\3\2\2\2\u02a1\u02a2\3\2\2\2\u02a2" +
					"Y\3\2\2\2\u02a3\u02a1\3\2\2\2\u02a4\u02a5\t\7\2\2\u02a5[\3\2\2\2\u02a6" +
					"\u02a7\5`\61\2\u02a7\u02a8\b/\1\2\u02a8\u02b0\3\2\2\2\u02a9\u02aa\7.\2" +
					"\2\u02aa\u02ab\5^\60\2\u02ab\u02ac\7/\2\2\u02ac\u02ad\5\\/\2\u02ad\u02ae" +
					"\b/\1\2\u02ae\u02b0\3\2\2\2\u02af\u02a6\3\2\2\2\u02af\u02a9\3\2\2\2\u02b0" +
					"]\3\2\2\2\u02b1\u02b2\5\f\7\2\u02b2\u02b3\b\60\1\2\u02b3\u02ba\3\2\2\2" +
					"\u02b4\u02b5\5\f\7\2\u02b5\u02b6\b\60\1\2\u02b6\u02b7\5\"\22\2\u02b7\u02b8" +
					"\b\60\1\2\u02b8\u02ba\3\2\2\2\u02b9\u02b1\3\2\2\2\u02b9\u02b4\3\2\2\2" +
					"\u02ba_\3\2\2\2\u02bb\u02bc\5d\63\2\u02bc\u02bd\b\61\1\2\u02bd\u02e2\3" +
					"\2\2\2\u02be\u02bf\7$\2\2\u02bf\u02c0\5`\61\2\u02c0\u02c1\b\61\1\2\u02c1" +
					"\u02e2\3\2\2\2\u02c2\u02c3\7%\2\2\u02c3\u02c4\5`\61\2\u02c4\u02c5\b\61" +
					"\1\2\u02c5\u02e2\3\2\2\2\u02c6\u02c7\5b\62\2\u02c7\u02c8\5\\/\2\u02c8" +
					"\u02c9\b\61\1\2\u02c9\u02e2\3\2\2\2\u02ca\u02cb\7?\2\2\u02cb\u02cc\7." +
					"\2\2\u02cc\u02cd\7@\2\2\u02cd\u02ce\7/\2\2\u02ce\u02e2\b\61\1\2\u02cf" +
					"\u02d0\7?\2\2\u02d0\u02d1\7.\2\2\u02d1\u02d2\5^\60\2\u02d2\u02d3\7/\2" +
					"\2\u02d3\u02d4\b\61\1\2\u02d4\u02e2\3\2\2\2\u02d5\u02d6\7?\2\2\u02d6\u02d7" +
					"\5`\61\2\u02d7\u02d8\b\61\1\2\u02d8\u02e2\3\2\2\2\u02d9\u02da\7&\2\2\u02da" +
					"\u02db\7.\2\2\u02db\u02dc\7@\2\2\u02dc\u02dd\7-\2\2\u02dd\u02de\5^\60" +
					"\2\u02de\u02df\7/\2\2\u02df\u02e0\b\61\1\2\u02e0\u02e2\3\2\2\2\u02e1\u02bb" +
					"\3\2\2\2\u02e1\u02be\3\2\2\2\u02e1\u02c2\3\2\2\2\u02e1\u02c6\3\2\2\2\u02e1" +
					"\u02ca\3\2\2\2\u02e1\u02cf\3\2\2\2\u02e1\u02d5\3\2\2\2\u02e1\u02d9\3\2" +
					"\2\2\u02e2a\3\2\2\2\u02e3\u02e4\t\b\2\2\u02e4c\3\2\2\2\u02e5\u02e6\b\63" +
					"\1\2\u02e6\u02e7\5h\65\2\u02e7\u02e8\b\63\1\2\u02e8\u0308\3\2\2\2\u02e9" +
					"\u02ea\f\b\2\2\u02ea\u02eb\7\60\2\2\u02eb\u02ec\5\66\34\2\u02ec\u02ed" +
					"\7\61\2\2\u02ed\u02ee\b\63\1\2\u02ee\u0307\3\2\2\2\u02ef\u02f0\f\7\2\2" +
					"\u02f0\u02f4\7.\2\2\u02f1\u02f2\5f\64\2\u02f2\u02f3\b\63\1\2\u02f3\u02f5" +
					"\3\2\2\2\u02f4\u02f1\3\2\2\2\u02f4\u02f5\3\2\2\2\u02f5\u02f6\3\2\2\2\u02f6" +
					"\u02f7\7/\2\2\u02f7\u0307\b\63\1\2\u02f8\u02f9\f\6\2\2\u02f9\u02fa\7)" +
					"\2\2\u02fa\u02fb\7@\2\2\u02fb\u0307\b\63\1\2\u02fc\u02fd\f\5\2\2\u02fd" +
					"\u02fe\7*\2\2\u02fe\u02ff\7@\2\2\u02ff\u0307\b\63\1\2\u0300\u0301\f\4" +
					"\2\2\u0301\u0302\7$\2\2\u0302\u0307\b\63\1\2\u0303\u0304\f\3\2\2\u0304" +
					"\u0305\7%\2\2\u0305\u0307\b\63\1\2\u0306\u02e9\3\2\2\2\u0306\u02ef\3\2" +
					"\2\2\u0306\u02f8\3\2\2\2\u0306\u02fc\3\2\2\2\u0306\u0300\3\2\2\2\u0306" +
					"\u0303\3\2\2\2\u0307\u030a\3\2\2\2\u0308\u0306\3\2\2\2\u0308\u0309\3\2" +
					"\2\2\u0309e\3\2\2\2\u030a\u0308\3\2\2\2\u030b\u030c\58\35\2\u030c\u0313" +
					"\b\64\1\2\u030d\u030e\7-\2\2\u030e\u030f\58\35\2\u030f\u0310\b\64\1\2" +
					"\u0310\u0312\3\2\2\2\u0311\u030d\3\2\2\2\u0312\u0315\3\2\2\2\u0313\u0311" +
					"\3\2\2\2\u0313\u0314\3\2\2\2\u0314g\3\2\2\2\u0315\u0313\3\2\2\2\u0316" +
					"\u0317\6\65\21\2\u0317\u0318\7@\2\2\u0318\u032c\b\65\1\2\u0319\u031a\5" +
					"l\67\2\u031a\u031b\b\65\1\2\u031b\u032c\3\2\2\2\u031c\u031d\7E\2\2\u031d" +
					"\u031f\b\65\1\2\u031e\u031c\3\2\2\2\u031f\u0320\3\2\2\2\u0320\u031e\3" +
					"\2\2\2\u0320\u0321\3\2\2\2\u0321\u0322\3\2\2\2\u0322\u032c\b\65\1\2\u0323" +
					"\u0324\7.\2\2\u0324\u0325\5\66\34\2\u0325\u0326\7/\2\2\u0326\u0327\b\65" +
					"\1\2\u0327\u032c\3\2\2\2\u0328\u0329\5j\66\2\u0329\u032a\b\65\1\2\u032a" +
					"\u032c\3\2\2\2\u032b\u0316\3\2\2\2\u032b\u0319\3\2\2\2\u032b\u031e\3\2" +
					"\2\2\u032b\u0323\3\2\2\2\u032b\u0328\3\2\2\2\u032ci\3\2\2\2\u032d\u032e" +
					"\7\60\2\2\u032e\u032f\7\61\2\2\u032f\u0333\7.\2\2\u0330\u0331\5\34\17" +
					"\2\u0331\u0332\b\66\1\2\u0332\u0334\3\2\2\2\u0333\u0330\3\2\2\2\u0333" +
					"\u0334\3\2\2\2\u0334\u0335\3\2\2\2\u0335\u033a\7/\2\2\u0336\u0337\7*\2" +
					"\2\u0337\u0338\5^\60\2\u0338\u0339\b\66\1\2\u0339\u033b\3\2\2\2\u033a" +
					"\u0336\3\2\2\2\u033a\u033b\3\2\2\2\u033b\u033c\3\2\2\2\u033c\u033d\b\66" +
					"\1\2\u033d\u033e\5.\30\2\u033e\u033f\b\66\1\2\u033fk\3\2\2\2\u0340\u0341" +
					"\7A\2\2\u0341\u0349\b\67\1\2\u0342\u0343\7B\2\2\u0343\u0349\b\67\1\2\u0344" +
					"\u0345\7C\2\2\u0345\u0349\b\67\1\2\u0346\u0347\7D\2\2\u0347\u0349\b\67" +
					"\1\2\u0348\u0340\3\2\2\2\u0348\u0342\3\2\2\2\u0348\u0344\3\2\2\2\u0348" +
					"\u0346\3\2\2\2\u0349m\3\2\2\2Fqs|\u0083\u0087\u008f\u0098\u00a4\u00ad" +
					"\u00bd\u00c3\u00cc\u00d7\u00dd\u00e9\u00ec\u00f4\u0102\u010d\u0127\u0129" +
					"\u0131\u013b\u014b\u0151\u0157\u015b\u0172\u0185\u0189\u018b\u019e\u01a3" +
					"\u01b4\u01b9\u01c4\u01c6\u01d7\u01e8\u01ee\u01f4\u01fb\u0207\u020b\u0215" +
					"\u0227\u0236\u0241\u024c\u0257\u0262\u026d\u027a\u0287\u0294\u02a1\u02af" +
					"\u02b9\u02e1\u02f4\u0306\u0308\u0313\u0320\u032b\u0333\u033a\u0348";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	private static final String[] _LITERAL_NAMES = {
			null, "'void'", "'char'", "'int'", "'va_list'", "'struct'", "'union'",
			"'...'", "'continue'", "'break'", "'return'", "'va_start'", "'*='", "'/='",
			"'%='", "'+='", "'-='", "'<<='", "'>>='", "'&='", "'^='", "'|='", "'=='",
			"'!='", "'<'", "'>'", "'<='", "'>='", "'<<'", "'>>'", "'+'", "'-'", "'/'",
			"'%'", "'++'", "'--'", "'va_arg'", "'~'", "'!'", "'.'", "'->'", "'typedef'",
			"';'", "','", "'('", "')'", "'['", "']'", "'{'", "'}'", "'='", "'*'",
			"'if'", "'else'", "'while'", "'for'", "'||'", "'&&'", "'|'", "'^'", "'&'",
			"'sizeof'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, "Typedef", "Semi", "Comma", "L1", "R1",
			"L2", "R2", "L3", "R3", "EQ", "STAR", "If", "Else", "While", "For", "OrOr",
			"AndAnd", "Or", "Caret", "And", "SizeOf", "Identifier", "DecimalConstant",
			"OctalConstant", "HexadecimalConstant", "CharacterConstant", "StringLiteral",
			"Preprocessing", "Whitespace", "Newline", "BlockComment", "LineComment"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	static {
		RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION);
	}

	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}

	public Compiler2015Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() {
		return "Compiler2015.g4";
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(113);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							setState(111);
							switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
								case 1:
						{
							setState(108);
							functionDefinition();
						}
								break;
								case 2:
						{
							setState(109);
							declaration();
						}
								break;
								case 3:
						{
							setState(110);
							match(Semi);
						}
								break;
							}
						}
				}
					setState(115);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
			}
				setState(116);
				match(EOF);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		int _la;
		try {
			setState(133);
			switch (getInterpreter().adaptivePredict(_input, 4, _ctx)) {
				case 1:
					_localctx = new Declaration1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(118);
					match(Typedef);
					setState(119);
					((Declaration1Context) _localctx).typeSpecifier = typeSpecifier();

					TypeAnalyser.enter(((Declaration1Context) _localctx).typeSpecifier.ret);

					setState(122);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L1) | (1L << STAR) | (1L << Identifier))) != 0)) {
						{
							setState(121);
							declaratorList();
					}
					}

					setState(124);
					match(Semi);
				}
				break;
				case 2:
					_localctx = new Declaration2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(126);
					((Declaration2Context) _localctx).typeSpecifier = typeSpecifier();

					TypeAnalyser.enter(((Declaration2Context) _localctx).typeSpecifier.ret);

					setState(129);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L1) | (1L << STAR) | (1L << Identifier))) != 0)) {
						{
							setState(128);
							initDeclaratorList();
					}
					}

					setState(131);
					match(Semi);
				}
				break;
			}

			TypeAnalyser.exit();

		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final FunctionDefinitionContext functionDefinition() throws RecognitionException {
		FunctionDefinitionContext _localctx = new FunctionDefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_functionDefinition);

		((FunctionDefinitionContext) _localctx).parameterTypes = new ArrayList<Type>();
		((FunctionDefinitionContext) _localctx).parameterNames = new ArrayList<String>();
		((FunctionDefinitionContext) _localctx).loopStack = Environment.loopStack;
		Environment.loopStack = new Stack<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(135);
				((FunctionDefinitionContext) _localctx).typeSpecifier = typeSpecifier();
				((FunctionDefinitionContext) _localctx).returnType = ((FunctionDefinitionContext) _localctx).typeSpecifier.ret;
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == STAR) {
					{
						{
							setState(137);
							match(STAR);
							((FunctionDefinitionContext) _localctx).returnType = new VariablePointerType(_localctx.returnType);
						}
					}
					setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				}
				TypeAnalyser.enter(_localctx.returnType);
				setState(145);
				((FunctionDefinitionContext) _localctx).directDeclarator = directDeclarator(0);
				setState(146);
				match(L1);
				setState(150);
				switch (getInterpreter().adaptivePredict(_input, 6, _ctx)) {
					case 1: {
						setState(147);
						((FunctionDefinitionContext) _localctx).parameterTypeList = parameterTypeList();

						((FunctionDefinitionContext) _localctx).parameterTypes = ((FunctionDefinitionContext) _localctx).parameterTypeList.types;
						((FunctionDefinitionContext) _localctx).parameterNames = ((FunctionDefinitionContext) _localctx).parameterTypeList.names;
						((FunctionDefinitionContext) _localctx).hasVaList = ((FunctionDefinitionContext) _localctx).parameterTypeList.hasVaList;
						if (_localctx.parameterNames.size() != 0 && (new HashSet<>(_localctx.parameterNames).size()) != _localctx.parameterNames.size())
							throw new CompilationError("parameter should have different names.");
						for (String name : _localctx.parameterNames)
							if (name == null || name.equals(""))
								throw new CompilationError("No parameter name.");

				}
					break;
				}
				setState(152);
				match(R1);

				TypeAnalyser.addParameter(_localctx.parameterTypes, _localctx.parameterNames, _localctx.hasVaList);
				((FunctionDefinitionContext) _localctx).type = (FunctionType) TypeAnalyser.analyse(true);
				((FunctionDefinitionContext) _localctx).name = ((FunctionDefinitionContext) _localctx).directDeclarator.name;

				if (!Environment.isCompleteType(_localctx.type))
					throw new CompilationError("Incomplete type.");

				Environment.functionReturnStack.push(_localctx.type.returnType);
				if (Environment.symbolNames.currentScope == 1)
					((FunctionDefinitionContext) _localctx).uId = Environment.symbolNames.defineVariable(_localctx.name, _localctx.type);
				else
					((FunctionDefinitionContext) _localctx).uId = Environment.symbolNames.defineLocalFunction(_localctx.name, _localctx.type);

				((FunctionDefinitionContext) _localctx).parameterTypes = _localctx.type.parameterTypes;
				((FunctionDefinitionContext) _localctx).parameterNames = _localctx.type.parameterNames;

				((FunctionDefinitionContext) _localctx).typePassDown = new ArrayList<>(_localctx.parameterTypes);
				((FunctionDefinitionContext) _localctx).namePassDown = new ArrayList<>(_localctx.parameterNames);
				//			if (!(_localctx.returnType instanceof VoidType)) {
				//				_localctx.typePassDown.add(_localctx.returnType);
				//				_localctx.namePassDown.add(".return");
				//			}

				setState(154);
				((FunctionDefinitionContext) _localctx).compoundStatement = compoundStatement(_localctx.typePassDown, _localctx.namePassDown);

				((FunctionDefinitionContext) _localctx).s = ((FunctionDefinitionContext) _localctx).compoundStatement.ret;
				_localctx.s.youAreAFrame(Environment.symbolNames.currentScope + 1);
				Environment.symbolNames.defineVariable(_localctx.uId, _localctx.type, _localctx.s);
				Environment.functionReturnStack.pop();

			}

			TypeAnalyser.exit();
			Environment.loopStack = _localctx.loopStack;

		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final InitDeclaratorListContext initDeclaratorList() throws RecognitionException {
		InitDeclaratorListContext _localctx = new InitDeclaratorListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_initDeclaratorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(157);
				initDeclarator();
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Comma) {
					{
						{
							setState(158);
							match(Comma);
							setState(159);
				initDeclarator();
						}
					}
					setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final InitDeclaratorContext initDeclarator() throws RecognitionException {
		InitDeclaratorContext _localctx = new InitDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_initDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(165);
				((InitDeclaratorContext) _localctx).declarator = declarator();

				((InitDeclaratorContext) _localctx).type = TypeAnalyser.analyse();
				((InitDeclaratorContext) _localctx).name = ((InitDeclaratorContext) _localctx).declarator.name;
				((InitDeclaratorContext) _localctx).uId = Environment.symbolNames.defineVariable(_localctx.name, _localctx.type);

				setState(171);
				_la = _input.LA(1);
				if (_la == EQ) {
					{
						setState(167);
						match(EQ);
						setState(168);
						((InitDeclaratorContext) _localctx).initializer = initializer();

						((InitDeclaratorContext) _localctx).init = ((InitDeclaratorContext) _localctx).initializer.ret;
						Environment.symbolNames.defineVariable(_localctx.uId, _localctx.type, _localctx.init);

				}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final TypeSpecifierContext typeSpecifier() throws RecognitionException {
		TypeSpecifierContext _localctx = new TypeSpecifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_typeSpecifier);
		try {
			setState(187);
			switch (getInterpreter().adaptivePredict(_input, 9, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(173);
					match(T__0);
					((TypeSpecifierContext) _localctx).ret = VoidType.instance;
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(175);
					match(T__1);
					((TypeSpecifierContext) _localctx).ret = CharType.instance;
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(177);
					match(T__2);
					((TypeSpecifierContext) _localctx).ret = IntType.instance;
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(179);
					match(T__3);
					((TypeSpecifierContext) _localctx).ret = new VariablePointerType(CharType.instance);
				}
				break;
				case 5:
					enterOuterAlt(_localctx, 5);
				{
					setState(181);
					((TypeSpecifierContext) _localctx).typedefName = typedefName();
					((TypeSpecifierContext) _localctx).ret = ((TypeSpecifierContext) _localctx).typedefName.ret;
				}
				break;
				case 6:
					enterOuterAlt(_localctx, 6);
				{
					setState(184);
					((TypeSpecifierContext) _localctx).structOrUnionSpecifier = structOrUnionSpecifier();
					((TypeSpecifierContext) _localctx).ret = ((TypeSpecifierContext) _localctx).structOrUnionSpecifier.ret;
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final StructOrUnionSpecifierContext structOrUnionSpecifier() throws RecognitionException {
		StructOrUnionSpecifierContext _localctx = new StructOrUnionSpecifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_structOrUnionSpecifier);

		((StructOrUnionSpecifierContext) _localctx).name = "";

		int _la;
		try {
			int _alt;
			setState(213);
			switch (getInterpreter().adaptivePredict(_input, 12, _ctx)) {
				case 1:
					_localctx = new StructOrUnionSpecifier1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(189);
					((StructOrUnionSpecifier1Context) _localctx).structOrUnion = structOrUnion();

					((StructOrUnionSpecifier1Context) _localctx).isUnion = ((StructOrUnionSpecifier1Context) _localctx).structOrUnion.isUnion;

					setState(193);
					_la = _input.LA(1);
					if (_la == Identifier) {
						{
							setState(191);
							((StructOrUnionSpecifier1Context) _localctx).Identifier = match(Identifier);
							((StructOrUnionSpecifier1Context) _localctx).name = (((StructOrUnionSpecifier1Context) _localctx).Identifier != null ? ((StructOrUnionSpecifier1Context) _localctx).Identifier.getText() : null);
					}
					}

					setState(195);
					match(L3);
					StructBuilder.enter(_localctx.name, _localctx.isUnion);
					setState(202);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 11, _ctx);
					while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
						if (_alt == 1) {
							{
								{
									setState(197);
									((StructOrUnionSpecifier1Context) _localctx).structDeclaration = structDeclaration();

									StructBuilder.addAttributes(((StructOrUnionSpecifier1Context) _localctx).structDeclaration.types, ((StructOrUnionSpecifier1Context) _localctx).structDeclaration.names);

						}
							}
					}
						setState(204);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 11, _ctx);
				}
					setState(205);
					match(R3);
					((StructOrUnionSpecifier1Context) _localctx).ret = StructBuilder.exit();
				}
				break;
				case 2:
					_localctx = new StructOrUnionSpecifier2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(208);
					((StructOrUnionSpecifier2Context) _localctx).structOrUnion = structOrUnion();
					((StructOrUnionSpecifier2Context) _localctx).isUnion = ((StructOrUnionSpecifier2Context) _localctx).structOrUnion.isUnion;
					setState(210);
					((StructOrUnionSpecifier2Context) _localctx).Identifier = match(Identifier);

					((StructOrUnionSpecifier2Context) _localctx).name = (((StructOrUnionSpecifier2Context) _localctx).Identifier != null ? ((StructOrUnionSpecifier2Context) _localctx).Identifier.getText() : null);
					((StructOrUnionSpecifier2Context) _localctx).ret = StructBuilder.declareDirectly(_localctx.name, _localctx.isUnion);

				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final StructOrUnionContext structOrUnion() throws RecognitionException {
		StructOrUnionContext _localctx = new StructOrUnionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_structOrUnion);
		try {
			setState(219);
			switch (_input.LA(1)) {
				case T__4:
					enterOuterAlt(_localctx, 1);
				{
					setState(215);
					match(T__4);
					((StructOrUnionContext) _localctx).isUnion = false;
				}
				break;
				case T__5:
					enterOuterAlt(_localctx, 2);
				{
					setState(217);
					match(T__5);
					((StructOrUnionContext) _localctx).isUnion = true;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final StructDeclarationContext structDeclaration() throws RecognitionException {
		StructDeclarationContext _localctx = new StructDeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_structDeclaration);

		((StructDeclarationContext) _localctx).types = new ArrayList<Type>();
		((StructDeclarationContext) _localctx).names = new ArrayList<String>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(221);
				((StructDeclarationContext) _localctx).typeSpecifier = typeSpecifier();
				TypeAnalyser.enter(((StructDeclarationContext) _localctx).typeSpecifier.ret);
				setState(234);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L1) | (1L << STAR) | (1L << Identifier))) != 0)) {
					{
						setState(223);
						((StructDeclarationContext) _localctx).d1 = declarator();

						_localctx.types.add(TypeAnalyser.analyse());
						_localctx.names.add(((StructDeclarationContext) _localctx).d1.name);

						setState(231);
						_errHandler.sync(this);
				_la = _input.LA(1);
						while (_la == Comma) {
					{
						{
							setState(225);
							match(Comma);
							setState(226);
							((StructDeclarationContext) _localctx).d2 = declarator();

							_localctx.types.add(TypeAnalyser.analyse());
							_localctx.names.add(((StructDeclarationContext) _localctx).d2.name);

					}
					}
							setState(233);
							_errHandler.sync(this);
							_la = _input.LA(1);
				}
				}
				}

				setState(236);
				match(Semi);
			}

			for (Type t : _localctx.types)
				if (t instanceof FunctionType)
					throw new CompilationError("Could not declare / define functions inside struct / union.");
			TypeAnalyser.exit();

		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final DeclaratorContext declarator() throws RecognitionException {
		DeclaratorContext _localctx = new DeclaratorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == STAR) {
					{
						{
							setState(238);
							match(STAR);
							++_localctx.n;
						}
					}
					setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
				setState(245);
				((DeclaratorContext) _localctx).directDeclarator = directDeclarator(0);
				((DeclaratorContext) _localctx).name = ((DeclaratorContext) _localctx).directDeclarator.name;
			}

			for (int i = 0; i < _localctx.n; ++i)
				TypeAnalyser.addStar();

		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final PlainDeclaratorContext plainDeclarator() throws RecognitionException {
		PlainDeclaratorContext _localctx = new PlainDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_plainDeclarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(248);
				((PlainDeclaratorContext) _localctx).declarator = declarator();

				((PlainDeclaratorContext) _localctx).type = TypeAnalyser.analyse();
				((PlainDeclaratorContext) _localctx).name = ((PlainDeclaratorContext) _localctx).declarator.name;
				Environment.symbolNames.defineTypedefName(_localctx.name, _localctx.type);

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final DeclaratorListContext declaratorList() throws RecognitionException {
		DeclaratorListContext _localctx = new DeclaratorListContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_declaratorList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(251);
				plainDeclarator();
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Comma) {
					{
						{
							setState(252);
							match(Comma);
							setState(253);
				plainDeclarator();
						}
					}
					setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final DirectDeclaratorContext directDeclarator() throws RecognitionException {
		return directDeclarator(0);
	}

	private DirectDeclaratorContext directDeclarator(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DirectDeclaratorContext _localctx = new DirectDeclaratorContext(_ctx, _parentState);
		DirectDeclaratorContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_directDeclarator, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(267);
				switch (_input.LA(1)) {
					case Identifier: {
						setState(260);
						((DirectDeclaratorContext) _localctx).Identifier = match(Identifier);
						((DirectDeclaratorContext) _localctx).name = (((DirectDeclaratorContext) _localctx).Identifier != null ? ((DirectDeclaratorContext) _localctx).Identifier.getText() : null);
				}
					break;
					case L1: {
						setState(262);
						match(L1);
						setState(263);
						((DirectDeclaratorContext) _localctx).declarator = declarator();
						setState(264);
						match(R1);
						((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).declarator.name;
				}
					break;
					default:
						throw new NoViableAltException(this);
				}
				_ctx.stop = _input.LT(-1);
				setState(295);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(293);
							switch (getInterpreter().adaptivePredict(_input, 19, _ctx)) {
								case 1:
						{
							_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
							_localctx.d1 = _prevctx;
							_localctx.d1 = _prevctx;
							pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
							setState(269);
							if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
							((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).d1.name;
							setState(271);
							match(L2);
							setState(272);
							((DirectDeclaratorContext) _localctx).constantExpression = constantExpression();
							setState(273);
							match(R2);
							TypeAnalyser.addArray(((DirectDeclaratorContext) _localctx).constantExpression.ret);
						}
								break;
								case 2:
						{
							_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
							_localctx.d2 = _prevctx;
							_localctx.d2 = _prevctx;
							pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
							setState(276);
							if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
							((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).d2.name;
							setState(278);
							match(L2);
							setState(279);
							match(R2);
							TypeAnalyser.addArray(null);
						}
								break;
								case 3: {
									_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
									_localctx.d3 = _prevctx;
									_localctx.d3 = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
									setState(281);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).d3.name;
									setState(283);
						match(L1);
									setState(284);
						match(R1);
									TypeAnalyser.addParameter();
								}
								break;
								case 4:
						{
							_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
							_localctx.d4 = _prevctx;
							_localctx.d4 = _prevctx;
							pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
							setState(286);
							if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
							((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).d4.name;
							setState(288);
							match(L1);
							setState(289);
							((DirectDeclaratorContext) _localctx).parameterTypeList = parameterTypeList();
							setState(290);
							match(R1);
							TypeAnalyser.addParameter(((DirectDeclaratorContext) _localctx).parameterTypeList.types, ((DirectDeclaratorContext) _localctx).parameterTypeList.names, ((DirectDeclaratorContext) _localctx).parameterTypeList.hasVaList);
						}
								break;
					}
						}
				}
					setState(297);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public final ParameterTypeListContext parameterTypeList() throws RecognitionException {
		ParameterTypeListContext _localctx = new ParameterTypeListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_parameterTypeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(298);
				((ParameterTypeListContext) _localctx).parameterList = parameterList();

				((ParameterTypeListContext) _localctx).types = ((ParameterTypeListContext) _localctx).parameterList.types;
				((ParameterTypeListContext) _localctx).names = ((ParameterTypeListContext) _localctx).parameterList.names;

				setState(303);
				_la = _input.LA(1);
				if (_la == Comma) {
					{
						setState(300);
						match(Comma);
						setState(301);
						match(T__6);
						((ParameterTypeListContext) _localctx).hasVaList = true;
				}
				}

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_parameterList);

		((ParameterListContext) _localctx).types = new ArrayList<Type>();
		((ParameterListContext) _localctx).names = new ArrayList<String>();

		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(305);
				((ParameterListContext) _localctx).p1 = parameterDeclaration();

				_localctx.types.add(((ParameterListContext) _localctx).p1.type);
				_localctx.names.add(((ParameterListContext) _localctx).p1.name);

				setState(313);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 22, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							{
								setState(307);
								match(Comma);
								setState(308);
								((ParameterListContext) _localctx).p2 = parameterDeclaration();

								_localctx.types.add(((ParameterListContext) _localctx).p2.type);
								_localctx.names.add(((ParameterListContext) _localctx).p2.name);

					}
						}
				}
					setState(315);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 22, _ctx);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ParameterDeclarationContext parameterDeclaration() throws RecognitionException {
		ParameterDeclarationContext _localctx = new ParameterDeclarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_parameterDeclaration);
		try {
			setState(329);
			switch (getInterpreter().adaptivePredict(_input, 23, _ctx)) {
				case 1:
					_localctx = new ParameterDeclaration1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(316);
					((ParameterDeclaration1Context) _localctx).t1 = ((ParameterDeclaration1Context) _localctx).typeSpecifier = typeSpecifier();

					TypeAnalyser.enter(((ParameterDeclaration1Context) _localctx).typeSpecifier.ret);
					((ParameterDeclaration1Context) _localctx).type = ((ParameterDeclaration1Context) _localctx).typeSpecifier.ret;
					((ParameterDeclaration1Context) _localctx).name = "";

				}
				break;
				case 2:
					_localctx = new ParameterDeclaration2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(319);
					((ParameterDeclaration2Context) _localctx).t2 = ((ParameterDeclaration2Context) _localctx).typeSpecifier = typeSpecifier();
					TypeAnalyser.enter(((ParameterDeclaration2Context) _localctx).typeSpecifier.ret);
					setState(321);
					((ParameterDeclaration2Context) _localctx).declarator = declarator();

					((ParameterDeclaration2Context) _localctx).type = TypeAnalyser.analyse();
					((ParameterDeclaration2Context) _localctx).name = ((ParameterDeclaration2Context) _localctx).declarator.name;

				}
				break;
				case 3:
					_localctx = new ParameterDeclaration3Context(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(324);
					((ParameterDeclaration3Context) _localctx).t3 = ((ParameterDeclaration3Context) _localctx).typeSpecifier = typeSpecifier();
					TypeAnalyser.enter(((ParameterDeclaration3Context) _localctx).typeSpecifier.ret);
					setState(326);
					abstractDeclarator();

					((ParameterDeclaration3Context) _localctx).type = TypeAnalyser.analyse();
					((ParameterDeclaration3Context) _localctx).name = "";

				}
				break;
			}

			TypeAnalyser.exit();

		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final AbstractDeclaratorContext abstractDeclarator() throws RecognitionException {
		AbstractDeclaratorContext _localctx = new AbstractDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_abstractDeclarator);
		int _la;
		try {
			setState(345);
			switch (getInterpreter().adaptivePredict(_input, 26, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(333);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(331);
								match(STAR);
								++_localctx.n;
							}
						}
						setState(335);
					_errHandler.sync(this);
					_la = _input.LA(1);
					} while (_la == STAR);
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(341);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == STAR) {
						{
							{
								setState(337);
								match(STAR);
								++_localctx.n;
							}
						}
						setState(343);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
					setState(344);
					directAbstractDeclarator(0);
				}
				break;
			}

			for (int i = 0; i < _localctx.n; ++i)
				TypeAnalyser.addStar();

		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final DirectAbstractDeclaratorContext directAbstractDeclarator() throws RecognitionException {
		return directAbstractDeclarator(0);
	}

	private DirectAbstractDeclaratorContext directAbstractDeclarator(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DirectAbstractDeclaratorContext _localctx = new DirectAbstractDeclaratorContext(_ctx, _parentState);
		DirectAbstractDeclaratorContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_directAbstractDeclarator, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(368);
				switch (getInterpreter().adaptivePredict(_input, 27, _ctx)) {
					case 1: {
						setState(348);
						match(L1);
						setState(349);
						abstractDeclarator();
						setState(350);
						match(R1);
				}
					break;
					case 2: {
						setState(352);
						match(L2);
						setState(353);
						match(R2);
						TypeAnalyser.addArray(null);
				}
					break;
					case 3: {
						setState(355);
						match(L2);
						setState(356);
						((DirectAbstractDeclaratorContext) _localctx).constantExpression = constantExpression();
						setState(357);
						match(R2);
						TypeAnalyser.addArray(((DirectAbstractDeclaratorContext) _localctx).constantExpression.ret);
					}
					break;
					case 4: {
						setState(360);
						match(L1);
						setState(361);
						match(R1);
						TypeAnalyser.addParameter();
					}
					break;
					case 5: {
						setState(363);
						match(L1);
						setState(364);
						((DirectAbstractDeclaratorContext) _localctx).parameterTypeList = parameterTypeList();
						setState(365);
						match(R1);
						TypeAnalyser.addParameter(((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.types, ((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.names, ((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.hasVaList);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(393);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(391);
							switch (getInterpreter().adaptivePredict(_input, 29, _ctx)) {
								case 1:
						{
							_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
							pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
							setState(370);
							if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
							setState(371);
						match(L2);
							setState(372);
						match(R2);
							TypeAnalyser.addArray(null);
						}
								break;
								case 2: {
									_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
									setState(374);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(375);
						match(L2);
									setState(376);
									((DirectAbstractDeclaratorContext) _localctx).constantExpression = constantExpression();
									setState(377);
						match(R2);
									TypeAnalyser.addArray(((DirectAbstractDeclaratorContext) _localctx).constantExpression.ret);
								}
								break;
								case 3: {
									_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
									setState(380);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(381);
						match(L1);
									setState(382);
						match(R1);
									TypeAnalyser.addParameter();
								}
								break;
								case 4:
						{
							_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
							pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
							setState(384);
							if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
							setState(385);
							match(L1);
							setState(387);
							switch (getInterpreter().adaptivePredict(_input, 28, _ctx)) {
								case 1: {
									setState(386);
									((DirectAbstractDeclaratorContext) _localctx).parameterTypeList = parameterTypeList();
							}
								break;
						}
							setState(389);
							match(R1);
							TypeAnalyser.addParameter(((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.types, ((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.names, ((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.hasVaList);
						}
								break;
					}
						}
				}
					setState(395);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public final TypedefNameContext typedefName() throws RecognitionException {
		TypedefNameContext _localctx = new TypedefNameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typedefName);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(396);
				if (!(Environment.isTypedefName(_input.LT(1).getText())))
					throw new FailedPredicateException(this, " Environment.isTypedefName(_input.LT(1).getText()) ");
				setState(397);
				((TypedefNameContext) _localctx).Identifier = match(Identifier);
				((TypedefNameContext) _localctx).ret = (Type) Environment.symbolNames.queryName((((TypedefNameContext) _localctx).Identifier != null ? ((TypedefNameContext) _localctx).Identifier.getText() : null)).ref;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final InitializerContext initializer() throws RecognitionException {
		InitializerContext _localctx = new InitializerContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_initializer);
		int _la;
		try {
			setState(417);
			switch (getInterpreter().adaptivePredict(_input, 32, _ctx)) {
				case 1:
					_localctx = new Initializer1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(400);
					((Initializer1Context) _localctx).assignmentExpression = assignmentExpression();
					((Initializer1Context) _localctx).ret = new SimpleInitializerList(((Initializer1Context) _localctx).assignmentExpression.ret);
				}
				break;
				case 2:
					_localctx = new Initializer2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(403);
					match(L3);
					setState(404);
					((Initializer2Context) _localctx).i1 = initializer();

					((Initializer2Context) _localctx).ret = new SimpleInitializerList(new ArrayList<SimpleInitializerList>());
					_localctx.ret.list.add(((Initializer2Context) _localctx).i1.ret);

					setState(412);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == Comma) {
						{
							{
								setState(406);
								match(Comma);
								setState(407);
								((Initializer2Context) _localctx).i2 = initializer();
								_localctx.ret.list.add(((Initializer2Context) _localctx).i2.ret);
							}
						}
						setState(414);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
					setState(415);
					match(R3);
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_statement);
		try {
			setState(434);
			switch (getInterpreter().adaptivePredict(_input, 33, _ctx)) {
				case 1:
					enterOuterAlt(_localctx, 1);
				{
					setState(419);
					((StatementContext) _localctx).expressionStatement = expressionStatement();
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).expressionStatement.ret;
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(422);
					((StatementContext) _localctx).compoundStatement = compoundStatement(null, null);
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).compoundStatement.ret;
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(425);
					((StatementContext) _localctx).selectionStatement = selectionStatement();
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).selectionStatement.ret;
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(428);
					((StatementContext) _localctx).iterationStatement = iterationStatement();
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).iterationStatement.ret;
				}
				break;
				case 5:
					enterOuterAlt(_localctx, 5);
				{
					setState(431);
					((StatementContext) _localctx).jumpStatement = jumpStatement();
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).jumpStatement.ret;
				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expressionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(439);
				switch (getInterpreter().adaptivePredict(_input, 34, _ctx)) {
					case 1: {
						setState(436);
						((ExpressionStatementContext) _localctx).expression = expression();
						((ExpressionStatementContext) _localctx).ret = ((ExpressionStatementContext) _localctx).expression.ret;
				}
					break;
			}
				setState(441);
				match(Semi);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final CompoundStatementContext compoundStatement(ArrayList<Type> toDefineTypes, ArrayList<String> toDefineNames) throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState(), toDefineTypes, toDefineNames);
		enterRule(_localctx, 44, RULE_compoundStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				setState(443);
				match(L3);

				Environment.enterScope();
				if (_localctx.toDefineTypes != null) {
					int n = _localctx.toDefineTypes.size();
					for (int i = 0; i < n; ++i) {
						int uId = Environment.symbolNames.defineVariable(_localctx.toDefineNames.get(i), _localctx.toDefineTypes.get(i));
						_localctx.givenVariables.add(uId);
						_localctx.parameters.add(uId);
								}
							}

				setState(452);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 36, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							setState(450);
							switch (getInterpreter().adaptivePredict(_input, 35, _ctx)) {
								case 1:
						{
							setState(445);
							declaration();
						}
								break;
								case 2:
						{
							{
								setState(446);
								((CompoundStatementContext) _localctx).statement = statement();
								_localctx.statements.add(((CompoundStatementContext) _localctx).statement.ret);
						}
						}
								break;
								case 3: {
									setState(449);
									functionDefinition();
						}
								break;
							}
						}
				}
					setState(454);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 36, _ctx);
				}
				setState(455);
				match(R3);

				((CompoundStatementContext) _localctx).ret = new CompoundStatement(Environment.symbolNames.getVariablesInCurrentScope(), _localctx.statements, _localctx.givenVariables, _localctx.parameters);
				Environment.exitScope();

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final SelectionStatementContext selectionStatement() throws RecognitionException {
		SelectionStatementContext _localctx = new SelectionStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_selectionStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(458);
				match(If);
				setState(459);
				match(L1);
				setState(460);
				((SelectionStatementContext) _localctx).expression = expression();
				((SelectionStatementContext) _localctx).e1 = ((SelectionStatementContext) _localctx).expression.ret;
				setState(462);
				match(R1);
				setState(463);
				((SelectionStatementContext) _localctx).st1 = statement();
				((SelectionStatementContext) _localctx).s1 = ((SelectionStatementContext) _localctx).st1.ret;
				setState(469);
				switch (getInterpreter().adaptivePredict(_input, 37, _ctx)) {
					case 1: {
						setState(465);
						match(Else);
						setState(466);
						((SelectionStatementContext) _localctx).st2 = statement();
						((SelectionStatementContext) _localctx).s2 = ((SelectionStatementContext) _localctx).st2.ret;
				}
					break;
				}

				((SelectionStatementContext) _localctx).ret = new IfStatement(_localctx.e1, _localctx.s1, _localctx.s2);

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_iterationStatement);
		try {
			setState(505);
			switch (_input.LA(1)) {
				case While:
					_localctx = new IterationStatement1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(473);
					match(While);
					setState(474);
					match(L1);
					setState(475);
					((IterationStatement1Context) _localctx).expression = expression();
					setState(476);
					match(R1);

					((IterationStatement1Context) _localctx).whileS = new WhileStatement(((IterationStatement1Context) _localctx).expression.ret);
					Environment.loopStack.push(_localctx.whileS);

					setState(478);
					((IterationStatement1Context) _localctx).statement = statement();

					_localctx.whileS.a = ((IterationStatement1Context) _localctx).statement.ret;
					Environment.loopStack.pop();
					((IterationStatement1Context) _localctx).ret = _localctx.whileS;

				}
				break;
				case For:
					_localctx = new IterationStatement2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(481);
					match(For);
					setState(482);
					match(L1);
					setState(486);
					switch (getInterpreter().adaptivePredict(_input, 38, _ctx)) {
						case 1: {
							setState(483);
							((IterationStatement2Context) _localctx).ex1 = expression();
							((IterationStatement2Context) _localctx).e1 = ((IterationStatement2Context) _localctx).ex1.ret;
					}
						break;
					}
					setState(488);
					match(Semi);
					setState(492);
					switch (getInterpreter().adaptivePredict(_input, 39, _ctx)) {
						case 1: {
							setState(489);
							((IterationStatement2Context) _localctx).ex2 = expression();
							((IterationStatement2Context) _localctx).e2 = ((IterationStatement2Context) _localctx).ex2.ret;
					}
						break;
					}
					setState(494);
					match(Semi);
					setState(498);
					switch (getInterpreter().adaptivePredict(_input, 40, _ctx)) {
						case 1: {
							setState(495);
							((IterationStatement2Context) _localctx).ex3 = expression();
							((IterationStatement2Context) _localctx).e3 = ((IterationStatement2Context) _localctx).ex3.ret;
					}
						break;
					}
					setState(500);
					match(R1);

					((IterationStatement2Context) _localctx).forS = new ForStatement(_localctx.e1, _localctx.e2, _localctx.e3);
					Environment.loopStack.push(_localctx.forS);

					setState(502);
					((IterationStatement2Context) _localctx).statement = statement();

					_localctx.forS.d = ((IterationStatement2Context) _localctx).statement.ret;
					Environment.loopStack.pop();
					((IterationStatement2Context) _localctx).ret = _localctx.forS;

				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final JumpStatementContext jumpStatement() throws RecognitionException {
		JumpStatementContext _localctx = new JumpStatementContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_jumpStatement);
		try {
			setState(521);
			switch (_input.LA(1)) {
				case T__7:
					_localctx = new JumpStatement1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(507);
					match(T__7);
					setState(508);
					match(Semi);

					((JumpStatement1Context) _localctx).ret = new ContinueStatement(Environment.getTopLoop());

				}
				break;
				case T__8:
					_localctx = new JumpStatement2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(510);
					match(T__8);
					setState(511);
					match(Semi);

					((JumpStatement2Context) _localctx).ret = new BreakStatement(Environment.getTopLoop());

				}
				break;
				case T__9:
					_localctx = new JumpStatement3Context(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(513);
					match(T__9);
					setState(517);
					switch (getInterpreter().adaptivePredict(_input, 42, _ctx)) {
						case 1: {
							setState(514);
							((JumpStatement3Context) _localctx).expression = expression();
							((JumpStatement3Context) _localctx).e = ((JumpStatement3Context) _localctx).expression.ret;
					}
						break;
					}
					setState(519);
					match(Semi);

					if (_localctx.e != null)
						Environment.matchReturn(_localctx.e.type);
					else
						Environment.matchReturn(VoidType.instance);
					((JumpStatement3Context) _localctx).ret = new ReturnStatement(_localctx.e);

				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(523);
				((ExpressionContext) _localctx).a1 = assignmentExpression();
				((ExpressionContext) _localctx).ret = ((ExpressionContext) _localctx).a1.ret;
				setState(531);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Comma) {
					{
						{
							setState(525);
							match(Comma);
							setState(526);
							((ExpressionContext) _localctx).a2 = assignmentExpression();
							((ExpressionContext) _localctx).ret = CommaExpression.getExpression(_localctx.ret, ((ExpressionContext) _localctx).a2.ret);
						}
					}
					setState(533);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_assignmentExpression);
		try {
			setState(549);
			switch (getInterpreter().adaptivePredict(_input, 45, _ctx)) {
				case 1:
					_localctx = new AssignmentExpression1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(534);
					((AssignmentExpression1Context) _localctx).logicalOrExpression = logicalOrExpression();
					((AssignmentExpression1Context) _localctx).ret = ((AssignmentExpression1Context) _localctx).logicalOrExpression.ret;
				}
				break;
				case 2:
					_localctx = new AssignmentExpression2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(537);
					((AssignmentExpression2Context) _localctx).a = unaryExpression();
					setState(538);
					((AssignmentExpression2Context) _localctx).assignmentOperator = assignmentOperator();
					setState(539);
					((AssignmentExpression2Context) _localctx).b = assignmentExpression();
					((AssignmentExpression2Context) _localctx).ret = Assign.getExpression(((AssignmentExpression2Context) _localctx).a.ret, ((AssignmentExpression2Context) _localctx).b.ret, (((AssignmentExpression2Context) _localctx).assignmentOperator != null ? _input.getText(((AssignmentExpression2Context) _localctx).assignmentOperator.start, ((AssignmentExpression2Context) _localctx).assignmentOperator.stop) : null));
				}
				break;
				case 3:
					_localctx = new AssignmentExpression3Context(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(542);
					match(T__10);
					setState(543);
					match(L1);
					setState(544);
					((AssignmentExpression3Context) _localctx).ap = match(Identifier);
					setState(545);
					match(Comma);
					setState(546);
					((AssignmentExpression3Context) _localctx).prev = match(Identifier);
					setState(547);
					match(R1);

					if (!Environment.isVariable((((AssignmentExpression3Context) _localctx).ap != null ? ((AssignmentExpression3Context) _localctx).ap.getText() : null)))
						throw new CompilationError((((AssignmentExpression3Context) _localctx).ap != null ? ((AssignmentExpression3Context) _localctx).ap.getText() : null) + " is not defined.");
					if (!Environment.isVariable((((AssignmentExpression3Context) _localctx).prev != null ? ((AssignmentExpression3Context) _localctx).prev.getText() : null)))
						throw new CompilationError((((AssignmentExpression3Context) _localctx).prev != null ? ((AssignmentExpression3Context) _localctx).prev.getText() : null) + " is not defined.");
					((AssignmentExpression3Context) _localctx).ret = Assign.getExpression(
							IdentifierExpression.getExpression((((AssignmentExpression3Context) _localctx).ap != null ? ((AssignmentExpression3Context) _localctx).ap.getText() : null)),
							Add.getExpression(
									CastExpression.getExpression(
											IntType.instance,
											AddressFetch.getExpression(
													IdentifierExpression.getExpression((((AssignmentExpression3Context) _localctx).prev != null ? ((AssignmentExpression3Context) _localctx).prev.getText() : null))
											)
									),
									new IntConstant(Panel.getPointerSize())
							),
							"="
					);

				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final AssignmentOperatorContext assignmentOperator() throws RecognitionException {
		AssignmentOperatorContext _localctx = new AssignmentOperatorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_assignmentOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(551);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << EQ))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					consume();
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(553);
				((ConstantExpressionContext) _localctx).logicalOrExpression = logicalOrExpression();

				((ConstantExpressionContext) _localctx).ret = ((ConstantExpressionContext) _localctx).logicalOrExpression.ret;
				if (!(_localctx.ret instanceof Constant))
					throw new CompilationError("Not constant.");

			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final LogicalOrExpressionContext logicalOrExpression() throws RecognitionException {
		LogicalOrExpressionContext _localctx = new LogicalOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_logicalOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(556);
				((LogicalOrExpressionContext) _localctx).a1 = logicalAndExpression();
				((LogicalOrExpressionContext) _localctx).ret = ((LogicalOrExpressionContext) _localctx).a1.ret;
				setState(564);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == OrOr) {
					{
						{
							setState(558);
							match(OrOr);
							setState(559);
							((LogicalOrExpressionContext) _localctx).a2 = logicalAndExpression();
							((LogicalOrExpressionContext) _localctx).ret = LogicalOr.getExpression(_localctx.ret, ((LogicalOrExpressionContext) _localctx).a2.ret);
						}
					}
					setState(566);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final LogicalAndExpressionContext logicalAndExpression() throws RecognitionException {
		LogicalAndExpressionContext _localctx = new LogicalAndExpressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_logicalAndExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(567);
				((LogicalAndExpressionContext) _localctx).a1 = inclusiveOrExpression();
				((LogicalAndExpressionContext) _localctx).ret = ((LogicalAndExpressionContext) _localctx).a1.ret;
				setState(575);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == AndAnd) {
					{
						{
							setState(569);
							match(AndAnd);
							setState(570);
							((LogicalAndExpressionContext) _localctx).a2 = inclusiveOrExpression();
							((LogicalAndExpressionContext) _localctx).ret = LogicalAnd.getExpression(_localctx.ret, ((LogicalAndExpressionContext) _localctx).a2.ret);
						}
					}
					setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final InclusiveOrExpressionContext inclusiveOrExpression() throws RecognitionException {
		InclusiveOrExpressionContext _localctx = new InclusiveOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_inclusiveOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(578);
				((InclusiveOrExpressionContext) _localctx).a1 = exclusiveOrExpression();
				((InclusiveOrExpressionContext) _localctx).ret = ((InclusiveOrExpressionContext) _localctx).a1.ret;
				setState(586);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Or) {
					{
						{
							setState(580);
							match(Or);
							setState(581);
							((InclusiveOrExpressionContext) _localctx).a2 = exclusiveOrExpression();
							((InclusiveOrExpressionContext) _localctx).ret = BitwiseOr.getExpression(_localctx.ret, ((InclusiveOrExpressionContext) _localctx).a2.ret);
						}
					}
					setState(588);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ExclusiveOrExpressionContext exclusiveOrExpression() throws RecognitionException {
		ExclusiveOrExpressionContext _localctx = new ExclusiveOrExpressionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_exclusiveOrExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(589);
				((ExclusiveOrExpressionContext) _localctx).a1 = andExpression();
				((ExclusiveOrExpressionContext) _localctx).ret = ((ExclusiveOrExpressionContext) _localctx).a1.ret;
				setState(597);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Caret) {
					{
						{
							setState(591);
							match(Caret);
							setState(592);
							((ExclusiveOrExpressionContext) _localctx).a2 = andExpression();
							((ExclusiveOrExpressionContext) _localctx).ret = BitwiseXOR.getExpression(_localctx.ret, ((ExclusiveOrExpressionContext) _localctx).a2.ret);
						}
					}
					setState(599);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_andExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(600);
				((AndExpressionContext) _localctx).a1 = equalityExpression();
				((AndExpressionContext) _localctx).ret = ((AndExpressionContext) _localctx).a1.ret;
				setState(608);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == And) {
					{
						{
							setState(602);
							match(And);
							setState(603);
							((AndExpressionContext) _localctx).a2 = equalityExpression();
							((AndExpressionContext) _localctx).ret = BitwiseAnd.getExpression(_localctx.ret, ((AndExpressionContext) _localctx).a2.ret);
						}
					}
					setState(610);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_equalityExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(611);
				((EqualityExpressionContext) _localctx).a1 = relationalExpression();
				((EqualityExpressionContext) _localctx).ret = ((EqualityExpressionContext) _localctx).a1.ret;
				setState(619);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__21 || _la == T__22) {
					{
						{
							setState(613);
							((EqualityExpressionContext) _localctx).op = equalityOperator();
							setState(614);
							((EqualityExpressionContext) _localctx).a2 = relationalExpression();

							if ((((EqualityExpressionContext) _localctx).op != null ? _input.getText(((EqualityExpressionContext) _localctx).op.start, ((EqualityExpressionContext) _localctx).op.stop) : null).equals("=="))
								((EqualityExpressionContext) _localctx).ret = EqualTo.getExpression(_localctx.ret, ((EqualityExpressionContext) _localctx).a2.ret);
							else
								((EqualityExpressionContext) _localctx).ret = NotEqualTo.getExpression(_localctx.ret, ((EqualityExpressionContext) _localctx).a2.ret);

				}
					}
					setState(621);
					_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final EqualityOperatorContext equalityOperator() throws RecognitionException {
		EqualityOperatorContext _localctx = new EqualityOperatorContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_equalityOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(622);
				_la = _input.LA(1);
				if (!(_la == T__21 || _la == T__22)) {
					_errHandler.recoverInline(this);
				} else {
					consume();
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_relationalExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(624);
				((RelationalExpressionContext) _localctx).a1 = shiftExpression();
				((RelationalExpressionContext) _localctx).ret = ((RelationalExpressionContext) _localctx).a1.ret;
				setState(632);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0)) {
					{
						{
							setState(626);
							((RelationalExpressionContext) _localctx).op = relationalOperator();
							setState(627);
							((RelationalExpressionContext) _localctx).a2 = shiftExpression();

							if ((((RelationalExpressionContext) _localctx).op != null ? _input.getText(((RelationalExpressionContext) _localctx).op.start, ((RelationalExpressionContext) _localctx).op.stop) : null).equals("<"))
								((RelationalExpressionContext) _localctx).ret = LessThan.getExpression(_localctx.ret, ((RelationalExpressionContext) _localctx).a2.ret);
							else if ((((RelationalExpressionContext) _localctx).op != null ? _input.getText(((RelationalExpressionContext) _localctx).op.start, ((RelationalExpressionContext) _localctx).op.stop) : null).equals(">"))
								((RelationalExpressionContext) _localctx).ret = GreaterThan.getExpression(_localctx.ret, ((RelationalExpressionContext) _localctx).a2.ret);
							else if ((((RelationalExpressionContext) _localctx).op != null ? _input.getText(((RelationalExpressionContext) _localctx).op.start, ((RelationalExpressionContext) _localctx).op.stop) : null).equals("<="))
								((RelationalExpressionContext) _localctx).ret = LE.getExpression(_localctx.ret, ((RelationalExpressionContext) _localctx).a2.ret);
							else
								((RelationalExpressionContext) _localctx).ret = GE.getExpression(_localctx.ret, ((RelationalExpressionContext) _localctx).a2.ret);

				}
					}
					setState(634);
					_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final RelationalOperatorContext relationalOperator() throws RecognitionException {
		RelationalOperatorContext _localctx = new RelationalOperatorContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_relationalOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(635);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__24) | (1L << T__25) | (1L << T__26))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					consume();
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ShiftExpressionContext shiftExpression() throws RecognitionException {
		ShiftExpressionContext _localctx = new ShiftExpressionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_shiftExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(637);
				((ShiftExpressionContext) _localctx).a1 = additiveExpression();
				((ShiftExpressionContext) _localctx).ret = ((ShiftExpressionContext) _localctx).a1.ret;
				setState(645);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__27 || _la == T__28) {
					{
						{
							setState(639);
							((ShiftExpressionContext) _localctx).op = shiftOperator();
							setState(640);
							((ShiftExpressionContext) _localctx).a2 = additiveExpression();

							if ((((ShiftExpressionContext) _localctx).op != null ? _input.getText(((ShiftExpressionContext) _localctx).op.start, ((ShiftExpressionContext) _localctx).op.stop) : null).equals("<<"))
								((ShiftExpressionContext) _localctx).ret = ShiftLeft.getExpression(_localctx.ret, ((ShiftExpressionContext) _localctx).a2.ret);
							else
								((ShiftExpressionContext) _localctx).ret = ShiftRight.getExpression(_localctx.ret, ((ShiftExpressionContext) _localctx).a2.ret);

				}
					}
					setState(647);
					_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ShiftOperatorContext shiftOperator() throws RecognitionException {
		ShiftOperatorContext _localctx = new ShiftOperatorContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_shiftOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(648);
				_la = _input.LA(1);
				if (!(_la == T__27 || _la == T__28)) {
					_errHandler.recoverInline(this);
				} else {
					consume();
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_additiveExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(650);
				((AdditiveExpressionContext) _localctx).a1 = multiplicativeExpression();
				((AdditiveExpressionContext) _localctx).ret = ((AdditiveExpressionContext) _localctx).a1.ret;
				setState(658);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__29 || _la == T__30) {
					{
						{
							setState(652);
							((AdditiveExpressionContext) _localctx).op = additiveOperator();
							setState(653);
							((AdditiveExpressionContext) _localctx).a2 = multiplicativeExpression();

							if ((((AdditiveExpressionContext) _localctx).op != null ? _input.getText(((AdditiveExpressionContext) _localctx).op.start, ((AdditiveExpressionContext) _localctx).op.stop) : null).equals("+"))
								((AdditiveExpressionContext) _localctx).ret = Add.getExpression(_localctx.ret, ((AdditiveExpressionContext) _localctx).a2.ret);
							else
								((AdditiveExpressionContext) _localctx).ret = Subtract.getExpression(_localctx.ret, ((AdditiveExpressionContext) _localctx).a2.ret);

				}
					}
					setState(660);
					_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final AdditiveOperatorContext additiveOperator() throws RecognitionException {
		AdditiveOperatorContext _localctx = new AdditiveOperatorContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_additiveOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(661);
				_la = _input.LA(1);
				if (!(_la == T__29 || _la == T__30)) {
					_errHandler.recoverInline(this);
				} else {
					consume();
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_multiplicativeExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(663);
				((MultiplicativeExpressionContext) _localctx).a1 = ((MultiplicativeExpressionContext) _localctx).castExpression = castExpression();
				((MultiplicativeExpressionContext) _localctx).ret = ((MultiplicativeExpressionContext) _localctx).castExpression.ret;
				setState(671);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << STAR))) != 0)) {
					{
						{
							setState(665);
							((MultiplicativeExpressionContext) _localctx).op = multiplicativeOperator();
							setState(666);
							((MultiplicativeExpressionContext) _localctx).a2 = ((MultiplicativeExpressionContext) _localctx).castExpression = castExpression();

							if ((((MultiplicativeExpressionContext) _localctx).op != null ? _input.getText(((MultiplicativeExpressionContext) _localctx).op.start, ((MultiplicativeExpressionContext) _localctx).op.stop) : null).equals("*"))
								((MultiplicativeExpressionContext) _localctx).ret = Multiply.getExpression(_localctx.ret, ((MultiplicativeExpressionContext) _localctx).a2.ret);
							else if ((((MultiplicativeExpressionContext) _localctx).op != null ? _input.getText(((MultiplicativeExpressionContext) _localctx).op.start, ((MultiplicativeExpressionContext) _localctx).op.stop) : null).equals("/"))
								((MultiplicativeExpressionContext) _localctx).ret = Divide.getExpression(_localctx.ret, ((MultiplicativeExpressionContext) _localctx).a2.ret);
							else
								((MultiplicativeExpressionContext) _localctx).ret = Modulo.getExpression(_localctx.ret, ((MultiplicativeExpressionContext) _localctx).a2.ret);

				}
					}
					setState(673);
					_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final MultiplicativeOperatorContext multiplicativeOperator() throws RecognitionException {
		MultiplicativeOperatorContext _localctx = new MultiplicativeOperatorContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_multiplicativeOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(674);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__31) | (1L << T__32) | (1L << STAR))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					consume();
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final CastExpressionContext castExpression() throws RecognitionException {
		CastExpressionContext _localctx = new CastExpressionContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_castExpression);
		try {
			setState(685);
			switch (getInterpreter().adaptivePredict(_input, 56, _ctx)) {
				case 1:
					_localctx = new CastExpression1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(676);
					((CastExpression1Context) _localctx).unaryExpression = unaryExpression();
					((CastExpression1Context) _localctx).ret = ((CastExpression1Context) _localctx).unaryExpression.ret;
				}
				break;
				case 2:
					_localctx = new CastExpression2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(679);
					match(L1);
					setState(680);
					((CastExpression2Context) _localctx).typeName = typeName();
					setState(681);
					match(R1);
					setState(682);
					((CastExpression2Context) _localctx).c1 = castExpression();

					((CastExpression2Context) _localctx).ret = CastExpression.getExpression(((CastExpression2Context) _localctx).typeName.ret, ((CastExpression2Context) _localctx).c1.ret);

				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_typeName);
		try {
			setState(695);
			switch (getInterpreter().adaptivePredict(_input, 57, _ctx)) {
				case 1:
					_localctx = new TypeName1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(687);
					((TypeName1Context) _localctx).typeSpecifier = typeSpecifier();

					TypeAnalyser.enter(((TypeName1Context) _localctx).typeSpecifier.ret);
					((TypeName1Context) _localctx).ret = ((TypeName1Context) _localctx).typeSpecifier.ret;

				}
				break;
				case 2:
					_localctx = new TypeName2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(690);
					((TypeName2Context) _localctx).typeSpecifier = typeSpecifier();
					TypeAnalyser.enter(((TypeName2Context) _localctx).typeSpecifier.ret);
					setState(692);
					abstractDeclarator();

					((TypeName2Context) _localctx).ret = TypeAnalyser.analyse();

				}
				break;
			}

			TypeAnalyser.exit();

		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_unaryExpression);
		try {
			setState(735);
			switch (getInterpreter().adaptivePredict(_input, 58, _ctx)) {
				case 1:
					_localctx = new UnaryExpression1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(697);
					((UnaryExpression1Context) _localctx).postfixExpression = postfixExpression(0);
					((UnaryExpression1Context) _localctx).ret = ((UnaryExpression1Context) _localctx).postfixExpression.ret;
				}
				break;
				case 2:
					_localctx = new UnaryExpression2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(700);
					match(T__33);
					setState(701);
					((UnaryExpression2Context) _localctx).u1 = unaryExpression();
					((UnaryExpression2Context) _localctx).ret = PrefixSelfInc.getExpression(((UnaryExpression2Context) _localctx).u1.ret);
				}
				break;
				case 3:
					_localctx = new UnaryExpression3Context(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(704);
					match(T__34);
					setState(705);
					((UnaryExpression3Context) _localctx).u2 = unaryExpression();
					((UnaryExpression3Context) _localctx).ret = PrefixSelfDec.getExpression(((UnaryExpression3Context) _localctx).u2.ret);
				}
				break;
				case 4:
					_localctx = new UnaryExpression4Context(_localctx);
					enterOuterAlt(_localctx, 4);
				{
					setState(708);
					((UnaryExpression4Context) _localctx).op = unaryOperator();
					setState(709);
					((UnaryExpression4Context) _localctx).a2 = castExpression();

					if ((((UnaryExpression4Context) _localctx).op != null ? _input.getText(((UnaryExpression4Context) _localctx).op.start, ((UnaryExpression4Context) _localctx).op.stop) : null).equals("&"))
						((UnaryExpression4Context) _localctx).ret = AddressFetch.getExpression(((UnaryExpression4Context) _localctx).a2.ret);
					else if ((((UnaryExpression4Context) _localctx).op != null ? _input.getText(((UnaryExpression4Context) _localctx).op.start, ((UnaryExpression4Context) _localctx).op.stop) : null).equals("*"))
						((UnaryExpression4Context) _localctx).ret = AddressAccess.getExpression(((UnaryExpression4Context) _localctx).a2.ret);
					else if ((((UnaryExpression4Context) _localctx).op != null ? _input.getText(((UnaryExpression4Context) _localctx).op.start, ((UnaryExpression4Context) _localctx).op.stop) : null).equals("+"))
						((UnaryExpression4Context) _localctx).ret = Positive.getExpression(((UnaryExpression4Context) _localctx).a2.ret);
					else if ((((UnaryExpression4Context) _localctx).op != null ? _input.getText(((UnaryExpression4Context) _localctx).op.start, ((UnaryExpression4Context) _localctx).op.stop) : null).equals("-"))
						((UnaryExpression4Context) _localctx).ret = Negative.getExpression(((UnaryExpression4Context) _localctx).a2.ret);
					else if ((((UnaryExpression4Context) _localctx).op != null ? _input.getText(((UnaryExpression4Context) _localctx).op.start, ((UnaryExpression4Context) _localctx).op.stop) : null).equals("~"))
						((UnaryExpression4Context) _localctx).ret = BitwiseNot.getExpression(((UnaryExpression4Context) _localctx).a2.ret);
					else if ((((UnaryExpression4Context) _localctx).op != null ? _input.getText(((UnaryExpression4Context) _localctx).op.start, ((UnaryExpression4Context) _localctx).op.stop) : null).equals("!"))
						((UnaryExpression4Context) _localctx).ret = LogicalNot.getExpression(((UnaryExpression4Context) _localctx).a2.ret);

				}
				break;
				case 5:
					_localctx = new UnaryExpression5Context(_localctx);
					enterOuterAlt(_localctx, 5);
				{
					setState(712);
					match(SizeOf);
					setState(713);
					match(L1);
					setState(714);
					((UnaryExpression5Context) _localctx).Identifier = match(Identifier);
					setState(715);
					match(R1);

					if (Environment.isVariable((((UnaryExpression5Context) _localctx).Identifier != null ? ((UnaryExpression5Context) _localctx).Identifier.getText() : null)))
						((UnaryExpression5Context) _localctx).ret = new Sizeof(IdentifierExpression.getExpression((((UnaryExpression5Context) _localctx).Identifier != null ? ((UnaryExpression5Context) _localctx).Identifier.getText() : null)));
					else if (Environment.isTypedefName((((UnaryExpression5Context) _localctx).Identifier != null ? ((UnaryExpression5Context) _localctx).Identifier.getText() : null)))
						((UnaryExpression5Context) _localctx).ret = new IntConstant(Environment._pretend_being_private_sizeof);
					else
						throw new CompilationError("Unknow " + (((UnaryExpression5Context) _localctx).Identifier != null ? ((UnaryExpression5Context) _localctx).Identifier.getText() : null));

				}
				break;
				case 6:
					_localctx = new UnaryExpression6Context(_localctx);
					enterOuterAlt(_localctx, 6);
				{
					setState(717);
					match(SizeOf);
					setState(718);
					match(L1);
					setState(719);
					((UnaryExpression6Context) _localctx).typeName = typeName();
					setState(720);
					match(R1);
					((UnaryExpression6Context) _localctx).ret = new IntConstant(((UnaryExpression6Context) _localctx).typeName.ret.sizeof());
				}
				break;
				case 7:
					_localctx = new UnaryExpression7Context(_localctx);
					enterOuterAlt(_localctx, 7);
				{
					setState(723);
					match(SizeOf);
					setState(724);
					((UnaryExpression7Context) _localctx).u3 = unaryExpression();
					((UnaryExpression7Context) _localctx).ret = new Sizeof(((UnaryExpression7Context) _localctx).u3.ret);
				}
				break;
				case 8:
					_localctx = new UnaryExpression8Context(_localctx);
					enterOuterAlt(_localctx, 8);
				{
					setState(727);
					match(T__35);
					setState(728);
					match(L1);
					setState(729);
					((UnaryExpression8Context) _localctx).ap = match(Identifier);
					setState(730);
					match(Comma);
					setState(731);
					((UnaryExpression8Context) _localctx).typeName = typeName();
					setState(732);
					match(R1);

					if (!Environment.isVariable((((UnaryExpression8Context) _localctx).ap != null ? ((UnaryExpression8Context) _localctx).ap.getText() : null)))
						throw new CompilationError((((UnaryExpression8Context) _localctx).ap != null ? ((UnaryExpression8Context) _localctx).ap.getText() : null) + " is not defined.");
					((UnaryExpression8Context) _localctx).ret = ArrayAccess.getExpression(
							CastExpression.getExpression(
									new VariablePointerType(((UnaryExpression8Context) _localctx).typeName.ret),
									Subtract.getExpression(
											Assign.getExpression(
													IdentifierExpression.getExpression((((UnaryExpression8Context) _localctx).ap != null ? ((UnaryExpression8Context) _localctx).ap.getText() : null)),
													new IntConstant(Panel.getPointerSize()),
													"+="
											),
											new IntConstant(Panel.getPointerSize())
									)
							),
							new IntConstant(0)
					);

				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_unaryOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(737);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__36) | (1L << T__37) | (1L << STAR) | (1L << And))) != 0))) {
					_errHandler.recoverInline(this);
				} else {
					consume();
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		return postfixExpression(0);
	}

	private PostfixExpressionContext postfixExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, _parentState);
		PostfixExpressionContext _prevctx = _localctx;
		int _startState = 98;
		enterRecursionRule(_localctx, 98, RULE_postfixExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				{
					setState(740);
					((PostfixExpressionContext) _localctx).primaryExpression = primaryExpression();
					((PostfixExpressionContext) _localctx).ret = ((PostfixExpressionContext) _localctx).primaryExpression.ret;
				}
				_ctx.stop = _input.LT(-1);
				setState(774);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 61, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(772);
							switch (getInterpreter().adaptivePredict(_input, 60, _ctx)) {
								case 1:
						{
							_localctx = new PostfixExpressionContext(_parentctx, _parentState);
							_localctx.p = _prevctx;
							_localctx.p = _prevctx;
							pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
							setState(743);
							if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
							setState(744);
							match(L2);
							setState(745);
							((PostfixExpressionContext) _localctx).expression = expression();
							setState(746);
							match(R2);
							((PostfixExpressionContext) _localctx).ret = ArrayAccess.getExpression(((PostfixExpressionContext) _localctx).p.ret, ((PostfixExpressionContext) _localctx).expression.ret);
						}
								break;
								case 2: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(749);
									if (!(precpred(_ctx, 5)))
										throw new FailedPredicateException(this, "precpred(_ctx, 5)");
									setState(750);
									match(L1);
									setState(754);
									switch (getInterpreter().adaptivePredict(_input, 59, _ctx)) {
										case 1: {
											setState(751);
											((PostfixExpressionContext) _localctx).arguments = arguments();
											((PostfixExpressionContext) _localctx).arg = ((PostfixExpressionContext) _localctx).arguments.ret;
							}
										break;
						}
									setState(756);
									match(R1);
									((PostfixExpressionContext) _localctx).ret = FunctionCall.getExpression(((PostfixExpressionContext) _localctx).p.ret, _localctx.arg);
						}
								break;
								case 3: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(758);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									setState(759);
									match(T__38);
									setState(760);
									((PostfixExpressionContext) _localctx).Identifier = match(Identifier);
									((PostfixExpressionContext) _localctx).ret = MemberAccess.getExpression(((PostfixExpressionContext) _localctx).p.ret, (((PostfixExpressionContext) _localctx).Identifier != null ? ((PostfixExpressionContext) _localctx).Identifier.getText() : null));
						}
								break;
								case 4: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(762);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(763);
									match(T__39);
									setState(764);
									((PostfixExpressionContext) _localctx).Identifier = match(Identifier);
									((PostfixExpressionContext) _localctx).ret = PointerMemberAccess.getExpression(((PostfixExpressionContext) _localctx).p.ret, (((PostfixExpressionContext) _localctx).Identifier != null ? ((PostfixExpressionContext) _localctx).Identifier.getText() : null));
								}
								break;
								case 5: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(766);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(767);
									match(T__33);
									((PostfixExpressionContext) _localctx).ret = PostfixSelfInc.getExpression(((PostfixExpressionContext) _localctx).p.ret);
								}
								break;
								case 6: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(769);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(770);
									match(T__34);
									((PostfixExpressionContext) _localctx).ret = PostfixSelfDec.getExpression(((PostfixExpressionContext) _localctx).p.ret);
								}
								break;
							}
						}
				}
					setState(776);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 61, _ctx);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_arguments);

		((ArgumentsContext) _localctx).ret = new ArrayList<Expression>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(777);
				((ArgumentsContext) _localctx).a1 = assignmentExpression();
				_localctx.ret.add(((ArgumentsContext) _localctx).a1.ret);
				setState(785);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Comma) {
					{
						{
							setState(779);
							match(Comma);
							setState(780);
							((ArgumentsContext) _localctx).a2 = assignmentExpression();

							_localctx.ret.add(((ArgumentsContext) _localctx).a2.ret);

				}
					}
					setState(787);
					_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_primaryExpression);

		((PrimaryExpressionContext) _localctx).s = new ArrayList<String>();

		try {
			int _alt;
			setState(809);
			switch (getInterpreter().adaptivePredict(_input, 64, _ctx)) {
				case 1:
					_localctx = new PrimaryExpression1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(788);
					if (!(Environment.isVariable(_input.LT(1).getText())))
						throw new FailedPredicateException(this, " Environment.isVariable(_input.LT(1).getText()) ");
					setState(789);
					((PrimaryExpression1Context) _localctx).Identifier = match(Identifier);

					((PrimaryExpression1Context) _localctx).ret = IdentifierExpression.getExpression((((PrimaryExpression1Context) _localctx).Identifier != null ? ((PrimaryExpression1Context) _localctx).Identifier.getText() : null));

				}
				break;
				case 2:
					_localctx = new PrimaryExpression2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(791);
					((PrimaryExpression2Context) _localctx).constant = constant();

					((PrimaryExpression2Context) _localctx).ret = ((PrimaryExpression2Context) _localctx).constant.ret;

				}
				break;
				case 3:
					_localctx = new PrimaryExpression3Context(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(796);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
							case 1: {
								{
									setState(794);
									((PrimaryExpression3Context) _localctx).StringLiteral = match(StringLiteral);

									_localctx.s.add((((PrimaryExpression3Context) _localctx).StringLiteral != null ? ((PrimaryExpression3Context) _localctx).StringLiteral.getText() : null));

						}
						}
							break;
							default:
								throw new NoViableAltException(this);
						}
						setState(798);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 63, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);

					((PrimaryExpression3Context) _localctx).ret = StringConstant.getExpression(_localctx.s);

				}
				break;
				case 4:
					_localctx = new PrimaryExpression4Context(_localctx);
					enterOuterAlt(_localctx, 4);
				{
					setState(801);
					match(L1);
					setState(802);
					((PrimaryExpression4Context) _localctx).expression = expression();
					setState(803);
					match(R1);

					((PrimaryExpression4Context) _localctx).ret = ((PrimaryExpression4Context) _localctx).expression.ret;

				}
				break;
				case 5:
					_localctx = new PrimaryExpression5Context(_localctx);
					enterOuterAlt(_localctx, 5);
				{
					setState(806);
					((PrimaryExpression5Context) _localctx).lambdaExpression = lambdaExpression();

					((PrimaryExpression5Context) _localctx).ret = ((PrimaryExpression5Context) _localctx).lambdaExpression.ret;

				}
				break;
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final LambdaExpressionContext lambdaExpression() throws RecognitionException {
		LambdaExpressionContext _localctx = new LambdaExpressionContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_lambdaExpression);

		((LambdaExpressionContext) _localctx).parameterTypes = new ArrayList<Type>();
		((LambdaExpressionContext) _localctx).parameterNames = new ArrayList<String>();
		((LambdaExpressionContext) _localctx).loopStack = Environment.loopStack;
		Environment.loopStack = new Stack<>();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(811);
				match(L2);
				setState(812);
				match(R2);
				setState(813);
				match(L1);
				setState(817);
				switch (getInterpreter().adaptivePredict(_input, 65, _ctx)) {
					case 1: {
						setState(814);
						((LambdaExpressionContext) _localctx).parameterTypeList = parameterTypeList();

						((LambdaExpressionContext) _localctx).parameterTypes = ((LambdaExpressionContext) _localctx).parameterTypeList.types;
						((LambdaExpressionContext) _localctx).parameterNames = ((LambdaExpressionContext) _localctx).parameterTypeList.names;
						((LambdaExpressionContext) _localctx).hasVaList = ((LambdaExpressionContext) _localctx).parameterTypeList.hasVaList;
						if (_localctx.parameterNames.size() != 0 && (new HashSet<>(_localctx.parameterNames).size()) != _localctx.parameterNames.size())
							throw new CompilationError("parameter should have different names.");
						for (String name : _localctx.parameterNames)
							if (name == null || name.equals(""))
								throw new CompilationError("No parameter name.");

				}
					break;
				}
				setState(819);
				match(R1);
				setState(824);
				_la = _input.LA(1);
				if (_la == T__39) {
					{
						setState(820);
						match(T__39);
						setState(821);
						((LambdaExpressionContext) _localctx).typeName = typeName();

						((LambdaExpressionContext) _localctx).type = ((LambdaExpressionContext) _localctx).typeName.ret;

				}
				}


				Environment.functionReturnStack.push(_localctx.type);
				if (_localctx.type == null) ((LambdaExpressionContext) _localctx).type = VoidType.instance;
				((LambdaExpressionContext) _localctx).type = new FunctionType(_localctx.type, _localctx.parameterTypes, _localctx.parameterNames, _localctx.hasVaList);
				((LambdaExpressionContext) _localctx).uId = Environment.symbolNames.defineLocalFunction("", _localctx.type);

				setState(827);
				((LambdaExpressionContext) _localctx).compoundStatement = compoundStatement(_localctx.parameterTypes, _localctx.parameterNames);

				((LambdaExpressionContext) _localctx).s = ((LambdaExpressionContext) _localctx).compoundStatement.ret;
				Environment.symbolNames.defineVariable(_localctx.uId, _localctx.type, _localctx.s);
				_localctx.s.youAreAFrame(Environment.symbolNames.currentScope + 1);
				Environment.functionReturnStack.pop();
				((LambdaExpressionContext) _localctx).ret = IdentifierExpression.getExpression(_localctx.uId);

			}

			Environment.loopStack = _localctx.loopStack;

		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_constant);
		try {
			setState(838);
			switch (_input.LA(1)) {
				case DecimalConstant:
					enterOuterAlt(_localctx, 1);
				{
					setState(830);
					((ConstantContext) _localctx).DecimalConstant = match(DecimalConstant);
					((ConstantContext) _localctx).ret = IntConstant.getExpression((((ConstantContext) _localctx).DecimalConstant != null ? ((ConstantContext) _localctx).DecimalConstant.getText() : null), 10);
				}
				break;
				case OctalConstant:
					enterOuterAlt(_localctx, 2);
				{
					setState(832);
					((ConstantContext) _localctx).OctalConstant = match(OctalConstant);
					((ConstantContext) _localctx).ret = IntConstant.getExpression((((ConstantContext) _localctx).OctalConstant != null ? ((ConstantContext) _localctx).OctalConstant.getText() : null), 8);
				}
				break;
				case HexadecimalConstant:
					enterOuterAlt(_localctx, 3);
				{
					setState(834);
					((ConstantContext) _localctx).HexadecimalConstant = match(HexadecimalConstant);
					((ConstantContext) _localctx).ret = IntConstant.getExpression((((ConstantContext) _localctx).HexadecimalConstant != null ? ((ConstantContext) _localctx).HexadecimalConstant.getText() : null), 16);
				}
				break;
				case CharacterConstant:
					enterOuterAlt(_localctx, 4);
				{
					setState(836);
					((ConstantContext) _localctx).CharacterConstant = match(CharacterConstant);
					((ConstantContext) _localctx).ret = CharConstant.getExpression((((ConstantContext) _localctx).CharacterConstant != null ? ((ConstantContext) _localctx).CharacterConstant.getText() : null));
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		} catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		} finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
			case 12:
				return directDeclarator_sempred((DirectDeclaratorContext) _localctx, predIndex);
			case 17:
				return directAbstractDeclarator_sempred((DirectAbstractDeclaratorContext) _localctx, predIndex);
			case 18:
				return typedefName_sempred((TypedefNameContext) _localctx, predIndex);
			case 49:
				return postfixExpression_sempred((PostfixExpressionContext) _localctx, predIndex);
			case 51:
				return primaryExpression_sempred((PrimaryExpressionContext) _localctx, predIndex);
		}
		return true;
	}

	private boolean directDeclarator_sempred(DirectDeclaratorContext _localctx, int predIndex) {
		switch (predIndex) {
			case 0:
				return precpred(_ctx, 4);
			case 1:
				return precpred(_ctx, 3);
			case 2:
				return precpred(_ctx, 2);
			case 3:
				return precpred(_ctx, 1);
		}
		return true;
	}

	private boolean directAbstractDeclarator_sempred(DirectAbstractDeclaratorContext _localctx, int predIndex) {
		switch (predIndex) {
			case 4:
				return precpred(_ctx, 4);
			case 5:
				return precpred(_ctx, 3);
			case 6:
				return precpred(_ctx, 2);
			case 7:
				return precpred(_ctx, 1);
		}
		return true;
	}

	private boolean typedefName_sempred(TypedefNameContext _localctx, int predIndex) {
		switch (predIndex) {
			case 8:
				return Environment.isTypedefName(_input.LT(1).getText());
		}
		return true;
	}

	private boolean postfixExpression_sempred(PostfixExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
			case 9:
				return precpred(_ctx, 6);
			case 10:
				return precpred(_ctx, 5);
			case 11:
				return precpred(_ctx, 4);
			case 12:
				return precpred(_ctx, 3);
			case 13:
				return precpred(_ctx, 2);
			case 14:
				return precpred(_ctx, 1);
		}
		return true;
	}

	private boolean primaryExpression_sempred(PrimaryExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
			case 15:
				return Environment.isVariable(_input.LT(1).getText());
		}
		return true;
	}

	public static class CompilationUnitContext extends ParserRuleContext {
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode EOF() {
			return getToken(Compiler2015Parser.EOF, 0);
		}

		public List<FunctionDefinitionContext> functionDefinition() {
			return getRuleContexts(FunctionDefinitionContext.class);
		}

		public FunctionDefinitionContext functionDefinition(int i) {
			return getRuleContext(FunctionDefinitionContext.class, i);
		}

		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}

		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class, i);
		}

		public List<TerminalNode> Semi() {
			return getTokens(Compiler2015Parser.Semi);
		}

		public TerminalNode Semi(int i) {
			return getToken(Compiler2015Parser.Semi, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_compilationUnit;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitCompilationUnit(this);
		}
	}

	public static class DeclarationContext extends ParserRuleContext {
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public DeclarationContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_declaration;
		}

		public void copyFrom(DeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}

	public static class Declaration1Context extends DeclarationContext {
		public TypeSpecifierContext typeSpecifier;

		public Declaration1Context(DeclarationContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode Typedef() {
			return getToken(Compiler2015Parser.Typedef, 0);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		public TerminalNode Semi() {
			return getToken(Compiler2015Parser.Semi, 0);
		}

		public DeclaratorListContext declaratorList() {
			return getRuleContext(DeclaratorListContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterDeclaration1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitDeclaration1(this);
		}
	}

	public static class Declaration2Context extends DeclarationContext {
		public TypeSpecifierContext typeSpecifier;

		public Declaration2Context(DeclarationContext ctx) {
			copyFrom(ctx);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		public TerminalNode Semi() {
			return getToken(Compiler2015Parser.Semi, 0);
		}

		public InitDeclaratorListContext initDeclaratorList() {
			return getRuleContext(InitDeclaratorListContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterDeclaration2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitDeclaration2(this);
		}
	}

	public static class FunctionDefinitionContext extends ParserRuleContext {
		public FunctionType type;
		public String name;
		public CompoundStatement s = null;
		public ArrayList<Type> parameterTypes;
		public ArrayList<String> parameterNames;
		public boolean hasVaList = false;
		public int uId = -1;
		public Stack<Loop> loopStack = null;
		public Type returnType = null;
		public ArrayList<Type> typePassDown;
		public ArrayList<String> namePassDown;
		public TypeSpecifierContext typeSpecifier;
		public DirectDeclaratorContext directDeclarator;
		public ParameterTypeListContext parameterTypeList;
		public CompoundStatementContext compoundStatement;
		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class, 0);
		}

		public TerminalNode L1() {
			return getToken(Compiler2015Parser.L1, 0);
		}

		public TerminalNode R1() {
			return getToken(Compiler2015Parser.R1, 0);
		}

		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class, 0);
		}

		public List<TerminalNode> STAR() {
			return getTokens(Compiler2015Parser.STAR);
		}

		public TerminalNode STAR(int i) {
			return getToken(Compiler2015Parser.STAR, i);
		}

		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionDefinition;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitFunctionDefinition(this);
		}
	}

	public static class InitDeclaratorListContext extends ParserRuleContext {
		public InitDeclaratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<InitDeclaratorContext> initDeclarator() {
			return getRuleContexts(InitDeclaratorContext.class);
		}

		public InitDeclaratorContext initDeclarator(int i) {
			return getRuleContext(InitDeclaratorContext.class, i);
		}

		public List<TerminalNode> Comma() {
			return getTokens(Compiler2015Parser.Comma);
		}

		public TerminalNode Comma(int i) {
			return getToken(Compiler2015Parser.Comma, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_initDeclaratorList;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterInitDeclaratorList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitInitDeclaratorList(this);
		}
	}

	public static class InitDeclaratorContext extends ParserRuleContext {
		public Type type;
		public String name;
		public SimpleInitializerList init = null;
		public int uId;
		public DeclaratorContext declarator;
		public InitializerContext initializer;
		public InitDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class, 0);
		}

		public TerminalNode EQ() {
			return getToken(Compiler2015Parser.EQ, 0);
		}

		public InitializerContext initializer() {
			return getRuleContext(InitializerContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_initDeclarator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterInitDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitInitDeclarator(this);
		}
	}

	public static class TypeSpecifierContext extends ParserRuleContext {
		public Type ret;
		public TypedefNameContext typedefName;
		public StructOrUnionSpecifierContext structOrUnionSpecifier;
		public TypeSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TypedefNameContext typedefName() {
			return getRuleContext(TypedefNameContext.class, 0);
		}

		public StructOrUnionSpecifierContext structOrUnionSpecifier() {
			return getRuleContext(StructOrUnionSpecifierContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_typeSpecifier;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterTypeSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitTypeSpecifier(this);
		}
	}

	public static class StructOrUnionSpecifierContext extends ParserRuleContext {
		public Type ret;
		public boolean isUnion;
		public String name;
		public StructOrUnionSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public StructOrUnionSpecifierContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_structOrUnionSpecifier;
		}

		public void copyFrom(StructOrUnionSpecifierContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
			this.isUnion = ctx.isUnion;
			this.name = ctx.name;
		}
	}

	public static class StructOrUnionSpecifier1Context extends StructOrUnionSpecifierContext {
		public StructOrUnionContext structOrUnion;
		public Token Identifier;
		public StructDeclarationContext structDeclaration;

		public StructOrUnionSpecifier1Context(StructOrUnionSpecifierContext ctx) {
			copyFrom(ctx);
		}

		public StructOrUnionContext structOrUnion() {
			return getRuleContext(StructOrUnionContext.class, 0);
		}

		public TerminalNode L3() {
			return getToken(Compiler2015Parser.L3, 0);
		}

		public TerminalNode R3() {
			return getToken(Compiler2015Parser.R3, 0);
		}

		public TerminalNode Identifier() {
			return getToken(Compiler2015Parser.Identifier, 0);
		}

		public List<StructDeclarationContext> structDeclaration() {
			return getRuleContexts(StructDeclarationContext.class);
		}

		public StructDeclarationContext structDeclaration(int i) {
			return getRuleContext(StructDeclarationContext.class, i);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterStructOrUnionSpecifier1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitStructOrUnionSpecifier1(this);
		}
	}

	public static class StructOrUnionSpecifier2Context extends StructOrUnionSpecifierContext {
		public StructOrUnionContext structOrUnion;
		public Token Identifier;

		public StructOrUnionSpecifier2Context(StructOrUnionSpecifierContext ctx) {
			copyFrom(ctx);
		}

		public StructOrUnionContext structOrUnion() {
			return getRuleContext(StructOrUnionContext.class, 0);
		}

		public TerminalNode Identifier() {
			return getToken(Compiler2015Parser.Identifier, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterStructOrUnionSpecifier2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitStructOrUnionSpecifier2(this);
		}
	}

	public static class StructOrUnionContext extends ParserRuleContext {
		public boolean isUnion;
		public StructOrUnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_structOrUnion;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterStructOrUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitStructOrUnion(this);
		}
	}

	public static class StructDeclarationContext extends ParserRuleContext {
		public ArrayList<Type> types;
		public ArrayList<String> names;
		public TypeSpecifierContext typeSpecifier;
		public DeclaratorContext d1;
		public DeclaratorContext d2;
		public StructDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		public TerminalNode Semi() {
			return getToken(Compiler2015Parser.Semi, 0);
		}

		public List<DeclaratorContext> declarator() {
			return getRuleContexts(DeclaratorContext.class);
		}

		public DeclaratorContext declarator(int i) {
			return getRuleContext(DeclaratorContext.class, i);
		}

		public List<TerminalNode> Comma() {
			return getTokens(Compiler2015Parser.Comma);
		}

		public TerminalNode Comma(int i) {
			return getToken(Compiler2015Parser.Comma, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_structDeclaration;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterStructDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitStructDeclaration(this);
		}
	}

	public static class DeclaratorContext extends ParserRuleContext {
		public String name;
		public int n = 0;
		public DirectDeclaratorContext directDeclarator;
		public DeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class, 0);
		}

		public List<TerminalNode> STAR() {
			return getTokens(Compiler2015Parser.STAR);
		}

		public TerminalNode STAR(int i) {
			return getToken(Compiler2015Parser.STAR, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_declarator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitDeclarator(this);
		}
	}

	public static class PlainDeclaratorContext extends ParserRuleContext {
		public Type type;
		public String name;
		public DeclaratorContext declarator;
		public PlainDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_plainDeclarator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterPlainDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitPlainDeclarator(this);
		}
	}

	public static class DeclaratorListContext extends ParserRuleContext {
		public DeclaratorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<PlainDeclaratorContext> plainDeclarator() {
			return getRuleContexts(PlainDeclaratorContext.class);
		}

		public PlainDeclaratorContext plainDeclarator(int i) {
			return getRuleContext(PlainDeclaratorContext.class, i);
		}

		public List<TerminalNode> Comma() {
			return getTokens(Compiler2015Parser.Comma);
		}

		public TerminalNode Comma(int i) {
			return getToken(Compiler2015Parser.Comma, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_declaratorList;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterDeclaratorList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitDeclaratorList(this);
		}
	}

	public static class DirectDeclaratorContext extends ParserRuleContext {
		public String name;
		public DirectDeclaratorContext d1;
		public DirectDeclaratorContext d2;
		public DirectDeclaratorContext d3;
		public DirectDeclaratorContext d4;
		public Token Identifier;
		public DeclaratorContext declarator;
		public ConstantExpressionContext constantExpression;
		public ParameterTypeListContext parameterTypeList;
		public DirectDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode Identifier() {
			return getToken(Compiler2015Parser.Identifier, 0);
		}

		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class, 0);
		}

		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class, 0);
		}

		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class, 0);
		}

		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_directDeclarator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterDirectDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitDirectDeclarator(this);
		}
	}

	public static class ParameterTypeListContext extends ParserRuleContext {
		public ArrayList<Type> types;
		public ArrayList<String> names;
		public boolean hasVaList = false;
		public ParameterListContext parameterList;
		public ParameterTypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class, 0);
		}

		public TerminalNode Comma() {
			return getToken(Compiler2015Parser.Comma, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_parameterTypeList;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterParameterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitParameterTypeList(this);
		}
	}

	public static class ParameterListContext extends ParserRuleContext {
		public ArrayList<Type> types;
		public ArrayList<String> names;
		public ParameterDeclarationContext p1;
		public ParameterDeclarationContext p2;
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<ParameterDeclarationContext> parameterDeclaration() {
			return getRuleContexts(ParameterDeclarationContext.class);
		}

		public ParameterDeclarationContext parameterDeclaration(int i) {
			return getRuleContext(ParameterDeclarationContext.class, i);
		}

		public List<TerminalNode> Comma() {
			return getTokens(Compiler2015Parser.Comma);
		}

		public TerminalNode Comma(int i) {
			return getToken(Compiler2015Parser.Comma, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_parameterList;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitParameterList(this);
		}
	}

	public static class ParameterDeclarationContext extends ParserRuleContext {
		public Type type;
		public String name;
		public ParameterDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ParameterDeclarationContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_parameterDeclaration;
		}

		public void copyFrom(ParameterDeclarationContext ctx) {
			super.copyFrom(ctx);
			this.type = ctx.type;
			this.name = ctx.name;
		}
	}

	public static class ParameterDeclaration1Context extends ParameterDeclarationContext {
		public TypeSpecifierContext t1;
		public TypeSpecifierContext typeSpecifier;

		public ParameterDeclaration1Context(ParameterDeclarationContext ctx) {
			copyFrom(ctx);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterParameterDeclaration1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitParameterDeclaration1(this);
		}
	}

	public static class ParameterDeclaration2Context extends ParameterDeclarationContext {
		public TypeSpecifierContext t2;
		public TypeSpecifierContext typeSpecifier;
		public DeclaratorContext declarator;

		public ParameterDeclaration2Context(ParameterDeclarationContext ctx) {
			copyFrom(ctx);
		}

		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class, 0);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterParameterDeclaration2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitParameterDeclaration2(this);
		}
	}

	public static class ParameterDeclaration3Context extends ParameterDeclarationContext {
		public TypeSpecifierContext t3;
		public TypeSpecifierContext typeSpecifier;

		public ParameterDeclaration3Context(ParameterDeclarationContext ctx) {
			copyFrom(ctx);
		}

		public AbstractDeclaratorContext abstractDeclarator() {
			return getRuleContext(AbstractDeclaratorContext.class, 0);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterParameterDeclaration3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitParameterDeclaration3(this);
		}
	}

	public static class AbstractDeclaratorContext extends ParserRuleContext {
		public int n = 0;
		public AbstractDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public DirectAbstractDeclaratorContext directAbstractDeclarator() {
			return getRuleContext(DirectAbstractDeclaratorContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_abstractDeclarator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterAbstractDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitAbstractDeclarator(this);
		}
	}

	public static class DirectAbstractDeclaratorContext extends ParserRuleContext {
		public ConstantExpressionContext constantExpression;
		public ParameterTypeListContext parameterTypeList;
		public DirectAbstractDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public AbstractDeclaratorContext abstractDeclarator() {
			return getRuleContext(AbstractDeclaratorContext.class, 0);
		}

		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class, 0);
		}

		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class, 0);
		}

		public DirectAbstractDeclaratorContext directAbstractDeclarator() {
			return getRuleContext(DirectAbstractDeclaratorContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_directAbstractDeclarator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterDirectAbstractDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitDirectAbstractDeclarator(this);
		}
	}

	public static class TypedefNameContext extends ParserRuleContext {
		public Type ret;
		public Token Identifier;
		public TypedefNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode Identifier() {
			return getToken(Compiler2015Parser.Identifier, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_typedefName;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterTypedefName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitTypedefName(this);
		}
	}

	public static class InitializerContext extends ParserRuleContext {
		public SimpleInitializerList ret;
		public InitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public InitializerContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_initializer;
		}

		public void copyFrom(InitializerContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
		}
	}

	public static class Initializer2Context extends InitializerContext {
		public InitializerContext i1;
		public InitializerContext i2;

		public Initializer2Context(InitializerContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode L3() {
			return getToken(Compiler2015Parser.L3, 0);
		}

		public TerminalNode R3() {
			return getToken(Compiler2015Parser.R3, 0);
		}

		public List<InitializerContext> initializer() {
			return getRuleContexts(InitializerContext.class);
		}

		public InitializerContext initializer(int i) {
			return getRuleContext(InitializerContext.class, i);
		}

		public List<TerminalNode> Comma() {
			return getTokens(Compiler2015Parser.Comma);
		}

		public TerminalNode Comma(int i) {
			return getToken(Compiler2015Parser.Comma, i);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterInitializer2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitInitializer2(this);
		}
	}

	public static class Initializer1Context extends InitializerContext {
		public AssignmentExpressionContext assignmentExpression;

		public Initializer1Context(InitializerContext ctx) {
			copyFrom(ctx);
		}

		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterInitializer1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitInitializer1(this);
		}
	}

	public static class StatementContext extends ParserRuleContext {
		public Statement ret;
		public ExpressionStatementContext expressionStatement;
		public CompoundStatementContext compoundStatement;
		public SelectionStatementContext selectionStatement;
		public IterationStatementContext iterationStatement;
		public JumpStatementContext jumpStatement;
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ExpressionStatementContext expressionStatement() {
			return getRuleContext(ExpressionStatementContext.class, 0);
		}

		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class, 0);
		}

		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class, 0);
		}

		public IterationStatementContext iterationStatement() {
			return getRuleContext(IterationStatementContext.class, 0);
		}

		public JumpStatementContext jumpStatement() {
			return getRuleContext(JumpStatementContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_statement;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitStatement(this);
		}
	}

	public static class ExpressionStatementContext extends ParserRuleContext {
		public Statement ret = null;
		public ExpressionContext expression;
		public ExpressionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expressionStatement;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitExpressionStatement(this);
		}
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public ArrayList<Type> toDefineTypes;
		public ArrayList<String> toDefineNames;
		public CompoundStatement ret;
		public ArrayList<Statement> statements = new ArrayList<Statement>();
		public ArrayList<Integer> givenVariables = new ArrayList<>();
		public ArrayList<Integer> parameters = new ArrayList<>();
		public StatementContext statement;

		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		public CompoundStatementContext(ParserRuleContext parent, int invokingState, ArrayList<Type> toDefineTypes, ArrayList<String> toDefineNames) {
			super(parent, invokingState);
			this.toDefineTypes = toDefineTypes;
			this.toDefineNames = toDefineNames;
		}

		public TerminalNode L3() {
			return getToken(Compiler2015Parser.L3, 0);
		}

		public TerminalNode R3() {
			return getToken(Compiler2015Parser.R3, 0);
		}

		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}

		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class, i);
		}

		public List<FunctionDefinitionContext> functionDefinition() {
			return getRuleContexts(FunctionDefinitionContext.class);
		}

		public FunctionDefinitionContext functionDefinition(int i) {
			return getRuleContext(FunctionDefinitionContext.class, i);
		}

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_compoundStatement;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterCompoundStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitCompoundStatement(this);
		}
	}

	public static class SelectionStatementContext extends ParserRuleContext {
		public IfStatement ret;
		public Expression e1 = null;
		public Statement s1 = null;
		public Statement s2 = null;
		public ExpressionContext expression;
		public StatementContext st1;
		public StatementContext st2;
		public SelectionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode If() {
			return getToken(Compiler2015Parser.If, 0);
		}

		public TerminalNode L1() {
			return getToken(Compiler2015Parser.L1, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public TerminalNode R1() {
			return getToken(Compiler2015Parser.R1, 0);
		}

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class, i);
		}

		public TerminalNode Else() {
			return getToken(Compiler2015Parser.Else, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_selectionStatement;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterSelectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitSelectionStatement(this);
		}
	}

	public static class IterationStatementContext extends ParserRuleContext {
		public Statement ret;
		public WhileStatement whileS;
		public ForStatement forS;
		public Expression e1 = null;
		public Expression e2 = null;
		public Expression e3;
		public IterationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public IterationStatementContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_iterationStatement;
		}

		public void copyFrom(IterationStatementContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
			this.whileS = ctx.whileS;
			this.forS = ctx.forS;
			this.e1 = ctx.e1;
			this.e2 = ctx.e2;
			this.e3 = ctx.e3;
		}
	}

	public static class IterationStatement2Context extends IterationStatementContext {
		public ExpressionContext ex1;
		public ExpressionContext ex2;
		public ExpressionContext ex3;
		public StatementContext statement;

		public IterationStatement2Context(IterationStatementContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode For() {
			return getToken(Compiler2015Parser.For, 0);
		}

		public TerminalNode L1() {
			return getToken(Compiler2015Parser.L1, 0);
		}

		public List<TerminalNode> Semi() {
			return getTokens(Compiler2015Parser.Semi);
		}

		public TerminalNode Semi(int i) {
			return getToken(Compiler2015Parser.Semi, i);
		}

		public TerminalNode R1() {
			return getToken(Compiler2015Parser.R1, 0);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}

		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class, i);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterIterationStatement2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitIterationStatement2(this);
		}
	}

	public static class IterationStatement1Context extends IterationStatementContext {
		public ExpressionContext expression;
		public StatementContext statement;

		public IterationStatement1Context(IterationStatementContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode While() {
			return getToken(Compiler2015Parser.While, 0);
		}

		public TerminalNode L1() {
			return getToken(Compiler2015Parser.L1, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public TerminalNode R1() {
			return getToken(Compiler2015Parser.R1, 0);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterIterationStatement1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitIterationStatement1(this);
		}
	}

	public static class JumpStatementContext extends ParserRuleContext {
		public Statement ret;
		public Expression e = null;
		public JumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public JumpStatementContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_jumpStatement;
		}

		public void copyFrom(JumpStatementContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
			this.e = ctx.e;
		}
	}

	public static class JumpStatement1Context extends JumpStatementContext {
		public JumpStatement1Context(JumpStatementContext ctx) {
			copyFrom(ctx);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterJumpStatement1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitJumpStatement1(this);
		}
	}

	public static class JumpStatement2Context extends JumpStatementContext {
		public JumpStatement2Context(JumpStatementContext ctx) {
			copyFrom(ctx);
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterJumpStatement2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitJumpStatement2(this);
		}
	}

	public static class JumpStatement3Context extends JumpStatementContext {
		public ExpressionContext expression;

		public JumpStatement3Context(JumpStatementContext ctx) {
			copyFrom(ctx);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterJumpStatement3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitJumpStatement3(this);
		}
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression ret;
		public AssignmentExpressionContext a1;
		public AssignmentExpressionContext a2;
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<AssignmentExpressionContext> assignmentExpression() {
			return getRuleContexts(AssignmentExpressionContext.class);
		}

		public AssignmentExpressionContext assignmentExpression(int i) {
			return getRuleContext(AssignmentExpressionContext.class, i);
		}

		public List<TerminalNode> Comma() {
			return getTokens(Compiler2015Parser.Comma);
		}

		public TerminalNode Comma(int i) {
			return getToken(Compiler2015Parser.Comma, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_expression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitExpression(this);
		}
	}

	public static class AssignmentExpressionContext extends ParserRuleContext {
		public Expression ret;
		public AssignmentExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public AssignmentExpressionContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_assignmentExpression;
		}

		public void copyFrom(AssignmentExpressionContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
		}
	}

	public static class AssignmentExpression3Context extends AssignmentExpressionContext {
		public Token ap;
		public Token prev;

		public AssignmentExpression3Context(AssignmentExpressionContext ctx) {
			copyFrom(ctx);
		}

		public List<TerminalNode> Identifier() {
			return getTokens(Compiler2015Parser.Identifier);
		}

		public TerminalNode Identifier(int i) {
			return getToken(Compiler2015Parser.Identifier, i);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterAssignmentExpression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitAssignmentExpression3(this);
		}
	}

	public static class AssignmentExpression1Context extends AssignmentExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression;

		public AssignmentExpression1Context(AssignmentExpressionContext ctx) {
			copyFrom(ctx);
		}

		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterAssignmentExpression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitAssignmentExpression1(this);
		}
	}

	public static class AssignmentExpression2Context extends AssignmentExpressionContext {
		public UnaryExpressionContext a;
		public AssignmentOperatorContext assignmentOperator;
		public AssignmentExpressionContext b;

		public AssignmentExpression2Context(AssignmentExpressionContext ctx) {
			copyFrom(ctx);
		}

		public AssignmentOperatorContext assignmentOperator() {
			return getRuleContext(AssignmentOperatorContext.class, 0);
		}

		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class, 0);
		}

		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterAssignmentExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitAssignmentExpression2(this);
		}
	}

	public static class AssignmentOperatorContext extends ParserRuleContext {
		public AssignmentOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_assignmentOperator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterAssignmentOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitAssignmentOperator(this);
		}
	}

	public static class ConstantExpressionContext extends ParserRuleContext {
		public Expression ret;
		public LogicalOrExpressionContext logicalOrExpression;
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_constantExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterConstantExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitConstantExpression(this);
		}
	}

	public static class LogicalOrExpressionContext extends ParserRuleContext {
		public Expression ret;
		public LogicalAndExpressionContext a1;
		public LogicalAndExpressionContext a2;
		public LogicalOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<LogicalAndExpressionContext> logicalAndExpression() {
			return getRuleContexts(LogicalAndExpressionContext.class);
		}

		public LogicalAndExpressionContext logicalAndExpression(int i) {
			return getRuleContext(LogicalAndExpressionContext.class, i);
		}

		public List<TerminalNode> OrOr() {
			return getTokens(Compiler2015Parser.OrOr);
		}

		public TerminalNode OrOr(int i) {
			return getToken(Compiler2015Parser.OrOr, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_logicalOrExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterLogicalOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitLogicalOrExpression(this);
		}
	}

	public static class LogicalAndExpressionContext extends ParserRuleContext {
		public Expression ret;
		public InclusiveOrExpressionContext a1;
		public InclusiveOrExpressionContext a2;
		public LogicalAndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<InclusiveOrExpressionContext> inclusiveOrExpression() {
			return getRuleContexts(InclusiveOrExpressionContext.class);
		}

		public InclusiveOrExpressionContext inclusiveOrExpression(int i) {
			return getRuleContext(InclusiveOrExpressionContext.class, i);
		}

		public List<TerminalNode> AndAnd() {
			return getTokens(Compiler2015Parser.AndAnd);
		}

		public TerminalNode AndAnd(int i) {
			return getToken(Compiler2015Parser.AndAnd, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_logicalAndExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterLogicalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitLogicalAndExpression(this);
		}
	}

	public static class InclusiveOrExpressionContext extends ParserRuleContext {
		public Expression ret;
		public ExclusiveOrExpressionContext a1;
		public ExclusiveOrExpressionContext a2;
		public InclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<ExclusiveOrExpressionContext> exclusiveOrExpression() {
			return getRuleContexts(ExclusiveOrExpressionContext.class);
		}

		public ExclusiveOrExpressionContext exclusiveOrExpression(int i) {
			return getRuleContext(ExclusiveOrExpressionContext.class, i);
		}

		public List<TerminalNode> Or() {
			return getTokens(Compiler2015Parser.Or);
		}

		public TerminalNode Or(int i) {
			return getToken(Compiler2015Parser.Or, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_inclusiveOrExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterInclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitInclusiveOrExpression(this);
		}
	}

	public static class ExclusiveOrExpressionContext extends ParserRuleContext {
		public Expression ret;
		public AndExpressionContext a1;
		public AndExpressionContext a2;
		public ExclusiveOrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}

		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class, i);
		}

		public List<TerminalNode> Caret() {
			return getTokens(Compiler2015Parser.Caret);
		}

		public TerminalNode Caret(int i) {
			return getToken(Compiler2015Parser.Caret, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_exclusiveOrExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterExclusiveOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitExclusiveOrExpression(this);
		}
	}

	public static class AndExpressionContext extends ParserRuleContext {
		public Expression ret;
		public EqualityExpressionContext a1;
		public EqualityExpressionContext a2;
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<EqualityExpressionContext> equalityExpression() {
			return getRuleContexts(EqualityExpressionContext.class);
		}

		public EqualityExpressionContext equalityExpression(int i) {
			return getRuleContext(EqualityExpressionContext.class, i);
		}

		public List<TerminalNode> And() {
			return getTokens(Compiler2015Parser.And);
		}

		public TerminalNode And(int i) {
			return getToken(Compiler2015Parser.And, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_andExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitAndExpression(this);
		}
	}

	public static class EqualityExpressionContext extends ParserRuleContext {
		public Expression ret;
		public RelationalExpressionContext a1;
		public EqualityOperatorContext op;
		public RelationalExpressionContext a2;
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}

		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class, i);
		}

		public List<EqualityOperatorContext> equalityOperator() {
			return getRuleContexts(EqualityOperatorContext.class);
		}

		public EqualityOperatorContext equalityOperator(int i) {
			return getRuleContext(EqualityOperatorContext.class, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_equalityExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitEqualityExpression(this);
		}
	}

	public static class EqualityOperatorContext extends ParserRuleContext {
		public EqualityOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_equalityOperator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterEqualityOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitEqualityOperator(this);
		}
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public Expression ret;
		public ShiftExpressionContext a1;
		public RelationalOperatorContext op;
		public ShiftExpressionContext a2;
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<ShiftExpressionContext> shiftExpression() {
			return getRuleContexts(ShiftExpressionContext.class);
		}

		public ShiftExpressionContext shiftExpression(int i) {
			return getRuleContext(ShiftExpressionContext.class, i);
		}

		public List<RelationalOperatorContext> relationalOperator() {
			return getRuleContexts(RelationalOperatorContext.class);
		}

		public RelationalOperatorContext relationalOperator(int i) {
			return getRuleContext(RelationalOperatorContext.class, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_relationalExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitRelationalExpression(this);
		}
	}

	public static class RelationalOperatorContext extends ParserRuleContext {
		public RelationalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_relationalOperator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterRelationalOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitRelationalOperator(this);
		}
	}

	public static class ShiftExpressionContext extends ParserRuleContext {
		public Expression ret;
		public AdditiveExpressionContext a1;
		public ShiftOperatorContext op;
		public AdditiveExpressionContext a2;
		public ShiftExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}

		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class, i);
		}

		public List<ShiftOperatorContext> shiftOperator() {
			return getRuleContexts(ShiftOperatorContext.class);
		}

		public ShiftOperatorContext shiftOperator(int i) {
			return getRuleContext(ShiftOperatorContext.class, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_shiftExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterShiftExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitShiftExpression(this);
		}
	}

	public static class ShiftOperatorContext extends ParserRuleContext {
		public ShiftOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_shiftOperator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterShiftOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitShiftOperator(this);
		}
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public Expression ret;
		public MultiplicativeExpressionContext a1;
		public AdditiveOperatorContext op;
		public MultiplicativeExpressionContext a2;
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}

		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class, i);
		}

		public List<AdditiveOperatorContext> additiveOperator() {
			return getRuleContexts(AdditiveOperatorContext.class);
		}

		public AdditiveOperatorContext additiveOperator(int i) {
			return getRuleContext(AdditiveOperatorContext.class, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_additiveExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitAdditiveExpression(this);
		}
	}

	public static class AdditiveOperatorContext extends ParserRuleContext {
		public AdditiveOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_additiveOperator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterAdditiveOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitAdditiveOperator(this);
		}
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public Expression ret;
		public CastExpressionContext a1;
		public CastExpressionContext castExpression;
		public MultiplicativeOperatorContext op;
		public CastExpressionContext a2;
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<CastExpressionContext> castExpression() {
			return getRuleContexts(CastExpressionContext.class);
		}

		public CastExpressionContext castExpression(int i) {
			return getRuleContext(CastExpressionContext.class, i);
		}

		public List<MultiplicativeOperatorContext> multiplicativeOperator() {
			return getRuleContexts(MultiplicativeOperatorContext.class);
		}

		public MultiplicativeOperatorContext multiplicativeOperator(int i) {
			return getRuleContext(MultiplicativeOperatorContext.class, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_multiplicativeExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitMultiplicativeExpression(this);
		}
	}

	public static class MultiplicativeOperatorContext extends ParserRuleContext {
		public MultiplicativeOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_multiplicativeOperator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterMultiplicativeOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitMultiplicativeOperator(this);
		}
	}

	public static class CastExpressionContext extends ParserRuleContext {
		public Expression ret;
		public CastExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public CastExpressionContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_castExpression;
		}

		public void copyFrom(CastExpressionContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
		}
	}

	public static class CastExpression1Context extends CastExpressionContext {
		public UnaryExpressionContext unaryExpression;

		public CastExpression1Context(CastExpressionContext ctx) {
			copyFrom(ctx);
		}

		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterCastExpression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitCastExpression1(this);
		}
	}

	public static class CastExpression2Context extends CastExpressionContext {
		public TypeNameContext typeName;
		public CastExpressionContext c1;

		public CastExpression2Context(CastExpressionContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode L1() {
			return getToken(Compiler2015Parser.L1, 0);
		}

		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class, 0);
		}

		public TerminalNode R1() {
			return getToken(Compiler2015Parser.R1, 0);
		}

		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterCastExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitCastExpression2(this);
		}
	}

	public static class TypeNameContext extends ParserRuleContext {
		public Type ret;
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TypeNameContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_typeName;
		}

		public void copyFrom(TypeNameContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
		}
	}

	public static class TypeName1Context extends TypeNameContext {
		public TypeSpecifierContext typeSpecifier;

		public TypeName1Context(TypeNameContext ctx) {
			copyFrom(ctx);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterTypeName1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitTypeName1(this);
		}
	}

	public static class TypeName2Context extends TypeNameContext {
		public TypeSpecifierContext typeSpecifier;

		public TypeName2Context(TypeNameContext ctx) {
			copyFrom(ctx);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class, 0);
		}

		public AbstractDeclaratorContext abstractDeclarator() {
			return getRuleContext(AbstractDeclaratorContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterTypeName2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitTypeName2(this);
		}
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public Expression ret;
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public UnaryExpressionContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_unaryExpression;
		}

		public void copyFrom(UnaryExpressionContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
		}
	}

	public static class UnaryExpression2Context extends UnaryExpressionContext {
		public UnaryExpressionContext u1;

		public UnaryExpression2Context(UnaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryExpression2(this);
		}
	}

	public static class UnaryExpression1Context extends UnaryExpressionContext {
		public PostfixExpressionContext postfixExpression;

		public UnaryExpression1Context(UnaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryExpression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryExpression1(this);
		}
	}

	public static class UnaryExpression6Context extends UnaryExpressionContext {
		public TypeNameContext typeName;

		public UnaryExpression6Context(UnaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode SizeOf() {
			return getToken(Compiler2015Parser.SizeOf, 0);
		}

		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryExpression6(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryExpression6(this);
		}
	}

	public static class UnaryExpression5Context extends UnaryExpressionContext {
		public Token Identifier;

		public UnaryExpression5Context(UnaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode SizeOf() {
			return getToken(Compiler2015Parser.SizeOf, 0);
		}

		public TerminalNode Identifier() {
			return getToken(Compiler2015Parser.Identifier, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryExpression5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryExpression5(this);
		}
	}

	public static class UnaryExpression4Context extends UnaryExpressionContext {
		public UnaryOperatorContext op;
		public CastExpressionContext a2;

		public UnaryExpression4Context(UnaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class, 0);
		}

		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryExpression4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryExpression4(this);
		}
	}

	public static class UnaryExpression3Context extends UnaryExpressionContext {
		public UnaryExpressionContext u2;

		public UnaryExpression3Context(UnaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryExpression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryExpression3(this);
		}
	}

	public static class UnaryExpression8Context extends UnaryExpressionContext {
		public Token ap;
		public TypeNameContext typeName;

		public UnaryExpression8Context(UnaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class, 0);
		}

		public TerminalNode Identifier() {
			return getToken(Compiler2015Parser.Identifier, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryExpression8(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryExpression8(this);
		}
	}

	public static class UnaryExpression7Context extends UnaryExpressionContext {
		public UnaryExpressionContext u3;

		public UnaryExpression7Context(UnaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode SizeOf() {
			return getToken(Compiler2015Parser.SizeOf, 0);
		}

		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryExpression7(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryExpression7(this);
		}
	}

	public static class UnaryOperatorContext extends ParserRuleContext {
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_unaryOperator;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterUnaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitUnaryOperator(this);
		}
	}

	public static class PostfixExpressionContext extends ParserRuleContext {
		public Expression ret;
		public ArrayList<Expression> arg = null;
		public PostfixExpressionContext p;
		public PrimaryExpressionContext primaryExpression;
		public ExpressionContext expression;
		public ArgumentsContext arguments;
		public Token Identifier;
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class, 0);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class, 0);
		}

		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class, 0);
		}

		public TerminalNode Identifier() {
			return getToken(Compiler2015Parser.Identifier, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_postfixExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterPostfixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitPostfixExpression(this);
		}
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public ArrayList<Expression> ret;
		public AssignmentExpressionContext a1;
		public AssignmentExpressionContext a2;
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public List<AssignmentExpressionContext> assignmentExpression() {
			return getRuleContexts(AssignmentExpressionContext.class);
		}

		public AssignmentExpressionContext assignmentExpression(int i) {
			return getRuleContext(AssignmentExpressionContext.class, i);
		}

		public List<TerminalNode> Comma() {
			return getTokens(Compiler2015Parser.Comma);
		}

		public TerminalNode Comma(int i) {
			return getToken(Compiler2015Parser.Comma, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_arguments;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitArguments(this);
		}
	}

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public Expression ret;
		public ArrayList<String> s;
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public PrimaryExpressionContext() {
		}

		@Override
		public int getRuleIndex() {
			return RULE_primaryExpression;
		}

		public void copyFrom(PrimaryExpressionContext ctx) {
			super.copyFrom(ctx);
			this.ret = ctx.ret;
			this.s = ctx.s;
		}
	}

	public static class PrimaryExpression1Context extends PrimaryExpressionContext {
		public Token Identifier;

		public PrimaryExpression1Context(PrimaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public TerminalNode Identifier() {
			return getToken(Compiler2015Parser.Identifier, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterPrimaryExpression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitPrimaryExpression1(this);
		}
	}

	public static class PrimaryExpression3Context extends PrimaryExpressionContext {
		public Token StringLiteral;

		public PrimaryExpression3Context(PrimaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public List<TerminalNode> StringLiteral() {
			return getTokens(Compiler2015Parser.StringLiteral);
		}

		public TerminalNode StringLiteral(int i) {
			return getToken(Compiler2015Parser.StringLiteral, i);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterPrimaryExpression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitPrimaryExpression3(this);
		}
	}

	public static class PrimaryExpression2Context extends PrimaryExpressionContext {
		public ConstantContext constant;

		public PrimaryExpression2Context(PrimaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterPrimaryExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitPrimaryExpression2(this);
		}
	}

	public static class PrimaryExpression5Context extends PrimaryExpressionContext {
		public LambdaExpressionContext lambdaExpression;

		public PrimaryExpression5Context(PrimaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public LambdaExpressionContext lambdaExpression() {
			return getRuleContext(LambdaExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterPrimaryExpression5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitPrimaryExpression5(this);
		}
	}

	public static class PrimaryExpression4Context extends PrimaryExpressionContext {
		public ExpressionContext expression;

		public PrimaryExpression4Context(PrimaryExpressionContext ctx) {
			copyFrom(ctx);
		}

		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class, 0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).enterPrimaryExpression4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener)
				((Compiler2015Listener) listener).exitPrimaryExpression4(this);
		}
	}

	public static class LambdaExpressionContext extends ParserRuleContext {
		public Expression ret;
		public Type type = null;
		public CompoundStatement s = null;
		public ArrayList<Type> parameterTypes;
		public ArrayList<String> parameterNames;
		public boolean hasVaList = false;
		public int uId = -1;
		public Stack<Loop> loopStack = null;
		public ParameterTypeListContext parameterTypeList;
		public TypeNameContext typeName;
		public CompoundStatementContext compoundStatement;
		public LambdaExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class, 0);
		}

		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class, 0);
		}

		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_lambdaExpression;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterLambdaExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitLambdaExpression(this);
		}
	}

	public static class ConstantContext extends ParserRuleContext {
		public Expression ret;
		public Token DecimalConstant;
		public Token OctalConstant;
		public Token HexadecimalConstant;
		public Token CharacterConstant;
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TerminalNode DecimalConstant() {
			return getToken(Compiler2015Parser.DecimalConstant, 0);
		}

		public TerminalNode OctalConstant() {
			return getToken(Compiler2015Parser.OctalConstant, 0);
		}

		public TerminalNode HexadecimalConstant() {
			return getToken(Compiler2015Parser.HexadecimalConstant, 0);
		}

		public TerminalNode CharacterConstant() {
			return getToken(Compiler2015Parser.CharacterConstant, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_constant;
		}
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitConstant(this);
		}
	}
}