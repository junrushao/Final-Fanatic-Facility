package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * a |= b
 */
public class BitwiseOrAssign extends BinaryExpression {
	public BitwiseOrAssign(Expression left, Expression right) {
		super(left, right);
	}
}
