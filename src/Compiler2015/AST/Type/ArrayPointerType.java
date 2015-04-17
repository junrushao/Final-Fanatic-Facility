package Compiler2015.AST.Type;

import Compiler2015.Exception.CompilationError;

import java.util.ArrayList;

/**
 *
 * a is an ArrayPointerType
 * TODO: ArrayPointerType -> Variable Pointer Type, e.g. for int a[3][4], a[1] returns a pointer type
 */
public class ArrayPointerType extends Pointer {
	public Type pointTo;
	public ArrayList<Integer> dimensions;

	/**
	 * @param t   the type of the array
	 * @param len length of the current dimension
	 */
	public ArrayPointerType(Type t, final int len) {
		if (t instanceof ArrayPointerType) {
			if (len < 0)
				throw new CompilationError("Dimension must be non-negative and determined.");
			this.pointTo = ((ArrayPointerType) t).pointTo;
			this.dimensions = ((ArrayPointerType) t).dimensions;
			this.dimensions.add(len);
		} else {
			this.pointTo = t;
			this.dimensions = new ArrayList<Integer>() {{
				add(len);
			}};
		}
	}

	@Override
	public int sizeof() { // assume there is no integer overflow
		int ret = pointTo.sizeof();
		for (int i = 0, size = dimensions.size(); i < size; ++i)
			ret *= dimensions.get(i);
		return ret;
	}
}
