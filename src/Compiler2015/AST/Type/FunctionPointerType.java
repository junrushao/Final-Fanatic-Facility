package Compiler2015.AST.Type;

import java.util.ArrayList;

/**
 *
 */
public class FunctionPointerType extends Pointer {
	public Type returnType;
	public ArrayList<Type> parameterType;
	public ArrayList<String> parameterName;
	public boolean isConstant;
	public boolean hasVarList;

	public FunctionPointerType(Type returnType, ArrayList<Type> parameterType, ArrayList<String> parameterName, boolean isConstant, boolean hasVarList) {
		this.returnType = returnType;
		this.parameterType = parameterType;
		this.parameterName = parameterName;
		this.isConstant = isConstant;
		this.hasVarList = hasVarList;
	}

	@Override
	public int sizeof() {
		return 8;
	}
}
