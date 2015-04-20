package Compiler2015.AST.Declaration;

import Compiler2015.AST.Statement.Statement;
import Compiler2015.AST.Type.FunctionPointerType;
import Compiler2015.AST.Type.Type;

import java.util.ArrayList;

public class FunctionDeclaration extends Declaration {
	public int uId;
	public Type returnType;
	public ArrayList<Type> parameterTypes;
	public ArrayList<String> parameterNames;
	public boolean hasVaList;
	public Statement statement;

	public FunctionDeclaration(int uId, Type returnType, ArrayList<Type> parameterTypes, ArrayList<String> parameterNames, boolean hasVaList, Statement statement) {
		this.uId = uId;
		this.returnType = returnType;
		this.parameterTypes = parameterTypes;
		this.parameterNames = parameterNames;
		this.hasVaList = hasVaList;
		this.statement = statement;
	}

	public FunctionPointerType toFunctionPointerType() {
		return new FunctionPointerType(returnType, parameterTypes, parameterNames, hasVaList);
	}
}
