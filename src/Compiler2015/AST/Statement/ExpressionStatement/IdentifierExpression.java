package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.ArrayPointerType;
import Compiler2015.AST.Type.FunctionPointerType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

public class IdentifierExpression extends Expression {
	public int uId;

	public IdentifierExpression(int uId, Type type) {
		this.type = type;
		this.isLValue = !(type instanceof ArrayPointerType) && !(type instanceof FunctionPointerType);
		this.uId = uId;
	}

	public static Expression getExpression(String name) {
		SymbolTableEntry e = Environment.symbolNames.queryName(name);
		if (e.type != Tokens.VARIABLE)
			throw new CompilationError("Internal Error.");
		return new IdentifierExpression(e.uId, (Type) e.ref);
	}

	@Override
	public String toString(int depth) {
		return Utility.getIndent(depth).append(uId).toString();
	}
}
