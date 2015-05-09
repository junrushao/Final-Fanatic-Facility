// Generated from /home/junrushao/IdeaProjects/compiler2015/src/Compiler2015/Parser/Compiler2015.g4 by ANTLR 4.5
package Compiler2015.Parser;

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
	void enterCompilationUnit(@NotNull Compiler2015Parser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(@NotNull Compiler2015Parser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaration1}
	 * labeled alternative in {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration1(@NotNull Compiler2015Parser.Declaration1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code declaration1}
	 * labeled alternative in {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration1(@NotNull Compiler2015Parser.Declaration1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code declaration2}
	 * labeled alternative in {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration2(@NotNull Compiler2015Parser.Declaration2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code declaration2}
	 * labeled alternative in {@link Compiler2015Parser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration2(@NotNull Compiler2015Parser.Declaration2Context ctx);
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
	 * Enter a parse tree produced by the {@code structOrUnionSpecifier1}
	 * labeled alternative in {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnionSpecifier1(@NotNull Compiler2015Parser.StructOrUnionSpecifier1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code structOrUnionSpecifier1}
	 * labeled alternative in {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnionSpecifier1(@NotNull Compiler2015Parser.StructOrUnionSpecifier1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code structOrUnionSpecifier2}
	 * labeled alternative in {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructOrUnionSpecifier2(@NotNull Compiler2015Parser.StructOrUnionSpecifier2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code structOrUnionSpecifier2}
	 * labeled alternative in {@link Compiler2015Parser#structOrUnionSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructOrUnionSpecifier2(@NotNull Compiler2015Parser.StructOrUnionSpecifier2Context ctx);
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
	 * Enter a parse tree produced by {@link Compiler2015Parser#plainDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterPlainDeclarator(@NotNull Compiler2015Parser.PlainDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#plainDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitPlainDeclarator(@NotNull Compiler2015Parser.PlainDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#declaratorList}.
	 * @param ctx the parse tree
	 */
	void enterDeclaratorList(@NotNull Compiler2015Parser.DeclaratorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#declaratorList}.
	 * @param ctx the parse tree
	 */
	void exitDeclaratorList(@NotNull Compiler2015Parser.DeclaratorListContext ctx);
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
	 * Enter a parse tree produced by the {@code parameterDeclaration1}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration1(@NotNull Compiler2015Parser.ParameterDeclaration1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code parameterDeclaration1}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration1(@NotNull Compiler2015Parser.ParameterDeclaration1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code parameterDeclaration2}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration2(@NotNull Compiler2015Parser.ParameterDeclaration2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code parameterDeclaration2}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration2(@NotNull Compiler2015Parser.ParameterDeclaration2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code parameterDeclaration3}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration3(@NotNull Compiler2015Parser.ParameterDeclaration3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code parameterDeclaration3}
	 * labeled alternative in {@link Compiler2015Parser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration3(@NotNull Compiler2015Parser.ParameterDeclaration3Context ctx);
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
	 * Enter a parse tree produced by the {@code initializer1}
	 * labeled alternative in {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer1(@NotNull Compiler2015Parser.Initializer1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code initializer1}
	 * labeled alternative in {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer1(@NotNull Compiler2015Parser.Initializer1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code initializer2}
	 * labeled alternative in {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void enterInitializer2(@NotNull Compiler2015Parser.Initializer2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code initializer2}
	 * labeled alternative in {@link Compiler2015Parser#initializer}.
	 * @param ctx the parse tree
	 */
	void exitInitializer2(@NotNull Compiler2015Parser.Initializer2Context ctx);
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
	 * Enter a parse tree produced by the {@code iterationStatement1}
	 * labeled alternative in {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement1(@NotNull Compiler2015Parser.IterationStatement1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code iterationStatement1}
	 * labeled alternative in {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement1(@NotNull Compiler2015Parser.IterationStatement1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code iterationStatement2}
	 * labeled alternative in {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement2(@NotNull Compiler2015Parser.IterationStatement2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code iterationStatement2}
	 * labeled alternative in {@link Compiler2015Parser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement2(@NotNull Compiler2015Parser.IterationStatement2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStatement1}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement1(@NotNull Compiler2015Parser.JumpStatement1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStatement1}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement1(@NotNull Compiler2015Parser.JumpStatement1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStatement2}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement2(@NotNull Compiler2015Parser.JumpStatement2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStatement2}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement2(@NotNull Compiler2015Parser.JumpStatement2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code jumpStatement3}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement3(@NotNull Compiler2015Parser.JumpStatement3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code jumpStatement3}
	 * labeled alternative in {@link Compiler2015Parser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement3(@NotNull Compiler2015Parser.JumpStatement3Context ctx);
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
	 * Enter a parse tree produced by the {@code assignmentExpression1}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression1(@NotNull Compiler2015Parser.AssignmentExpression1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentExpression1}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression1(@NotNull Compiler2015Parser.AssignmentExpression1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code assignmentExpression2}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression2(@NotNull Compiler2015Parser.AssignmentExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code assignmentExpression2}
	 * labeled alternative in {@link Compiler2015Parser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression2(@NotNull Compiler2015Parser.AssignmentExpression2Context ctx);
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
	 * Enter a parse tree produced by {@link Compiler2015Parser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void enterEqualityOperator(@NotNull Compiler2015Parser.EqualityOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#equalityOperator}.
	 * @param ctx the parse tree
	 */
	void exitEqualityOperator(@NotNull Compiler2015Parser.EqualityOperatorContext ctx);
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
	 * Enter a parse tree produced by {@link Compiler2015Parser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOperator(@NotNull Compiler2015Parser.RelationalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOperator(@NotNull Compiler2015Parser.RelationalOperatorContext ctx);
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
	 * Enter a parse tree produced by {@link Compiler2015Parser#shiftOperator}.
	 * @param ctx the parse tree
	 */
	void enterShiftOperator(@NotNull Compiler2015Parser.ShiftOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#shiftOperator}.
	 * @param ctx the parse tree
	 */
	void exitShiftOperator(@NotNull Compiler2015Parser.ShiftOperatorContext ctx);
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
	 * Enter a parse tree produced by {@link Compiler2015Parser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveOperator(@NotNull Compiler2015Parser.AdditiveOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#additiveOperator}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveOperator(@NotNull Compiler2015Parser.AdditiveOperatorContext ctx);
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
	 * Enter a parse tree produced by {@link Compiler2015Parser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeOperator(@NotNull Compiler2015Parser.MultiplicativeOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#multiplicativeOperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeOperator(@NotNull Compiler2015Parser.MultiplicativeOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code castExpression1}
	 * labeled alternative in {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression1(@NotNull Compiler2015Parser.CastExpression1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code castExpression1}
	 * labeled alternative in {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression1(@NotNull Compiler2015Parser.CastExpression1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code castExpression2}
	 * labeled alternative in {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression2(@NotNull Compiler2015Parser.CastExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code castExpression2}
	 * labeled alternative in {@link Compiler2015Parser#castExpression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression2(@NotNull Compiler2015Parser.CastExpression2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code typeName1}
	 * labeled alternative in {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName1(@NotNull Compiler2015Parser.TypeName1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code typeName1}
	 * labeled alternative in {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName1(@NotNull Compiler2015Parser.TypeName1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code typeName2}
	 * labeled alternative in {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName2(@NotNull Compiler2015Parser.TypeName2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code typeName2}
	 * labeled alternative in {@link Compiler2015Parser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName2(@NotNull Compiler2015Parser.TypeName2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression1}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression1(@NotNull Compiler2015Parser.UnaryExpression1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression1}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression1(@NotNull Compiler2015Parser.UnaryExpression1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression2}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression2(@NotNull Compiler2015Parser.UnaryExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression2}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression2(@NotNull Compiler2015Parser.UnaryExpression2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression3}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression3(@NotNull Compiler2015Parser.UnaryExpression3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression3}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression3(@NotNull Compiler2015Parser.UnaryExpression3Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression4}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression4(@NotNull Compiler2015Parser.UnaryExpression4Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression4}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression4(@NotNull Compiler2015Parser.UnaryExpression4Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression5}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression5(@NotNull Compiler2015Parser.UnaryExpression5Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression5}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression5(@NotNull Compiler2015Parser.UnaryExpression5Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression6}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression6(@NotNull Compiler2015Parser.UnaryExpression6Context ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpression6}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression6(@NotNull Compiler2015Parser.UnaryExpression6Context ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpression7}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression7(@NotNull Compiler2015Parser.UnaryExpression7Context ctx);

	/**
	 * Exit a parse tree produced by the {@code unaryExpression7}
	 * labeled alternative in {@link Compiler2015Parser#unaryExpression}.
	 *
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression7(@NotNull Compiler2015Parser.UnaryExpression7Context ctx);

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
	 * Enter a parse tree produced by {@link Compiler2015Parser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(@NotNull Compiler2015Parser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(@NotNull Compiler2015Parser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression1}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression1(@NotNull Compiler2015Parser.PrimaryExpression1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression1}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression1(@NotNull Compiler2015Parser.PrimaryExpression1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression2}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression2(@NotNull Compiler2015Parser.PrimaryExpression2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression2}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression2(@NotNull Compiler2015Parser.PrimaryExpression2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression3}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression3(@NotNull Compiler2015Parser.PrimaryExpression3Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression3}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression3(@NotNull Compiler2015Parser.PrimaryExpression3Context ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression4}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression4(@NotNull Compiler2015Parser.PrimaryExpression4Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression4}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression4(@NotNull Compiler2015Parser.PrimaryExpression4Context ctx);
	/**
	 * Enter a parse tree produced by the {@code primaryExpression5}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression5(@NotNull Compiler2015Parser.PrimaryExpression5Context ctx);
	/**
	 * Exit a parse tree produced by the {@code primaryExpression5}
	 * labeled alternative in {@link Compiler2015Parser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression5(@NotNull Compiler2015Parser.PrimaryExpression5Context ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpression(@NotNull Compiler2015Parser.LambdaExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#lambdaExpression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpression(@NotNull Compiler2015Parser.LambdaExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Compiler2015Parser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(@NotNull Compiler2015Parser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link Compiler2015Parser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(@NotNull Compiler2015Parser.ConstantContext ctx);
}