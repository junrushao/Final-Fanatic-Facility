package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 *
 */
public class StringConstant extends Expression {
	public String c;
	public StringConstant(String c, Type type, boolean isLValue) {
		super(type, isLValue);
		this.c = c;
	}

	@Override
	public int sizeof() {
		return c.length() + 1;
	}
}
