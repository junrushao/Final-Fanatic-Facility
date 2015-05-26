package Compiler2015.AST.Statement;

import Compiler2015.AST.ASTNode;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.IRRegister.IRRegister;

public abstract class Statement extends ASTNode {
	public CFGVertex beginCFGBlock = null, endCFGBlock = null;

	@Override
	public abstract String toString();

	public abstract void emitCFG();

	public void setBranch(CFGVertex branchTo, IRRegister register) {
		if (endCFGBlock.branchIfFalse == null) {
			endCFGBlock.branchIfFalse = branchTo;
			endCFGBlock.branchRegister = register.clone();
		}
	}

	public abstract Statement rebuild();
}
