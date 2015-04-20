package Compiler2015.AST.Type;

import java.util.ArrayList;

public class FunctionPointerType extends Pointer {
	public Type returnType;
	public ArrayList<Type> parameterType;
	public ArrayList<String> parameterName;
	public boolean hasVarList;

	public FunctionPointerType(Type returnType, ArrayList<Type> parameterType, ArrayList<String> parameterName, boolean hasVarList) {
		this.returnType = returnType;
		this.parameterType = parameterType;
		this.parameterName = parameterName;
		this.hasVarList = hasVarList;
	}

	@Override
	public int sizeof() {
		return 8;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FunctionPointerType) {
			FunctionPointerType other = (FunctionPointerType) obj;
			if (hasVarList != other.hasVarList)
				return false;
			if (!returnType.equals(other.returnType))
				return false;
			int size = parameterType.size();
			if (size != other.parameterType.size())
				return false;
			for (int i = 0; i < size; ++i)
				if (!parameterType.get(i).equals(other.parameterType.get(i)))
					return false;
		}
		return super.equals(obj);
	}
}
