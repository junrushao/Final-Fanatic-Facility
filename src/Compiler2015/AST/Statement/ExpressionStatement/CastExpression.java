package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * (castTo)e
 */
public class CastExpression extends Expression {
	public Type castTo;
	public Expression e;
	public CastExpression(Type castTo, Expression e, Type type, boolean isLValue) {
		super(type, isLValue);
		this.castTo = castTo;
		this.e = e;
	}
}
