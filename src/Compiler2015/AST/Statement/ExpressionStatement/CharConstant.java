package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * 'c'
 */
public class CharConstant extends Constant {
	public Character c;
	public CharConstant(Character c, Type type, boolean isLValue) {
		super(type, isLValue);
		this.c = c;
	}

	@Override
	public int toInt() {
		return (int) c;
	}
}
