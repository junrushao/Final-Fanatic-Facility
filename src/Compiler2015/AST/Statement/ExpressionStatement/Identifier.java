package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class Identifier extends Expression {
	public String name;
	public Identifier(String name, Type type, boolean isLValue) {
		super(type, isLValue);
		this.name = name;
	}
}
