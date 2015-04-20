package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * a *= b
 */
public class MultiplyAssign extends AssignClass {
	public MultiplyAssign(Expression left, Expression right) {
		super(left, right);
	}
}
