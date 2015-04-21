package Compiler2015.AST.Declaration;

import Compiler2015.AST.Statement.Statement;
import Compiler2015.AST.Type.FunctionPointerType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Utility.Utility;

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

	public String toString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("[FunctionDeclaration] return = ");
		sb.append(returnType.toString()).append(", parameter =");
		String sep = " <";
		for (int i = 0, n = parameterTypes.size(); i < n; ++i) {
			Type t = parameterTypes.get(i);
			String s = parameterNames.get(i);
			sb.append(sep).append('(').append(t.toString()).append(", ").append(s).append(')');
			sep = ", ";
		}
		sb.append(">, hasVaList = ").append(hasVaList).append(Utility.NEW_LINE);
		if (statement != null)
			sb.append(statement.toString(depth + 1));
		return sb.toString();
	}
}
