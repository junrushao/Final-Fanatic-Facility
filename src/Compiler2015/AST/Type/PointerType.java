package Compiler2015.AST.Type;

/**
 * Created by junrushao on 15-4-11.
 * PointerType to types other than function / array
 */
public class PointerType extends Type {
	public boolean isConstant = false;
	public boolean isCallable = false;

	@Override
	public int sizeof() { // TODO
		return 0;
	}
}
