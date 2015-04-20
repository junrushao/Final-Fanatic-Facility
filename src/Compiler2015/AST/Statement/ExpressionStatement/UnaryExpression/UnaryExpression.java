package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

public class UnaryExpression extends Expression {
	public Expression e;

	public UnaryExpression(Expression e) {
		this.e = e;
	}
}
