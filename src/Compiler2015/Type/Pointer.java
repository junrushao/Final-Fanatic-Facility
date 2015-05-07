package Compiler2015.Type;

import Compiler2015.Utility.Panel;

public abstract class Pointer extends Type {
	public static Type getPointTo(Type x) {
		if (x instanceof ArrayPointerType)
			return ((ArrayPointerType) x).pointTo;
		if (x instanceof VariablePointerType)
			return ((VariablePointerType) x).pointTo;
		return ((FunctionPointerType) x).pointTo;
	}

	@Override
	public int sizeof() {
		return Panel.architecture.equals(Panel.MIPS) ? 4 : 8;
	}
}
