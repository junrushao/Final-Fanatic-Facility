package Compiler2015.AST.Type;

/**
 */
public class VoidType extends Type {
	public VoidType() {
	}

	@Override
	public int sizeof() {
		return 1;
	}
}
