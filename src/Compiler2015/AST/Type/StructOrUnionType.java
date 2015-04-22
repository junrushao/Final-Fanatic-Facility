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
		StringBuilder sb = new StringBuilder();
		if (!isUnion) {
			return sb.append("[Union #").append(uId).append("] ").toString();
		} else {
			return sb.append("[Struct #").append(uId).append("] ").toString();
		}
	}

	public String deepToString() {
		StringBuilder sb = new StringBuilder();
		if (!isUnion) {
			sb.append("[Union #").append(uId).append("]");
		} else {
			sb.append("[Struct #").append(uId).append("]");
		}
		sb.append(Utility.toString(types, names)).append("    direct = ").append(Utility.toString(new ArrayList<>(directlyAccessableMembers.keySet())));
		return sb.toString();
	}
}
