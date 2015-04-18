package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

public abstract class Constant extends Expression {
	public Constant(Type type, boolean isLValue) {
		super(type, isLValue);
	}

	public abstract int toInt();
}
