package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.FunctionPointerType;
import Compiler2015.AST.Type.Type;

/**
 * f(...)
 */
public class FunctionCall extends Expression {
	public FunctionPointerType function;
	public Expression argumentExpressionList[];

	public FunctionCall(FunctionPointerType function, Expression[] argumentExpressionList, Type type, boolean isLValue) {
		super(type, isLValue);
		this.function = function;
		this.argumentExpressionList = argumentExpressionList;
	}
}
