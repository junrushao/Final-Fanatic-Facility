package Compiler2015.IR.CFG;

import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Instruction.NopForBranch;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DepthFirstSearcher {
	public static int timeStamp;

	public static void depthFirstSearch(CFGVertex vertex) {
		CFGVertex child;
		vertex.id = ++timeStamp;

		child = vertex.unconditionalNext;
		if (child != null && child.id == -1) {
			child.parent = vertex;
			depthFirstSearch(child);
		}

		child = vertex.branchIfFalse;
		if (child != null && child.id == -1) {
			int size = child.internal.size();
			boolean doSearch = true;
			if (size != 0) {
				IRInstruction ins = child.internal.get(size - 1);
				if (ins instanceof NopForBranch) {
					NopForBranch branchIns = (NopForBranch) ins;
					if (branchIns.rs instanceof ImmediateValue)
						if (((ImmediateValue) branchIns.rs).a == 0)
							doSearch = false;
				}
			}
			child.parent = vertex;
			if (doSearch)
				depthFirstSearch(child);
		}
	}

	public static int process(HashSet<CFGVertex> vertices, CFGVertex root) {
		timeStamp = 0;
		vertices.stream().forEach(x -> x.id = -1);
		depthFirstSearch(root);
		return timeStamp;
	}

	public static List<CFGVertex> getReachable(HashSet<CFGVertex> vertices, CFGVertex root) {
		process(vertices, root);
		return vertices.stream().filter(x -> x.id != -1).collect(Collectors.toList());
	}

}
