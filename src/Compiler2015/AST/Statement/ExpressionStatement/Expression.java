package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.SizeMeasurable;
import Compiler2015.AST.Statement.Statement;
import Compiler2015.AST.Type.Type;

public class Expression extends Statement implements SizeMeasurable {
	public Type type;
	public boolean isLValue;

	public Expression(Type type, boolean isLValue) {
		this.type = type;
		this.isLValue = isLValue;
	}

	@Override
	public int sizeof() {
		return type.sizeof();
	}
}
