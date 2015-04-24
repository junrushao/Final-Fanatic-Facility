package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.IntType;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Utility;

/**
 * while (e) a;
 */
public class WhileStatement extends Statement {
	public Expression e;
	public Statement a;
	public WhileStatement(Expression e, Statement a) {
		if (!CastExpression.castable(e.type, new IntType()))
			throw new CompilationError("Expression inside while statement could not be converted to int type.");
		this.e = e;
		this.a = a;
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("WHILE").append(Utility.NEW_LINE);
		String aa = e.deepToString(depth + 1);
		String bb = a.deepToString(depth + 1);
		return sb.append(aa).append(Utility.NEW_LINE).append(bb).toString();
	}

	@Override
	public String toString() {
		return null;
	}
}
