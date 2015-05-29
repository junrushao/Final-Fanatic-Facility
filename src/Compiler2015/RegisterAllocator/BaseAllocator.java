package Compiler2015.RegisterAllocator;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseAllocator {
	public ControlFlowGraph graph;
	public HashMap<Integer, MachineRegister> mapping;

	public BaseAllocator(ControlFlowGraph graph) {
		this.graph = graph;
		this.mapping = null;
	}

	public void DFSInDominatorTree(CFGVertex t, ArrayList<CFGVertex> blockList) {
		blockList.add(t);
		t.children.forEach(x -> DFSInDominatorTree(x, blockList));
	}
}
