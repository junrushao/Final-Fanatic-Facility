package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.CharType;
import Compiler2015.Exception.CompilationError;

/**
 * 'c'
 */
public class CharConstant extends Constant {
	public Character c;

	public CharConstant(Character c) {
		this.c = c;
		this.type = new CharType();
	}

	public static Expression getExpression(String s) {
		s = StringConstant.convert(s);
		if (s.length() == 0)
			throw new CompilationError("Empty character constant.");
		return new CharConstant(s.charAt(0));
	}

	@Override
	public String toString() {
		return Character.toString(c);
	}
}
