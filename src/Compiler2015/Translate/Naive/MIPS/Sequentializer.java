package Compiler2015.Translate.Naive.MIPS;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;

import java.util.ArrayList;

public final class Sequentializer {
	public static ArrayList<CFGVertex> process() {
		ArrayList<CFGVertex> ret = new ArrayList<>(ControlFlowGraph.vertices);
		ControlFlowGraph.outBody.id = Integer.MAX_VALUE;
		ret.sort((o1, o2) -> o1.id - o2.id);
/*
		int pos, index;
		CFGVertex tmp;

		pos = 0;
		index = ret.indexOf(ControlFlowGraph.root);
		tmp = ret.get(pos);
		ret.set(index, tmp);
		ret.set(pos, ControlFlowGraph.root);

		pos = ret.size() - 1;
		index = ret.indexOf(ControlFlowGraph.outBody);
		tmp = ret.get(pos);
		ret.set(index, tmp);
		ret.set(pos, ControlFlowGraph.outBody);
*/

		return ret;
	}
}
