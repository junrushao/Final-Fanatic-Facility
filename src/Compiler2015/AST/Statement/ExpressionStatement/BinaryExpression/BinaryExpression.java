package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

public abstract class BinaryExpression extends Expression {
	public Expression left, right;

	public BinaryExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
		this.type = left.type;
		this.isLValue = false;
	}

	public abstract String getOperator();
}
