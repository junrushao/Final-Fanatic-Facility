package Compiler2015.Translate.Naive.MIPS;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;

import java.util.ArrayList;

public final class NaiveSequentializer {
	public static ArrayList<CFGVertex> process(ControlFlowGraph graph) {
		ArrayList<CFGVertex> ret = new ArrayList<>(graph.vertices);
		int backupSinkId = graph.sink.id;
		graph.sink.id = Integer.MAX_VALUE;
		ret.sort((o1, o2) -> o1.id - o2.id);
		graph.sink.id = backupSinkId;
		return ret;
	}
}
