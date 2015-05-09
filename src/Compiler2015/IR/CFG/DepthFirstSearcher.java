package Compiler2015.IR.CFG;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DepthFirstSearcher {
	public static HashSet<CFGVertex> vertices;
	public static int timeStamp;

	public static void depthFirstSearch(CFGVertex vertex) {
		vertex.id = ++timeStamp;
		Stream.of(vertex.unconditionalNext, vertex.branchIfFalse).filter(child -> child != null && child.id == -1)
				.forEach(child -> {
					child.parent = vertex;
					depthFirstSearch(child);
				});
	}

	public static int process(HashSet<CFGVertex> _vertices, CFGVertex root) {
		vertices = _vertices;
		timeStamp = 0;
		vertices.stream().forEach(x -> x.id = -1);
		depthFirstSearch(root);
		return timeStamp;
	}

	public static List<CFGVertex> getReachable(HashSet<CFGVertex> _vertices, CFGVertex root) {
		process(_vertices, root);
		return vertices.stream().filter(x -> x.id != -1).collect(Collectors.toList());
	}

}
