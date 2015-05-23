package Compiler2015.Type;

public class VoidType extends Type {
	public static VoidType instance = new VoidType();

	private VoidType() {
	}

	@Override
	public int sizeof() {
		return 1;
	}

	@Override
	public int classifiedSizeof() {
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
