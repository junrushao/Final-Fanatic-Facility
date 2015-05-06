package Compiler2015.IR.CFG;

import Compiler2015.IR.Instruction.IRInstruction;

import java.util.ArrayList;

public class CFGVertex {
	public ArrayList<IRInstruction> internal = null;
	public CFGVertex branchIfFalse = null;
	public CFGVertex unconditionalNext = null;
}
