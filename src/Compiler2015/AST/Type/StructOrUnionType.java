package Compiler2015.AST.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class StructOrUnionType extends Type {
	public int uId = -1;
	public boolean isUnion = false;
	public ArrayList<Type> types;
	public ArrayList<String> names;
	public HashMap<String, Type> directlyAccessableMembers;

	public StructOrUnionType() {
		types = new ArrayList<>();
		names = new ArrayList<>();
		directlyAccessableMembers = new HashMap<>();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof StructOrUnionType && ((StructOrUnionType) obj).uId == uId;
	}

	@Override
	public int sizeof() {
		return 0;
	}
}
