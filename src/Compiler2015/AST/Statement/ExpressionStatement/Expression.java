package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Statement.Statement;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

public abstract class Expression extends Statement {
	public Type type = null;
	public boolean isLValue = false;
	public int tempRegister = -1;

	public static Integer toInt(Expression x) {
		if (x instanceof IntConstant)
			return ((IntConstant) x).c;
		if (x instanceof CharConstant)
			return (int) (((CharConstant) x).c);
		return null;
	}

	@Override
	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).append(Utility.NEW_LINE).toString();
	}
}
