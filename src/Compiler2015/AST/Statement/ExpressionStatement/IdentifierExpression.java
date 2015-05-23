package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.FunctionType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Tokens;

import java.util.HashMap;

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
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e.scope == 1 && !(e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType || e.ref instanceof FunctionType) && !dumpTo.containsKey(uId))
			dumpTo.put(uId, Environment.getVirtualRegister());
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		if (ControlFlowGraph.globalNonArrayVariables.containsKey(uId))
			tempRegister = ControlFlowGraph.globalNonArrayVariables.get(uId).clone();
		else
			tempRegister = new VirtualRegister(uId);
	}
}
