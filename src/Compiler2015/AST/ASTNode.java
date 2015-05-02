package Compiler2015.AST;

import Compiler2015.IR.IRStream;

public abstract class ASTNode {
	public abstract String deepToString(int depth);
	public abstract void emitIR(IRStream stream);
}
