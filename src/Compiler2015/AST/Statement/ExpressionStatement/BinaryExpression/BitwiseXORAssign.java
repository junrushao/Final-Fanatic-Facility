package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * a ^= b
 */
public class BitwiseXORAssign extends BinaryExpression {
	public BitwiseXORAssign(Expression left, Expression right) {
		super(left, right);
	}
}
