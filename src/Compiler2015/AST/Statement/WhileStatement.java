package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Utility.Utility;

/**
 * while (e) a;
 */
public class WhileStatement extends Statement {
	public Expression e;
	public Statement a;
	public WhileStatement(Expression e, Statement a) {
		this.e = e;
		this.a = a;
	}

	@Override
	public String toString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("[while]").append(Utility.NEW_LINE);
		return sb.append(e.toString(depth + 1)).append(a.toString(depth + 1)).toString();
	}
}
