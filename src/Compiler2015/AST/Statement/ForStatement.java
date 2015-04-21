package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Utility.Utility;

/**
 * for (a; b; c) d
 */
public class ForStatement extends Statement {
	public Expression a, b, c;
	public Statement d;
	public ForStatement(Expression a, Expression b, Expression c, Statement d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	@Override
	public String toString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("[for]").append(Utility.NEW_LINE);
		return sb.append(a.toString(depth + 1)).append(b.toString(depth + 1)).append(c.toString(depth + 1)).append(d.toString(depth + 1)).toString();
	}
}
