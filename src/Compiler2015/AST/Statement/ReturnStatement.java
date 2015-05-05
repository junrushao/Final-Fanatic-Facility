package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Utility.Utility;

/**
 * return e
 */
public class ReturnStatement extends Statement {
	public Expression e;
	public ReturnStatement(Expression e) {
		this.e = e;
	}

	@Override
	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).toString();
	}

	@Override
	public String toString() {
		String s = e == null ? "null" : e.toString();
		return "return " + s + Utility.NEW_LINE;
	}

	@Override
	public void emitCFG() {
		if (e == null) {
			beginCFGBlock = endCFGBlock = ControlFlowGraph.getNewVertex();
		}
		else {
			e.emitCFG();
			beginCFGBlock = endCFGBlock = e.endCFGBlock;
		}
		endCFGBlock.unconditionalNext = Environment.functionOutVertex;
		endCFGBlock.branchIfFalse = null;
	}
}
