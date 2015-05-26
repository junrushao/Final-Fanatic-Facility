package Compiler2015.IR.CFG;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Instruction.PhiFunction;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CFGVertex {
	public ArrayList<IRInstruction> internal = new ArrayList<>();
	public CFGVertex branchIfFalse = null;
	public CFGVertex unconditionalNext = null;
	public IRRegister branchRegister = null;

	public int id = 0;
	public CFGVertex parent = null;
	public CFGVertex idom = null;
	public HashMap<CFGVertex, Integer> predecessor = new HashMap<>();
	public HashSet<CFGVertex> dominanceFrontier = new HashSet<>();
	public ArrayList<CFGVertex> children = new ArrayList<>();
	public HashMap<Integer, PhiFunction> phis = new HashMap<>();

	protected CFGVertex() {
	}

	public void addPhi(int x) {
		phis.put(x, new PhiFunction(predecessor.size(), new VirtualRegister(x)));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(Utility.getIndent(1) + "Vertex~" + id + Utility.NEW_LINE);
		if (unconditionalNext != null)
			sb.append(Utility.getIndent(2)).append("--> ").append(unconditionalNext.id).append(Utility.NEW_LINE);
		if (branchIfFalse != null)
			sb.append(Utility.getIndent(2)).append("--(BEQ 0)--> ").append(branchIfFalse.id).append("  ").append(branchRegister).append(Utility.NEW_LINE);
		phis.entrySet().stream().forEach(
				x -> sb.append(Utility.getIndent(2))
						.append(new VirtualRegister(x.getKey()).toString())
						.append("_").append(x.getValue().toString()).append(Utility.NEW_LINE));
		internal.stream().forEach(x -> sb.append(Utility.getIndent(2)).append(x).append(Utility.NEW_LINE));
		return sb.toString();
	}
}
