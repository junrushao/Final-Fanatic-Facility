package Compiler2015.AST.Statement;

import Compiler2015.AST.ASTNode;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.IRRegister.VirtualRegister;

import java.util.HashMap;

public abstract class Statement extends ASTNode {
	public CFGVertex beginCFGBlock = null, endCFGBlock = null;

	@Override
	public abstract String toString();
	public abstract void emitCFG();

	public abstract void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo);
}
