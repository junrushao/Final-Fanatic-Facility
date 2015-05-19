package Compiler2015.AST.Statement;

import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Utility.Utility;

import java.util.HashMap;

/**
 * continue;
 */
public class ContinueStatement extends Statement {
	public Loop continueTo;

	public ContinueStatement(Loop continueTo) {
		this.continueTo = continueTo;
	}

	@Override
	public String toString() {
		return "continue";
	}

	@Override
	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).append(Utility.NEW_LINE).toString();
	}

	@Override
	public void emitCFG() {
		beginCFGBlock = endCFGBlock = ControlFlowGraph.getNewVertex();
		endCFGBlock.unconditionalNext = continueTo.getLoop();
	}

	@Override
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {

	}
}
