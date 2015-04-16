package Compiler2015.AST.Type;

/**
 * int a[][3];
 *
 * a is an ArrayPointerType
 * TODO: a[1] returns a pointer type
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
	} // TODO: error, not 8 but the size of array
}
