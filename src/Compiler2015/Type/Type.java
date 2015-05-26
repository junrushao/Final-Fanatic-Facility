package Compiler2015.Type;

import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Utility;

public abstract class Type implements Cloneable {
	/**
	 * @param a one type
	 * @param b the other type
	 * @return if a could be a parameter while b is the counterpart in parameter list of function declaration
	 * <p>
	 * 1. void types are not allowed
	 * 2. Struct / Union types are compared strictly
	 * 3. the resting types, including numeric ones and pointers, are allowed to cast as they wish to
	 */
	public static boolean suitable(Type a, Type b) {
		// Void
		if (a instanceof VoidType || b instanceof VoidType)
			return false;

		// Struct / Union
		if (a instanceof StructOrUnionType && b instanceof StructOrUnionType)
			return ((StructOrUnionType) a).uId == ((StructOrUnionType) b).uId;
		return !(a instanceof StructOrUnionType || b instanceof StructOrUnionType);

	}

	public static boolean isNumeric(Type x) {
		return (x instanceof IntType) || (x instanceof CharType);
	}

	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).append(Utility.NEW_LINE).toString();
	}

	@Override
	public int hashCode() {
		throw new CompilationError("Internal Error.");
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();

	public abstract int sizeof();

	@Override
	public Type clone() {
		try {
			return (Type) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
