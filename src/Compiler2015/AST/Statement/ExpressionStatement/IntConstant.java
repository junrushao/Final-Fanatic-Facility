package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.Type.IntType;

public class IntConstant extends Constant {
	public Integer c;

	public IntConstant(Integer c) {
		this.type = new IntType();
		this.c = c;
	}

	public static Expression getExpression(String s, int radixBase) {
		try {
			if (radixBase == 16)
				s = s.substring(2);
			return new IntConstant(Integer.parseInt(s, radixBase));
		} catch (NumberFormatException e) {
			throw new CompilationError("Number format error.");
		}
	}

	@Override
	public String toString() {
		return Integer.toString(c);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		tempRegister = Environment.getImmRegister(builder, c);
	}
}
