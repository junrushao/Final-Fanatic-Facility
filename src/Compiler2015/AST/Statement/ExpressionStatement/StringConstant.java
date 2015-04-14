package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class StringConstant extends Expression {
	public String c;
	public StringConstant(String c, Type type, boolean isLValue) {
		super(type, isLValue);
		this.c = c;
	}
}
