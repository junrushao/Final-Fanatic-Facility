package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.IntType;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Utility;

/**
 * if (e) ifTrue else ifFalse
 */

public class IfStatement extends Statement {
	public Expression e;
	public Statement ifTrue, ifFalse;
	public IfStatement(Expression e, Statement ifTrue, Statement ifFalse) {
		if (!CastExpression.castable(e.type, new IntType()))
			throw new CompilationError("Expression inside if statement could not be converted to int type.");
		this.e = e;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("IF");
		StringBuilder indent = Utility.getIndent(depth + 1);
		String aa = e == null ? indent.append("null").toString() : e.deepToString(depth + 1);
		String bb = ifTrue == null ? indent.append("null").append(Utility.NEW_LINE).toString() : ifTrue.deepToString(depth + 1);
		String cc = ifFalse == null ? indent.append("null").append(Utility.NEW_LINE).toString() : ifFalse.deepToString(depth + 1);
		return sb.append(indent).append(aa).append(Utility.NEW_LINE)
				.append(indent).append(bb)
				.append(indent).append(cc).toString();
	}

	@Override
	public String toString() {
		return deepToString(0);
	}
}
