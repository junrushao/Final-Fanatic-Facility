package Compiler2015.AST.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class ArrayPointerType extends Pointer {
	public Type pointTo;
	public int dimension;

	public ArrayPointerType(Type pointTo, int dimension) {
		this.pointTo = pointTo;
		this.dimension = dimension; // -1 for undimensioned array
	}

	@Override
	public int sizeof() { // TODO
		return 8;
	}
}
