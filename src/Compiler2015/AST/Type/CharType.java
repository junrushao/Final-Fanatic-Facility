package Compiler2015.AST.Type;

public class CharType extends Type {
	public static CharType instance = new CharType();

	public CharType() {
	}

	@Override
	public int sizeof() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CharType)
			return true;
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "char";
	}
}
