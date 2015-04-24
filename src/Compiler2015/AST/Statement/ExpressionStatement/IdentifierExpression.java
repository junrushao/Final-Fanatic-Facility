package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.ArrayPointerType;
import Compiler2015.AST.Type.FunctionType;
import Compiler2015.AST.Type.Type;
import Compiler2015.AST.Type.VoidType;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Tokens;

public class IdentifierExpression extends Expression {
	public int uId;

	public IdentifierExpression(int uId, Type type) {
		this.type = type;
		this.isLValue = !(type instanceof ArrayPointerType) && !(type instanceof FunctionType) && !(type instanceof VoidType);
		this.uId = uId;
	}

	public static Expression getExpression(String name) {
		SymbolTableEntry e = Environment.symbolNames.queryName(name);
		if (e.type != Tokens.VARIABLE)
			throw new CompilationError("Internal Error.");
		return new IdentifierExpression(e.uId, (Type) e.ref);
	}

	@Override
	public String toString() {
		return String.format("#%d", uId);
	}
}
