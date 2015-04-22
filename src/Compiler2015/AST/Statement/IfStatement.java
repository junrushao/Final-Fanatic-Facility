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
	public String toString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("[if]").append(Utility.NEW_LINE);
		sb.append(e.toString(depth + 1)).append(ifTrue.toString(depth + 1));
		if (ifFalse != null)
			sb.append(ifFalse.toString(depth + 1));
		return sb.toString();
	}
}
