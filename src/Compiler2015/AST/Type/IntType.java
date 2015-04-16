package Compiler2015.AST.Type;

/**
 *
 */
public class IntType extends Type {
	public IntType() {
	}

	@Override
	public int sizeof() {
		return 4;
	}
}
