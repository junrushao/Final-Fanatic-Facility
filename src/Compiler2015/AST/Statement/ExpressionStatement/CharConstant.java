package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class CharConstant extends Expression {
	public Character c;
	public CharConstant(Character c, Type type, boolean isLValue) {
		super(type, isLValue);
		this.c = c;
	}
}
