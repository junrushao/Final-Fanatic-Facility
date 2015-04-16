package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * 'c'
 */
public class CharConstant extends Expression {
	public Character c;
	public CharConstant(Character c, Type type, boolean isLValue) {
		super(type, isLValue);
		this.c = c;
	}
}
