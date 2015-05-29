package Compiler2015.RegisterAllocator;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;

import java.util.ArrayList;
import java.util.HashMap;

public class NaiveAllocator extends BaseAllocator {

	public NaiveAllocator(ControlFlowGraph graph) {
		super(graph);

		ArrayList<CFGVertex> blockList = new ArrayList<>();
		DFSInDominatorTree(graph.source, blockList);

		mapping = new HashMap<>();
		for (CFGVertex block : blockList) {
			if (!block.phiBlock.isEmpty())
				throw new CompilationError("Internal Error.");
			for (IRInstruction i : block.internal) {
				for (VirtualRegister r : i.getAllUseVR())
					if (r != null && r.uId > 0)
						mapping.put(r.uId, null);
				for (VirtualRegister r : i.getAllDefVR())
					if (r != null && r.uId > 0)
						mapping.put(r.uId, null);
			}
		}
	}

}
