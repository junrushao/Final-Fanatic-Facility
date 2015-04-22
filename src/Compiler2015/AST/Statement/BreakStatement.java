package Compiler2015.AST.Statement;

import Compiler2015.Utility.Utility;

/**
 * break;
 */
public class BreakStatement extends Statement {
	public Statement breakTo;

	public BreakStatement(Statement breakTo) {
		this.breakTo = breakTo;
	}

	@Override
	public String toString(int depth) {
		return Utility.getIndent(depth).append("break;").append(Utility.NEW_LINE).toString();
	}
}
