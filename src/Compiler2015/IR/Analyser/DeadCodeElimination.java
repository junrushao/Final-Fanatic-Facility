package Compiler2015.IR.Analyser;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.*;

import java.util.ArrayList;

public final class DeadCodeElimination {

	public static boolean usedAfter(ArrayList<IRInstruction> list, int pos, VirtualRegister r) {
		for (int i = pos, size = list.size(); i < size; ++i) {
			for (VirtualRegister rp : list.get(i).getAllSSAUse())
				if (r.equals(rp))
					return true;
		}
		return false;
	}

	public static void process() {
		DataFlow.analyse();
		// def not used below in the same block, and not live out the block
		for (CFGVertex block : ControlFlowGraph.vertices) {
			for (int i = 0, size = block.phiBlock.size(); i < size; ++i) {
				boolean allDefUseless = true;
				IRInstruction ins = block.phiBlock.get(i);
				VirtualRegister defs[] = ins.getAllSSADef();
				if (defs.length == 0)
					continue;
				for (VirtualRegister r : defs)
					if (r != null) {
						if (r.uId == 0) {
							allDefUseless = false;
							break;
						}
						if (usedAfter(block.phiBlock, i + 1, r)) {
							allDefUseless = false;
							break;
						}
						if (usedAfter(block.internal, 0, r)) {
							allDefUseless = false;
							break;
						}
						if (block.liveOut.contains(r)) {
							allDefUseless = false;
							break;
						}
					}
				if (allDefUseless) {
					System.err.println("replace " + ins.getClass().getName() + " with Nop.");
					block.phiBlock.set(i, Nop.instance);
				}
			}
			for (int i = 0, size = block.internal.size(); i < size; ++i) {
				boolean allDefUseless = true;
				IRInstruction ins = block.internal.get(i);
				if (ins instanceof Call || ins instanceof FetchReturn || ins instanceof Nop || ins instanceof NopForBranch || ins instanceof PushStack || ins instanceof SetReturn)
					continue;
				VirtualRegister defs[] = ins.getAllSSADef();
				if (defs.length == 0)
					throw new CompilationError("Internal Error.");
				for (VirtualRegister r : defs)
					if (r != null) {
						if (r.uId == 0) {
							allDefUseless = false;
							break;
						}
						if (usedAfter(block.internal, i + 1, r)) {
							allDefUseless = false;
							break;
						}
						if (block.liveOut.contains(r)) {
							allDefUseless = false;
							break;
						}
					}
				if (allDefUseless) {
					block.internal.set(i, Nop.instance);
				}
			}
		}
	}
}
