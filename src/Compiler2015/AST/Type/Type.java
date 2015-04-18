package Compiler2015.AST.Type;

import Compiler2015.AST.ASTNode;
import Compiler2015.AST.SizeMeasurable;

public abstract class Type extends ASTNode implements SizeMeasurable {
	// TODO: need to be scrutinized
	public static boolean suitable(Type a, Type b) {
		if (a instanceof StructOrUnionType && b instanceof StructOrUnionType)
			return ((StructOrUnionType)a).ref.uId == ((StructOrUnionType)b).ref.uId;
		if (a instanceof StructOrUnionType || b instanceof StructOrUnionType)
			return false;

		boolean isBasicA = a instanceof IntType || a instanceof CharType;
		boolean isBasicB = b instanceof IntType || b instanceof CharType;
		if (isBasicA && isBasicB)
			return true;
		boolean va = a instanceof VoidType;
		boolean vb = b instanceof VoidType;
		if (va && vb)
			return true;
		if (isBasicA || va || isBasicB || vb)
			return false;
		if (a instanceof FunctionPointerType && b instanceof FunctionPointerType) {
			FunctionPointerType A = (FunctionPointerType)a;
			FunctionPointerType B = (FunctionPointerType)b;
			int size = A.parameterType.size();
			if (A.parameterType.size() != B.parameterType.size())
				if (!suitable(A.returnType, B.returnType))
				return false;
			for (int i = 0; i < size; ++i)
				if (!suitable(A.parameterType.get(i), B.parameterType.get(i)))
					return false;
			return true;
		}
		if (a instanceof FunctionPointerType || b instanceof FunctionPointerType)
			return false;
		Type aRef = a instanceof ArrayPointerType ? ((ArrayPointerType) a).pointTo : ((VariablePointerType) b).pointTo;
		Type bRef = b instanceof ArrayPointerType ? ((ArrayPointerType) b).pointTo : ((VariablePointerType) b).pointTo;
		return sameType(aRef, bRef);
	}

	// TODO: need to be scrutinized
	public static boolean sameType(Type a, Type b) {
		if (a instanceof StructOrUnionType && b instanceof StructOrUnionType)
			return ((StructOrUnionType)a).ref.uId == ((StructOrUnionType)b).ref.uId;
		if (a instanceof StructOrUnionType || b instanceof StructOrUnionType)
			return false;

		boolean isVoidA = a instanceof VoidType;
		boolean isVoidB = b instanceof VoidType;
		if (isVoidA && isVoidB)
			return true;
		boolean isIntA = a instanceof IntType;
		boolean isIntB = b instanceof IntType;
		if (isIntA && isIntB)
			return true;
		boolean isCharA = a instanceof CharType;
		boolean isCharB = b instanceof CharType;
		if (isCharA && isCharB)
			return true;
		if (isVoidA || isVoidB || isIntA || isIntB || isCharA || isCharB)
			return false;
		if (a instanceof FunctionPointerType && b instanceof FunctionPointerType) {
			FunctionPointerType A = (FunctionPointerType)a;
			FunctionPointerType B = (FunctionPointerType)b;
			int size = A.parameterType.size();
			if (A.parameterType.size() != B.parameterType.size())
				if (!sameType(A.returnType, B.returnType))
					return false;
			for (int i = 0; i < size; ++i)
				if (!sameType(A.parameterType.get(i), B.parameterType.get(i)))
					return false;
			return true;
		}
		if (a instanceof FunctionPointerType || b instanceof FunctionPointerType)
			return false;
		Type aRef = a instanceof ArrayPointerType ? ((ArrayPointerType) a).pointTo : ((VariablePointerType) b).pointTo;
		Type bRef = b instanceof ArrayPointerType ? ((ArrayPointerType) b).pointTo : ((VariablePointerType) b).pointTo;
		return sameType(aRef, bRef);
	}

	public static boolean isCalculatableType(Type x) {
		return isNumeric(x)|| Pointer.isCommonPointer(x);
	}

	public static boolean isNumeric(Type x) {
		return x instanceof IntType || x instanceof CharType;
	}
}
