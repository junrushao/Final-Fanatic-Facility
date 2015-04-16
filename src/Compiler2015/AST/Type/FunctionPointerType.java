package Compiler2015.AST.Type;

import Compiler2015.AST.Declaration.FunctionDeclaration;

/**
 *
 */
public class FunctionPointerType extends Pointer {
	public FunctionDeclaration ref;

	public FunctionPointerType(FunctionDeclaration ref) {
		this.ref = ref;
	}

	@Override
	public int sizeof() { // TODO
		return 8;
	}
}
