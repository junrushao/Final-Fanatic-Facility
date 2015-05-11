package Compiler2015.Type;

import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Panel;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StructOrUnionType extends Type {
	public int uId = -1;
	public boolean isUnion = false;
	public ArrayList<Type> types;
	public ArrayList<String> names;
	public HashMap<String, Type> directlyAccessibleMembers;
	public HashMap<String, Integer> memberDelta;

	public StructOrUnionType(int uId, boolean isUnion) {
		this.uId = uId;
		this.isUnion = isUnion;
		this.types = new ArrayList<>();
		this.names = new ArrayList<>();
		this.directlyAccessibleMembers = new HashMap<>();
		this.memberDelta = null;
	}

	public StructOrUnionType() {
		this.types = new ArrayList<>();
		this.names = new ArrayList<>();
		this.directlyAccessibleMembers = new HashMap<>();
	}

	public void calcMemberDelta() {
		int n = types.size();
		int last = 0;
		memberDelta = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			String name = names.get(i);
			Type type = types.get(i);
			if (name == null || name.equals("")) {
				if (!(type instanceof StructOrUnionType))
					throw new CompilationError("Internal Error.");
				for (Map.Entry<String, Integer> e : ((StructOrUnionType) type).memberDelta.entrySet()) {
					String subName = e.getKey();
					int subDelta = e.getValue();
					if (memberDelta.containsKey(subName))
						throw new CompilationError("Internal Error");
					memberDelta.put(subName, last + subDelta);
				}
			} else {
				memberDelta.put(name, last);
			}
			if (!isUnion)
				last += type.sizeof();
		}
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof StructOrUnionType && ((StructOrUnionType) obj).uId == uId;
	}

	@Override
	public int sizeof() {
		int ans = 0;
		if (isUnion)
			for (Type t : types)
				ans = Math.max(ans, t.sizeof());
		else
			for (Type t : types)
				ans += t.sizeof();
		return (ans + Panel.getRegisterSize() - 1) / Panel.getRegisterSize();
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
