package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Utility.Utility;

/**
 * return e
 */
public class ReturnStatement extends Statement {
	public Expression e;
	public ReturnStatement(Expression e) {
		this.e = e;
	}

	@Override
	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).toString();
	}

	@Override
	public String toString() {
		String s = e == null ? "null" : e.toString();
		return "return " + s + Utility.NEW_LINE;
	}
}
