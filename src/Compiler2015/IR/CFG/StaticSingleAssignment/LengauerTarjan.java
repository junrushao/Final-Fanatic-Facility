package Compiler2015.IR.CFG.StaticSingleAssignment;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.DepthFirstSearcher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class LengauerTarjan {

	public HashSet<CFGVertex> vertices;
	public CFGVertex root;
	public int n;
	public VertexInfo vi[];

	public LengauerTarjan(HashSet<CFGVertex> vertices, CFGVertex root) {
		this.vertices = vertices;
		this.root = root;
	}

	public void compress(VertexInfo v) {
		VertexInfo a = v.ancestor;
		if (a.ancestor == null)
			return;
		compress(a);
		if (v.best.semi.me.id > a.best.semi.me.id)
			v.best = a.best;
		v.ancestor = a.ancestor;
	}

	public VertexInfo bestInPath(VertexInfo v) {
		if (v.ancestor == null)
			return v;
		compress(v);
		return v.best;
	}

	public void process() {
		n = DepthFirstSearcher.process(vertices, root);
		// eliminate unreachable vertices
		List<CFGVertex> unreachable = vertices.stream().filter(x -> x.id == -1).collect(Collectors.toList());
		unreachable.stream().forEach(vertices::remove);

		if (vertices.size() != n)
			throw new CompilationError("Internal Error.");

		vi = new VertexInfo[n + 1];
		for (int i = 1; i <= n; ++i) vi[i] = new VertexInfo();
		vertices.stream().filter(v -> v.id != -1)
				.forEach(v -> {
					vi[v.id].me = v;
					if (v.unconditionalNext != null)
						vi[v.unconditionalNext.id].pred.add(vi[v.id]);
					if (v.branchIfFalse != null)
						vi[v.branchIfFalse.id].pred.add(vi[v.id]);
				});

		System.out.println("root = " + root.id);
		for (int i = 1; i <= n; ++i) {
			int y = vi[i].me.id;
			for (VertexInfo v : vi[i].pred) {
				int x = v.me.id;
				System.out.println(x + " " + y);
			}
		}

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
	}

	public class VertexInfo {
		public CFGVertex me;
		public VertexInfo semi = this, best = this, idom = null, ancestor = null;
		public ArrayList<VertexInfo> pred = new ArrayList<>(4);
		public ArrayList<VertexInfo> bucket = new ArrayList<>();
	}
}
