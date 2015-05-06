package Compiler2015.AST.Statement;

import Compiler2015.AST.ASTNode;
import Compiler2015.IR.CFG.CFGVertex;

public abstract class Statement extends ASTNode {
	public CFGVertex beginCFGBlock = null, endCFGBlock = null;
	public abstract String toString();
	public abstract void emitCFG();
}
