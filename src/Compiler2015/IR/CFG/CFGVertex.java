package Compiler2015.IR.CFG;

import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;

public class CFGVertex {
	public ArrayList<IRInstruction> internal = new ArrayList<>();
	public CFGVertex branchIfFalse = null;
	public CFGVertex unconditionalNext = null;

	public int id = 0;
	public CFGVertex parent = null;
	public CFGVertex idom = null;

	protected CFGVertex() {
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(Utility.getIndent(1) + "Vertex ~" + id + Utility.NEW_LINE);
		if (unconditionalNext != null)
			sb.append(Utility.getIndent(2)).append("--> ").append(unconditionalNext.id).append(Utility.NEW_LINE);
		if (branchIfFalse != null)
			sb.append(Utility.getIndent(2)).append("-->(BEQ 0)--> ").append(branchIfFalse.id).append(Utility.NEW_LINE);
		internal.stream().forEach(x -> sb.append(Utility.getIndent(2)).append(x).append(Utility.NEW_LINE));
		return sb.toString();
	}
}
