package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.IntType;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Utility;

/**
 * for (a; b; c) d
 */
public class ForStatement extends Statement {
	public Expression a, b, c;
	public Statement d;
	public ForStatement(Expression a, Expression b, Expression c, Statement d) {
		if (!CastExpression.castable(b.type, new IntType()))
			throw new CompilationError("Expression inside for statement could not be converted to int type.");
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
