package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

public class IntConstant extends Expression {
	public Integer c;
	public IntConstant(Integer c, Type type, boolean isLValue) {
		super(type, isLValue);
		this.c = c;
	}
}
