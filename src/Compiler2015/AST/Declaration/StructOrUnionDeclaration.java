package Compiler2015.AST.Declaration;

import Compiler2015.AST.Type.Type;

import java.util.ArrayList;
import java.util.HashMap;

public class StructOrUnionDeclaration extends Declaration {
	public int uId;
	public boolean isUnion;
	public HashMap<String, Type> members; // name -> type
	public ArrayList<StructOrUnionDeclaration> anonymousMembers;

	public StructOrUnionDeclaration(int uId, boolean isUnion, HashMap<String, Type> members, ArrayList<StructOrUnionDeclaration> anonymousMembers) {
		this.uId = uId;
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
}
