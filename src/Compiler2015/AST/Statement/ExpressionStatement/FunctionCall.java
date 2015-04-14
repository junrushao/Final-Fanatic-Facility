package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.FunctionPointerType;

/**
 * Created by junrushao on 15-4-11.
 *
 * f(...)
 */
public class FunctionCall extends Expression {
	public FunctionPointerType function;
	public Expression argumentExpressionList[];

	public FunctionCall(FunctionPointerType function, Expression[] argumentExpressionList) {
		this.function = function;
		this.argumentExpressionList = argumentExpressionList;
	}
}
