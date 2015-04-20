package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * a <<= b;
 */
public class ShiftLeftAssign extends BinaryExpression {
	public ShiftLeftAssign(Expression left, Expression right) {
		super(left, right);
	}
}
