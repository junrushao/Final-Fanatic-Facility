// Generated from /home/junrushao/IdeaProjects/Compiler2015/src/Parser/Compiler2015.g4 by ANTLR 4.5


package Compiler2015.Parser;
import Compiler2015.AST.*;


import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Compiler2015Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Compiler2015Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(@NotNull Compiler2015Parser.PrimaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#postfixExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpression(@NotNull Compiler2015Parser.PostfixExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#argumentExpressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentExpressionList(@NotNull Compiler2015Parser.ArgumentExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(@NotNull Compiler2015Parser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#unaryOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(@NotNull Compiler2015Parser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(@NotNull Compiler2015Parser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(@NotNull Compiler2015Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(@NotNull Compiler2015Parser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#shiftExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShiftExpression(@NotNull Compiler2015Parser.ShiftExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(@NotNull Compiler2015Parser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(@NotNull Compiler2015Parser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#andExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(@NotNull Compiler2015Parser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclusiveOrExpression(@NotNull Compiler2015Parser.ExclusiveOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInclusiveOrExpression(@NotNull Compiler2015Parser.InclusiveOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(@NotNull Compiler2015Parser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(@NotNull Compiler2015Parser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(@NotNull Compiler2015Parser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(@NotNull Compiler2015Parser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(@NotNull Compiler2015Parser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull Compiler2015Parser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#constantExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantExpression(@NotNull Compiler2015Parser.ConstantExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(@NotNull Compiler2015Parser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifiers(@NotNull Compiler2015Parser.DeclarationSpecifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifiers2(@NotNull Compiler2015Parser.DeclarationSpecifiers2Context ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#declarationSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationSpecifier(@NotNull Compiler2015Parser.DeclarationSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#initDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitDeclaratorList(@NotNull Compiler2015Parser.InitDeclaratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#initDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitDeclarator(@NotNull Compiler2015Parser.InitDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#typeSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSpecifier(@NotNull Compiler2015Parser.TypeSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructOrUnionSpecifier(@NotNull Compiler2015Parser.StructOrUnionSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#structOrUnion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructOrUnion(@NotNull Compiler2015Parser.StructOrUnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#structDeclarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclarationList(@NotNull Compiler2015Parser.StructDeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#structDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaration(@NotNull Compiler2015Parser.StructDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#structDeclaratorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclaratorList(@NotNull Compiler2015Parser.StructDeclaratorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#structDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructDeclarator(@NotNull Compiler2015Parser.StructDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#declarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarator(@NotNull Compiler2015Parser.DeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#directDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectDeclarator(@NotNull Compiler2015Parser.DirectDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#pointer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointer(@NotNull Compiler2015Parser.PointerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#parameterTypeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterTypeList(@NotNull Compiler2015Parser.ParameterTypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(@NotNull Compiler2015Parser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclaration(@NotNull Compiler2015Parser.ParameterDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(@NotNull Compiler2015Parser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(@NotNull Compiler2015Parser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#abstractDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbstractDeclarator(@NotNull Compiler2015Parser.AbstractDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectAbstractDeclarator(@NotNull Compiler2015Parser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#typedefName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypedefName(@NotNull Compiler2015Parser.TypedefNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializer(@NotNull Compiler2015Parser.InitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#initializerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitializerList(@NotNull Compiler2015Parser.InitializerListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#designation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignation(@NotNull Compiler2015Parser.DesignationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#designatorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignatorList(@NotNull Compiler2015Parser.DesignatorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#designator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDesignator(@NotNull Compiler2015Parser.DesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull Compiler2015Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#labeledStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabeledStatement(@NotNull Compiler2015Parser.LabeledStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(@NotNull Compiler2015Parser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#blockItemList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItemList(@NotNull Compiler2015Parser.BlockItemListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#blockItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockItem(@NotNull Compiler2015Parser.BlockItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#expressionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionStatement(@NotNull Compiler2015Parser.ExpressionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#selectionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectionStatement(@NotNull Compiler2015Parser.SelectionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(@NotNull Compiler2015Parser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJumpStatement(@NotNull Compiler2015Parser.JumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(@NotNull Compiler2015Parser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#translationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranslationUnit(@NotNull Compiler2015Parser.TranslationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#externalDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExternalDeclaration(@NotNull Compiler2015Parser.ExternalDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(@NotNull Compiler2015Parser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Compiler2015Parser#declarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationList(@NotNull Compiler2015Parser.DeclarationListContext ctx);
}