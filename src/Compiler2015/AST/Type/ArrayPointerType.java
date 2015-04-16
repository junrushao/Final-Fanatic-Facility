package Compiler2015.AST.Type;

import java.util.ArrayList;

/**
 *
 * a is an ArrayPointerType
 * TODO: ArrayPointerType -> Variable Pointer Type, e.g. for int a[3][4], a[1] returns a pointer type
 */
public class ArrayPointerType extends Pointer {
	public Type pointTo;
	public ArrayList<Integer> dimensions;

	public ArrayPointerType(Type t, final int dimension) {
		if (t instanceof ArrayPointerType) {
			this.pointTo = ((ArrayPointerType) t).pointTo;
			this.dimensions = ((ArrayPointerType) t).dimensions;
			this.dimensions.add(dimension);
		} else {
			this.pointTo = t;
			this.dimensions = new ArrayList<Integer>() {{
				add(dimension);
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
