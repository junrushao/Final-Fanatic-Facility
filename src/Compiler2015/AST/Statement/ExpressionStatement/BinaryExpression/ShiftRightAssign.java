package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * a >>= b
 */
public class ShiftRightAssign extends BinaryExpression {
	public ShiftRightAssign(Expression left, Expression right) {
		super(left, right);
	}
}
