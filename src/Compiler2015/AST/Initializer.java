package Compiler2015.AST;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.CharConstant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.StringConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;

public class Initializer extends ASTNode {
	public ArrayList<InitEntry> entries;

	public Initializer(ArrayList<InitEntry> entries) {
		this.entries = new ArrayList<>(entries);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		boolean isFirst = true;
		for (InitEntry i : entries) {
			if (isFirst) isFirst = false;
			else sb.append(", ");
			sb.append(i.toString());
		}
		return sb.append("}").toString();
	}

	@Override
	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).append(Utility.NEW_LINE).toString();
	}

	public static class InitEntry {
		public int position[];
		public Expression value;

		public InitEntry(int position[], Expression value) {
			this.position = Arrays.copyOf(position, position.length);
			this.value = value;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder("<{");
			boolean isFirst = true;
			for (int i : position) {
				if (isFirst) isFirst = false;
				else sb.append(", ");
				sb.append(i);
			}
			return sb.append("}, ").append(value.toString()).append(">").toString();
		}
	}

	public static class Constructor {
		public ArrayList<InitEntry> entries = new ArrayList<>();
		public ArrayList<Integer> dimensions;
		public Type t;
		public int now[];

		void DFS(int n, SimpleInitializerList pos) {
			if (pos.list != null && pos.list.size() == 1 && pos.list.get(0).single != null && pos.list.get(0).single instanceof StringConstant) {
				StringConstant e = (StringConstant) pos.list.get(0).single;
				pos.list = null;
				pos.single = e;
			}
			if (pos.single != null && pos.single instanceof StringConstant && n < dimensions.size()) {
				StringConstant e = (StringConstant) pos.single;
				pos.single = null;
				pos.list = new ArrayList<>();
				for (char c : e.c.toCharArray())
					pos.list.add(new SimpleInitializerList(new CharConstant(c)));
			}
			if (pos.single != null) { // leaf node
				Expression e = pos.single;
				if (!CastExpression.castable(e.type, t))
					throw new CompilationError("Type not convertible.");
				entries.add(new InitEntry(now, e));
				return;
			}
			if (n == dimensions.size()) { // depth limit reached
				Expression e = pos.toArrayList().get(0);
				if (!CastExpression.castable(e.type, t))
					throw new CompilationError("Type not convertible.");
				entries.add(new InitEntry(now, e));
				return;
			}
			int totalBranches = (n == 0 && dimensions.get(0) == -1) ? pos.list.size() : Math.min(pos.list.size(), dimensions.get(n));
			for (int i = 0; i < totalBranches; ++i) {
				now[n] = i;
				DFS(n + 1, pos.list.get(i));
				now[n] = 0;
			}
		}

		public Initializer get(ArrayList<Integer> dimensions, SimpleInitializerList list, Type t) {
			this.dimensions = dimensions;
			this.t = t;
			this.now = new int[dimensions.size()];
			DFS(0, list);
			return new Initializer(entries);
		}
	}

}
