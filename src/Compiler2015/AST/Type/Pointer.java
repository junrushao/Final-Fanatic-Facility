package Compiler2015.AST.Type;

import Compiler2015.Exception.CompilationError;

/**
 */
public class Pointer extends Type {
	public static boolean isCommonPointer(Type x) {
		return x instanceof ArrayPointerType || x instanceof VariablePointerType;
	}

	public static Type getPointTo(Type x) {
		// assert x is common pointer
		if (x instanceof ArrayPointerType)
			return ((ArrayPointerType) x).pointTo;
		if (x instanceof VariablePointerType)
			return ((VariablePointerType) x).pointTo;
		throw new CompilationError("Not a pointer to variable.");
	}
	@Override
	public int sizeof() {
		return 8;
	}

}
