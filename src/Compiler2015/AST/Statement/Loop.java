package Compiler2015.AST.Statement;

import Compiler2015.IR.CFG.CFGVertex;

public interface Loop {
	CFGVertex getLoop();

	CFGVertex getOut();
}
