package Compiler2015.AST.Statement;

import Compiler2015.Utility.Utility;

/**
 * continue;
 */
public class ContinueStatement extends Statement {
	public Statement continueTo;

	public ContinueStatement(Statement continueTo) {
		this.continueTo = continueTo;
	}

	@Override
	public String toString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("[continue]").append(Utility.NEW_LINE);
		return sb.toString();
	}
}
