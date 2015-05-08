package Compiler2015.IR.CFG;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.Instruction.IRInstruction;

import java.util.ArrayList;

public class ExpressionCFGBuilder {
	public CFGVertex s, t;

	public ExpressionCFGBuilder() {
		s = t = ControlFlowGraph.getNewVertex();
	}

	public void addInstruction(IRInstruction i) {
		if (t.unconditionalNext == null && t.branchIfFalse == null) {
			t.internal.add(i);
		}
		else {
			CFGVertex newT = ControlFlowGraph.getNewVertex();
			if (t.unconditionalNext != null)
				throw new CompilationError("Internal Error.");
			t.unconditionalNext = newT;
			newT = t;
			newT.internal.add(i);
		}
	}

	public void addBlock(CFGVertex sp, CFGVertex tp) {
		if (sp == null || tp == null)
			throw new CompilationError("Internal Error.");
		if (t.unconditionalNext == null && t.branchIfFalse == null) {
			t.internal.addAll(sp.internal);
			sp.internal = new ArrayList<>(0);
			t.unconditionalNext = sp;
			t.branchIfFalse = sp.branchIfFalse;
			sp.branchIfFalse = null;
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
