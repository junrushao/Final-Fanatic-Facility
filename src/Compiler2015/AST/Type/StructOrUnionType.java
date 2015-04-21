package Compiler2015.AST.Type;

import Compiler2015.AST.Declaration.StructOrUnionDeclaration;

public class StructOrUnionType extends Type {
	public StructOrUnionDeclaration ref;

	public StructOrUnionType(StructOrUnionDeclaration ref) {
		this.ref = ref;
	}

	@Override
	public int sizeof() {
		return ref.sizeof();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof StructOrUnionType) {
			StructOrUnionType other = (StructOrUnionType) obj;
			return ref.uId == other.ref.uId;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		if (ref.isUnion)
			return "Union#" + ref.uId;
		else
			return "Struct#" + ref.uId;
	}
}
