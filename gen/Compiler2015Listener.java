// Generated from /home/junrushao/IdeaProjects/Compiler2015/src/Parser/Compiler2015.g4 by ANTLR 4.5


package Compiler2015.Parser;
import Compiler2015.AST.*;


import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Compiler2015Parser}.
 */
public interface Compiler2015Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(@NotNull Compiler2015Parser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(@NotNull Compiler2015Parser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpression(@NotNull Compiler2015Parser.PostfixExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#postfixExpression}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpression(@NotNull Compiler2015Parser.PostfixExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#argumentExpressionList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentExpressionList(@NotNull Compiler2015Parser.ArgumentExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#argumentExpressionList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentExpressionList(@NotNull Compiler2015Parser.ArgumentExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(@NotNull Compiler2015Parser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(@NotNull Compiler2015Parser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(@NotNull Compiler2015Parser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(@NotNull Compiler2015Parser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(@NotNull Compiler2015Parser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(@NotNull Compiler2015Parser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(@NotNull Compiler2015Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(@NotNull Compiler2015Parser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(@NotNull Compiler2015Parser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(@NotNull Compiler2015Parser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void enterShiftExpression(@NotNull Compiler2015Parser.ShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#shiftExpression}.
	 * @param ctx the parse tree
	 */
	void exitShiftExpression(@NotNull Compiler2015Parser.ShiftExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(@NotNull Compiler2015Parser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(@NotNull Compiler2015Parser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(@NotNull Compiler2015Parser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(@NotNull Compiler2015Parser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#andExpression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(@NotNull Compiler2015Parser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#andExpression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(@NotNull Compiler2015Parser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterExclusiveOrExpression(@NotNull Compiler2015Parser.ExclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#exclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitExclusiveOrExpression(@NotNull Compiler2015Parser.ExclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterInclusiveOrExpression(@NotNull Compiler2015Parser.InclusiveOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#inclusiveOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitInclusiveOrExpression(@NotNull Compiler2015Parser.InclusiveOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(@NotNull Compiler2015Parser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(@NotNull Compiler2015Parser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(@NotNull Compiler2015Parser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(@NotNull Compiler2015Parser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(@NotNull Compiler2015Parser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(@NotNull Compiler2015Parser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(@NotNull Compiler2015Parser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(@NotNull Compiler2015Parser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentOperator(@NotNull Compiler2015Parser.AssignmentOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#assignmentOperator}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentOperator(@NotNull Compiler2015Parser.AssignmentOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull Compiler2015Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull Compiler2015Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void enterConstantExpression(@NotNull Compiler2015Parser.ConstantExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#constantExpression}.
	 * @param ctx the parse tree
	 */
	void exitConstantExpression(@NotNull Compiler2015Parser.ConstantExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(@NotNull Compiler2015Parser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(@NotNull Compiler2015Parser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifiers(@NotNull Compiler2015Parser.DeclarationSpecifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declarationSpecifiers}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifiers(@NotNull Compiler2015Parser.DeclarationSpecifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifiers2(@NotNull Compiler2015Parser.DeclarationSpecifiers2Context ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declarationSpecifiers2}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifiers2(@NotNull Compiler2015Parser.DeclarationSpecifiers2Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declarationSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationSpecifier(@NotNull Compiler2015Parser.DeclarationSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declarationSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationSpecifier(@NotNull Compiler2015Parser.DeclarationSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclaratorList(@NotNull Compiler2015Parser.InitDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#initDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclaratorList(@NotNull Compiler2015Parser.InitDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterInitDeclarator(@NotNull Compiler2015Parser.InitDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#initDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitInitDeclarator(@NotNull Compiler2015Parser.InitDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifier(@NotNull Compiler2015Parser.TypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifier(@NotNull Compiler2015Parser.TypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnionSpecifier(@NotNull Compiler2015Parser.StructOrUnionSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnionSpecifier(@NotNull Compiler2015Parser.StructOrUnionSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#structOrUnion}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnion(@NotNull Compiler2015Parser.StructOrUnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#structOrUnion}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnion(@NotNull Compiler2015Parser.StructOrUnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#structDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclarationList(@NotNull Compiler2015Parser.StructDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#structDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclarationList(@NotNull Compiler2015Parser.StructDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaration(@NotNull Compiler2015Parser.StructDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#structDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaration(@NotNull Compiler2015Parser.StructDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#structDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclaratorList(@NotNull Compiler2015Parser.StructDeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#structDeclaratorList}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclaratorList(@NotNull Compiler2015Parser.StructDeclaratorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#structDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterStructDeclarator(@NotNull Compiler2015Parser.StructDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#structDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitStructDeclarator(@NotNull Compiler2015Parser.StructDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declarator}.
	 * @param ctx the parse tree
	 */
	void enterDeclarator(@NotNull Compiler2015Parser.DeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declarator}.
	 * @param ctx the parse tree
	 */
	void exitDeclarator(@NotNull Compiler2015Parser.DeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#directDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterDirectDeclarator(@NotNull Compiler2015Parser.DirectDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#directDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitDirectDeclarator(@NotNull Compiler2015Parser.DirectDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#pointer}.
	 * @param ctx the parse tree
	 */
	void enterPointer(@NotNull Compiler2015Parser.PointerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#pointer}.
	 * @param ctx the parse tree
	 */
	void exitPointer(@NotNull Compiler2015Parser.PointerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#parameterTypeList}.
	 * @param ctx the parse tree
	 */
	void enterParameterTypeList(@NotNull Compiler2015Parser.ParameterTypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#parameterTypeList}.
	 * @param ctx the parse tree
	 */
	void exitParameterTypeList(@NotNull Compiler2015Parser.ParameterTypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(@NotNull Compiler2015Parser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(@NotNull Compiler2015Parser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(@NotNull Compiler2015Parser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(@NotNull Compiler2015Parser.ParameterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(@NotNull Compiler2015Parser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(@NotNull Compiler2015Parser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(@NotNull Compiler2015Parser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(@NotNull Compiler2015Parser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#abstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterAbstractDeclarator(@NotNull Compiler2015Parser.AbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#abstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitAbstractDeclarator(@NotNull Compiler2015Parser.AbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterDirectAbstractDeclarator(@NotNull Compiler2015Parser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#directAbstractDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitDirectAbstractDeclarator(@NotNull Compiler2015Parser.DirectAbstractDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#typedefName}.
	 * @param ctx the parse tree
	 */
	void enterTypedefName(@NotNull Compiler2015Parser.TypedefNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#typedefName}.
	 * @param ctx the parse tree
	 */
	void exitTypedefName(@NotNull Compiler2015Parser.TypedefNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer(@NotNull Compiler2015Parser.InitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer(@NotNull Compiler2015Parser.InitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#initializerList}.
	 * @param ctx the parse tree
	 */
	void enterInitializerList(@NotNull Compiler2015Parser.InitializerListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#initializerList}.
	 * @param ctx the parse tree
	 */
	void exitInitializerList(@NotNull Compiler2015Parser.InitializerListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#designation}.
	 * @param ctx the parse tree
	 */
	void enterDesignation(@NotNull Compiler2015Parser.DesignationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#designation}.
	 * @param ctx the parse tree
	 */
	void exitDesignation(@NotNull Compiler2015Parser.DesignationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#designatorList}.
	 * @param ctx the parse tree
	 */
	void enterDesignatorList(@NotNull Compiler2015Parser.DesignatorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#designatorList}.
	 * @param ctx the parse tree
	 */
	void exitDesignatorList(@NotNull Compiler2015Parser.DesignatorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#designator}.
	 * @param ctx the parse tree
	 */
	void enterDesignator(@NotNull Compiler2015Parser.DesignatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#designator}.
	 * @param ctx the parse tree
	 */
	void exitDesignator(@NotNull Compiler2015Parser.DesignatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull Compiler2015Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull Compiler2015Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void enterLabeledStatement(@NotNull Compiler2015Parser.LabeledStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#labeledStatement}.
	 * @param ctx the parse tree
	 */
	void exitLabeledStatement(@NotNull Compiler2015Parser.LabeledStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(@NotNull Compiler2015Parser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(@NotNull Compiler2015Parser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#blockItemList}.
	 * @param ctx the parse tree
	 */
	void enterBlockItemList(@NotNull Compiler2015Parser.BlockItemListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#blockItemList}.
	 * @param ctx the parse tree
	 */
	void exitBlockItemList(@NotNull Compiler2015Parser.BlockItemListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#blockItem}.
	 * @param ctx the parse tree
	 */
	void enterBlockItem(@NotNull Compiler2015Parser.BlockItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#blockItem}.
	 * @param ctx the parse tree
	 */
	void exitBlockItem(@NotNull Compiler2015Parser.BlockItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(@NotNull Compiler2015Parser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(@NotNull Compiler2015Parser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectionStatement(@NotNull Compiler2015Parser.SelectionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#selectionStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectionStatement(@NotNull Compiler2015Parser.SelectionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(@NotNull Compiler2015Parser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(@NotNull Compiler2015Parser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(@NotNull Compiler2015Parser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(@NotNull Compiler2015Parser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(@NotNull Compiler2015Parser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(@NotNull Compiler2015Parser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void enterTranslationUnit(@NotNull Compiler2015Parser.TranslationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#translationUnit}.
	 * @param ctx the parse tree
	 */
	void exitTranslationUnit(@NotNull Compiler2015Parser.TranslationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterExternalDeclaration(@NotNull Compiler2015Parser.ExternalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#externalDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitExternalDeclaration(@NotNull Compiler2015Parser.ExternalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(@NotNull Compiler2015Parser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(@NotNull Compiler2015Parser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declarationList}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationList(@NotNull Compiler2015Parser.DeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declarationList}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationList(@NotNull Compiler2015Parser.DeclarationListContext ctx);
}