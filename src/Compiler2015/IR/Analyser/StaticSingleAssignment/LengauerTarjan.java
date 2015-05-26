package Compiler2015.IR.Analyser.StaticSingleAssignment;

import Compiler2015.IR.CFG.CFGVertex;

import java.util.ArrayList;
import java.util.HashSet;

public final class LengauerTarjan {

	public static HashSet<CFGVertex> vertices;
	public static CFGVertex root;
	public static int n;
	public static VertexInfo vi[];

	public static void compress(VertexInfo v) {
		VertexInfo a = v.ancestor;
		if (a.ancestor == null)
			return;
		compress(a);
		if (v.best.semi.me.id > a.best.semi.me.id)
			v.best = a.best;
		v.ancestor = a.ancestor;
	}

	public static VertexInfo bestInPath(VertexInfo v) {
		if (v.ancestor == null)
			return v;
		compress(v);
		return v.best;
	}

	public static void process(HashSet<CFGVertex> vertices, CFGVertex root, int n) {
		LengauerTarjan.vertices = vertices;
		LengauerTarjan.root = root;
		vi = new VertexInfo[n + 1];
		for (int i = 1; i <= n; ++i) vi[i] = new VertexInfo();
		// add predecessor edges
		vertices.stream().filter(v -> v.id != -1)
				.forEach(v -> {
					vi[v.id].me = v;
					if (v.unconditionalNext != null) {
						vi[v.unconditionalNext.id].pred.add(vi[v.id]);
						v.unconditionalNext.predecessor.put(v, null);
					}
					if (v.branchIfFalse != null) {
						vi[v.branchIfFalse.id].pred.add(vi[v.id]);
						v.branchIfFalse.predecessor.put(v, null);
					}
				});
		vertices.forEach(v -> {
			final int[] cnt = {0};
			v.predecessor.entrySet().stream().forEach(e -> e.setValue(++cnt[0]));
		});
		// calculate semi-dominator
		for (int dfn = n; dfn >= 2; --dfn) {
			VertexInfo w = vi[dfn];
			VertexInfo p = vi[w.me.parent.id];
			for (VertexInfo v : w.pred) {
				VertexInfo u = bestInPath(v);
				if (w.semi.me.id > u.semi.me.id)
					w.semi = u.semi;
			}
			w.semi.bucket.add(w);
			w.ancestor = p;
			for (VertexInfo v : p.bucket) {
				VertexInfo u = bestInPath(v);
				v.idom = u.semi.me.id < p.me.id ? u : p;
			}
			p.bucket.clear();
		}

		// calculate dominator
		for (int dfn = 2; dfn <= n; ++dfn) {
			VertexInfo w = vi[dfn];
			if (w.idom != w.semi)
				w.idom = w.idom.idom;
		}
		vi[1].me.idom = vi[1].me;
		for (int dfn = 2; dfn <= n; ++dfn) {
			VertexInfo v = vi[dfn];
			v.me.idom = v.idom.me;
		}
		vertices.forEach(v -> {
			if (v != root) { // not root
				v.idom.children.add(v);
			}
		});

		// calculate dominance frontier
		for (VertexInfo toAdd : vi)
			if (toAdd != null && toAdd.pred.size() > 1)
				for (VertexInfo prev : toAdd.pred)
					for (VertexInfo here = prev; here != toAdd.idom; here = here.idom)
						here.me.dominanceFrontier.add(toAdd.me);
	}

	public static class VertexInfo {
		public CFGVertex me;
		public VertexInfo semi = this, best = this, idom = null, ancestor = null;
		public ArrayList<VertexInfo> pred = new ArrayList<>(4);
		public ArrayList<VertexInfo> bucket = new ArrayList<>();
	}
}
