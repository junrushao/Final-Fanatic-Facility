package Compiler2015.IR.CFG;

import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.Instruction.Nop;
import Compiler2015.IR.StaticSingleAssignment.LengauerTarjan;
import Compiler2015.Type.FunctionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControlFlowGraph {

	public static HashSet<CFGVertex> vertices;

	public static CFGVertex root, outBody;
	public static int nowUId;
	public static int tempVertexCount;
	public static CompoundStatement scope;
	public static Type returnType;

	public static HashMap<Integer, Integer> tempDelta;
	public static HashMap<Integer, Integer> parameterDelta;
	public static int frameSize;

	static {
		vertices = new HashSet<>();
		root = outBody = null;
		nowUId = 0;
	}

	public static CFGVertex getNewVertex() {
		CFGVertex ret = new CFGVertex();
		vertices.add(ret);
		ret.id = tempVertexCount++;
		return ret;
	}

	public static CFGVertex getNewTempVertex() {
		CFGVertex ret = new CFGVertex();
		ret.id = -1;
		return ret;
	}

	public static void process(CompoundStatement body, int uId) {
		nowUId = uId;
		tempVertexCount = 0;
		vertices.clear();
		ControlFlowGraph.scope = body;
		returnType = ((FunctionType) Environment.symbolNames.table.get(uId).ref).returnType;

		root = null;
		outBody = ControlFlowGraph.getNewVertex();

		// emit control flow graph
		body.emitCFG();
		root = body.beginCFGBlock;
		if (body.endCFGBlock.unconditionalNext == null)
			body.endCFGBlock.unconditionalNext = outBody;

		// add if condition
		vertices.stream().forEach(x -> {
			if (x.branchIfFalse != null && x.branchRegister == null) {
				if (x.internal.isEmpty())
					System.err.println("fuck id = " + x.id);
				x.branchRegister = x.internal.get(x.internal.size() - 1).rd.clone();
			}
			if (x.branchIfFalse != null) {
				x.internal.add(new Nop(x.branchRegister));
				x.branchRegister = null;
			}
		});

		mergeBlocks();
		splitEdges();

		vertices.stream().filter(v -> v.id != -1).forEach(v -> {
			if (v.unconditionalNext != null)
				v.unconditionalNext.predecessor.put(v, null);
			if (v.branchIfFalse != null)
				v.branchIfFalse.predecessor.put(v, null);
		});
		vertices.forEach(v -> {
			final int[] cnt = {0};
			v.predecessor.entrySet().stream().forEach(e -> e.setValue(cnt[0]++));
		});

		LengauerTarjan.process(vertices, root, vertices.size());
/*
		// insert phi-functions
		RegisterManager rm = new RegisterManager(body.givenVariables, root);
		PhiInserter phiInserter = new PhiInserter(vertices, rm);
		phiInserter.process();
*/
	}

	public static void splitEdges() {
		int n = DepthFirstSearcher.process(vertices, root);
		List<CFGVertex> unreachable = vertices.stream().filter(x -> x.id == -1).collect(Collectors.toList());
		unreachable.stream().forEach(vertices::remove);
		if (vertices.size() != n)
			throw new CompilationError("Internal Error.");
		int inDegree[] = new int[n + 1];
		int outDegree[] = new int[n + 1];
		ArrayList<CFGVertex> added = new ArrayList<>();
		for (CFGVertex x : vertices) {
			final int finalN = n;
			Stream.of(x.unconditionalNext, x.branchIfFalse).filter(y -> y != null).forEach(
					y -> {
						if (!(1 <= x.id && x.id <= finalN))
							throw new CompilationError("Internal Error.");
						if (!(1 <= y.id && y.id <= finalN))
							throw new CompilationError("Internal Error.");
						++outDegree[x.id];
						++inDegree[y.id];
					}
			);
		}
		for (CFGVertex x : vertices)
			if (x.unconditionalNext != null && x.branchIfFalse != null) {
				CFGVertex y, z;
				y = x.unconditionalNext;
				if (inDegree[y.id] > 1) {
					z = getNewTempVertex();
					x.unconditionalNext = z;
					z.unconditionalNext = y;
					added.add(z);
				}
				y = x.branchIfFalse;
				if (inDegree[y.id] > 1) {
					z = getNewTempVertex();
					x.branchIfFalse = z;
					z.unconditionalNext = y;
					added.add(z);
				}
			}
		vertices.addAll(added);
		n = DepthFirstSearcher.process(vertices, root);
		unreachable = vertices.stream().filter(x -> x.id == -1).collect(Collectors.toList());
		unreachable.stream().forEach(vertices::remove);
		if (vertices.size() != n)
			throw new CompilationError("Internal Error.");
	}

	public static void mergeBlocks() {
		boolean changed;
		do {
			changed = false;
			int n = DepthFirstSearcher.process(vertices, root);
			List<CFGVertex> unreachable = vertices.stream().filter(x -> x.id == -1).collect(Collectors.toList());
			unreachable.stream().forEach(vertices::remove);
			if (vertices.size() != n)
				throw new CompilationError("Internal Error.");
			int inDegree[] = new int[n + 1];
			int outDegree[] = new int[n + 1];
			for (CFGVertex x : vertices)
				Stream.of(x.unconditionalNext, x.branchIfFalse).filter(y -> y != null).forEach(
						y -> {
							if (!(1 <= x.id && x.id <= n))
								throw new CompilationError("Internal Error.");
							if (!(1 <= y.id && y.id <= n))
								throw new CompilationError("Internal Error.");
							++outDegree[x.id];
							++inDegree[y.id];
						}
				);
			for (CFGVertex x : vertices) {
				if (x.unconditionalNext != null && x.branchIfFalse == null) {
					CFGVertex y = x.unconditionalNext;
					if (y != outBody && inDegree[y.id] == 1) {
						x.internal.addAll(y.internal);
						x.unconditionalNext = y.unconditionalNext;
						x.branchIfFalse = y.branchIfFalse;
						changed = true;
						vertices.remove(y);
						break;
					}
				}
			}
		} while (changed);
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
