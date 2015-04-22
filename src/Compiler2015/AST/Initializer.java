package Compiler2015.AST;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

import java.util.ArrayList;

public class Initializer {
	public Expression single;
	public ArrayList<Initializer> list;

	public Initializer(Expression single) {
		this.single = single;
		this.list = null;
	}

	public Initializer(ArrayList<Initializer> list) {
		this.single = null;
		this.list = list;
	}

	public ArrayList<Expression> toArrayList() {
		if (single != null)
			return new ArrayList<Expression>() {{
				add(single);
			}};
		ArrayList<Expression> ret = new ArrayList<>();
		for (Initializer i : list)
			ret.addAll(i.toArrayList());
		return ret;
	}

	@Override
	public String toString() {
		if (single != null)
			return single.toString();
		StringBuilder sb = new StringBuilder();
		String sep = "{";
		for (Initializer i : list) {
			sb.append(sep).append(i.toString());
			sep = ", ";
		}
		sb.append("}");
		return sb.toString();
	}
}
