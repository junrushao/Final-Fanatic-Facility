package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

public abstract class UnaryExpression extends Expression {
	public Expression e;

	public UnaryExpression(Expression e) {
		this.e = e;
	}

	public abstract String getOperator();

	@Override
	public String toString() {
		return String.format("(%s %s)", getOperator(), e.toString());
	}

	@Override
	public UnaryExpression clone() {
		UnaryExpression ret = (UnaryExpression) super.clone();
		ret.e = ret.e.clone();
		return ret;
	}

}
