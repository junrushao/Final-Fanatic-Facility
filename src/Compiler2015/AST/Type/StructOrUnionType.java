package Compiler2015.AST.Type;

import Compiler2015.AST.Declaration.StructOrUnionDeclaration;

/**
 *
 */
public class StructOrUnionType extends Type {
	public StructOrUnionDeclaration ref;

	public StructOrUnionType(StructOrUnionDeclaration ref) {
		this.ref = ref;
	}

	@Override
	public int sizeof() {
		return ref.sizeof();
	}
}
