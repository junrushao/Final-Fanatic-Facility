package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.Type.CharType;

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

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		tempRegister = Environment.getImmRegister(builder, (int)c);
	}
}
