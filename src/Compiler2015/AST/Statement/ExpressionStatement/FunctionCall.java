package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.FunctionPointerType;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-11.
 *
 * f(...)
 */
public class FunctionCall extends Expression {
	public FunctionPointerType function;
	public ArrayList<Expression> argumentExpressionList;
	public FunctionCall(FunctionPointerType function, ArrayList<Expression> argumentExpressionList) {
		this.function = function;
		this.argumentExpressionList = argumentExpressionList;
	}
}
