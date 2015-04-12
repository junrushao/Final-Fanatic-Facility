package Compiler2015.AST.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class FunctionPointerType extends PointerType {
	public FunctionPointerType(Type pointTo) {
		super(pointTo);
		super.isConstant = true;
		super.isCallable = true;
	}

	@Override
	public int sizeof() { // TODO
		return 0;
	}
}
