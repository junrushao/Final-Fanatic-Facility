// Generated from Compiler2015.g4 by ANTLR 4.5
package Compiler2015.Parser;

import Compiler2015.AST.*;
import Compiler2015.AST.Statement.*;
import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.*;
import Compiler2015.AST.Statement.ExpressionStatement.*;
import Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression.*;
import Compiler2015.Type.*;
import Compiler2015.Environment.*;
import Compiler2015.Exception.*;
import Compiler2015.Utility.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Compiler2015Parser}.
 */
public interface Compiler2015Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(Compiler2015Parser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(Compiler2015Parser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaration1}
	 * labeled alternative in {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration1(Compiler2015Parser.Declaration1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code declaration1}
	 * labeled alternative in {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration1(Compiler2015Parser.Declaration1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code declaration2}
	 * labeled alternative in {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration2(Compiler2015Parser.Declaration2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code declaration2}
	 * labeled alternative in {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration2(Compiler2015Parser.Declaration2Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(Compiler2015Parser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(Compiler2015Parser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclaratorList(Compiler2015Parser.InitDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclaratorList(Compiler2015Parser.InitDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclarator(Compiler2015Parser.InitDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclarator(Compiler2015Parser.InitDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifier(Compiler2015Parser.TypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifier(Compiler2015Parser.TypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code structOrUnionSpecifier1}
	 * labeled alternative in {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnionSpecifier1(Compiler2015Parser.StructOrUnionSpecifier1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code structOrUnionSpecifier1}
	 * labeled alternative in {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnionSpecifier1(Compiler2015Parser.StructOrUnionSpecifier1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code structOrUnionSpecifier2}
	 * labeled alternative in {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnionSpecifier2(Compiler2015Parser.StructOrUnionSpecifier2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code structOrUnionSpecifier2}
	 * labeled alternative in {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnionSpecifier2(Compiler2015Parser.StructOrUnionSpecifier2Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#structOrUnion}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnion(Compiler2015Parser.StructOrUnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#structOrUnion}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnion(Compiler2015Parser.StructOrUnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(Compiler2015Parser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(Compiler2015Parser.StructDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(Compiler2015Parser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(Compiler2015Parser.DeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#plainDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterPlainDeclarator(Compiler2015Parser.PlainDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#plainDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitPlainDeclarator(Compiler2015Parser.PlainDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declaratorList}.
	 * @param ctx the parse tree
	 */
	void enterDeclaratorList(Compiler2015Parser.DeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declaratorList}.
	 * @param ctx the parse tree
	 */
	void exitDeclaratorList(Compiler2015Parser.DeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#directDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterDirectDeclarator(Compiler2015Parser.DirectDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#directDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitDirectDeclarator(Compiler2015Parser.DirectDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#parameterTypeList}.
	 * @param ctx the parse tree
	 */
	void enterParameterTypeList(Compiler2015Parser.ParameterTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#parameterTypeList}.
	 * @param ctx the parse tree
	 */
	void exitParameterTypeList(Compiler2015Parser.ParameterTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(Compiler2015Parser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(Compiler2015Parser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parameterDeclaration1}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration1(Compiler2015Parser.ParameterDeclaration1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code parameterDeclaration1}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration1(Compiler2015Parser.ParameterDeclaration1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code parameterDeclaration2}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration2(Compiler2015Parser.ParameterDeclaration2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code parameterDeclaration2}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration2(Compiler2015Parser.ParameterDeclaration2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code parameterDeclaration3}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration3(Compiler2015Parser.ParameterDeclaration3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code parameterDeclaration3}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration3(Compiler2015Parser.ParameterDeclaration3Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#abstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterAbstractDeclarator(Compiler2015Parser.AbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#abstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitAbstractDeclarator(Compiler2015Parser.AbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterDirectAbstractDeclarator(Compiler2015Parser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitDirectAbstractDeclarator(Compiler2015Parser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#typedefName}.
	 * @param ctx the parse tree
	 */
	void enterTypedefName(Compiler2015Parser.TypedefNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#typedefName}.
	 * @param ctx the parse tree
	 */
	void exitTypedefName(Compiler2015Parser.TypedefNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code initializer1}
	 * labeled alternative in {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer1(Compiler2015Parser.Initializer1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code initializer1}
	 * labeled alternative in {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer1(Compiler2015Parser.Initializer1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code initializer2}
	 * labeled alternative in {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer2(Compiler2015Parser.Initializer2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code initializer2}
	 * labeled alternative in {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer2(Compiler2015Parser.Initializer2Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(Compiler2015Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(Compiler2015Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(Compiler2015Parser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(Compiler2015Parser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(Compiler2015Parser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(Compiler2015Parser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(Compiler2015Parser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(Compiler2015Parser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code iterationStatement1}
	 * labeled alternative in {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement1(Compiler2015Parser.IterationStatement1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code iterationStatement1}
	 * labeled alternative in {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement1(Compiler2015Parser.IterationStatement1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code iterationStatement2}
	 * labeled alternative in {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement2(Compiler2015Parser.IterationStatement2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code iterationStatement2}
	 * labeled alternative in {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement2(Compiler2015Parser.IterationStatement2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStatement1}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement1(Compiler2015Parser.JumpStatement1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStatement1}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement1(Compiler2015Parser.JumpStatement1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStatement2}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement2(Compiler2015Parser.JumpStatement2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStatement2}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement2(Compiler2015Parser.JumpStatement2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStatement3}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement3(Compiler2015Parser.JumpStatement3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStatement3}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement3(Compiler2015Parser.JumpStatement3Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(Compiler2015Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(Compiler2015Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentExpression1}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression1(Compiler2015Parser.AssignmentExpression1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentExpression1}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression1(Compiler2015Parser.AssignmentExpression1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentExpression2}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression2(Compiler2015Parser.AssignmentExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentExpression2}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression2(Compiler2015Parser.AssignmentExpression2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentExpression3}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression3(Compiler2015Parser.AssignmentExpression3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentExpression3}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression3(Compiler2015Parser.AssignmentExpression3Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(Compiler2015Parser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(Compiler2015Parser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(Compiler2015Parser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(Compiler2015Parser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(Compiler2015Parser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(Compiler2015Parser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(Compiler2015Parser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(Compiler2015Parser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveOrExpression(Compiler2015Parser.InclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveOrExpression(Compiler2015Parser.InclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(Compiler2015Parser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(Compiler2015Parser.ExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(Compiler2015Parser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(Compiler2015Parser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(Compiler2015Parser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(Compiler2015Parser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void enterEqualityOperator(Compiler2015Parser.EqualityOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void exitEqualityOperator(Compiler2015Parser.EqualityOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(Compiler2015Parser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(Compiler2015Parser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOperator(Compiler2015Parser.RelationalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOperator(Compiler2015Parser.RelationalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(Compiler2015Parser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(Compiler2015Parser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#shiftOperator}.
	 * @param ctx the parse tree
	 */
	void enterShiftOperator(Compiler2015Parser.ShiftOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#shiftOperator}.
	 * @param ctx the parse tree
	 */
	void exitShiftOperator(Compiler2015Parser.ShiftOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(Compiler2015Parser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(Compiler2015Parser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveOperator(Compiler2015Parser.AdditiveOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveOperator(Compiler2015Parser.AdditiveOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(Compiler2015Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(Compiler2015Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeOperator(Compiler2015Parser.MultiplicativeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeOperator(Compiler2015Parser.MultiplicativeOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code castExpression1}
	 * labeled alternative in {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression1(Compiler2015Parser.CastExpression1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code castExpression1}
	 * labeled alternative in {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression1(Compiler2015Parser.CastExpression1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code castExpression2}
	 * labeled alternative in {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression2(Compiler2015Parser.CastExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code castExpression2}
	 * labeled alternative in {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression2(Compiler2015Parser.CastExpression2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code typeName1}
	 * labeled alternative in {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName1(Compiler2015Parser.TypeName1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code typeName1}
	 * labeled alternative in {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName1(Compiler2015Parser.TypeName1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code typeName2}
	 * labeled alternative in {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName2(Compiler2015Parser.TypeName2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code typeName2}
	 * labeled alternative in {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName2(Compiler2015Parser.TypeName2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression1}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression1(Compiler2015Parser.UnaryExpression1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression1}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression1(Compiler2015Parser.UnaryExpression1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression2}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression2(Compiler2015Parser.UnaryExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression2}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression2(Compiler2015Parser.UnaryExpression2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression3}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression3(Compiler2015Parser.UnaryExpression3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression3}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression3(Compiler2015Parser.UnaryExpression3Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression4}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression4(Compiler2015Parser.UnaryExpression4Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression4}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression4(Compiler2015Parser.UnaryExpression4Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression5}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression5(Compiler2015Parser.UnaryExpression5Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression5}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression5(Compiler2015Parser.UnaryExpression5Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression6}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression6(Compiler2015Parser.UnaryExpression6Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression6}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression6(Compiler2015Parser.UnaryExpression6Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression7}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression7(Compiler2015Parser.UnaryExpression7Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression7}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression7(Compiler2015Parser.UnaryExpression7Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression8}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression8(Compiler2015Parser.UnaryExpression8Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression8}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression8(Compiler2015Parser.UnaryExpression8Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(Compiler2015Parser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(Compiler2015Parser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(Compiler2015Parser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(Compiler2015Parser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(Compiler2015Parser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(Compiler2015Parser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression1}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression1(Compiler2015Parser.PrimaryExpression1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression1}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression1(Compiler2015Parser.PrimaryExpression1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression2}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression2(Compiler2015Parser.PrimaryExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression2}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression2(Compiler2015Parser.PrimaryExpression2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression3}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression3(Compiler2015Parser.PrimaryExpression3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression3}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression3(Compiler2015Parser.PrimaryExpression3Context ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression4}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression4(Compiler2015Parser.PrimaryExpression4Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression4}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression4(Compiler2015Parser.PrimaryExpression4Context ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression5}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression5(Compiler2015Parser.PrimaryExpression5Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression5}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression5(Compiler2015Parser.PrimaryExpression5Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(Compiler2015Parser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(Compiler2015Parser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(Compiler2015Parser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(Compiler2015Parser.ConstantContext ctx);
}