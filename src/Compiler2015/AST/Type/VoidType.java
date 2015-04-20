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
		if (obj instanceof VoidType)
			return true;
		return super.equals(obj);
	}
}
