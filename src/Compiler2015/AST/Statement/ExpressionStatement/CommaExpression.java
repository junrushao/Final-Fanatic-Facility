package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * a, b, c
 */
public class CommaExpression extends Expression {
	public Expression e1, e2;

	public CommaExpression(Expression e1, Expression e2, Type type, boolean isLValue) {
		super(type, isLValue);
		this.e1 = e2;
		this.e2 = e2;
	}
}
