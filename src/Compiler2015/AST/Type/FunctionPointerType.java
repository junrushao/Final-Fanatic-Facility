package Compiler2015.AST.Type;

import java.util.ArrayList;

public class FunctionPointerType extends Pointer {
	public Type returnType;
	public ArrayList<Type> parameterTypes;
	public ArrayList<String> parameterNames;
	public boolean hasVaList;

	public FunctionPointerType(Type returnType, ArrayList<Type> parameterTypes, ArrayList<String> parameterNames, boolean hasVaList) {
		this.returnType = returnType;
		this.parameterTypes = parameterTypes;
		this.parameterNames = parameterNames;
		this.hasVaList = hasVaList;
	}

	@Override
	public int sizeof() {
		return 8;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FunctionPointerType) {
			FunctionPointerType other = (FunctionPointerType) obj;
			if (hasVaList != other.hasVaList)
				return false;
			if (!returnType.equals(other.returnType))
				return false;
			int size = parameterTypes.size();
			if (size != other.parameterTypes.size())
				return false;
			for (int i = 0; i < size; ++i)
				if (!parameterTypes.get(i).equals(other.parameterTypes.get(i)))
					return false;
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("FunctionPointer(return = ");
		sb.append(returnType.toString()).append(", parameter =");
		String sep = " <";
		for (Type t : parameterTypes) {
			sb.append(sep).append(t.toString());
			sep = ", ";
		}
		sb.append(">, hasVaList = ").append(hasVaList).append(']');
		return sb.toString();
	}
}
