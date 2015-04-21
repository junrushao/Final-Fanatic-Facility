package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Utility.Utility;

/**
 * if (e) ifTrue else ifFalse
 */

public class IfStatement extends Statement {
	public Expression e;
	public Statement ifTrue, ifFalse;
	public IfStatement(Expression e, Statement ifTrue, Statement ifFalse) {
		this.e = e;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	@Override
	public String toString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("[if]").append(Utility.NEW_LINE);
		return sb.append(e.toString(depth + 1)).append(ifTrue.toString(depth + 1)).append(ifFalse.toString(depth + 1)).toString();
	}
}
