package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * Created by junrushao on 15-4-11.
 */
public class UnaryExpression extends Expression {
	Expression e;
	public UnaryExpression(Expression e) {
		this.e = e;
	}
}
