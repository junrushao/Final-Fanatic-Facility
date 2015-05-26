package Compiler2015.Type;

public class IntType extends Type {
	public static IntType instance = new IntType();

	private IntType() {
	}

	@Override
	public int sizeof() {
		return 4;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof IntType;
	}

	@Override
	public String toString() {
		return "int";
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	@Override
	public IntType clone() {
		return instance;
	}
}
