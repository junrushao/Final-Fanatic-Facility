package Compiler2015.AST.Type;

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
		return ((FunctionPointerType) x).pointTo;
	}
	@Override
	public int sizeof() {
		return 8;
	}

}
