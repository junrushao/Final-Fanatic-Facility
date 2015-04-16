package Compiler2015.AST.Declaration;

import Compiler2015.AST.Type.Type;

public class FunctionDeclaration extends Declaration {
	public int uId;
	public Type returnType;
	public Type parameterTypes[];
	public String parameterNames[];

	public FunctionDeclaration(int uId, Type returnType, Type[] parameterTypes, String[] parameterNames) {
		this.uId = uId;
		this.returnType = returnType;
		this.parameterTypes = parameterTypes;
		this.parameterNames = parameterNames;
	}

}
