package Compiler2015.AST.Type;

public class VoidType extends Type {
	public static VoidType instance = new VoidType();

	public VoidType() {
	}

	@Override
	public int sizeof() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof VoidType;
	}

	@Override
	public String toString() {
		return "void";
	}
}
