package Compiler2015.AST.Type;

public class IntType extends Type {
	public static IntType instance = new IntType();

	public IntType() {
	}

	@Override
	public int sizeof() {
		return 4;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IntType)
			return true;
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "int";
	}
}
