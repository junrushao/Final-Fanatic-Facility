package Compiler2015.RegisterAllocator;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Optimizer.DataFlow;

import java.util.*;
import java.util.stream.Collectors;

public class InterferenceGraphColoring extends BaseAllocator {

	public HashMap<Integer, Integer> disjointSetFa;

	public InterferenceGraphColoring(ControlFlowGraph graph) {
		super(graph);

		HashMap<Integer, Integer> liveRange = new HashMap<>();
		rename(liveRange);

		DataFlow.livenessAnalysis(graph);
		ArrayList<Integer> e1 = new ArrayList<>(), e2 = new ArrayList<>();
		for (CFGVertex b : graph.vertices) {
			HashSet<Integer> liveNow = b.liveOut.stream().filter(v -> v != null && v.uId > 0 && !Environment.functionTable.containsKey(v.uId)).map(v -> liveRange.get(v.uId)).collect(Collectors.toCollection(HashSet::new));
			for (int itr = b.internal.size() - 1; itr >= 0; --itr) {
				IRInstruction i = b.internal.get(itr);
				for (int def : getDef(i))
					for (int lr : liveNow) {
						e1.add(liveRange.get(def));
						e2.add(lr);
					}
				for (int def : getDef(i))
					liveNow.remove(liveRange.get(def));
				for (int use : getUse(i))
					liveNow.add(liveRange.get(use));
			}
		}
		GraphColoring coloring = new GraphColoring(liveRange.size(), MachineRegister.K, e1, e2);
		mapping = new HashMap<>();
		for (Map.Entry<Integer, Integer> mapEntry : liveRange.entrySet()) {
			int x = mapEntry.getKey(); // variable x
			int lrx = mapEntry.getValue(); // live range of x
			int color = coloring.color[lrx];
			if (color == -2) {
				mapping.put(x, null);
			} else if (color == -1) {
				throw new CompilationError("Internal Error.");
			} else {
				mapping.put(x, MachineRegister.availableMachineRegisters[color]);
			}
		}
		collectPhysicalRegistersUsed();
	}

	public static HashSet<Integer> getUse(IRInstruction i) {
		HashSet<Integer> uses = new HashSet<>();
		for (VirtualRegister r : i.getAllUseVR())
			if (r != null && r.uId > 0 && !Environment.functionTable.containsKey(r.uId))
				uses.add(r.uId);
		return uses;
	}

	public int findSet(int x) {
		int fa = disjointSetFa.get(x);
		if (x == fa)
			return x;
		int ret = findSet(fa);
		disjointSetFa.put(x, ret);
		return ret;
	}

	@SuppressWarnings("SuspiciousNameCombination")
	public void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x == y)
			return;
		disjointSetFa.put(x, y);
	}

	public void rename(HashMap<Integer, Integer> liveRange) {
		disjointSetFa = new HashMap<>();
		for (CFGVertex v : graph.vertices)
			for (IRInstruction i : v.internal) {
				for (int x : getDef(i))
					disjointSetFa.put(x, x);
				for (int x : getUse(i))
					disjointSetFa.put(x, x);
			}
/*
		for (CFGVertex v : graph.vertices)
			for (IRInstruction i : v.internal) {
				if (i instanceof Move) {
					int x = ((Move) i).rs.getUId();
					int y = i.rd.uId;
					if (x == -1)
						continue;
					if (disjointSetFa.containsKey(x) && disjointSetFa.containsKey(y))
						unionSet(x, y);
				}
			}
*/
		int cnt = 0;
		for (CFGVertex v : graph.vertices)
			for (IRInstruction i : v.internal) {
				for (int x : getDef(i)) {
					int y = findSet(x);
					if (!liveRange.containsKey(y))
						liveRange.put(y, cnt++);
					if (x != y)
						liveRange.put(x, liveRange.get(y));
				}
				for (int x : getUse(i)) {
					int y = findSet(x);
					if (!liveRange.containsKey(y))
						liveRange.put(y, cnt++);
					if (x != y)
						liveRange.put(x, liveRange.get(y));
				}
			}
	}

	public HashSet<Integer> getDef(IRInstruction i) {
		HashSet<Integer> defs = new HashSet<>();
		for (VirtualRegister r : i.getAllDefVR())
			if (r != null && r.uId > 0 && !Environment.functionTable.containsKey(r.uId))
				defs.add(r.uId);
		return defs;
	}

	public static class GraphColoring {
		public boolean g[][];
		public boolean deleted[];
		public int degree[];
		public int color[]; // -1 if not colored, -2 if spilled

		public int n, k;

		public GraphColoring(int n, int k, ArrayList<Integer> e1, ArrayList<Integer> e2) {
			// build graph
			this.n = n;
			this.k = k;
			g = new boolean[n][n];
			deleted = new boolean[n];
			degree = new int[n];
			color = new int[n];
			Arrays.fill(color, -1);

			for (int i = 0, size = e1.size(); i < size; ++i) {
				int a = e1.get(i), b = e2.get(i);
				if (g[a][b])
					continue;
				if (a == b)
					continue;
				g[a][b] = g[b][a] = true;
				++degree[a];
				++degree[b];
			}

			Stack<Integer> vertexList = new Stack<>();
			for (int numberOfVerticesLeft = n; numberOfVerticesLeft > 0; --numberOfVerticesLeft) {
				Integer find = null;
				for (int i = 0; i < n; ++i)
					if (!deleted[i] && degree[i] < k && (find == null || degree[find] < degree[i]))
						find = i;
				if (find != null) {
					deleteVertexFromGraph(find);
					vertexList.push(find);
					continue;
				}
				for (int i = 0; i < n; ++i)
					if (!deleted[i] && (find == null || degree[find] < degree[i]))
						find = i;
				if (find == null)
					throw new CompilationError("Internal Error.");
				deleteVertexFromGraph(find);
				vertexList.push(find);
			}

			while (!vertexList.isEmpty()) {
				int x = vertexList.pop();
				boolean colorCanUse[] = new boolean[k];
				Arrays.fill(colorCanUse, true);
				for (int y = 0; y < n; ++y)
					if (!deleted[y] && g[x][y] && color[y] >= 0)
						colorCanUse[color[y]] = false;
				int colorToUse = -2;
				for (int i = 0; i < k; ++i)
					if (colorCanUse[i]) {
						colorToUse = i;
						break;
					}
				color[x] = colorToUse;
				recoverVertexFromGraph(x);
			}
		}

		public void deleteVertexFromGraph(int x) {
			if (deleted[x])
				throw new CompilationError("Internal Error.");
			deleted[x] = true;
			for (int y = 0; y < n; ++y)
				if (g[x][y] && !deleted[y])
					--degree[y];
		}

		public void recoverVertexFromGraph(int x) {
			if (!deleted[x])
				throw new CompilationError("Internal Error.");
			deleted[x] = false;
			for (int y = 0; y < n; ++y)
				if (g[x][y] && !deleted[y])
					++degree[y];
		}
	}
}
