package Compiler2015.IR.CFG;

import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.FunctionType;
import Compiler2015.Utility.Utility;

import java.util.HashSet;
import java.util.Stack;
import java.util.stream.Stream;

public class ControlFlowGraph {

	public static HashSet<CFGVertex> vertices;
	public static CFGVertex root, outBody;
	public static int nowUId;

	public static CFGVertex getNewVertex() {
		CFGVertex ret = new CFGVertex();
		vertices.add(ret);
		return ret;
	}

	public static void deleteVertex(CFGVertex v) {
		vertices.remove(v);
	}

	public static void refresh() {
		root = outBody = null;
		vertices.clear();
	}

	public static CFGVertex findGoto(CFGVertex x) {
		CFGVertex to = x.unconditionalNext;
		if (to == null) {
			if (x.branchIfFalse != null)
				throw new CompilationError("Internal Error."); // what if the result is true
			return x;
		}
		if (!to.internal.isEmpty())
			return to;
		x.unconditionalNext = findGoto(to);
		return x.unconditionalNext;
	}

	public static void process(FunctionType function, CompoundStatement body, int uId) {
		nowUId = uId;
		refresh();
		body.emitCFG();
		root = body.beginCFGBlock;
		outBody = body.endCFGBlock;

		// remove unnecessary goto
		vertices.stream().forEach(x -> ++findGoto(x).id); // calculate in-degree
		vertices.stream().filter(x -> x.id == 0).forEach(vertices::remove);

		// number the vertices
		{
			final int[] totalVertices = {0}; // to use lambda correctly
			vertices.stream().forEach(x -> x.id = ++totalVertices[0]);
		}

	}

	public static String toStr() {
		StringBuilder sb = new StringBuilder();
		sb.append("Function #").append(nowUId).append(Utility.NEW_LINE);
		boolean visited[] = new boolean[vertices.size()];
		Stack<CFGVertex> stack = new Stack<>();
		for (stack.add(root), visited[root.id] = true; !stack.isEmpty(); ) {
			CFGVertex x = stack.pop();
			sb.append(x.toString());

			Stream.of(x.branchIfFalse, x.unconditionalNext)
					.filter(y -> y != null && !visited[y.id])
					.forEach(y -> {
						stack.push(y);
						visited[y.id] = true;
					});
		}
		return sb.toString();
	}
}
