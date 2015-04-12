package Compiler2015.AST.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class ArrayPointerTypeType extends PointerType {
	public ArrayPointerTypeType(Type pointTo) {
		super(pointTo);
		super.isConstant = true;
		super.isCallable = false;
	}

	@Override
	public int sizeof() { // TODO
		return 0;
	}
}
