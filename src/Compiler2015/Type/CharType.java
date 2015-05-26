package Compiler2015.Type;

public class CharType extends Type {
	public static CharType instance = new CharType();

	private CharType() {
	}

	@Override
	public int sizeof() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CharType;
	}

	@Override
	public String toString() {
		return "char";
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	@Override
	public CharType clone() {
		return instance;
	}
}
