package Compiler2015.IR.CFG;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRInstruction;

public class ExpressionCFGBuilder {
	public CFGVertex s, t;

	public ExpressionCFGBuilder() {
		s = t = new CFGVertex();
	}

	public void addInstruction(IRInstruction i) {
		if (t.unconditionalNext == null && t.branchIfFalse == null) {
			t.internal.add(i);
		}
		else {
			CFGVertex newT = new CFGVertex();
			if (t.unconditionalNext != null)
				throw new CompilationError("Internal Error.");
			t.unconditionalNext = newT;
			newT = t;
			newT.internal.add(i);
		}
	}

	public void addBlock(CFGVertex sp, CFGVertex tp) {
		if (t.unconditionalNext == null && t.branchIfFalse == null) {
			t.internal.addAll(sp.internal);
			t.unconditionalNext = sp.unconditionalNext;
			t.branchIfFalse = sp.branchIfFalse;
			ControlFlowGraph.deleteVertex(t); // merge two nodes

			sp.internal = null; // help GC
			t = tp;
		}
		else {
			if (t.unconditionalNext != null)
				throw new CompilationError("Internal Error.");
			t.unconditionalNext = sp;
			t = tp;
		}
	}

	public void addBlock(CFGVertex tp) {
		t = tp;
	}
}
