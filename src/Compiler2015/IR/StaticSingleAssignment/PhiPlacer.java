package Compiler2015.IR.StaticSingleAssignment;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.Instruction.IRInstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public final class PhiPlacer {

	public static void process() {
		HashSet<Integer> globals;
		HashMap<Integer, HashSet<CFGVertex>> blocks;

		globals = new HashSet<>();
		blocks = new HashMap<>();

		for (CFGVertex block : ControlFlowGraph.vertices) {
			HashSet<Integer> varKill = new HashSet<>();
			for (IRInstruction ins : block.internal) {
				for (int x : ins.getAllUse())
					if (x != -1 && !varKill.contains(x))
						globals.add(x);
				for (int x : ins.getAllDef())
					if (x != -1) {
						varKill.add(x);
						if (!blocks.containsKey(x))
							blocks.put(x, new HashSet<>());
						blocks.get(x).add(block);
					}
			}
		}

		for (int x : globals) {
			ArrayList<CFGVertex> worklist = new ArrayList<>(blocks.get(x));
			HashSet<CFGVertex> alreadyProcessedBlocks = new HashSet<>(worklist);
			HashSet<CFGVertex> alreadyContainPhi = new HashSet<>();
			for (int i = 0; i < worklist.size(); ++i) {
				CFGVertex b = worklist.get(i);
				for (CFGVertex d : b.dominanceFrontier) {
					if (alreadyContainPhi.contains(d))
						continue;
					d.addPhi(x);
					alreadyContainPhi.add(d);
					if (!alreadyProcessedBlocks.contains(d)) {
						alreadyProcessedBlocks.add(d);
						worklist.add(d);
					}
				}
			}
		}
	}
}
