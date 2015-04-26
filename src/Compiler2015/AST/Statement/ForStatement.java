package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.IntType;
import Compiler2015.Utility.Utility;

/**
 * for (a; b; c) d
 */
public class ForStatement extends Statement {
	public Expression a, b, c;
	public Statement d;

	public ForStatement(Expression a, Expression b, Expression c) {
		if (b != null && !CastExpression.castable(b.type, new IntType()))
			throw new CompilationError("Expression inside for statement could not be converted to int type.");
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = null;
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("FOR").append(Utility.NEW_LINE);
		StringBuilder indent = Utility.getIndent(depth + 1);
		String aa = a == null ? indent.append("null").toString() : a.deepToString(depth + 1);
		String bb = b == null ? indent.append("null").toString() : b.deepToString(depth + 1);
		String cc = c == null ? indent.append("null").toString() : c.deepToString(depth + 1);
		String dd = d == null ? indent.append("null").toString() : d.deepToString(depth + 1);
		return sb.append(aa)
				.append(bb)
				.append(cc)
				.append(dd).toString();
	}

	@Override
	public String toString() {
		return deepToString(0);
	}
}
