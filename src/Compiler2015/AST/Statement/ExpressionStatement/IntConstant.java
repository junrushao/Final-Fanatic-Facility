package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.IntType;
import Compiler2015.Exception.CompilationError;

public class IntConstant extends Constant {
	public Integer c;

	public IntConstant(Integer c) {
		this.type = new IntType();
		this.c = c;
	}

	public static Expression getExpression(String s, int radixBase) {
		try {
			return new IntConstant(Integer.parseInt(s, radixBase));
		} catch (NumberFormatException e) {
			throw new CompilationError("Number format error.");
		}
	}
}
