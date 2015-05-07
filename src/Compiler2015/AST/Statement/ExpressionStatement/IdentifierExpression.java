package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Tokens;

public class IdentifierExpression extends Expression {
	public int uId;

	public IdentifierExpression(int uId, Type type) {
		this.type = type;
		this.isLValue = true;
		this.uId = uId;
	}

	public static Expression getExpression(String name) {
		SymbolTableEntry e = Environment.symbolNames.queryName(name);
		if (e.type != Tokens.VARIABLE)
			throw new CompilationError("Internal Error.");
		return new IdentifierExpression(e.uId, (Type) e.ref);
	}

	public static Expression getExpression(int uId) {
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e.type != Tokens.VARIABLE)
			throw new CompilationError("Internal Error.");
		return new IdentifierExpression(e.uId, (Type) e.ref);
	}

	@Override
	public String toString() {
		return String.format("#%d", uId);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		tempRegister = new VirtualRegister(uId);
	}
}
