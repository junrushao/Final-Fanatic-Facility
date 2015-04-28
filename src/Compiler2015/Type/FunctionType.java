package Compiler2015.Type;

import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;

public class FunctionType extends Type {
	public Type returnType;
	public ArrayList<Type> parameterTypes;
	public ArrayList<String> parameterNames;
	public boolean hasVaList;

	public FunctionType(Type returnType, ArrayList<Type> parameterTypes, ArrayList<String> parameterNames, boolean hasVaList) {
		if (parameterTypes == null)
			parameterTypes = new ArrayList<>();
		if (parameterNames == null)
			parameterNames = new ArrayList<>();
		if (returnType instanceof ArrayPointerType)
			throw new CompilationError("A function should not return an array.");
		if (returnType instanceof FunctionType)
			throw new CompilationError("A function should not return a function.");
//		if (parameterNames.size() != 0 && (new HashSet<>(parameterNames).size()) != parameterNames.size())
//			throw new CompilationError("parameter should have different names");

		for (int i = 0, n = parameterTypes.size(); i < n; ++i) {
			Type t = parameterTypes.get(i);
			if (t instanceof ArrayPointerType)
				parameterTypes.set(i, ((ArrayPointerType) t).toVariablePointerType());
		}

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
		if (!(obj instanceof FunctionType))
			return false;
		FunctionType other = (FunctionType) obj;
		if (hasVaList != other.hasVaList)
			return false;
		int size = parameterTypes.size();
		if (size != other.parameterTypes.size())
			return false;
		if (!returnType.equals(other.returnType))
			return false;
		for (int i = 0; i < size; ++i)
			if (!parameterTypes.get(i).equals(other.parameterTypes.get(i)))
				return false;
		return true;
	}

	@Override
	public String toString() {
		return "FunctionType(return = " + returnType.toString()
				+ ", parameter = " + Utility.toString(parameterTypes, parameterNames)
				+ (hasVaList ? ", ..." : "") + ")";
	}

}
