package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * a -= b
 */
public class SubtractAssign extends BinaryExpression {
	public SubtractAssign(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String getOperator() {
		return "-=";
	}
}
