package Compiler2015.AST.Declaration;

import Compiler2015.AST.Type.StructOrUnionType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Exception.CompilationError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StructOrUnionDeclaration extends Declaration {
	public int uId = -1;
	public boolean isUnion;
	public HashMap<String, Type> members; // name -> type
	public ArrayList<StructOrUnionDeclaration> anonymousMembers;

	public StructOrUnionDeclaration(boolean isUnion, HashMap<String, Type> members, ArrayList<StructOrUnionDeclaration> anonymousMembers) {
//		this.uId = uId;
		this.isUnion = isUnion;
		this.members = members;
		this.anonymousMembers = anonymousMembers;
	}

	public int sizeof() {
		int size = 0;
		if (isUnion) {
			for (Type t : members.values())
				size = Math.max(size, t.sizeof());
			for (StructOrUnionDeclaration t: anonymousMembers)
				size = Math.max(size, t.sizeof());
		}
		else {
			for (Type t : members.values())
				size += t.sizeof();
			for (StructOrUnionDeclaration t: anonymousMembers)
				size += t.sizeof();
		}
		return size;
	}

	public Type memberType(String memberName) {
		if (members.containsKey(memberName))
			return members.get(memberName);
		for (StructOrUnionDeclaration t : anonymousMembers) {
			Type ret = t.memberType(memberName);
			if (ret != null)
				return ret;
		}
		return null;
	}

	public HashSet<String> getDirectMemberNames() {
		HashSet<String> res = new HashSet<String>();
		for (String s : members.keySet()) {
			if (s == null || s.equals(""))
				throw new CompilationError("Internal Error!");
			if (res.contains(s))
				throw new CompilationError("Internal Error!");
			res.add(s);
		}
		for (StructOrUnionDeclaration s : anonymousMembers) {
			HashSet<String> tmp = s.getDirectMemberNames();
			res.addAll(tmp);
		}
		return res;
	}

	public boolean checkOverlap(HashSet<String> a, HashSet<String> b) {
		for (String x : a)
			if (b.contains(x))
				return false;
		return true;
	}

	public void addAttributes(ArrayList<Type> types, ArrayList<String> names) {
		int n = types.size();
		for (int i = 0; i < n; ++i) {
			Type type = types.get(i);
			String name = names.get(i);
			if (name == null || name.equals("")) name = null;
			if (name == null) {
				if (!(type instanceof StructOrUnionType))
					throw new CompilationError("Anonymous member should be instance of some struct / union.");
				HashSet<String> newNames = ((StructOrUnionType) type).ref.getDirectMemberNames();
				HashSet<String> originalNames = getDirectMemberNames();
				if (checkOverlap(newNames, originalNames))
					throw new CompilationError("Naming overlap.");
				anonymousMembers.add(((StructOrUnionType) type).ref);
			} else {
				HashSet<String> originalNames = getDirectMemberNames();
				if (originalNames.contains(name))
					throw new CompilationError("Naming overlap.");
				types.add(type);
				names.add(name);
			}
		}
	}
}
