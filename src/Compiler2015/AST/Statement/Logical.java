package Compiler2015.AST.Statement;

import Compiler2015.IR.CFG.CFGVertex;

public interface Logical {
	void emitCFG(CFGVertex trueTo, CFGVertex falseTo);

	CFGVertex getStartNode();
}
