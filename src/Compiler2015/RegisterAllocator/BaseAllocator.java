package Compiler2015.RegisterAllocator;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseAllocator {
	public ControlFlowGraph graph;
	public HashMap<Integer, MachineRegister> mapping;
	public HashSet<MachineRegister> physicalRegistersUsed;

	public BaseAllocator(ControlFlowGraph graph) {
		this.graph = graph;
		this.mapping = null;
		this.physicalRegistersUsed = null;
		graph.touchGraph();
	}

	public void DFSInDominatorTree(CFGVertex t, ArrayList<CFGVertex> blockList) {
		blockList.add(t);
		t.children.forEach(x -> DFSInDominatorTree(x, blockList));
	}

	public void collectPhysicalRegistersUsed() {
		physicalRegistersUsed = new HashSet<>();
		physicalRegistersUsed.addAll(mapping.entrySet().stream().map(Map.Entry::getValue).filter(x -> x != null).collect(Collectors.toList()));
	}
}
