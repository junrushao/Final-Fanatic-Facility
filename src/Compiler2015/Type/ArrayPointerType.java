package Compiler2015.Type;

import Compiler2015.AST.Statement.ExpressionStatement.Constant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Panel;

import java.util.ArrayList;

public class ArrayPointerType extends Pointer {
	public Type pointTo;
	public ArrayList<Integer> dimensions;

	public ArrayPointerType(Type pointTo, ArrayList<Integer> dimensions) {
		this.pointTo = pointTo;
		this.dimensions = dimensions;
	}

	public ArrayPointerType() {
		this.pointTo = null;
		this.dimensions = null;
	}

	public static Type pushFromUndimensioned(Type t) {
		ArrayPointerType ret = new ArrayPointerType();
		if (t instanceof ArrayPointerType) {
			if (((ArrayPointerType) t).dimensions.get(0) == -1)
				throw new CompilationError("Dimension error.");
			ret.pointTo = ((ArrayPointerType) t).pointTo;
			ret.dimensions = new ArrayList<Integer>() {{
				add(-1);
			}};
			ret.dimensions.addAll(((ArrayPointerType) t).dimensions);
		} else {
			ret.pointTo = t;
			ret.dimensions = new ArrayList<Integer>() {{
				add(-1);
			}};
		}
		return ret;
	}

	public static Type pushFrontDimension(Type t, Object o) {
		if (!(o instanceof Constant))
			throw new CompilationError("Length should be a non-negative integer.");
		int v = Expression.toInt((Expression) o);
		if (v < 0)
			throw new CompilationError("Length should be non-negative.");
		ArrayPointerType ret = new ArrayPointerType();
		if (t instanceof ArrayPointerType) {
			if (((ArrayPointerType) t).dimensions.get(0) == -1)
				throw new CompilationError("Dimension error.");
			ret.pointTo = ((ArrayPointerType) t).pointTo;
			ret.dimensions = new ArrayList<Integer>() {{
				add(v);
			}};
			ret.dimensions.addAll(((ArrayPointerType) t).dimensions);
		} else {
			ret.pointTo = t;
			ret.dimensions = new ArrayList<Integer>() {{
				add(v);
			}};
		}
		return ret;
	}

	public Type lower() {
		int n = dimensions.size();
		if (n == 1)
			return pointTo;
		ArrayPointerType ret = new ArrayPointerType();
		ret.pointTo = pointTo;
		ret.dimensions = new ArrayList<>(n - 1);
		for (int i = 1; i < n; ++i)
			ret.dimensions.add(dimensions.get(i));
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
	public int classifiedSizeof() {
		return Panel.getPointerSize();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ArrayPointerType))
			return false;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Array");
		for (int i : dimensions) {
			sb.append('[');
			if (i != -1)
				sb.append(i);
			sb.append(']');
		}
		sb.append('(').append(pointTo.toString()).append(')');
		return sb.toString();
	}
}
