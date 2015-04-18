package Compiler2015.AST.Type;

import Compiler2015.AST.Statement.ExpressionStatement.Constant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
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
	 * @param t the type of the array
	 * @param d length of each dimension
	 */
	public ArrayPointerType(Type t, ArrayList<Expression> d) {
		pointTo = t;
		dimensions = new ArrayList<Integer>();
		int n = d.size();
		for (int i = 0; i < n; ++i) {
			Expression e = d.get(i);
			if (e == null) {
				if (i != 0)
					throw new CompilationError("Array type has incomplete dimensions.");
				dimensions.add(-1);
			} else if (e instanceof Constant) {
				int v = ((Constant) e).toInt();
				if (v < 0)
					throw new CompilationError("The length of each dimension must be non-negative.");
				dimensions.add(v);
			} else
				throw new CompilationError("The length of each dimension must be constant.");
		}
	}

	@Override
	public int sizeof() { // assume there is no integer overflow
		int ret = pointTo.sizeof();
		for (int d : dimensions)
			ret *= d;
		return ret;
	}
}
