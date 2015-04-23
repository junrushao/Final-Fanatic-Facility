package Compiler2015.AST;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

import java.util.ArrayList;

public class SimpleInitializerList {
	public Expression single;
	public ArrayList<SimpleInitializerList> list;

	public SimpleInitializerList(Expression single) {
		this.single = single;
		this.list = null;
	}

	public SimpleInitializerList(ArrayList<SimpleInitializerList> list) {
		this.single = null;
		this.list = list;
	}

	public ArrayList<Expression> toArrayList() {
		if (single != null)
			return new ArrayList<Expression>() {{
				add(single);
			}};
		ArrayList<Expression> ret = new ArrayList<>();
		for (SimpleInitializerList i : list)
			ret.addAll(i.toArrayList());
		return ret;
	}

	@Override
	public String toString() {
		if (single != null)
			return single.toString();
		StringBuilder sb = new StringBuilder();
		String sep = "{";
		for (SimpleInitializerList i : list) {
			sb.append(sep).append(i.toString());
			sep = ", ";
		}
		sb.append("}");
		return sb.toString();
	}
}
