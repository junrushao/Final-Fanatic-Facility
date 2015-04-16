package Compiler2015.AST.Type;

public class CharType extends Type {
	public CharType() {
	}

	@Override
	public int sizeof() {
		return 1;
	}
}
