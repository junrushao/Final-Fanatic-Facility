package Compiler2015.IR.CFG;

import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.StaticSingleAssignment.LengauerTarjan;
import Compiler2015.IR.CFG.StaticSingleAssignment.PhiInserter;
import Compiler2015.IR.CFG.StaticSingleAssignment.RegisterManager;
import Compiler2015.IR.Instruction.Pop;
import Compiler2015.Utility.Utility;

import java.util.HashSet;

public class ControlFlowGraph {

	public static HashSet<CFGVertex> vertices;

	public static CFGVertex root, outBody;
	public static int nowUId;
	public static int tempVertexCount;
	public static CompoundStatement scope;

	static {
		vertices = new HashSet<>();
		root = outBody = null;
		nowUId = 0;
	}

	public static void checkForDebug() {
		for (CFGVertex x : vertices) {
			if (x.internal.isEmpty() && x.branchIfFalse != null)
				throw new CompilationError("Internal Error.");
			if (x.unconditionalNext == null && x.branchIfFalse != null)
				throw new CompilationError("Internal Error.");
			if (x != outBody && x.unconditionalNext == null)
				throw new CompilationError("Internal Error.");
		}
	}

	public static CFGVertex getNewVertex() {
		CFGVertex ret = new CFGVertex();
		vertices.add(ret);
		ret.id = tempVertexCount++;
		return ret;
	}

	public static void findGoto(CFGVertex x) {
		if (x == outBody)
			return;
		if (x.unconditionalNext != null && x.unconditionalNext != outBody && x.unconditionalNext.internal.isEmpty()) {
			findGoto(x.unconditionalNext);
			x.unconditionalNext = x.unconditionalNext.unconditionalNext;
		}
		if (x.branchIfFalse != null && x.branchIfFalse != outBody && x.branchIfFalse.internal.isEmpty()) {
			findGoto(x.branchIfFalse);
			x.branchIfFalse = x.branchIfFalse.unconditionalNext;
		}
	}

	public static void process(CompoundStatement _scope, CompoundStatement body, int uId) {
		nowUId = uId;
		tempVertexCount = 0;
		vertices.clear();
		scope = _scope;

		root = null;
		outBody = ControlFlowGraph.getNewVertex();
		outBody.internal.add(Pop.instance);

		body.emitCFG();
		root = body.beginCFGBlock;
		if (body.endCFGBlock.unconditionalNext == null)
			body.endCFGBlock.unconditionalNext = outBody;
//		checkForDebug();

		// remove unnecessary jumps
		vertices.stream().forEach(ControlFlowGraph::findGoto);
//		checkForDebug();

		// eliminate unreachable vertices, build dominator tree, calculate dominance frontiers
		LengauerTarjan dominatorTreeSolver = new LengauerTarjan(vertices, root);
		dominatorTreeSolver.process();

		// insert phi-functions
		RegisterManager rm = new RegisterManager(body.givenVariables, root);
		PhiInserter phiInserter = new PhiInserter(vertices, rm);
		phiInserter.process();
	}

	public static String toStr() {
		StringBuilder sb = new StringBuilder();
		sb.append("CFG of Function #").append(nowUId).append(Utility.NEW_LINE);
		sb.append(Utility.getIndent(1)).
				append("in = ").append(root.id).
				append(", out = ").append(outBody.id).
				append(Utility.NEW_LINE);
		DepthFirstSearcher.getReachable(vertices, root).stream().forEach(x -> sb.append(x.toString()));
		return sb.toString();
	}
}
