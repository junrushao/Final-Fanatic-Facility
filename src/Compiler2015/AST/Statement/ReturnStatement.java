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
	public String toString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("[return]").append(Utility.NEW_LINE);
		if (e != null)
			sb.append(e.toString(depth + 1));
		return sb.toString();
	}
}
