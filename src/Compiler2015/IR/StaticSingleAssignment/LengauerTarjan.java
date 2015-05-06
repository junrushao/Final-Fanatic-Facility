package Compiler2015.IR.StaticSingleAssignment;

import Compiler2015.Exception.CompilationError;

import java.util.ArrayList;

public class LengauerTarjan {
	public class Vertex {
		public int id;							// ID in graph, starting from 1
		public Vertex parent;					// parent in DFS Tree
		public int dfn;							// depth-first number
		public Vertex semi;						// semi-dominator
		public Vertex idom;						// immediate dominator: father in the dominator tree
		public Vertex sameDom;
		public ArrayList<Vertex> bucket;

		public Vertex setFa;					// father in the union-find-set
		public Vertex best;						// ancestor with lowest semi-dominator

		public ArrayList<Vertex> edge;			// edges in CFG
		public ArrayList<Vertex> predecessor;	// predecessors in CFG

		public Vertex(int id) {
			this.id = id;
			this.parent = null;
			this.dfn = -1;
			this.semi = null;
			this.idom = null;
			this.sameDom = null;
			this.bucket = new ArrayList<>();
			this.setFa = this;
			this.best = this;
			this.edge = new ArrayList<>();
			this.predecessor = new ArrayList<>();
		}
	}

	public Vertex vertices[];
	public int timeStamp;

	public LengauerTarjan() {
		vertices = new Vertex[65533]; // is it enough?
		timeStamp = 0;
	}

	public Vertex addVertex(int n) {
		if (n >= vertices.length) {
			int newSize = Math.max(n + 1, vertices.length * 2);
			Vertex newOne[] = new Vertex[newSize];
			System.arraycopy(vertices, 0, newOne, 0, vertices.length);
			vertices = newOne;
		}
		if (vertices[n] == null)
			vertices[n] = new Vertex(n);
		return vertices[n];
	}

	public void addEdge(int a, int b) {
		Vertex va = addVertex(a);
		Vertex vb = addVertex(b);
		va.edge.add(vb);
		vb.predecessor.add(va);
	}

	public void DFS(Vertex x) {
		x.dfn = timeStamp++;
		x.edge.stream().filter(successor -> successor.dfn == -1).forEach(successor -> {
			successor.parent = x;
			DFS(successor);
		});
	}

	public Vertex findAncestorWithLowestSemiDominator(Vertex x) {
		if (x.setFa == x)
			return x.best;
		Vertex anotherBest = findAncestorWithLowestSemiDominator(x.setFa);
		x.setFa = x.setFa.setFa;
		if (anotherBest.semi.dfn < x.best.semi.dfn)
			x.best = anotherBest;
		return x.best;
	}

	public void linkInUnionFindSet(Vertex parent, Vertex child) {
		child.setFa = parent;
		child.best = child;
	}

	public void generateDominatorTree(int root) {
		if (root != 0)
			throw new CompilationError("Internal Error.");
		DFS(vertices[0]);
		for (int i = timeStamp - 1; i >= 1; --i) { // the root is ignored
			Vertex n = vertices[i];
			Vertex p = n.parent;
			Vertex s = p, y;
			for (Vertex v : n.predecessor) {
				if (v.dfn <= n.dfn)
					y = v;
				else
					y = findAncestorWithLowestSemiDominator(v).semi;
				if (y.dfn < s.dfn)
					s = y;
			}
			n.semi = s;
			s.bucket.add(n);
			linkInUnionFindSet(p, n);
			for (Vertex v : p.bucket) {
				y = findAncestorWithLowestSemiDominator(v);
				if (y.semi == v.semi)
					v.idom = p;
				else
					v.sameDom = y;
			}
			p.bucket = null;
		}
		for (int i = 1; i < timeStamp; ++i) {
			Vertex n = vertices[i];
			if (n.sameDom != null)
				n.idom = n.sameDom.idom;
		}
	}

	public int getIdom(int x) {
		return vertices[x].idom.id;
	}
}
