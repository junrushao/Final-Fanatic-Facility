package Compiler2015.AST.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class VoidType extends Type {
	public VoidType() {
	}

	@Override
	public int sizeof() { // TODO
		return 0;
	}
}
