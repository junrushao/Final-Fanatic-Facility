package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * a /= b
 */
public class DivideAssign extends BinaryExpression {
	public DivideAssign(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String getOperator() {
		return "/=";
	}
}
