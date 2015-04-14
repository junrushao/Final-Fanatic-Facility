package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class IntConstant extends Expression {
	public Integer c;
	public IntConstant(Integer c, Type type, boolean isLValue) {
		super(type, isLValue);
		this.c = c;
	}
}
