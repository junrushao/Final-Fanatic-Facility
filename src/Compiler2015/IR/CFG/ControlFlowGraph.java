package Compiler2015.IR.CFG;

import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.FunctionTableEntry;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Def;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Instruction.NopForBranch;
import Compiler2015.IR.Optimizer.NaiveDeadCodeElimination;
import Compiler2015.IR.StaticSingleAssignment.PhiPlacer;
import Compiler2015.IR.StaticSingleAssignment.SSADestroyer;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ControlFlowGraph {

	public static ControlFlowGraph instance = null;

	public FunctionTableEntry functionTableEntry;

	// for vertices allocation
	public int tempVertexCount;
	public HashSet<CFGVertex> vertices;
	public CFGVertex source, sink;

	public ControlFlowGraph(FunctionTableEntry functionTableEntry) {
		ControlFlowGraph.instance = this;
		this.functionTableEntry = functionTableEntry;
		this.tempVertexCount = 0;
		this.vertices = new HashSet<>();
		this.source = null;
		this.sink = getNewVertex();

		// emit control flow graph
		functionTableEntry.scope.emitCFG();
		source = functionTableEntry.scope.beginCFGBlock;
		if (functionTableEntry.scope.endCFGBlock.unconditionalNext == null)
			functionTableEntry.scope.endCFGBlock.unconditionalNext = sink;

		// add if condition
		vertices.stream().forEach(x -> {
			if (x.branchIfFalse != null && x.branchRegister == null) {
				if (x.internal.isEmpty())
					System.err.println("fuck id = " + x.id);
				x.branchRegister = x.internal.get(x.internal.size() - 1).rd.clone();
			}
			if (x.branchIfFalse != null) {
				x.internal.add(new NopForBranch(x.branchRegister));
				x.branchRegister = null;
			}
		});

		// prune CFG
		GraphManipulate.mergeBlocks(this);
		GraphManipulate.splitEdges(this);
		GraphManipulate.reCalculateGraphInfo(this);

		// define external variables and parameters passed at source
		defineExternalVariables();

		// do dead code elimination
		NaiveDeadCodeElimination.process(this, true);

		// insert phi functions
		PhiPlacer.process(this);

		// destroy ssa
		SSADestroyer.process(this);
		GraphManipulate.mergeBlocks(this);

		ControlFlowGraph.instance = null;
	}

	public void defineExternalVariables() {
		CompoundStatement scope = functionTableEntry.scope;
		ArrayList<IRInstruction> instructions = scope.givenVariables.stream().map(x -> new Def(new VirtualRegister(x))).collect(Collectors.toCollection(ArrayList::new));
		Environment.symbolNames.table.stream().filter(e -> e != null && e.scope == 1 && (e.type == Tokens.VARIABLE || e.type == Tokens.STRING_CONSTANT)).forEach(e -> instructions.add(new Def(new VirtualRegister(e.uId))));
		instructions.add(new Def(new VirtualRegister(0)));
		instructions.addAll(source.internal);
		source.internal = instructions;
	}

	public CFGVertex getNewVertex() {
		CFGVertex ret = new CFGVertex();
		vertices.add(ret);
		ret.id = tempVertexCount++;
		return ret;
	}

	public CFGVertex getNewTempVertex() {
		CFGVertex ret = new CFGVertex();
		ret.id = -1;
		return ret;
	}

	@Override
	public String toString() {
		if (functionTableEntry.uId == 6)
			return "CFG of printf omitted";
		StringBuilder sb = new StringBuilder();
		sb.append("CFG of Function #").append(functionTableEntry.uId).append(Utility.NEW_LINE);
		sb.append(Utility.getIndent(1)).
				append("in = ").append(source.id).
				append(", out = ").append(sink.id).
				append(Utility.NEW_LINE);
		DepthFirstSearcher.getReachable(vertices, source).stream().forEach(x -> sb.append(x.toString()));
		return sb.toString();
	}
}
