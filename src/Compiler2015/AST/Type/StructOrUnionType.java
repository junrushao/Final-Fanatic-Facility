package Compiler2015.AST.Type;

import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;

public class StructOrUnionType extends Type {
	public int uId = -1;
	public boolean isUnion = false;
	public ArrayList<Type> types;
	public ArrayList<String> names;
	public HashMap<String, Type> directlyAccessableMembers;

	public StructOrUnionType(int uId, boolean isUnion) {
		this.uId = uId;
		this.isUnion = isUnion;
		this.types = new ArrayList<>();
		this.names = new ArrayList<>();
		this.directlyAccessableMembers = new HashMap<>();
	}

	public StructOrUnionType() {
		this.types = new ArrayList<>();
		this.names = new ArrayList<>();
		this.directlyAccessableMembers = new HashMap<>();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof StructOrUnionType && ((StructOrUnionType) obj).uId == uId;
	}

	@Override
	public int sizeof() {
		return 0;
	}

	@Override
	public String toString() {
		if (isUnion) {
			return String.format("[Union #%d]", uId);
		} else {
			return String.format("[Struct #%d]", uId);
		}
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append(toString()).append(Utility.NEW_LINE);
		StringBuilder indent = Utility.getIndent(depth + 1);
		for (int i = 0, n = types.size(); i < n; ++i) {
			String aa = types.get(i).toString();
			String bb = names.get(i) == null || names.get(i).equals("") ? "##" : names.get(i);
			sb.append(indent).append(String.format("%s %s", aa, bb)).append(Utility.NEW_LINE);
		}
		return sb.toString();
	}
}
