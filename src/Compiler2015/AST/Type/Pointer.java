package Compiler2015.AST.Type;

/**
 */
public class Pointer extends Type {
	public static Type getPointTo(Type x) {
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
