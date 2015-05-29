package Compiler2015.Translate;

import Compiler2015.Environment.Environment;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Translate.Naive.MIPS.NaiveSequentializer;

import java.io.PrintWriter;
import java.util.ArrayList;

public final class Translator extends BaseTranslator {

	public Translator(ControlFlowGraph graph) {
		super(graph);
	}

	@Override
	public void generateFunction(PrintWriter out) {
		scanVirtualRegister(); // calculate space of stack frame

		if (Environment.symbolNames.table.get(graph.functionTableEntry.uId).name.equals("main"))
			out.println("main:");

		// enter a function
		out.println(getFunctionLabel());
		out.println("\taddu $sp, $sp, " + (-frameSize));
		out.println("\tsw $ra, 128($sp)");
		ArrayList<CFGVertex> sequence = NaiveSequentializer.process(graph);

		for (int itrBlock = 0, itrEnd = sequence.size(); itrBlock < itrEnd; ++itrBlock) {
		}

		// exit a function
		out.println("\tlw $ra, 128($sp)");
		out.println("\taddu $sp, $sp, " + frameSize);
		out.println("\tjr $ra");
	}


}
