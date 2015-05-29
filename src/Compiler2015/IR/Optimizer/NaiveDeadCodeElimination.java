package Compiler2015.IR.Optimizer;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.*;

import java.util.ArrayList;

public final class NaiveDeadCodeElimination {

	public static boolean usedAfter(ArrayList<IRInstruction> list, int pos, VirtualRegister r) {
		for (int i = pos, size = list.size(); i < size; ++i)
			for (VirtualRegister rp : list.get(i).getAllUseVR())
				if (r.equals(rp))
					return true;
		return false;
	}

	public static int onePass(boolean deleteDef) {
		int deleted = 0;
		DataFlow.livenessAnalysis();
		// def not used below in the same block, and not live out the block
		for (CFGVertex block : ControlFlowGraph.vertices) {
			for (int i = 0, size = block.phiBlock.size(); i < size; ++i) {
				boolean allDefUseless = true;
				IRInstruction ins = block.phiBlock.get(i);
				if (ins instanceof Call || ins instanceof FetchReturn || ins instanceof Nop || ins instanceof NopForBranch || ins instanceof PushStack || ins instanceof SetReturn)
					continue;
				if (!deleteDef && ins instanceof Def)
					continue;
				if (ins instanceof Move && ins.rd.equals(((Move) ins).rs)) {
					block.phiBlock.set(i, Nop.instance);
					continue;
				}
				VirtualRegister defs[] = ins.getAllDefVR();
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
					block.phiBlock.set(i, Nop.instance);
					++deleted;
				}
			}
			for (int i = 0, size = block.internal.size(); i < size; ++i) {
				boolean allDefUseless = true;
				IRInstruction ins = block.internal.get(i);
				if (ins instanceof Call || ins instanceof FetchReturn || ins instanceof Nop || ins instanceof NopForBranch || ins instanceof PushStack || ins instanceof SetReturn)
					continue;
				if (!deleteDef && ins instanceof Def)
					continue;
				if (ins instanceof Move && ins.rd.equals(((Move) ins).rs)) {
					block.internal.set(i, Nop.instance);
					continue;
				}
				VirtualRegister defs[] = ins.getAllDefVR();
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
					++deleted;
				}
			}
		}
		EliminateNop.process();
		return deleted;
	}

	public static void process(boolean deleteDef) {
		for (int deleted; ; ) {
			deleted = NaiveDeadCodeElimination.onePass(deleteDef);
			if (deleted == 0)
				break;
		}
	}

}
