package Compiler2015.AST.Type;

import Compiler2015.AST.Statement.ExpressionStatement.Constant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;

import java.util.ArrayList;
import java.util.List;

public class ArrayPointerType extends Pointer {
	public Type pointTo;
	public ArrayList<Integer> dimensions;

	public ArrayPointerType(Type t, List<Expression> d) {
		pointTo = t;
		dimensions = new ArrayList<Integer>();
		int n = d.size();
		if (n < 1)
			throw new CompilationError("Dimension should be positive");
		for (int i = 0; i < n; ++i) {
			Expression e = d.get(i);
			if (e == null) {
				if (i != 0)
					throw new CompilationError("Array type has incomplete dimensions.");
				dimensions.add(-1);
			} else if (e instanceof Constant) {
				int v = Expression.toInt(e);
				if (v < 0)
					throw new CompilationError("The length of each dimension must be non-negative.");
				dimensions.add(v);
			} else
				throw new CompilationError("The length of each dimension must be constant.");
		}
	}

	public ArrayPointerType() {
		this.pointTo = null;
		this.dimensions = null;
	}

	public Type lower() {
		int n = dimensions.size();
		if (n == 1)
			return pointTo;
		ArrayPointerType ret = new ArrayPointerType();
		ret.pointTo = pointTo;
		ret.dimensions = new ArrayList<Integer>(n - 1);
		for (int i = 1; i < n; ++i)
			ret.dimensions.set(i - 1, dimensions.get(i));
		return ret;
	}

	public VariablePointerType toVariablePointerType() {
		Type t = lower();
		return new VariablePointerType(t);
	}

	@Override
	public int sizeof() { // assume there is no integer overflow
		int ret = pointTo.sizeof();
		if (ret == 0)
			return 0;
		for (int d : dimensions)
			if (d == -1 || d == 0)
				return 0;
			else
				ret *= d;
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ArrayPointerType) {
			ArrayPointerType other = (ArrayPointerType) obj;
			if (!pointTo.equals(other.pointTo))
				return false;
			int n = dimensions.size();
			if (n != other.dimensions.size())
				return false;
			for (int i = 0; i < n; ++i)
				if (!dimensions.get(i).equals(other.dimensions.get(i)))
					return false;
			return true;
		}
		return super.equals(obj);
	}
}
