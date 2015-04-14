package Compiler2015.AST.Type;

/**
 * Created by junrushao on 15-4-13.
 */
public class VariablePointerType extends Pointer {
	public Type pointTo;
	public VariablePointerType(Type pointTo) {
		this.pointTo = pointTo;
	}

	@Override
	public int sizeof() {
		return 8;
	}
}
