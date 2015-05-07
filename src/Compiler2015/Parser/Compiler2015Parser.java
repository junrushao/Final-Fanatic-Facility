// Generated from /home/junrushao/IdeaProjects/compiler2015/src/Compiler2015/Parser/Compiler2015.g4 by ANTLR 4.5
package Compiler2015.Parser;

import Compiler2015.AST.SimpleInitializerList;
import Compiler2015.AST.Statement.*;
import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.*;
import Compiler2015.AST.Statement.ExpressionStatement.*;
import Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression.*;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
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
			T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, Typedef = 38,
			Semi = 39, Comma = 40, L1 = 41, R1 = 42, L2 = 43, R2 = 44, L3 = 45, R3 = 46, EQ = 47, STAR = 48,
			If = 49, Else = 50, While = 51, For = 52, OrOr = 53, AndAnd = 54, Or = 55, Caret = 56,
			And = 57, SizeOf = 58, Identifier = 59, DecimalConstant = 60, OctalConstant = 61,
			HexadecimalConstant = 62, CharacterConstant = 63, StringLiteral = 64, Preprocessing = 65,
			Whitespace = 66, Newline = 67, BlockComment = 68, LineComment = 69;
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
			"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3G\u032c\4\2\t\2\4" +
					"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t" +
					"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22" +
					"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31" +
					"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!" +
					"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4" +
					",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t" +
					"\64\4\65\t\65\4\66\t\66\4\67\t\67\3\2\3\2\3\2\7\2r\n\2\f\2\16\2u\13\2" +
					"\3\2\3\2\3\3\3\3\3\3\3\3\5\3}\n\3\3\3\3\3\3\3\3\3\3\3\5\3\u0084\n\3\3" +
					"\3\3\3\5\3\u0088\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0091\n\4\3\4\3\4" +
					"\3\4\3\4\3\4\3\5\3\5\3\5\7\5\u009b\n\5\f\5\16\5\u009e\13\5\3\6\3\6\3\6" +
					"\3\6\3\6\3\6\5\6\u00a6\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7" +
					"\3\7\5\7\u00b4\n\7\3\b\3\b\3\b\3\b\5\b\u00ba\n\b\3\b\3\b\3\b\3\b\3\b\7" +
					"\b\u00c1\n\b\f\b\16\b\u00c4\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00ce" +
					"\n\b\3\t\3\t\3\t\3\t\5\t\u00d4\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n" +
					"\u00de\n\n\f\n\16\n\u00e1\13\n\5\n\u00e3\n\n\3\n\3\n\3\13\3\13\7\13\u00e9" +
					"\n\13\f\13\16\13\u00ec\13\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\7" +
					"\r\u00f7\n\r\f\r\16\r\u00fa\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3" +
					"\16\5\16\u0104\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
					"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16" +
					"\7\16\u011e\n\16\f\16\16\16\u0121\13\16\3\17\3\17\3\17\3\17\3\17\5\17" +
					"\u0128\n\17\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0130\n\20\f\20\16\20\u0133" +
					"\13\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21" +
					"\5\21\u0142\n\21\3\22\3\22\6\22\u0146\n\22\r\22\16\22\u0147\3\22\3\22" +
					"\7\22\u014c\n\22\f\22\16\22\u014f\13\22\3\22\5\22\u0152\n\22\3\23\3\23" +
					"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23" +
					"\3\23\3\23\3\23\3\23\3\23\5\23\u0169\n\23\3\23\3\23\3\23\3\23\3\23\3\23" +
					"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u017c\n\23" +
					"\3\23\3\23\7\23\u0180\n\23\f\23\16\23\u0183\13\23\3\24\3\24\3\24\3\24" +
					"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0193\n\25\f\25" +
					"\16\25\u0196\13\25\3\25\3\25\5\25\u019a\n\25\3\26\3\26\3\26\3\26\3\26" +
					"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01ab\n\26\3\27" +
					"\3\27\3\27\5\27\u01b0\n\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30" +
					"\7\30\u01bb\n\30\f\30\16\30\u01be\13\30\3\30\3\30\3\30\3\31\3\31\3\31" +
					"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u01ce\n\31\3\31\3\31\3\32" +
					"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u01df" +
					"\n\32\3\32\3\32\3\32\3\32\5\32\u01e5\n\32\3\32\3\32\3\32\3\32\5\32\u01eb" +
					"\n\32\3\32\3\32\3\32\3\32\3\32\5\32\u01f2\n\32\3\33\3\33\3\33\3\33\3\33" +
					"\3\33\3\33\3\33\3\33\3\33\5\33\u01fe\n\33\3\33\3\33\5\33\u0202\n\33\3" +
					"\34\3\34\3\34\3\34\3\34\3\34\7\34\u020a\n\34\f\34\16\34\u020d\13\34\3" +
					"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0217\n\35\3\36\3\36\3\37" +
					"\3\37\3\37\3 \3 \3 \3 \3 \3 \7 \u0224\n \f \16 \u0227\13 \3!\3!\3!\3!" +
					"\3!\3!\7!\u022f\n!\f!\16!\u0232\13!\3\"\3\"\3\"\3\"\3\"\3\"\7\"\u023a" +
					"\n\"\f\"\16\"\u023d\13\"\3#\3#\3#\3#\3#\3#\7#\u0245\n#\f#\16#\u0248\13" +
					"#\3$\3$\3$\3$\3$\3$\7$\u0250\n$\f$\16$\u0253\13$\3%\3%\3%\3%\3%\3%\7%" +
					"\u025b\n%\f%\16%\u025e\13%\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\7\'\u0268\n\'" +
					"\f\'\16\'\u026b\13\'\3(\3(\3)\3)\3)\3)\3)\3)\7)\u0275\n)\f)\16)\u0278" +
					"\13)\3*\3*\3+\3+\3+\3+\3+\3+\7+\u0282\n+\f+\16+\u0285\13+\3,\3,\3-\3-" +
					"\3-\3-\3-\3-\7-\u028f\n-\f-\16-\u0292\13-\3.\3.\3/\3/\3/\3/\3/\3/\3/\3" +
					"/\3/\5/\u029f\n/\3\60\3\60\3\60\3\60\3\60\3\60\3\60\3\60\5\60\u02a9\n" +
					"\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3" +
					"\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u02c4" +
					"\n\61\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63" +
					"\3\63\3\63\3\63\3\63\5\63\u02d7\n\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63" +
					"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\7\63\u02e9\n\63\f\63\16" +
					"\63\u02ec\13\63\3\64\3\64\3\64\3\64\3\64\3\64\7\64\u02f4\n\64\f\64\16" +
					"\64\u02f7\13\64\3\65\3\65\3\65\3\65\3\65\3\65\3\65\6\65\u0300\n\65\r\65" +
					"\16\65\u0301\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\3\65\5\65\u030d\n" +
					"\65\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u0315\n\66\3\66\3\66\3\66\3\66" +
					"\3\66\5\66\u031c\n\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3\67\3\67" +
					"\3\67\3\67\5\67\u032a\n\67\3\67\2\5\32$d8\2\4\6\b\n\f\16\20\22\24\26\30" +
					"\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjl\2\t\4\2\f\25" +
					"\61\61\3\2\26\27\3\2\30\33\3\2\34\35\3\2\36\37\4\2 !\62\62\6\2\36\37$" +
					"%\62\62;;\u0356\2s\3\2\2\2\4\u0087\3\2\2\2\6\u0089\3\2\2\2\b\u0097\3\2" +
					"\2\2\n\u009f\3\2\2\2\f\u00b3\3\2\2\2\16\u00cd\3\2\2\2\20\u00d3\3\2\2\2" +
					"\22\u00d5\3\2\2\2\24\u00ea\3\2\2\2\26\u00f0\3\2\2\2\30\u00f3\3\2\2\2\32" +
					"\u0103\3\2\2\2\34\u0122\3\2\2\2\36\u0129\3\2\2\2 \u0141\3\2\2\2\"\u0151" +
					"\3\2\2\2$\u0168\3\2\2\2&\u0184\3\2\2\2(\u0199\3\2\2\2*\u01aa\3\2\2\2," +
					"\u01af\3\2\2\2.\u01b3\3\2\2\2\60\u01c2\3\2\2\2\62\u01f1\3\2\2\2\64\u0201" +
					"\3\2\2\2\66\u0203\3\2\2\28\u0216\3\2\2\2:\u0218\3\2\2\2<\u021a\3\2\2\2" +
					">\u021d\3\2\2\2@\u0228\3\2\2\2B\u0233\3\2\2\2D\u023e\3\2\2\2F\u0249\3" +
					"\2\2\2H\u0254\3\2\2\2J\u025f\3\2\2\2L\u0261\3\2\2\2N\u026c\3\2\2\2P\u026e" +
					"\3\2\2\2R\u0279\3\2\2\2T\u027b\3\2\2\2V\u0286\3\2\2\2X\u0288\3\2\2\2Z" +
					"\u0293\3\2\2\2\\\u029e\3\2\2\2^\u02a8\3\2\2\2`\u02c3\3\2\2\2b\u02c5\3" +
					"\2\2\2d\u02c7\3\2\2\2f\u02ed\3\2\2\2h\u030c\3\2\2\2j\u030e\3\2\2\2l\u0329" +
					"\3\2\2\2nr\5\6\4\2or\5\4\3\2pr\7)\2\2qn\3\2\2\2qo\3\2\2\2qp\3\2\2\2ru" +
					"\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3\2\2\2vw\7\2\2\3w\3\3\2\2\2" +
					"xy\7(\2\2yz\5\f\7\2z|\b\3\1\2{}\5\30\r\2|{\3\2\2\2|}\3\2\2\2}~\3\2\2\2" +
					"~\177\7)\2\2\177\u0088\3\2\2\2\u0080\u0081\5\f\7\2\u0081\u0083\b\3\1\2" +
					"\u0082\u0084\5\b\5\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085" +
					"\3\2\2\2\u0085\u0086\7)\2\2\u0086\u0088\3\2\2\2\u0087x\3\2\2\2\u0087\u0080" +
					"\3\2\2\2\u0088\5\3\2\2\2\u0089\u008a\5\f\7\2\u008a\u008b\b\4\1\2\u008b" +
					"\u008c\5\24\13\2\u008c\u0090\7+\2\2\u008d\u008e\5\34\17\2\u008e\u008f" +
					"\b\4\1\2\u008f\u0091\3\2\2\2\u0090\u008d\3\2\2\2\u0090\u0091\3\2\2\2\u0091" +
					"\u0092\3\2\2\2\u0092\u0093\7,\2\2\u0093\u0094\b\4\1\2\u0094\u0095\5.\30" +
					"\2\u0095\u0096\b\4\1\2\u0096\7\3\2\2\2\u0097\u009c\5\n\6\2\u0098\u0099" +
					"\7*\2\2\u0099\u009b\5\n\6\2\u009a\u0098\3\2\2\2\u009b\u009e\3\2\2\2\u009c" +
					"\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\t\3\2\2\2\u009e\u009c\3\2\2\2" +
					"\u009f\u00a0\5\24\13\2\u00a0\u00a5\b\6\1\2\u00a1\u00a2\7\61\2\2\u00a2" +
					"\u00a3\5(\25\2\u00a3\u00a4\b\6\1\2\u00a4\u00a6\3\2\2\2\u00a5\u00a1\3\2" +
					"\2\2\u00a5\u00a6\3\2\2\2\u00a6\13\3\2\2\2\u00a7\u00a8\7\3\2\2\u00a8\u00b4" +
					"\b\7\1\2\u00a9\u00aa\7\4\2\2\u00aa\u00b4\b\7\1\2\u00ab\u00ac\7\5\2\2\u00ac" +
					"\u00b4\b\7\1\2\u00ad\u00ae\5&\24\2\u00ae\u00af\b\7\1\2\u00af\u00b4\3\2" +
					"\2\2\u00b0\u00b1\5\16\b\2\u00b1\u00b2\b\7\1\2\u00b2\u00b4\3\2\2\2\u00b3" +
					"\u00a7\3\2\2\2\u00b3\u00a9\3\2\2\2\u00b3\u00ab\3\2\2\2\u00b3\u00ad\3\2" +
					"\2\2\u00b3\u00b0\3\2\2\2\u00b4\r\3\2\2\2\u00b5\u00b6\5\20\t\2\u00b6\u00b9" +
					"\b\b\1\2\u00b7\u00b8\7=\2\2\u00b8\u00ba\b\b\1\2\u00b9\u00b7\3\2\2\2\u00b9" +
					"\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\7/\2\2\u00bc\u00c2\b\b" +
					"\1\2\u00bd\u00be\5\22\n\2\u00be\u00bf\b\b\1\2\u00bf\u00c1\3\2\2\2\u00c0" +
					"\u00bd\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2" +
					"\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7\60\2\2\u00c6" +
					"\u00c7\b\b\1\2\u00c7\u00ce\3\2\2\2\u00c8\u00c9\5\20\t\2\u00c9\u00ca\b" +
					"\b\1\2\u00ca\u00cb\7=\2\2\u00cb\u00cc\b\b\1\2\u00cc\u00ce\3\2\2\2\u00cd" +
					"\u00b5\3\2\2\2\u00cd\u00c8\3\2\2\2\u00ce\17\3\2\2\2\u00cf\u00d0\7\6\2" +
					"\2\u00d0\u00d4\b\t\1\2\u00d1\u00d2\7\7\2\2\u00d2\u00d4\b\t\1\2\u00d3\u00cf" +
					"\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d4\21\3\2\2\2\u00d5\u00d6\5\f\7\2\u00d6" +
					"\u00e2\b\n\1\2\u00d7\u00d8\5\24\13\2\u00d8\u00df\b\n\1\2\u00d9\u00da\7" +
					"*\2\2\u00da\u00db\5\24\13\2\u00db\u00dc\b\n\1\2\u00dc\u00de\3\2\2\2\u00dd" +
					"\u00d9\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2" +
					"\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00d7\3\2\2\2\u00e2" +
					"\u00e3\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\7)\2\2\u00e5\23\3\2\2\2" +
					"\u00e6\u00e7\7\62\2\2\u00e7\u00e9\b\13\1\2\u00e8\u00e6\3\2\2\2\u00e9\u00ec" +
					"\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec" +
					"\u00ea\3\2\2\2\u00ed\u00ee\5\32\16\2\u00ee\u00ef\b\13\1\2\u00ef\25\3\2" +
					"\2\2\u00f0\u00f1\5\24\13\2\u00f1\u00f2\b\f\1\2\u00f2\27\3\2\2\2\u00f3" +
					"\u00f8\5\26\f\2\u00f4\u00f5\7*\2\2\u00f5\u00f7\5\26\f\2\u00f6\u00f4\3" +
					"\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9" +
					"\31\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fc\b\16\1\2\u00fc\u00fd\7=\2" +
					"\2\u00fd\u0104\b\16\1\2\u00fe\u00ff\7+\2\2\u00ff\u0100\5\24\13\2\u0100" +
					"\u0101\7,\2\2\u0101\u0102\b\16\1\2\u0102\u0104\3\2\2\2\u0103\u00fb\3\2" +
					"\2\2\u0103\u00fe\3\2\2\2\u0104\u011f\3\2\2\2\u0105\u0106\f\6\2\2\u0106" +
					"\u0107\b\16\1\2\u0107\u0108\7-\2\2\u0108\u0109\5<\37\2\u0109\u010a\7." +
					"\2\2\u010a\u010b\b\16\1\2\u010b\u011e\3\2\2\2\u010c\u010d\f\5\2\2\u010d" +
					"\u010e\b\16\1\2\u010e\u010f\7-\2\2\u010f\u0110\7.\2\2\u0110\u011e\b\16" +
					"\1\2\u0111\u0112\f\4\2\2\u0112\u0113\b\16\1\2\u0113\u0114\7+\2\2\u0114" +
					"\u0115\7,\2\2\u0115\u011e\b\16\1\2\u0116\u0117\f\3\2\2\u0117\u0118\b\16" +
					"\1\2\u0118\u0119\7+\2\2\u0119\u011a\5\34\17\2\u011a\u011b\7,\2\2\u011b" +
					"\u011c\b\16\1\2\u011c\u011e\3\2\2\2\u011d\u0105\3\2\2\2\u011d\u010c\3" +
					"\2\2\2\u011d\u0111\3\2\2\2\u011d\u0116\3\2\2\2\u011e\u0121\3\2\2\2\u011f" +
					"\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\33\3\2\2\2\u0121\u011f\3\2\2" +
					"\2\u0122\u0123\5\36\20\2\u0123\u0127\b\17\1\2\u0124\u0125\7*\2\2\u0125" +
					"\u0126\7\b\2\2\u0126\u0128\b\17\1\2\u0127\u0124\3\2\2\2\u0127\u0128\3" +
					"\2\2\2\u0128\35\3\2\2\2\u0129\u012a\5 \21\2\u012a\u0131\b\20\1\2\u012b" +
					"\u012c\7*\2\2\u012c\u012d\5 \21\2\u012d\u012e\b\20\1\2\u012e\u0130\3\2" +
					"\2\2\u012f\u012b\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0131" +
					"\u0132\3\2\2\2\u0132\37\3\2\2\2\u0133\u0131\3\2\2\2\u0134\u0135\5\f\7" +
					"\2\u0135\u0136\b\21\1\2\u0136\u0142\3\2\2\2\u0137\u0138\5\f\7\2\u0138" +
					"\u0139\b\21\1\2\u0139\u013a\5\24\13\2\u013a\u013b\b\21\1\2\u013b\u0142" +
					"\3\2\2\2\u013c\u013d\5\f\7\2\u013d\u013e\b\21\1\2\u013e\u013f\5\"\22\2" +
					"\u013f\u0140\b\21\1\2\u0140\u0142\3\2\2\2\u0141\u0134\3\2\2\2\u0141\u0137" +
					"\3\2\2\2\u0141\u013c\3\2\2\2\u0142!\3\2\2\2\u0143\u0144\7\62\2\2\u0144" +
					"\u0146\b\22\1\2\u0145\u0143\3\2\2\2\u0146\u0147\3\2\2\2\u0147\u0145\3" +
					"\2\2\2\u0147\u0148\3\2\2\2\u0148\u0152\3\2\2\2\u0149\u014a\7\62\2\2\u014a" +
					"\u014c\b\22\1\2\u014b\u0149\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3" +
					"\2\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150" +
					"\u0152\5$\23\2\u0151\u0145\3\2\2\2\u0151\u014d\3\2\2\2\u0152#\3\2\2\2" +
					"\u0153\u0154\b\23\1\2\u0154\u0155\7+\2\2\u0155\u0156\5\"\22\2\u0156\u0157" +
					"\7,\2\2\u0157\u0169\3\2\2\2\u0158\u0159\7-\2\2\u0159\u015a\7.\2\2\u015a" +
					"\u0169\b\23\1\2\u015b\u015c\7-\2\2\u015c\u015d\5<\37\2\u015d\u015e\7." +
					"\2\2\u015e\u015f\b\23\1\2\u015f\u0169\3\2\2\2\u0160\u0161\7+\2\2\u0161" +
					"\u0162\7,\2\2\u0162\u0169\b\23\1\2\u0163\u0164\7+\2\2\u0164\u0165\5\34" +
					"\17\2\u0165\u0166\7,\2\2\u0166\u0167\b\23\1\2\u0167\u0169\3\2\2\2\u0168" +
					"\u0153\3\2\2\2\u0168\u0158\3\2\2\2\u0168\u015b\3\2\2\2\u0168\u0160\3\2" +
					"\2\2\u0168\u0163\3\2\2\2\u0169\u0181\3\2\2\2\u016a\u016b\f\6\2\2\u016b" +
					"\u016c\7-\2\2\u016c\u016d\7.\2\2\u016d\u0180\b\23\1\2\u016e\u016f\f\5" +
					"\2\2\u016f\u0170\7-\2\2\u0170\u0171\5<\37\2\u0171\u0172\7.\2\2\u0172\u0173" +
					"\b\23\1\2\u0173\u0180\3\2\2\2\u0174\u0175\f\4\2\2\u0175\u0176\7+\2\2\u0176" +
					"\u0177\7,\2\2\u0177\u0180\b\23\1\2\u0178\u0179\f\3\2\2\u0179\u017b\7+" +
					"\2\2\u017a\u017c\5\34\17\2\u017b\u017a\3\2\2\2\u017b\u017c\3\2\2\2\u017c" +
					"\u017d\3\2\2\2\u017d\u017e\7,\2\2\u017e\u0180\b\23\1\2\u017f\u016a\3\2" +
					"\2\2\u017f\u016e\3\2\2\2\u017f\u0174\3\2\2\2\u017f\u0178\3\2\2\2\u0180" +
					"\u0183\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182%\3\2\2\2" +
					"\u0183\u0181\3\2\2\2\u0184\u0185\6\24\n\2\u0185\u0186\7=\2\2\u0186\u0187" +
					"\b\24\1\2\u0187\'\3\2\2\2\u0188\u0189\58\35\2\u0189\u018a\b\25\1\2\u018a" +
					"\u019a\3\2\2\2\u018b\u018c\7/\2\2\u018c\u018d\5(\25\2\u018d\u0194\b\25" +
					"\1\2\u018e\u018f\7*\2\2\u018f\u0190\5(\25\2\u0190\u0191\b\25\1\2\u0191" +
					"\u0193\3\2\2\2\u0192\u018e\3\2\2\2\u0193\u0196\3\2\2\2\u0194\u0192\3\2" +
					"\2\2\u0194\u0195\3\2\2\2\u0195\u0197\3\2\2\2\u0196\u0194\3\2\2\2\u0197" +
					"\u0198\7\60\2\2\u0198\u019a\3\2\2\2\u0199\u0188\3\2\2\2\u0199\u018b\3" +
					"\2\2\2\u019a)\3\2\2\2\u019b\u019c\5,\27\2\u019c\u019d\b\26\1\2\u019d\u01ab" +
					"\3\2\2\2\u019e\u019f\5.\30\2\u019f\u01a0\b\26\1\2\u01a0\u01ab\3\2\2\2" +
					"\u01a1\u01a2\5\60\31\2\u01a2\u01a3\b\26\1\2\u01a3\u01ab\3\2\2\2\u01a4" +
					"\u01a5\5\62\32\2\u01a5\u01a6\b\26\1\2\u01a6\u01ab\3\2\2\2\u01a7\u01a8" +
					"\5\64\33\2\u01a8\u01a9\b\26\1\2\u01a9\u01ab\3\2\2\2\u01aa\u019b\3\2\2" +
					"\2\u01aa\u019e\3\2\2\2\u01aa\u01a1\3\2\2\2\u01aa\u01a4\3\2\2\2\u01aa\u01a7" +
					"\3\2\2\2\u01ab+\3\2\2\2\u01ac\u01ad\5\66\34\2\u01ad\u01ae\b\27\1\2\u01ae" +
					"\u01b0\3\2\2\2\u01af\u01ac\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b1\3\2" +
					"\2\2\u01b1\u01b2\7)\2\2\u01b2-\3\2\2\2\u01b3\u01b4\7/\2\2\u01b4\u01bc" +
					"\b\30\1\2\u01b5\u01bb\5\4\3\2\u01b6\u01b7\5*\26\2\u01b7\u01b8\b\30\1\2" +
					"\u01b8\u01bb\3\2\2\2\u01b9\u01bb\5\6\4\2\u01ba\u01b5\3\2\2\2\u01ba\u01b6" +
					"\3\2\2\2\u01ba\u01b9\3\2\2\2\u01bb\u01be\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bc" +
					"\u01bd\3\2\2\2\u01bd\u01bf\3\2\2\2\u01be\u01bc\3\2\2\2\u01bf\u01c0\7\60" +
					"\2\2\u01c0\u01c1\b\30\1\2\u01c1/\3\2\2\2\u01c2\u01c3\7\63\2\2\u01c3\u01c4" +
					"\7+\2\2\u01c4\u01c5\5\66\34\2\u01c5\u01c6\b\31\1\2\u01c6\u01c7\7,\2\2" +
					"\u01c7\u01c8\5*\26\2\u01c8\u01cd\b\31\1\2\u01c9\u01ca\7\64\2\2\u01ca\u01cb" +
					"\5*\26\2\u01cb\u01cc\b\31\1\2\u01cc\u01ce\3\2\2\2\u01cd\u01c9\3\2\2\2" +
					"\u01cd\u01ce\3\2\2\2\u01ce\u01cf\3\2\2\2\u01cf\u01d0\b\31\1\2\u01d0\61" +
					"\3\2\2\2\u01d1\u01d2\7\65\2\2\u01d2\u01d3\7+\2\2\u01d3\u01d4\5\66\34\2" +
					"\u01d4\u01d5\7,\2\2\u01d5\u01d6\b\32\1\2\u01d6\u01d7\5*\26\2\u01d7\u01d8" +
					"\b\32\1\2\u01d8\u01f2\3\2\2\2\u01d9\u01da\7\66\2\2\u01da\u01de\7+\2\2" +
					"\u01db\u01dc\5\66\34\2\u01dc\u01dd\b\32\1\2\u01dd\u01df\3\2\2\2\u01de" +
					"\u01db\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e4\7)" +
					"\2\2\u01e1\u01e2\5\66\34\2\u01e2\u01e3\b\32\1\2\u01e3\u01e5\3\2\2\2\u01e4" +
					"\u01e1\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01ea\7)" +
					"\2\2\u01e7\u01e8\5\66\34\2\u01e8\u01e9\b\32\1\2\u01e9\u01eb\3\2\2\2\u01ea" +
					"\u01e7\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed\7," +
					"\2\2\u01ed\u01ee\b\32\1\2\u01ee\u01ef\5*\26\2\u01ef\u01f0\b\32\1\2\u01f0" +
					"\u01f2\3\2\2\2\u01f1\u01d1\3\2\2\2\u01f1\u01d9\3\2\2\2\u01f2\63\3\2\2" +
					"\2\u01f3\u01f4\7\t\2\2\u01f4\u01f5\7)\2\2\u01f5\u0202\b\33\1\2\u01f6\u01f7" +
					"\7\n\2\2\u01f7\u01f8\7)\2\2\u01f8\u0202\b\33\1\2\u01f9\u01fd\7\13\2\2" +
					"\u01fa\u01fb\5\66\34\2\u01fb\u01fc\b\33\1\2\u01fc\u01fe\3\2\2\2\u01fd" +
					"\u01fa\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0200\7)" +
					"\2\2\u0200\u0202\b\33\1\2\u0201\u01f3\3\2\2\2\u0201\u01f6\3\2\2\2\u0201" +
					"\u01f9\3\2\2\2\u0202\65\3\2\2\2\u0203\u0204\58\35\2\u0204\u020b\b\34\1" +
					"\2\u0205\u0206\7*\2\2\u0206\u0207\58\35\2\u0207\u0208\b\34\1\2\u0208\u020a" +
					"\3\2\2\2\u0209\u0205\3\2\2\2\u020a\u020d\3\2\2\2\u020b\u0209\3\2\2\2\u020b" +
					"\u020c\3\2\2\2\u020c\67\3\2\2\2\u020d\u020b\3\2\2\2\u020e\u020f\5> \2" +
					"\u020f\u0210\b\35\1\2\u0210\u0217\3\2\2\2\u0211\u0212\5`\61\2\u0212\u0213" +
					"\5:\36\2\u0213\u0214\58\35\2\u0214\u0215\b\35\1\2\u0215\u0217\3\2\2\2" +
					"\u0216\u020e\3\2\2\2\u0216\u0211\3\2\2\2\u02179\3\2\2\2\u0218\u0219\t" +
					"\2\2\2\u0219;\3\2\2\2\u021a\u021b\5> \2\u021b\u021c\b\37\1\2\u021c=\3" +
					"\2\2\2\u021d\u021e\5@!\2\u021e\u0225\b \1\2\u021f\u0220\7\67\2\2\u0220" +
					"\u0221\5@!\2\u0221\u0222\b \1\2\u0222\u0224\3\2\2\2\u0223\u021f\3\2\2" +
					"\2\u0224\u0227\3\2\2\2\u0225\u0223\3\2\2\2\u0225\u0226\3\2\2\2\u0226?" +
					"\3\2\2\2\u0227\u0225\3\2\2\2\u0228\u0229\5B\"\2\u0229\u0230\b!\1\2\u022a" +
					"\u022b\78\2\2\u022b\u022c\5B\"\2\u022c\u022d\b!\1\2\u022d\u022f\3\2\2" +
					"\2\u022e\u022a\3\2\2\2\u022f\u0232\3\2\2\2\u0230\u022e\3\2\2\2\u0230\u0231" +
					"\3\2\2\2\u0231A\3\2\2\2\u0232\u0230\3\2\2\2\u0233\u0234\5D#\2\u0234\u023b" +
					"\b\"\1\2\u0235\u0236\79\2\2\u0236\u0237\5D#\2\u0237\u0238\b\"\1\2\u0238" +
					"\u023a\3\2\2\2\u0239\u0235\3\2\2\2\u023a\u023d\3\2\2\2\u023b\u0239\3\2" +
					"\2\2\u023b\u023c\3\2\2\2\u023cC\3\2\2\2\u023d\u023b\3\2\2\2\u023e\u023f" +
					"\5F$\2\u023f\u0246\b#\1\2\u0240\u0241\7:\2\2\u0241\u0242\5F$\2\u0242\u0243" +
					"\b#\1\2\u0243\u0245\3\2\2\2\u0244\u0240\3\2\2\2\u0245\u0248\3\2\2\2\u0246" +
					"\u0244\3\2\2\2\u0246\u0247\3\2\2\2\u0247E\3\2\2\2\u0248\u0246\3\2\2\2" +
					"\u0249\u024a\5H%\2\u024a\u0251\b$\1\2\u024b\u024c\7;\2\2\u024c\u024d\5" +
					"H%\2\u024d\u024e\b$\1\2\u024e\u0250\3\2\2\2\u024f\u024b\3\2\2\2\u0250" +
					"\u0253\3\2\2\2\u0251\u024f\3\2\2\2\u0251\u0252\3\2\2\2\u0252G\3\2\2\2" +
					"\u0253\u0251\3\2\2\2\u0254\u0255\5L\'\2\u0255\u025c\b%\1\2\u0256\u0257" +
					"\5J&\2\u0257\u0258\5L\'\2\u0258\u0259\b%\1\2\u0259\u025b\3\2\2\2\u025a" +
					"\u0256\3\2\2\2\u025b\u025e\3\2\2\2\u025c\u025a\3\2\2\2\u025c\u025d\3\2" +
					"\2\2\u025dI\3\2\2\2\u025e\u025c\3\2\2\2\u025f\u0260\t\3\2\2\u0260K\3\2" +
					"\2\2\u0261\u0262\5P)\2\u0262\u0269\b\'\1\2\u0263\u0264\5N(\2\u0264\u0265" +
					"\5P)\2\u0265\u0266\b\'\1\2\u0266\u0268\3\2\2\2\u0267\u0263\3\2\2\2\u0268" +
					"\u026b\3\2\2\2\u0269\u0267\3\2\2\2\u0269\u026a\3\2\2\2\u026aM\3\2\2\2" +
					"\u026b\u0269\3\2\2\2\u026c\u026d\t\4\2\2\u026dO\3\2\2\2\u026e\u026f\5" +
					"T+\2\u026f\u0276\b)\1\2\u0270\u0271\5R*\2\u0271\u0272\5T+\2\u0272\u0273" +
					"\b)\1\2\u0273\u0275\3\2\2\2\u0274\u0270\3\2\2\2\u0275\u0278\3\2\2\2\u0276" +
					"\u0274\3\2\2\2\u0276\u0277\3\2\2\2\u0277Q\3\2\2\2\u0278\u0276\3\2\2\2" +
					"\u0279\u027a\t\5\2\2\u027aS\3\2\2\2\u027b\u027c\5X-\2\u027c\u0283\b+\1" +
					"\2\u027d\u027e\5V,\2\u027e\u027f\5X-\2\u027f\u0280\b+\1\2\u0280\u0282" +
					"\3\2\2\2\u0281\u027d\3\2\2\2\u0282\u0285\3\2\2\2\u0283\u0281\3\2\2\2\u0283" +
					"\u0284\3\2\2\2\u0284U\3\2\2\2\u0285\u0283\3\2\2\2\u0286\u0287\t\6\2\2" +
					"\u0287W\3\2\2\2\u0288\u0289\5\\/\2\u0289\u0290\b-\1\2\u028a\u028b\5Z." +
					"\2\u028b\u028c\5\\/\2\u028c\u028d\b-\1\2\u028d\u028f\3\2\2\2\u028e\u028a" +
					"\3\2\2\2\u028f\u0292\3\2\2\2\u0290\u028e\3\2\2\2\u0290\u0291\3\2\2\2\u0291" +
					"Y\3\2\2\2\u0292\u0290\3\2\2\2\u0293\u0294\t\7\2\2\u0294[\3\2\2\2\u0295" +
					"\u0296\5`\61\2\u0296\u0297\b/\1\2\u0297\u029f\3\2\2\2\u0298\u0299\7+\2" +
					"\2\u0299\u029a\5^\60\2\u029a\u029b\7,\2\2\u029b\u029c\5\\/\2\u029c\u029d" +
					"\b/\1\2\u029d\u029f\3\2\2\2\u029e\u0295\3\2\2\2\u029e\u0298\3\2\2\2\u029f" +
					"]\3\2\2\2\u02a0\u02a1\5\f\7\2\u02a1\u02a2\b\60\1\2\u02a2\u02a9\3\2\2\2" +
					"\u02a3\u02a4\5\f\7\2\u02a4\u02a5\b\60\1\2\u02a5\u02a6\5\"\22\2\u02a6\u02a7" +
					"\b\60\1\2\u02a7\u02a9\3\2\2\2\u02a8\u02a0\3\2\2\2\u02a8\u02a3\3\2\2\2" +
					"\u02a9_\3\2\2\2\u02aa\u02ab\5d\63\2\u02ab\u02ac\b\61\1\2\u02ac\u02c4\3" +
					"\2\2\2\u02ad\u02ae\7\"\2\2\u02ae\u02af\5`\61\2\u02af\u02b0\b\61\1\2\u02b0" +
					"\u02c4\3\2\2\2\u02b1\u02b2\7#\2\2\u02b2\u02b3\5`\61\2\u02b3\u02b4\b\61" +
					"\1\2\u02b4\u02c4\3\2\2\2\u02b5\u02b6\5b\62\2\u02b6\u02b7\5\\/\2\u02b7" +
					"\u02b8\b\61\1\2\u02b8\u02c4\3\2\2\2\u02b9\u02ba\7<\2\2\u02ba\u02bb\5`" +
					"\61\2\u02bb\u02bc\b\61\1\2\u02bc\u02c4\3\2\2\2\u02bd\u02be\7<\2\2\u02be" +
					"\u02bf\7+\2\2\u02bf\u02c0\5^\60\2\u02c0\u02c1\7,\2\2\u02c1\u02c2\b\61" +
					"\1\2\u02c2\u02c4\3\2\2\2\u02c3\u02aa\3\2\2\2\u02c3\u02ad\3\2\2\2\u02c3" +
					"\u02b1\3\2\2\2\u02c3\u02b5\3\2\2\2\u02c3\u02b9\3\2\2\2\u02c3\u02bd\3\2" +
					"\2\2\u02c4a\3\2\2\2\u02c5\u02c6\t\b\2\2\u02c6c\3\2\2\2\u02c7\u02c8\b\63" +
					"\1\2\u02c8\u02c9\5h\65\2\u02c9\u02ca\b\63\1\2\u02ca\u02ea\3\2\2\2\u02cb" +
					"\u02cc\f\b\2\2\u02cc\u02cd\7-\2\2\u02cd\u02ce\5\66\34\2\u02ce\u02cf\7" +
					".\2\2\u02cf\u02d0\b\63\1\2\u02d0\u02e9\3\2\2\2\u02d1\u02d2\f\7\2\2\u02d2" +
					"\u02d6\7+\2\2\u02d3\u02d4\5f\64\2\u02d4\u02d5\b\63\1\2\u02d5\u02d7\3\2" +
					"\2\2\u02d6\u02d3\3\2\2\2\u02d6\u02d7\3\2\2\2\u02d7\u02d8\3\2\2\2\u02d8" +
					"\u02d9\7,\2\2\u02d9\u02e9\b\63\1\2\u02da\u02db\f\6\2\2\u02db\u02dc\7&" +
					"\2\2\u02dc\u02dd\7=\2\2\u02dd\u02e9\b\63\1\2\u02de\u02df\f\5\2\2\u02df" +
					"\u02e0\7\'\2\2\u02e0\u02e1\7=\2\2\u02e1\u02e9\b\63\1\2\u02e2\u02e3\f\4" +
					"\2\2\u02e3\u02e4\7\"\2\2\u02e4\u02e9\b\63\1\2\u02e5\u02e6\f\3\2\2\u02e6" +
					"\u02e7\7#\2\2\u02e7\u02e9\b\63\1\2\u02e8\u02cb\3\2\2\2\u02e8\u02d1\3\2" +
					"\2\2\u02e8\u02da\3\2\2\2\u02e8\u02de\3\2\2\2\u02e8\u02e2\3\2\2\2\u02e8" +
					"\u02e5\3\2\2\2\u02e9\u02ec\3\2\2\2\u02ea\u02e8\3\2\2\2\u02ea\u02eb\3\2" +
					"\2\2\u02ebe\3\2\2\2\u02ec\u02ea\3\2\2\2\u02ed\u02ee\58\35\2\u02ee\u02f5" +
					"\b\64\1\2\u02ef\u02f0\7*\2\2\u02f0\u02f1\58\35\2\u02f1\u02f2\b\64\1\2" +
					"\u02f2\u02f4\3\2\2\2\u02f3\u02ef\3\2\2\2\u02f4\u02f7\3\2\2\2\u02f5\u02f3" +
					"\3\2\2\2\u02f5\u02f6\3\2\2\2\u02f6g\3\2\2\2\u02f7\u02f5\3\2\2\2\u02f8" +
					"\u02f9\7=\2\2\u02f9\u030d\b\65\1\2\u02fa\u02fb\5l\67\2\u02fb\u02fc\b\65" +
					"\1\2\u02fc\u030d\3\2\2\2\u02fd\u02fe\7B\2\2\u02fe\u0300\b\65\1\2\u02ff" +
					"\u02fd\3\2\2\2\u0300\u0301\3\2\2\2\u0301\u02ff\3\2\2\2\u0301\u0302\3\2" +
					"\2\2\u0302\u0303\3\2\2\2\u0303\u030d\b\65\1\2\u0304\u0305\7+\2\2\u0305" +
					"\u0306\5\66\34\2\u0306\u0307\7,\2\2\u0307\u0308\b\65\1\2\u0308\u030d\3" +
					"\2\2\2\u0309\u030a\5j\66\2\u030a\u030b\b\65\1\2\u030b\u030d\3\2\2\2\u030c" +
					"\u02f8\3\2\2\2\u030c\u02fa\3\2\2\2\u030c\u02ff\3\2\2\2\u030c\u0304\3\2" +
					"\2\2\u030c\u0309\3\2\2\2\u030di\3\2\2\2\u030e\u030f\7-\2\2\u030f\u0310" +
					"\7.\2\2\u0310\u0314\7+\2\2\u0311\u0312\5\34\17\2\u0312\u0313\b\66\1\2" +
					"\u0313\u0315\3\2\2\2\u0314\u0311\3\2\2\2\u0314\u0315\3\2\2\2\u0315\u0316" +
					"\3\2\2\2\u0316\u031b\7,\2\2\u0317\u0318\7\'\2\2\u0318\u0319\5^\60\2\u0319" +
					"\u031a\b\66\1\2\u031a\u031c\3\2\2\2\u031b\u0317\3\2\2\2\u031b\u031c\3" +
					"\2\2\2\u031c\u031d\3\2\2\2\u031d\u031e\b\66\1\2\u031e\u031f\5.\30\2\u031f" +
					"\u0320\b\66\1\2\u0320k\3\2\2\2\u0321\u0322\7>\2\2\u0322\u032a\b\67\1\2" +
					"\u0323\u0324\7?\2\2\u0324\u032a\b\67\1\2\u0325\u0326\7@\2\2\u0326\u032a" +
					"\b\67\1\2\u0327\u0328\7A\2\2\u0328\u032a\b\67\1\2\u0329\u0321\3\2\2\2" +
					"\u0329\u0323\3\2\2\2\u0329\u0325\3\2\2\2\u0329\u0327\3\2\2\2\u032am\3" +
					"\2\2\2Eqs|\u0083\u0087\u0090\u009c\u00a5\u00b3\u00b9\u00c2\u00cd\u00d3" +
					"\u00df\u00e2\u00ea\u00f8\u0103\u011d\u011f\u0127\u0131\u0141\u0147\u014d" +
					"\u0151\u0168\u017b\u017f\u0181\u0194\u0199\u01aa\u01af\u01ba\u01bc\u01cd" +
					"\u01de\u01e4\u01ea\u01f1\u01fd\u0201\u020b\u0216\u0225\u0230\u023b\u0246" +
					"\u0251\u025c\u0269\u0276\u0283\u0290\u029e\u02a8\u02c3\u02d6\u02e8\u02ea" +
					"\u02f5\u0301\u030c\u0314\u031b\u0329";
	public static final ATN _ATN =
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
			new PredictionContextCache();
	private static final String[] _LITERAL_NAMES = {
			null, "'void'", "'char'", "'int'", "'struct'", "'union'", "'...'", "'continue'",
			"'break'", "'return'", "'*='", "'/='", "'%='", "'+='", "'-='", "'<<='",
			"'>>='", "'&='", "'^='", "'|='", "'=='", "'!='", "'<'", "'>'", "'<='",
			"'>='", "'<<'", "'>>'", "'+'", "'-'", "'/'", "'%'", "'++'", "'--'", "'~'",
			"'!'", "'.'", "'->'", "'typedef'", "';'", "','", "'('", "')'", "'['",
			"']'", "'{'", "'}'", "'='", "'*'", "'if'", "'else'", "'while'", "'for'",
		"'||'", "'&&'", "'|'", "'^'", "'&'", "'sizeof'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null, null,
			null, null, "Typedef", "Semi", "Comma", "L1", "R1", "L2", "R2", "L3",
			"R3", "EQ", "STAR", "If", "Else", "While", "For", "OrOr", "AndAnd", "Or",
			"Caret", "And", "SizeOf", "Identifier", "DecimalConstant", "OctalConstant",
			"HexadecimalConstant", "CharacterConstant", "StringLiteral", "Preprocessing",
		"Whitespace", "Newline", "BlockComment", "LineComment"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	static {
		RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

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
	@NotNull
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
		return _ATN; }

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(111);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1: {
						setState(108);
						functionDefinition();
						}
						break;
					case 2: {
						setState(109);
						declaration();
						}
						break;
					case 3: {
						setState(110);
						match(Semi);
						}
						break;
					}
					}
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
				setState(116);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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

		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(135);
				((FunctionDefinitionContext) _localctx).typeSpecifier = typeSpecifier();
				TypeAnalyser.enter(((FunctionDefinitionContext) _localctx).typeSpecifier.ret);
				setState(137);
				((FunctionDefinitionContext) _localctx).declarator = declarator();
				setState(138);
				match(L1);
				setState(142);
				switch (getInterpreter().adaptivePredict(_input, 5, _ctx)) {
					case 1: {
						setState(139);
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
				setState(144);
				match(R1);

				TypeAnalyser.addParameter(_localctx.parameterTypes, _localctx.parameterNames, _localctx.hasVaList);
				((FunctionDefinitionContext) _localctx).type = (FunctionType) TypeAnalyser.analyse(true);
				((FunctionDefinitionContext) _localctx).name = ((FunctionDefinitionContext) _localctx).declarator.name;

				if (!Environment.isCompleteType(_localctx.type))
					throw new CompilationError("Incomplete type.");

				Environment.functionReturnStack.push(_localctx.type.returnType);
				if (Environment.symbolNames.currentScope == 1)
					((FunctionDefinitionContext) _localctx).uId = Environment.symbolNames.defineVariable(_localctx.name, _localctx.type);
				else
					((FunctionDefinitionContext) _localctx).uId = Environment.symbolNames.defineLocalFunction(_localctx.name, _localctx.type);

				((FunctionDefinitionContext) _localctx).parameterTypes = _localctx.type.parameterTypes;
				((FunctionDefinitionContext) _localctx).parameterNames = _localctx.type.parameterNames;

				setState(146);
				((FunctionDefinitionContext) _localctx).compoundStatement = compoundStatement(_localctx.parameterTypes, _localctx.parameterNames);

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
				setState(149);
			initDeclarator();
			setState(154);
				_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
					{
						setState(150);
				match(Comma);
						setState(151);
				initDeclarator();
					}
				}
				setState(156);
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
				setState(157);
				((InitDeclaratorContext) _localctx).declarator = declarator();

				((InitDeclaratorContext) _localctx).type = TypeAnalyser.analyse();
				((InitDeclaratorContext) _localctx).name = ((InitDeclaratorContext) _localctx).declarator.name;
				((InitDeclaratorContext) _localctx).uId = Environment.symbolNames.defineVariable(_localctx.name, _localctx.type);

				setState(163);
				_la = _input.LA(1);
				if (_la == EQ) {
					{
						setState(159);
						match(EQ);
						setState(160);
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
			setState(177);
			switch (getInterpreter().adaptivePredict(_input, 8, _ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
			{
				setState(165);
				match(T__0);
				((TypeSpecifierContext) _localctx).ret = VoidType.instance;
				}
				break;
				case 2:
					enterOuterAlt(_localctx, 2);
				{
					setState(167);
					match(T__1);
					((TypeSpecifierContext) _localctx).ret = CharType.instance;
				}
				break;
				case 3:
					enterOuterAlt(_localctx, 3);
				{
					setState(169);
					match(T__2);
					((TypeSpecifierContext) _localctx).ret = IntType.instance;
				}
				break;
				case 4:
					enterOuterAlt(_localctx, 4);
				{
					setState(171);
					((TypeSpecifierContext) _localctx).typedefName = typedefName();
					((TypeSpecifierContext) _localctx).ret = ((TypeSpecifierContext) _localctx).typedefName.ret;
				}
				break;
				case 5:
					enterOuterAlt(_localctx, 5);
				{
					setState(174);
					((TypeSpecifierContext) _localctx).structOrUnionSpecifier = structOrUnionSpecifier();
					((TypeSpecifierContext) _localctx).ret = ((TypeSpecifierContext) _localctx).structOrUnionSpecifier.ret;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			setState(203);
			switch (getInterpreter().adaptivePredict(_input, 11, _ctx)) {
				case 1:
					_localctx = new StructOrUnionSpecifier1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(179);
					((StructOrUnionSpecifier1Context) _localctx).structOrUnion = structOrUnion();

					((StructOrUnionSpecifier1Context) _localctx).isUnion = ((StructOrUnionSpecifier1Context) _localctx).structOrUnion.isUnion;

					setState(183);
					_la = _input.LA(1);
					if (_la == Identifier) {
						{
							setState(181);
							((StructOrUnionSpecifier1Context) _localctx).Identifier = match(Identifier);
							((StructOrUnionSpecifier1Context) _localctx).name = (((StructOrUnionSpecifier1Context) _localctx).Identifier != null ? ((StructOrUnionSpecifier1Context) _localctx).Identifier.getText() : null);
						}
					}

					setState(185);
					match(L3);
					StructBuilder.enter(_localctx.name, _localctx.isUnion);
					setState(192);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 10, _ctx);
					while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
						if (_alt == 1) {
							{
								{
									setState(187);
									((StructOrUnionSpecifier1Context) _localctx).structDeclaration = structDeclaration();

									StructBuilder.addAttributes(((StructOrUnionSpecifier1Context) _localctx).structDeclaration.types, ((StructOrUnionSpecifier1Context) _localctx).structDeclaration.names);

								}
							}
						}
						setState(194);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 10, _ctx);
					}
					setState(195);
					match(R3);
					((StructOrUnionSpecifier1Context) _localctx).ret = StructBuilder.exit();
				}
				break;
				case 2:
					_localctx = new StructOrUnionSpecifier2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(198);
					((StructOrUnionSpecifier2Context) _localctx).structOrUnion = structOrUnion();
					((StructOrUnionSpecifier2Context) _localctx).isUnion = ((StructOrUnionSpecifier2Context) _localctx).structOrUnion.isUnion;
					setState(200);
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
			setState(209);
			switch (_input.LA(1)) {
				case T__3:
					enterOuterAlt(_localctx, 1);
				{
					setState(205);
					match(T__3);
					((StructOrUnionContext) _localctx).isUnion = false;
				}
				break;
				case T__4:
					enterOuterAlt(_localctx, 2);
				{
					setState(207);
					match(T__4);
					((StructOrUnionContext) _localctx).isUnion = true;
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(211);
				((StructDeclarationContext) _localctx).typeSpecifier = typeSpecifier();
				TypeAnalyser.enter(((StructDeclarationContext) _localctx).typeSpecifier.ret);
				setState(224);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << L1) | (1L << STAR) | (1L << Identifier))) != 0)) {
					{
						setState(213);
						((StructDeclarationContext) _localctx).d1 = declarator();

						_localctx.types.add(TypeAnalyser.analyse());
						_localctx.names.add(((StructDeclarationContext) _localctx).d1.name);

						setState(221);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la == Comma) {
							{
								{
									setState(215);
									match(Comma);
									setState(216);
									((StructDeclarationContext) _localctx).d2 = declarator();

									_localctx.types.add(TypeAnalyser.analyse());
									_localctx.names.add(((StructDeclarationContext) _localctx).d2.name);

								}
							}
							setState(223);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
					}
				}

				setState(226);
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
				setState(232);
				_errHandler.sync(this);
			_la = _input.LA(1);
				while (_la == STAR) {
					{
						{
							setState(228);
							match(STAR);
							++_localctx.n;
						}
					}
					setState(234);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(235);
				((DeclaratorContext) _localctx).directDeclarator = directDeclarator(0);
				((DeclaratorContext) _localctx).name = ((DeclaratorContext) _localctx).directDeclarator.name;
			}

			for (int i = 0; i < _localctx.n; ++i)
				TypeAnalyser.addStar();

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(238);
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
				setState(241);
				plainDeclarator();
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Comma) {
				{
					{
						setState(242);
						match(Comma);
						setState(243);
						plainDeclarator();
				}
				}
					setState(248);
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
				setState(257);
				switch (_input.LA(1)) {
					case Identifier: {
						setState(250);
						((DirectDeclaratorContext) _localctx).Identifier = match(Identifier);
						((DirectDeclaratorContext) _localctx).name = (((DirectDeclaratorContext) _localctx).Identifier != null ? ((DirectDeclaratorContext) _localctx).Identifier.getText() :null);
				}
				break;
					case L1: {
						setState(252);
						match(L1);
						setState(253);
						((DirectDeclaratorContext) _localctx).declarator = declarator();
						setState(254);
						match(R1);
						((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).declarator.name;
				}
				break;
					default:
						throw new NoViableAltException(this);
				}
				_ctx.stop = _input.LT(-1);
				setState(285);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(283);
							switch (getInterpreter().adaptivePredict(_input, 18, _ctx)) {
								case 1: {
									_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
									_localctx.d1 = _prevctx;
									_localctx.d1 = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
									setState(259);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).d1.name;
									setState(261);
									match(L2);
									setState(262);
									((DirectDeclaratorContext) _localctx).constantExpression = constantExpression();
									setState(263);
									match(R2);
									TypeAnalyser.addArray(((DirectDeclaratorContext) _localctx).constantExpression.ret);
								}
								break;
								case 2: {
									_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
									_localctx.d2 = _prevctx;
									_localctx.d2 = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
									setState(266);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).d2.name;
									setState(268);
									match(L2);
									setState(269);
									match(R2);
									TypeAnalyser.addArray(null);
								}
								break;
								case 3: {
									_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
									_localctx.d3 = _prevctx;
									_localctx.d3 = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
									setState(271);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).d3.name;
									setState(273);
									match(L1);
									setState(274);
									match(R1);
									TypeAnalyser.addParameter();
								}
								break;
								case 4: {
									_localctx = new DirectDeclaratorContext(_parentctx, _parentState);
									_localctx.d4 = _prevctx;
									_localctx.d4 = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_directDeclarator);
									setState(276);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									((DirectDeclaratorContext) _localctx).name = ((DirectDeclaratorContext) _localctx).d4.name;
									setState(278);
									match(L1);
									setState(279);
									((DirectDeclaratorContext) _localctx).parameterTypeList = parameterTypeList();
									setState(280);
									match(R1);
									TypeAnalyser.addParameter(((DirectDeclaratorContext) _localctx).parameterTypeList.types, ((DirectDeclaratorContext) _localctx).parameterTypeList.names, ((DirectDeclaratorContext) _localctx).parameterTypeList.hasVaList);
								}
								break;
							}
						}
					}
					setState(287);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 19, _ctx);
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(288);
				((ParameterTypeListContext) _localctx).parameterList = parameterList();

				((ParameterTypeListContext) _localctx).types = ((ParameterTypeListContext) _localctx).parameterList.types;
				((ParameterTypeListContext) _localctx).names = ((ParameterTypeListContext) _localctx).parameterList.names;

				setState(293);
				_la = _input.LA(1);
				if (_la == Comma) {
					{
						setState(290);
						match(Comma);
						setState(291);
						match(T__5);
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
				setState(295);
				((ParameterListContext) _localctx).p1 = parameterDeclaration();

				_localctx.types.add(((ParameterListContext) _localctx).p1.type);
				_localctx.names.add(((ParameterListContext) _localctx).p1.name);

				setState(303);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 21, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt ==1 ) {
						{
							{
								setState(297);
								match(Comma);
								setState(298);
								((ParameterListContext) _localctx).p2 = parameterDeclaration();

								_localctx.types.add(((ParameterListContext) _localctx).p2.type);
								_localctx.names.add(((ParameterListContext) _localctx).p2.name);

							}
						}
					}
					setState(305);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 21,_ctx);
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public final ParameterDeclarationContext parameterDeclaration() throws RecognitionException {
		ParameterDeclarationContext _localctx = new ParameterDeclarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_parameterDeclaration);
		try {
			setState(319);
			switch (getInterpreter().adaptivePredict(_input, 22, _ctx)) {
				case 1:
					_localctx = new ParameterDeclaration1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(306);
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
					setState(309);
					((ParameterDeclaration2Context) _localctx).t2 = ((ParameterDeclaration2Context) _localctx).typeSpecifier = typeSpecifier();
					TypeAnalyser.enter(((ParameterDeclaration2Context) _localctx).typeSpecifier.ret);
					setState(311);
					((ParameterDeclaration2Context) _localctx).declarator = declarator();

					((ParameterDeclaration2Context) _localctx).type = TypeAnalyser.analyse();
					((ParameterDeclaration2Context) _localctx).name = ((ParameterDeclaration2Context) _localctx).declarator.name;

				}
				break;
				case 3:
					_localctx = new ParameterDeclaration3Context(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(314);
					((ParameterDeclaration3Context) _localctx).t3 = ((ParameterDeclaration3Context) _localctx).typeSpecifier = typeSpecifier();
					TypeAnalyser.enter(((ParameterDeclaration3Context) _localctx).typeSpecifier.ret);
					setState(316);
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
			setState(335);
			switch (getInterpreter().adaptivePredict(_input, 25, _ctx)) {
				case 1:
				enterOuterAlt(_localctx, 1);
				{
					setState(323);
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
							{
								setState(321);
								match(STAR);
								++_localctx.n;
							}
						}
						setState(325);
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while (_la == STAR );
				}
				break;
				case 2:
				enterOuterAlt(_localctx, 2);
				{
					setState(331);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la == STAR) {
						{
							{
								setState(327);
								match(STAR);
								++_localctx.n;
							}
						}
						setState(333);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(334);
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
				setState(358);
				switch (getInterpreter().adaptivePredict(_input, 26, _ctx)) {
					case 1: {
						setState(338);
						match(L1);
						setState(339);
						abstractDeclarator();
						setState(340);
						match(R1);
					}
					break;
					case 2: {
						setState(342);
						match(L2);
						setState(343);
						match(R2);
						TypeAnalyser.addArray(null);
					}
					break;
					case 3: {
						setState(345);
						match(L2);
						setState(346);
						((DirectAbstractDeclaratorContext) _localctx).constantExpression = constantExpression();
						setState(347);
						match(R2);
						TypeAnalyser.addArray(((DirectAbstractDeclaratorContext) _localctx).constantExpression.ret);
					}
					break;
					case 4: {
						setState(350);
						match(L1);
						setState(351);
						match(R1);
						TypeAnalyser.addParameter();
					}
					break;
					case 5: {
						setState(353);
						match(L1);
						setState(354);
						((DirectAbstractDeclaratorContext) _localctx).parameterTypeList = parameterTypeList();
						setState(355);
						match(R1);
						TypeAnalyser.addParameter(((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.types, ((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.names, ((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.hasVaList);
					}
					break;
				}
				_ctx.stop = _input.LT(-1);
				setState(383);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 29, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(381);
							switch (getInterpreter().adaptivePredict(_input, 28, _ctx)) {
								case 1: {
									_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
									setState(360);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									setState(361);
									match(L2);
									setState(362);
									match(R2);
									TypeAnalyser.addArray(null);
								}
								break;
								case 2: {
									_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
									setState(364);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(365);
									match(L2);
									setState(366);
									((DirectAbstractDeclaratorContext) _localctx).constantExpression = constantExpression();
									setState(367);
									match(R2);
									TypeAnalyser.addArray(((DirectAbstractDeclaratorContext) _localctx).constantExpression.ret);
								}
								break;
								case 3: {
									_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
									setState(370);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(371);
									match(L1);
									setState(372);
									match(R1);
									TypeAnalyser.addParameter();
								}
								break;
								case 4: {
									_localctx = new DirectAbstractDeclaratorContext(_parentctx, _parentState);
									pushNewRecursionContext(_localctx, _startState, RULE_directAbstractDeclarator);
									setState(374);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(375);
									match(L1);
									setState(377);
									switch (getInterpreter().adaptivePredict(_input, 27, _ctx)) {
										case 1: {
											setState(376);
											((DirectAbstractDeclaratorContext) _localctx).parameterTypeList = parameterTypeList();
										}
										break;
									}
									setState(379);
									match(R1);
									TypeAnalyser.addParameter(((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.types, ((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.names, ((DirectAbstractDeclaratorContext) _localctx).parameterTypeList.hasVaList);
								}
								break;
					}
					}
					}
					setState(385);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 29,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(386);
				if (!(Environment.isTypedefName(_input.LT(1).getText())))
					throw new FailedPredicateException(this, " Environment.isTypedefName(_input.LT(1).getText()) ");
				setState(387);
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
			setState(407);
			switch (_input.LA(1)) {
				case T__27:
				case T__28:
				case T__31:
				case T__32:
				case T__33:
				case T__34:
				case L1:
				case L2:
				case STAR:
				case And:
				case SizeOf:
				case Identifier:
				case DecimalConstant:
				case OctalConstant:
				case HexadecimalConstant:
				case CharacterConstant:
				case StringLiteral:
					_localctx = new Initializer1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(390);
					((Initializer1Context) _localctx).assignmentExpression = assignmentExpression();
					((Initializer1Context) _localctx).ret = new SimpleInitializerList(((Initializer1Context) _localctx).assignmentExpression.ret);
				}
				break;
				case L3:
					_localctx = new Initializer2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(393);
					match(L3);
					setState(394);
					((Initializer2Context) _localctx).i1 = initializer();

					((Initializer2Context) _localctx).ret = new SimpleInitializerList(new ArrayList<SimpleInitializerList>());
					_localctx.ret.list.add(((Initializer2Context) _localctx).i1.ret);

					setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
					while (_la == Comma) {
						{
							{
								setState(396);
								match(Comma);
								setState(397);
								((Initializer2Context) _localctx).i2 = initializer();
								_localctx.ret.list.add(((Initializer2Context) _localctx).i2.ret);
							}
						}
						setState(404);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(405);
					match(R3);
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_statement);
		try {
			setState(424);
			switch (_input.LA(1)) {
				case T__27:
				case T__28:
				case T__31:
				case T__32:
				case T__33:
				case T__34:
				case Semi:
				case L1:
				case L2:
				case STAR:
				case And:
				case SizeOf:
				case Identifier:
				case DecimalConstant:
				case OctalConstant:
				case HexadecimalConstant:
				case CharacterConstant:
				case StringLiteral:
					enterOuterAlt(_localctx, 1);
				{
					setState(409);
					((StatementContext) _localctx).expressionStatement = expressionStatement();
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).expressionStatement.ret;
				}
				break;
				case L3:
					enterOuterAlt(_localctx, 2);
				{
					setState(412);
					((StatementContext) _localctx).compoundStatement = compoundStatement(null, null);
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).compoundStatement.ret;
				}
				break;
				case If:
					enterOuterAlt(_localctx, 3);
				{
					setState(415);
					((StatementContext) _localctx).selectionStatement = selectionStatement();
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).selectionStatement.ret;
				}
				break;
				case While:
				case For:
					enterOuterAlt(_localctx, 4);
				{
					setState(418);
					((StatementContext) _localctx).iterationStatement = iterationStatement();
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).iterationStatement.ret;
				}
				break;
				case T__6:
				case T__7:
				case T__8:
					enterOuterAlt(_localctx, 5);
				{
					setState(421);
					((StatementContext) _localctx).jumpStatement = jumpStatement();
					((StatementContext) _localctx).ret = ((StatementContext) _localctx).jumpStatement.ret;
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

	public final ExpressionStatementContext expressionStatement() throws RecognitionException {
		ExpressionStatementContext _localctx = new ExpressionStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expressionStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
				setState(429);
				_la = _input.LA(1);
				if (((((_la - 28)) & ~0x3f) == 0 && ((1L << (_la - 28)) & ((1L << (T__27 - 28)) | (1L << (T__28 - 28)) | (1L << (T__31 - 28)) | (1L << (T__32 - 28)) | (1L << (T__33 - 28)) | (1L << (T__34 - 28)) | (1L << (L1 - 28)) | (1L << (L2 - 28)) | (1L << (STAR - 28)) | (1L << (And - 28)) | (1L << (SizeOf - 28)) | (1L << (Identifier - 28)) | (1L << (DecimalConstant - 28)) | (1L << (OctalConstant - 28)) | (1L << (HexadecimalConstant - 28)) | (1L << (CharacterConstant - 28)) | (1L << (StringLiteral - 28)))) != 0)) {
					{
						setState(426);
						((ExpressionStatementContext) _localctx).expression = expression();
						((ExpressionStatementContext) _localctx).ret = ((ExpressionStatementContext) _localctx).expression.ret;
					}
				}

				setState(431);
				match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(433);
				match(L3);

				Environment.enterScope();
				if (_localctx.toDefineTypes != null) {
					int n = _localctx.toDefineTypes.size();
					for (int i = 0; i < n; ++i)
						Environment.symbolNames.defineVariable(_localctx.toDefineNames.get(i), _localctx.toDefineTypes.get(i));
				}

				setState(442);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 35, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						{
							setState(440);
							switch (getInterpreter().adaptivePredict(_input, 34, _ctx)) {
								case 1: {
									setState(435);
									declaration();
								}
								break;
								case 2: {
									{
										setState(436);
										((CompoundStatementContext) _localctx).statement = statement();
										_localctx.statements.add(((CompoundStatementContext) _localctx).statement.ret);
									}
								}
								break;
								case 3: {
									setState(439);
									functionDefinition();
								}
								break;
							}
						}
					}
					setState(444);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 35, _ctx);
				}
				setState(445);
				match(R3);

				((CompoundStatementContext) _localctx).ret = new CompoundStatement(Environment.symbolNames.getVariablesInCurrentScope(), _localctx.statements);
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
				setState(448);
				match(If);
				setState(449);
				match(L1);
				setState(450);
				((SelectionStatementContext) _localctx).expression = expression();
				((SelectionStatementContext) _localctx).e1 = ((SelectionStatementContext) _localctx).expression.ret;
				setState(452);
				match(R1);
				setState(453);
				((SelectionStatementContext) _localctx).st1 = statement();
				((SelectionStatementContext) _localctx).s1 = ((SelectionStatementContext) _localctx).st1.ret;
				setState(459);
				switch (getInterpreter().adaptivePredict(_input, 36, _ctx)) {
					case 1: {
						setState(455);
						match(Else);
						setState(456);
						((SelectionStatementContext) _localctx).st2 = statement();
						((SelectionStatementContext) _localctx).s2 = ((SelectionStatementContext) _localctx).st2.ret;
					}
					break;
				}

				((SelectionStatementContext) _localctx).ret = new IfStatement(_localctx.e1, _localctx.s1, _localctx.s2);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public final IterationStatementContext iterationStatement() throws RecognitionException {
		IterationStatementContext _localctx = new IterationStatementContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_iterationStatement);
		int _la;
		try {
			setState(495);
			switch (_input.LA(1)) {
				case While:
					_localctx = new IterationStatement1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(463);
					match(While);
					setState(464);
					match(L1);
					setState(465);
					((IterationStatement1Context) _localctx).expression = expression();
					setState(466);
					match(R1);

					((IterationStatement1Context) _localctx).whileS = new WhileStatement(((IterationStatement1Context) _localctx).expression.ret);
					Environment.loopStack.push(_localctx.whileS);

					setState(468);
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
					setState(471);
					match(For);
					setState(472);
					match(L1);
					setState(476);
					_la = _input.LA(1);
					if (((((_la - 28)) & ~0x3f) == 0 && ((1L << (_la - 28)) & ((1L << (T__27 - 28)) | (1L << (T__28 - 28)) | (1L << (T__31 - 28)) | (1L << (T__32 - 28)) | (1L << (T__33 - 28)) | (1L << (T__34 - 28)) | (1L << (L1 - 28)) | (1L << (L2 - 28)) | (1L << (STAR - 28)) | (1L << (And - 28)) | (1L << (SizeOf - 28)) | (1L << (Identifier - 28)) | (1L << (DecimalConstant - 28)) | (1L << (OctalConstant - 28)) | (1L << (HexadecimalConstant - 28)) | (1L << (CharacterConstant - 28)) | (1L << (StringLiteral - 28)))) != 0)) {
						{
							setState(473);
							((IterationStatement2Context) _localctx).ex1 = expression();
							((IterationStatement2Context) _localctx).e1 = ((IterationStatement2Context) _localctx).ex1.ret;
						}
					}

					setState(478);
					match(Semi);
					setState(482);
					_la = _input.LA(1);
					if (((((_la - 28)) & ~0x3f) == 0 && ((1L << (_la - 28)) & ((1L << (T__27 - 28)) | (1L << (T__28 - 28)) | (1L << (T__31 - 28)) | (1L << (T__32 - 28)) | (1L << (T__33 - 28)) | (1L << (T__34 - 28)) | (1L << (L1 - 28)) | (1L << (L2 - 28)) | (1L << (STAR - 28)) | (1L << (And - 28)) | (1L << (SizeOf - 28)) | (1L << (Identifier - 28)) | (1L << (DecimalConstant - 28)) | (1L << (OctalConstant - 28)) | (1L << (HexadecimalConstant - 28)) | (1L << (CharacterConstant - 28)) | (1L << (StringLiteral - 28)))) != 0)) {
						{
							setState(479);
							((IterationStatement2Context) _localctx).ex2 = expression();
							((IterationStatement2Context) _localctx).e2 = ((IterationStatement2Context) _localctx).ex2.ret;
						}
					}

					setState(484);
					match(Semi);
					setState(488);
					_la = _input.LA(1);
					if (((((_la - 28)) & ~0x3f) == 0 && ((1L << (_la - 28)) & ((1L << (T__27 - 28)) | (1L << (T__28 - 28)) | (1L << (T__31 - 28)) | (1L << (T__32 - 28)) | (1L << (T__33 - 28)) | (1L << (T__34 - 28)) | (1L << (L1 - 28)) | (1L << (L2 - 28)) | (1L << (STAR - 28)) | (1L << (And - 28)) | (1L << (SizeOf - 28)) | (1L << (Identifier - 28)) | (1L << (DecimalConstant - 28)) | (1L << (OctalConstant - 28)) | (1L << (HexadecimalConstant - 28)) | (1L << (CharacterConstant - 28)) | (1L << (StringLiteral - 28)))) != 0)) {
						{
							setState(485);
							((IterationStatement2Context) _localctx).ex3 = expression();
							((IterationStatement2Context) _localctx).e3 = ((IterationStatement2Context) _localctx).ex3.ret;
						}
					}

					setState(490);
					match(R1);

					((IterationStatement2Context) _localctx).forS = new ForStatement(_localctx.e1, _localctx.e2, _localctx.e3);
					Environment.loopStack.push(_localctx.forS);

					setState(492);
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
		int _la;
		try {
			setState(511);
			switch (_input.LA(1)) {
				case T__6:
					_localctx = new JumpStatement1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(497);
					match(T__6);
					setState(498);
					match(Semi);

					((JumpStatement1Context) _localctx).ret = new ContinueStatement(Environment.getTopLoop());

				}
				break;
				case T__7:
					_localctx = new JumpStatement2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(500);
					match(T__7);
					setState(501);
					match(Semi);

					((JumpStatement2Context) _localctx).ret = new BreakStatement(Environment.getTopLoop());

				}
				break;
				case T__8:
					_localctx = new JumpStatement3Context(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(503);
					match(T__8);
					setState(507);
					_la = _input.LA(1);
					if (((((_la - 28)) & ~0x3f) == 0 && ((1L << (_la - 28)) & ((1L << (T__27 - 28)) | (1L << (T__28 - 28)) | (1L << (T__31 - 28)) | (1L << (T__32 - 28)) | (1L << (T__33 - 28)) | (1L << (T__34 - 28)) | (1L << (L1 - 28)) | (1L << (L2 - 28)) | (1L << (STAR - 28)) | (1L << (And - 28)) | (1L << (SizeOf - 28)) | (1L << (Identifier - 28)) | (1L << (DecimalConstant - 28)) | (1L << (OctalConstant - 28)) | (1L << (HexadecimalConstant - 28)) | (1L << (CharacterConstant - 28)) | (1L << (StringLiteral - 28)))) != 0)) {
						{
							setState(504);
							((JumpStatement3Context) _localctx).expression = expression();
							((JumpStatement3Context) _localctx).e = ((JumpStatement3Context) _localctx).expression.ret;
						}
					}

					setState(509);
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
				setState(513);
				((ExpressionContext) _localctx).a1 = assignmentExpression();
				((ExpressionContext) _localctx).ret = ((ExpressionContext) _localctx).a1.ret;
				setState(521);
			_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Comma) {
					{
						{
							setState(515);
							match(Comma);
							setState(516);
							((ExpressionContext) _localctx).a2 = assignmentExpression();
							((ExpressionContext) _localctx).ret = CommaExpression.getExpression(_localctx.ret, ((ExpressionContext) _localctx).a2.ret);
						}
					}
					setState(523);
				_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public final AssignmentExpressionContext assignmentExpression() throws RecognitionException {
		AssignmentExpressionContext _localctx = new AssignmentExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_assignmentExpression);
		try {
			setState(532);
			switch (getInterpreter().adaptivePredict(_input, 44, _ctx)) {
				case 1:
					_localctx = new AssignmentExpression1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(524);
					((AssignmentExpression1Context) _localctx).logicalOrExpression = logicalOrExpression();
					((AssignmentExpression1Context) _localctx).ret = ((AssignmentExpression1Context) _localctx).logicalOrExpression.ret;
				}
				break;
				case 2:
					_localctx = new AssignmentExpression2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(527);
					((AssignmentExpression2Context) _localctx).a = unaryExpression();
					setState(528);
					((AssignmentExpression2Context) _localctx).assignmentOperator = assignmentOperator();
					setState(529);
					((AssignmentExpression2Context) _localctx).b = assignmentExpression();
					((AssignmentExpression2Context) _localctx).ret = Assign.getExpression(((AssignmentExpression2Context) _localctx).a.ret, ((AssignmentExpression2Context) _localctx).b.ret, (((AssignmentExpression2Context) _localctx).assignmentOperator != null ? _input.getText(((AssignmentExpression2Context) _localctx).assignmentOperator.start, ((AssignmentExpression2Context) _localctx).assignmentOperator.stop) : null));
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
				setState(534);
			_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << EQ))) != 0))) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(536);
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
				setState(539);
				((LogicalOrExpressionContext) _localctx).a1 = logicalAndExpression();
				((LogicalOrExpressionContext) _localctx).ret = ((LogicalOrExpressionContext) _localctx).a1.ret;
				setState(547);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == OrOr) {
					{
						{
							setState(541);
							match(OrOr);
							setState(542);
							((LogicalOrExpressionContext) _localctx).a2 = logicalAndExpression();
							((LogicalOrExpressionContext) _localctx).ret = LogicalOr.getExpression(_localctx.ret, ((LogicalOrExpressionContext) _localctx).a2.ret);
						}
					}
					setState(549);
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
				setState(550);
				((LogicalAndExpressionContext) _localctx).a1 = inclusiveOrExpression();
				((LogicalAndExpressionContext) _localctx).ret = ((LogicalAndExpressionContext) _localctx).a1.ret;
				setState(558);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == AndAnd) {
					{
						{
							setState(552);
							match(AndAnd);
							setState(553);
							((LogicalAndExpressionContext) _localctx).a2 = inclusiveOrExpression();
							((LogicalAndExpressionContext) _localctx).ret = LogicalAnd.getExpression(_localctx.ret, ((LogicalAndExpressionContext) _localctx).a2.ret);
						}
					}
					setState(560);
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
				setState(561);
				((InclusiveOrExpressionContext) _localctx).a1 = exclusiveOrExpression();
				((InclusiveOrExpressionContext) _localctx).ret = ((InclusiveOrExpressionContext) _localctx).a1.ret;
				setState(569);
			_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Or) {
					{
						{
							setState(563);
							match(Or);
							setState(564);
							((InclusiveOrExpressionContext) _localctx).a2 = exclusiveOrExpression();
							((InclusiveOrExpressionContext) _localctx).ret = BitwiseOr.getExpression(_localctx.ret, ((InclusiveOrExpressionContext) _localctx).a2.ret);
						}
					}
					setState(571);
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
				setState(572);
				((ExclusiveOrExpressionContext) _localctx).a1 = andExpression();
				((ExclusiveOrExpressionContext) _localctx).ret = ((ExclusiveOrExpressionContext) _localctx).a1.ret;
				setState(580);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Caret) {
					{
						{
							setState(574);
							match(Caret);
							setState(575);
							((ExclusiveOrExpressionContext) _localctx).a2 = andExpression();
							((ExclusiveOrExpressionContext) _localctx).ret = BitwiseXOR.getExpression(_localctx.ret, ((ExclusiveOrExpressionContext) _localctx).a2.ret);
						}
					}
					setState(582);
				_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(583);
				((AndExpressionContext) _localctx).a1 = equalityExpression();
				((AndExpressionContext) _localctx).ret = ((AndExpressionContext) _localctx).a1.ret;
				setState(591);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == And) {
					{
						{
							setState(585);
							match(And);
							setState(586);
							((AndExpressionContext) _localctx).a2 = equalityExpression();
							((AndExpressionContext) _localctx).ret = BitwiseAnd.getExpression(_localctx.ret, ((AndExpressionContext) _localctx).a2.ret);
						}
					}
					setState(593);
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
				setState(594);
				((EqualityExpressionContext) _localctx).a1 = relationalExpression();
				((EqualityExpressionContext) _localctx).ret = ((EqualityExpressionContext) _localctx).a1.ret;
			setState(602);
			_errHandler.sync(this);
				_la = _input.LA(1);
			while (_la==T__19 || _la==T__20) {
				{
					{
						setState(596);
				((EqualityExpressionContext)_localctx).op = equalityOperator();
						setState(597);
				((EqualityExpressionContext)_localctx).a2 = relationalExpression();

								if ((((EqualityExpressionContext)_localctx).op!=null?_input.getText(((EqualityExpressionContext)_localctx).op.start,((EqualityExpressionContext)_localctx).op.stop):null).equals("=="))
									((EqualityExpressionContext)_localctx).ret =  EqualTo.getExpression(_localctx.ret, ((EqualityExpressionContext)_localctx).a2.ret);
								else
									((EqualityExpressionContext)_localctx).ret =  NotEqualTo.getExpression(_localctx.ret, ((EqualityExpressionContext)_localctx).a2.ret);

					}
				}
				setState(604);
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
				setState(605);
				_la = _input.LA(1);
				if (!(_la == T__19 || _la == T__20)) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		}
		catch (RecognitionException re) {
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
				setState(607);
				((RelationalExpressionContext) _localctx).a1 = shiftExpression();
				((RelationalExpressionContext) _localctx).ret = ((RelationalExpressionContext) _localctx).a1.ret;
				setState(615);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) {
					{
						{
							setState(609);
							((RelationalExpressionContext) _localctx).op = relationalOperator();
							setState(610);
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
					setState(617);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(618);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0))) {
					_errHandler.recoverInline(this);
				}
				consume();
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
				setState(620);
				((ShiftExpressionContext) _localctx).a1 = additiveExpression();
				((ShiftExpressionContext) _localctx).ret = ((ShiftExpressionContext) _localctx).a1.ret;
				setState(628);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__25 || _la == T__26) {
				{
					{
						setState(622);
						((ShiftExpressionContext) _localctx).op = shiftOperator();
						setState(623);
						((ShiftExpressionContext) _localctx).a2 = additiveExpression();

						if ((((ShiftExpressionContext) _localctx).op != null ? _input.getText(((ShiftExpressionContext) _localctx).op.start, ((ShiftExpressionContext) _localctx).op.stop) : null).equals("<<"))
							((ShiftExpressionContext) _localctx).ret = ShiftLeft.getExpression(_localctx.ret, ((ShiftExpressionContext) _localctx).a2.ret);
						else
							((ShiftExpressionContext) _localctx).ret = ShiftRight.getExpression(_localctx.ret, ((ShiftExpressionContext) _localctx).a2.ret);

				}
				}
					setState(630);
				_errHandler.sync(this);
					_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(631);
				_la = _input.LA(1);
				if (!(_la == T__25 || _la == T__26)) {
					_errHandler.recoverInline(this);
				}
				consume();
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
				setState(633);
				((AdditiveExpressionContext) _localctx).a1 = multiplicativeExpression();
				((AdditiveExpressionContext) _localctx).ret = ((AdditiveExpressionContext) _localctx).a1.ret;
				setState(641);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == T__27 || _la == T__28) {
					{
						{
							setState(635);
							((AdditiveExpressionContext) _localctx).op = additiveOperator();
							setState(636);
							((AdditiveExpressionContext) _localctx).a2 = multiplicativeExpression();

							if ((((AdditiveExpressionContext) _localctx).op != null ? _input.getText(((AdditiveExpressionContext) _localctx).op.start, ((AdditiveExpressionContext) _localctx).op.stop) : null).equals("+"))
								((AdditiveExpressionContext) _localctx).ret = Add.getExpression(_localctx.ret, ((AdditiveExpressionContext) _localctx).a2.ret);
							else
								((AdditiveExpressionContext) _localctx).ret = Subtract.getExpression(_localctx.ret, ((AdditiveExpressionContext) _localctx).a2.ret);

						}
					}
					setState(643);
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
				setState(644);
				_la = _input.LA(1);
				if (!(_la == T__27 || _la == T__28)) {
					_errHandler.recoverInline(this);
				}
				consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(646);
				((MultiplicativeExpressionContext) _localctx).a1 = ((MultiplicativeExpressionContext) _localctx).castExpression = castExpression();
				((MultiplicativeExpressionContext) _localctx).ret = ((MultiplicativeExpressionContext) _localctx).castExpression.ret;
				setState(654);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << STAR))) != 0)) {
					{
						{
							setState(648);
							((MultiplicativeExpressionContext) _localctx).op = multiplicativeOperator();
							setState(649);
							((MultiplicativeExpressionContext) _localctx).a2 = ((MultiplicativeExpressionContext) _localctx).castExpression = castExpression();

							if ((((MultiplicativeExpressionContext) _localctx).op != null ? _input.getText(((MultiplicativeExpressionContext) _localctx).op.start, ((MultiplicativeExpressionContext) _localctx).op.stop) : null).equals("*"))
								((MultiplicativeExpressionContext) _localctx).ret = Multiply.getExpression(_localctx.ret, ((MultiplicativeExpressionContext) _localctx).a2.ret);
							else if ((((MultiplicativeExpressionContext) _localctx).op != null ? _input.getText(((MultiplicativeExpressionContext) _localctx).op.start, ((MultiplicativeExpressionContext) _localctx).op.stop) : null).equals("/"))
								((MultiplicativeExpressionContext) _localctx).ret = Divide.getExpression(_localctx.ret, ((MultiplicativeExpressionContext) _localctx).a2.ret);
							else
								((MultiplicativeExpressionContext) _localctx).ret = Modulo.getExpression(_localctx.ret, ((MultiplicativeExpressionContext) _localctx).a2.ret);

						}
					}
					setState(656);
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
				setState(657);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << STAR))) != 0))) {
					_errHandler.recoverInline(this);
				}
				consume();
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
			setState(668);
			switch (getInterpreter().adaptivePredict(_input, 55, _ctx)) {
				case 1:
					_localctx = new CastExpression1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(659);
					((CastExpression1Context) _localctx).unaryExpression = unaryExpression();
					((CastExpression1Context) _localctx).ret = ((CastExpression1Context) _localctx).unaryExpression.ret;
				}
				break;
				case 2:
					_localctx = new CastExpression2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(662);
					match(L1);
					setState(663);
					((CastExpression2Context) _localctx).typeName = typeName();
					setState(664);
					match(R1);
					setState(665);
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
			setState(678);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				_localctx = new TypeName1Context(_localctx);
				enterOuterAlt(_localctx, 1);
			{
				setState(670);
				((TypeName1Context)_localctx).typeSpecifier = typeSpecifier();

							TypeAnalyser.enter(((TypeName1Context)_localctx).typeSpecifier.ret);
							((TypeName1Context)_localctx).ret =  ((TypeName1Context)_localctx).typeSpecifier.ret;

				}
				break;
			case 2:
				_localctx = new TypeName2Context(_localctx);
				enterOuterAlt(_localctx, 2);
			{
				setState(673);
				((TypeName2Context)_localctx).typeSpecifier = typeSpecifier();
				TypeAnalyser.enter(((TypeName2Context) _localctx).typeSpecifier.ret);
				setState(675);
				abstractDeclarator();

							((TypeName2Context)_localctx).ret =  TypeAnalyser.analyse();

				}
				break;
			}

				TypeAnalyser.exit();

		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_unaryExpression);
		try {
			setState(705);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				_localctx = new UnaryExpression1Context(_localctx);
				enterOuterAlt(_localctx, 1);
			{
				setState(680);
				((UnaryExpression1Context)_localctx).postfixExpression = postfixExpression(0);
				((UnaryExpression1Context) _localctx).ret = ((UnaryExpression1Context) _localctx).postfixExpression.ret;
				}
				break;
			case 2:
				_localctx = new UnaryExpression2Context(_localctx);
				enterOuterAlt(_localctx, 2);
			{
				setState(683);
				match(T__31);
				setState(684);
				((UnaryExpression2Context)_localctx).u1 = unaryExpression();
				((UnaryExpression2Context) _localctx).ret = PrefixSelfInc.getExpression(((UnaryExpression2Context) _localctx).u1.ret);
				}
				break;
			case 3:
				_localctx = new UnaryExpression3Context(_localctx);
				enterOuterAlt(_localctx, 3);
			{
				setState(687);
				match(T__32);
				setState(688);
				((UnaryExpression3Context) _localctx).u2 = unaryExpression();
				((UnaryExpression3Context) _localctx).ret = PrefixSelfDec.getExpression(((UnaryExpression3Context) _localctx).u2.ret);
				}
				break;
				case 4:
					_localctx = new UnaryExpression4Context(_localctx);
					enterOuterAlt(_localctx, 4);
				{
					setState(691);
					((UnaryExpression4Context) _localctx).op = unaryOperator();
					setState(692);
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
					setState(695);
					match(SizeOf);
					setState(696);
					((UnaryExpression5Context) _localctx).u3 = unaryExpression();
					((UnaryExpression5Context) _localctx).ret = new Sizeof(((UnaryExpression5Context) _localctx).u3.ret);
				}
				break;
				case 6:
					_localctx = new UnaryExpression6Context(_localctx);
					enterOuterAlt(_localctx, 6);
				{
					setState(699);
					match(SizeOf);
					setState(700);
					match(L1);
					setState(701);
					((UnaryExpression6Context) _localctx).typeName = typeName();
					setState(702);
					match(R1);
					((UnaryExpression6Context) _localctx).ret = new IntConstant(((UnaryExpression6Context) _localctx).typeName.ret.sizeof());
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(707);
				_la = _input.LA(1);
				if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__27) | (1L << T__28) | (1L << T__33) | (1L << T__34) | (1L << STAR) | (1L << And))) != 0))) {
					_errHandler.recoverInline(this);
				}
				consume();
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
				{
					setState(710);
					((PostfixExpressionContext) _localctx).primaryExpression = primaryExpression();
					((PostfixExpressionContext) _localctx).ret = ((PostfixExpressionContext) _localctx).primaryExpression.ret;
				}
				_ctx.stop = _input.LT(-1);
				setState(744);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input, 60, _ctx);
				while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
					if (_alt == 1) {
						if (_parseListeners != null) triggerExitRuleEvent();
						_prevctx = _localctx;
						{
							setState(742);
							switch (getInterpreter().adaptivePredict(_input, 59, _ctx)) {
								case 1: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(713);
									if (!(precpred(_ctx, 6)))
										throw new FailedPredicateException(this, "precpred(_ctx, 6)");
									setState(714);
									match(L2);
									setState(715);
									((PostfixExpressionContext) _localctx).expression = expression();
									setState(716);
									match(R2);
									((PostfixExpressionContext) _localctx).ret = ArrayAccess.getExpression(((PostfixExpressionContext) _localctx).p.ret, ((PostfixExpressionContext) _localctx).expression.ret);
								}
								break;
								case 2: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(719);
									if (!(precpred(_ctx, 5)))
										throw new FailedPredicateException(this, "precpred(_ctx, 5)");
									setState(720);
									match(L1);
									setState(724);
									_la = _input.LA(1);
									if (((((_la - 28)) & ~0x3f) == 0 && ((1L << (_la - 28)) & ((1L << (T__27 - 28)) | (1L << (T__28 - 28)) | (1L << (T__31 - 28)) | (1L << (T__32 - 28)) | (1L << (T__33 - 28)) | (1L << (T__34 - 28)) | (1L << (L1 - 28)) | (1L << (L2 - 28)) | (1L << (STAR - 28)) | (1L << (And - 28)) | (1L << (SizeOf - 28)) | (1L << (Identifier - 28)) | (1L << (DecimalConstant - 28)) | (1L << (OctalConstant - 28)) | (1L << (HexadecimalConstant - 28)) | (1L << (CharacterConstant - 28)) | (1L << (StringLiteral - 28)))) != 0)) {
										{
											setState(721);
											((PostfixExpressionContext) _localctx).arguments = arguments();
											((PostfixExpressionContext) _localctx).arg = ((PostfixExpressionContext) _localctx).arguments.ret;
										}
									}

									setState(726);
									match(R1);
									((PostfixExpressionContext) _localctx).ret = FunctionCall.getExpression(((PostfixExpressionContext) _localctx).p.ret, _localctx.arg);
								}
								break;
								case 3: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(728);
									if (!(precpred(_ctx, 4)))
										throw new FailedPredicateException(this, "precpred(_ctx, 4)");
									setState(729);
									match(T__35);
									setState(730);
									((PostfixExpressionContext) _localctx).Identifier = match(Identifier);
									((PostfixExpressionContext) _localctx).ret = MemberAccess.getExpression(((PostfixExpressionContext) _localctx).p.ret, (((PostfixExpressionContext) _localctx).Identifier != null ? ((PostfixExpressionContext) _localctx).Identifier.getText() : null));
								}
								break;
								case 4: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(732);
									if (!(precpred(_ctx, 3)))
										throw new FailedPredicateException(this, "precpred(_ctx, 3)");
									setState(733);
									match(T__36);
									setState(734);
									((PostfixExpressionContext) _localctx).Identifier = match(Identifier);
									((PostfixExpressionContext) _localctx).ret = PointerMemberAccess.getExpression(((PostfixExpressionContext) _localctx).p.ret, (((PostfixExpressionContext) _localctx).Identifier != null ? ((PostfixExpressionContext) _localctx).Identifier.getText() : null));
								}
								break;
								case 5: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(736);
									if (!(precpred(_ctx, 2)))
										throw new FailedPredicateException(this, "precpred(_ctx, 2)");
									setState(737);
									match(T__31);
									((PostfixExpressionContext) _localctx).ret = PostfixSelfInc.getExpression(((PostfixExpressionContext) _localctx).p.ret);
								}
								break;
								case 6: {
									_localctx = new PostfixExpressionContext(_parentctx, _parentState);
									_localctx.p = _prevctx;
									_localctx.p = _prevctx;
									pushNewRecursionContext(_localctx, _startState, RULE_postfixExpression);
									setState(739);
									if (!(precpred(_ctx, 1)))
										throw new FailedPredicateException(this, "precpred(_ctx, 1)");
									setState(740);
									match(T__32);
									((PostfixExpressionContext) _localctx).ret = PostfixSelfDec.getExpression(((PostfixExpressionContext) _localctx).p.ret);
								}
								break;
							}
						}
					}
					setState(746);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input, 60,_ctx);
				}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(747);
				((ArgumentsContext) _localctx).a1 = assignmentExpression();
				_localctx.ret.add(((ArgumentsContext) _localctx).a1.ret);
				setState(755);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la == Comma) {
					{
						{
							setState(749);
							match(Comma);
							setState(750);
							((ArgumentsContext) _localctx).a2 = assignmentExpression();

							_localctx.ret.add(((ArgumentsContext) _localctx).a2.ret);

						}
					}
					setState(757);
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
			setState(778);
			switch (_input.LA(1)) {
				case Identifier:
					_localctx = new PrimaryExpression1Context(_localctx);
					enterOuterAlt(_localctx, 1);
				{
					setState(758);
					((PrimaryExpression1Context) _localctx).Identifier = match(Identifier);

					if (!Environment.isVariable((((PrimaryExpression1Context) _localctx).Identifier != null ? ((PrimaryExpression1Context) _localctx).Identifier.getText() : null)))
						throw new CompilationError("Not properly defined.");
					((PrimaryExpression1Context) _localctx).ret = IdentifierExpression.getExpression((((PrimaryExpression1Context) _localctx).Identifier != null ? ((PrimaryExpression1Context) _localctx).Identifier.getText() : null));

				}
				break;
				case DecimalConstant:
				case OctalConstant:
				case HexadecimalConstant:
				case CharacterConstant:
					_localctx = new PrimaryExpression2Context(_localctx);
					enterOuterAlt(_localctx, 2);
				{
					setState(760);
					((PrimaryExpression2Context) _localctx).constant = constant();

					((PrimaryExpression2Context) _localctx).ret = ((PrimaryExpression2Context) _localctx).constant.ret;

				}
				break;
				case StringLiteral:
					_localctx = new PrimaryExpression3Context(_localctx);
					enterOuterAlt(_localctx, 3);
				{
					setState(765);
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
					case 1:
						{
							{
								setState(763);
								((PrimaryExpression3Context) _localctx).StringLiteral = match(StringLiteral);

								_localctx.s.add((((PrimaryExpression3Context) _localctx).StringLiteral != null ? ((PrimaryExpression3Context) _localctx).StringLiteral.getText() : null));

						}
						}
						break;
							default:
								throw new NoViableAltException(this);
						}
						setState(767);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input, 62, _ctx);
					} while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);

					((PrimaryExpression3Context) _localctx).ret = StringConstant.getExpression(_localctx.s);

				}
				break;
				case L1:
					_localctx = new PrimaryExpression4Context(_localctx);
					enterOuterAlt(_localctx, 4);
				{
					setState(770);
					match(L1);
					setState(771);
					((PrimaryExpression4Context) _localctx).expression = expression();
					setState(772);
					match(R1);

					((PrimaryExpression4Context) _localctx).ret = ((PrimaryExpression4Context) _localctx).expression.ret;

				}
				break;
				case L2:
					_localctx = new PrimaryExpression5Context(_localctx);
					enterOuterAlt(_localctx, 5);
				{
					setState(775);
					((PrimaryExpression5Context) _localctx).lambdaExpression = lambdaExpression();

					((PrimaryExpression5Context) _localctx).ret = ((PrimaryExpression5Context) _localctx).lambdaExpression.ret;

				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
				setState(780);
				match(L2);
				setState(781);
				match(R2);
				setState(782);
				match(L1);
				setState(786);
				switch (getInterpreter().adaptivePredict(_input, 64, _ctx)) {
					case 1: {
						setState(783);
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
				setState(788);
				match(R1);
				setState(793);
				_la = _input.LA(1);
				if (_la == T__36) {
					{
						setState(789);
						match(T__36);
						setState(790);
						((LambdaExpressionContext) _localctx).typeName = typeName();

						((LambdaExpressionContext) _localctx).type = ((LambdaExpressionContext) _localctx).typeName.ret;

					}
				}


				Environment.functionReturnStack.push(_localctx.type);
				if (_localctx.type == null) ((LambdaExpressionContext) _localctx).type = VoidType.instance;
				((LambdaExpressionContext) _localctx).type = new FunctionType(_localctx.type, _localctx.parameterTypes, _localctx.parameterNames, _localctx.hasVaList);
				((LambdaExpressionContext) _localctx).uId = Environment.symbolNames.defineLocalFunction("", _localctx.type);

				setState(796);
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
			setState(807);
			switch (_input.LA(1)) {
				case DecimalConstant:
					enterOuterAlt(_localctx, 1);
				{
					setState(799);
					((ConstantContext) _localctx).DecimalConstant = match(DecimalConstant);
					((ConstantContext) _localctx).ret = IntConstant.getExpression((((ConstantContext) _localctx).DecimalConstant != null ? ((ConstantContext) _localctx).DecimalConstant.getText() : null), 10);
				}
				break;
				case OctalConstant:
					enterOuterAlt(_localctx, 2);
				{
					setState(801);
					((ConstantContext) _localctx).OctalConstant = match(OctalConstant);
					((ConstantContext) _localctx).ret = IntConstant.getExpression((((ConstantContext) _localctx).OctalConstant != null ? ((ConstantContext) _localctx).OctalConstant.getText() : null), 8);
				}
				break;
				case HexadecimalConstant:
					enterOuterAlt(_localctx, 3);
				{
					setState(803);
					((ConstantContext) _localctx).HexadecimalConstant = match(HexadecimalConstant);
					((ConstantContext) _localctx).ret = IntConstant.getExpression((((ConstantContext) _localctx).HexadecimalConstant != null ? ((ConstantContext) _localctx).HexadecimalConstant.getText() : null), 16);
				}
				break;
				case CharacterConstant:
					enterOuterAlt(_localctx, 4);
				{
					setState(805);
					((ConstantContext) _localctx).CharacterConstant = match(CharacterConstant);
					((ConstantContext) _localctx).ret = CharConstant.getExpression((((ConstantContext) _localctx).CharacterConstant != null ? ((ConstantContext) _localctx).CharacterConstant.getText() : null));
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			return getRuleContext(DeclaratorListContext.class,0);
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
			return getRuleContext(TypeSpecifierContext.class,0);
		}

		public TerminalNode Semi() {
			return getToken(Compiler2015Parser.Semi, 0);
		}

		public InitDeclaratorListContext initDeclaratorList() {
			return getRuleContext(InitDeclaratorListContext.class,0);
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
		public TypeSpecifierContext typeSpecifier;
		public DeclaratorContext declarator;
		public ParameterTypeListContext parameterTypeList;
		public CompoundStatementContext compoundStatement;

		public FunctionDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}

		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}

		public TerminalNode L1() {
			return getToken(Compiler2015Parser.L1, 0);
		}

		public TerminalNode R1() {
			return getToken(Compiler2015Parser.R1, 0);
		}

		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}

		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_functionDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitFunctionDefinition(this);
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
			return getRuleContext(InitDeclaratorContext.class,i);
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
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitInitDeclaratorList(this);
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
		public StructOrUnionSpecifier1Context(StructOrUnionSpecifierContext ctx) { copyFrom(ctx); }

		public StructOrUnionContext structOrUnion() {
			return getRuleContext(StructOrUnionContext.class,0);
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
			return getRuleContext(StructDeclarationContext.class,i);
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
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitStructOrUnionSpecifier2(this);
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
			return RULE_structDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterStructDeclaration(this);
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
			return getRuleContext(DirectDeclaratorContext.class,0);
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
			return RULE_plainDeclarator; }
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
			return getRuleContext(PlainDeclaratorContext.class,i);
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
			return getRuleContext(DeclaratorContext.class,0);
		}

		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}

		public DirectDeclaratorContext directDeclarator() {
			return getRuleContext(DirectDeclaratorContext.class,0);
		}

		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_directDeclarator; }
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
			return getRuleContext(ParameterListContext.class,0);
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
			return getRuleContext(ParameterDeclarationContext.class,i);
		}

		public List<TerminalNode> Comma() {
			return getTokens(Compiler2015Parser.Comma);
		}

		public TerminalNode Comma(int i) {
			return getToken(Compiler2015Parser.Comma, i);
		}

		@Override
		public int getRuleIndex() {
			return RULE_parameterList; }
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
			return getRuleContext(TypeSpecifierContext.class,0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterParameterDeclaration1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitParameterDeclaration1(this);
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
			return getRuleContext(DeclaratorContext.class,0);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
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
			return getRuleContext(AbstractDeclaratorContext.class,0);
		}

		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterParameterDeclaration3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitParameterDeclaration3(this);
		}
	}

	public static class AbstractDeclaratorContext extends ParserRuleContext {
		public int n = 0;

		public AbstractDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public DirectAbstractDeclaratorContext directAbstractDeclarator() {
			return getRuleContext(DirectAbstractDeclaratorContext.class,0);
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
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitAbstractDeclarator(this);
		}
	}

	public static class DirectAbstractDeclaratorContext extends ParserRuleContext {
		public ConstantExpressionContext constantExpression;
		public ParameterTypeListContext parameterTypeList;

		public DirectAbstractDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		public AbstractDeclaratorContext abstractDeclarator() {
			return getRuleContext(AbstractDeclaratorContext.class,0);
		}

		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}

		public ParameterTypeListContext parameterTypeList() {
			return getRuleContext(ParameterTypeListContext.class,0);
		}

		public DirectAbstractDeclaratorContext directAbstractDeclarator() {
			return getRuleContext(DirectAbstractDeclaratorContext.class,0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_directAbstractDeclarator; }
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
			return getRuleContext(AssignmentExpressionContext.class,0);
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
			return getRuleContext(ExpressionStatementContext.class,0);
		}

		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}

		public SelectionStatementContext selectionStatement() {
			return getRuleContext(SelectionStatementContext.class,0);
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
			return RULE_expressionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitExpressionStatement(this);
		}
	}

	public static class CompoundStatementContext extends ParserRuleContext {
		public ArrayList<Type> toDefineTypes;
		public ArrayList<String> toDefineNames;
		public CompoundStatement ret;
		public ArrayList<Statement> statements = new ArrayList<Statement>();
		;
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
			return getRuleContext(ExpressionContext.class,0);
		}

		public TerminalNode R1() {
			return getToken(Compiler2015Parser.R1, 0);
		}

		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}

		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}

		public TerminalNode Else() {
			return getToken(Compiler2015Parser.Else, 0);
		}

		@Override
		public int getRuleIndex() {
			return RULE_selectionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterSelectionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitSelectionStatement(this);
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
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterIterationStatement2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitIterationStatement2(this);
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
			return getRuleContext(ExpressionContext.class,0);
		}

		public TerminalNode R1() {
			return getToken(Compiler2015Parser.R1, 0);
		}

		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
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
			copyFrom(ctx); }
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
			return getRuleContext(ExpressionContext.class,0);
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
			return RULE_expression; }
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

	public static class AssignmentExpression1Context extends AssignmentExpressionContext {
		public LogicalOrExpressionContext logicalOrExpression;

		public AssignmentExpression1Context(AssignmentExpressionContext ctx) {
			copyFrom(ctx);
		}

		public LogicalOrExpressionContext logicalOrExpression() {
			return getRuleContext(LogicalOrExpressionContext.class,0);
		}

		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterAssignmentExpression1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitAssignmentExpression1(this);
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
			return getRuleContext(AssignmentOperatorContext.class,0);
		}

		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}

		public AssignmentExpressionContext assignmentExpression() {
			return getRuleContext(AssignmentExpressionContext.class,0);
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
			return getRuleContext(InclusiveOrExpressionContext.class,i);
		}

		public List<TerminalNode> AndAnd() { return getTokens(Compiler2015Parser.AndAnd); }

		public TerminalNode AndAnd(int i) {
			return getToken(Compiler2015Parser.AndAnd, i);
		}

		@Override public int getRuleIndex() { return RULE_logicalAndExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Compiler2015Listener ) ((Compiler2015Listener)listener).enterLogicalAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Compiler2015Listener ) ((Compiler2015Listener)listener).exitLogicalAndExpression(this);
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
			return getRuleContext(EqualityOperatorContext.class,i);
		}

		@Override public int getRuleIndex() { return RULE_equalityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Compiler2015Listener ) ((Compiler2015Listener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Compiler2015Listener ) ((Compiler2015Listener)listener).exitEqualityExpression(this);
		}
	}

	public static class EqualityOperatorContext extends ParserRuleContext {
		public EqualityOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Compiler2015Listener ) ((Compiler2015Listener)listener).enterEqualityOperator(this);
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
			return getRuleContext(CastExpressionContext.class,i);
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
			return RULE_multiplicativeOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).enterMultiplicativeOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitMultiplicativeOperator(this);
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
			return getRuleContext(UnaryExpressionContext.class,0);
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
			return getRuleContext(TypeNameContext.class,0);
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
			return getRuleContext(TypeSpecifierContext.class,0);
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
		public UnaryExpressionContext u3;

		public UnaryExpression5Context(UnaryExpressionContext ctx) {
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
			return getRuleContext(UnaryOperatorContext.class,0);
		}

		public CastExpressionContext castExpression() {
			return getRuleContext(CastExpressionContext.class,0);
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
			return getRuleContext(UnaryExpressionContext.class,0);
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

	public static class UnaryOperatorContext extends ParserRuleContext {
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}

		@Override
		public int getRuleIndex() {
			return RULE_unaryOperator; }
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
			return RULE_arguments; }
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
			if (listener instanceof Compiler2015Listener) ((Compiler2015Listener) listener).exitPrimaryExpression5(this);
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