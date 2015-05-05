package Compiler2015.AST.Statement;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;

public interface Logical {
	void emitCFG(CFGVertex trueTo, CFGVertex falseTo, ExpressionCFGBuilder builder);
	CFGVertex getStartNode();
//	CFGVertex getEndNode();
}
