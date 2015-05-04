package Compiler2015.IR.CFG;

import Compiler2015.IR.IRInstruction;

import java.util.ArrayList;

public class CFGVertex {
	public ArrayList<IRInstruction> internal;
	public CFGVertex branchIfTrue;
	public CFGVertex unconditionalNext;
}
