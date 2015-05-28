package Compiler2015.IR.Optimizer;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Instruction.Nop;

import java.util.ArrayList;
import java.util.stream.Collectors;

public final class EliminateNop {

	public static ArrayList<IRInstruction> eliminateNop(ArrayList<IRInstruction> block) {
		return (ArrayList<IRInstruction>) block.stream().filter(ins -> !(ins instanceof Nop)).collect(Collectors.toList());
	}

	public static void process() {
		for (CFGVertex block : ControlFlowGraph.vertices) {
			block.phiBlock = eliminateNop(block.phiBlock);
			block.internal = eliminateNop(block.internal);
		}
	}
}
