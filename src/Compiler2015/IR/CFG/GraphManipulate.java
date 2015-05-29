package Compiler2015.IR.CFG;

import Compiler2015.Exception.CompilationError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class GraphManipulate {

	public static void reCalculateGraphInfo(ControlFlowGraph graph) {
		HashSet<CFGVertex> vertices = graph.vertices;
		CFGVertex source = graph.source;
		CFGVertex sink = graph.sink;

		int n = DepthFirstSearcher.process(vertices, source);
		List<CFGVertex> unreachable = vertices.stream().filter(x -> x.id == -1).collect(Collectors.toList());
		unreachable.stream().forEach(vertices::remove);
		if (vertices.size() != n)
			throw new CompilationError("Internal Error.");
		if (sink.id == -1) { // unreachable sink
			sink.id = vertices.size() + 1;
			vertices.add(sink);
		}
		vertices.forEach(v -> v.predecessor = new HashMap<>());
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
	}

	public static void splitEdges(ControlFlowGraph graph) {
		HashSet<CFGVertex> vertices = graph.vertices;
		CFGVertex source = graph.source;
		CFGVertex sink = graph.sink;

		int n = DepthFirstSearcher.process(vertices, source);
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
					z = graph.getNewTempVertex();
					x.unconditionalNext = z;
					z.unconditionalNext = y;
					added.add(z);
				}
				y = x.branchIfFalse;
				if (inDegree[y.id] > 1) {
					z = graph.getNewTempVertex();
					x.branchIfFalse = z;
					z.unconditionalNext = y;
					added.add(z);
				}
			}
		vertices.addAll(added);
		n = DepthFirstSearcher.process(vertices, source);
		unreachable = vertices.stream().filter(x -> x.id == -1).collect(Collectors.toList());
		unreachable.stream().forEach(vertices::remove);
		if (vertices.size() != n)
			throw new CompilationError("Internal Error.");
	}

	public static void mergeBlocks(ControlFlowGraph graph) {
		HashSet<CFGVertex> vertices = graph.vertices;
		CFGVertex source = graph.source;
		CFGVertex sink = graph.sink;
		boolean changed;
		do {
			changed = false;
			int n = DepthFirstSearcher.process(vertices, source);
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
					if (y != sink && inDegree[y.id] == 1) {
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

}
