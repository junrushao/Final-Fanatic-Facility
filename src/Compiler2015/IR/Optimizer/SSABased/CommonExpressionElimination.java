package Compiler2015.IR.Optimizer.SSABased;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.*;
import Compiler2015.IR.Optimizer.EliminateNop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CommonExpressionElimination {

	public static VirtualRegister fakeNullVirtualRegister = new VirtualRegister(-3);

	public HashMap<VirtualRegister, Stack<IRRegister>> copyTable;
	public HashMap<IRInstruction, Stack<VirtualRegister>> expressionTable;
	public ControlFlowGraph graph;

	public CommonExpressionElimination(ControlFlowGraph graph) {
		this.copyTable = new HashMap<>();
		this.expressionTable = new HashMap<>();
		this.graph = graph;
		eliminate(graph.source);
		EliminateNop.process(graph);
	}

	public void eliminate(CFGVertex b) {
		ArrayList<VirtualRegister> copiedHere = new ArrayList<>();
		ArrayList<IRInstruction> calcHere = new ArrayList<>();
		for (int itr = 0; itr < b.phiBlock.size(); ++itr) {
			PhiFunction phi = (PhiFunction) b.phiBlock.get(itr);
			VirtualRegister v = getInstructionFrom(phi);
			boolean deleted = false;
			if (v != null) {
				addCopy(phi.rd, v, copiedHere);
				deleted = true;
			} else {
				boolean allSame = true;
				for (int ptr = 1; ptr < phi.rs.length; ++ptr)
					if (!phi.rs[ptr].equals(phi.rs[0])) {
						allSame = false;
						break;
					}
				if (allSame) {
					addCopy(phi.rd, phi.rs[0], copiedHere);
					deleted = true;
				}
			}
			if (deleted)
				b.phiBlock.set(itr, Nop.instance);
			else
				addInstruction(phi, calcHere);
		}

		for (int itr = 0; itr < b.internal.size(); ++itr) {
			IRInstruction i = b.internal.get(itr);
			i = substituteWithCopies(i);
			i = i.getExpression();
			b.internal.set(itr, i.clone());
			if (isForbidden(i))
				continue;
			boolean deleted = false;
			if (i instanceof Move) {
				addCopy(i.rd, ((Move) i).rs, copiedHere);
				deleted = true;
			} else {
				VirtualRegister sub = getInstructionFrom(i);
				if (sub != null) {
					addCopy(i.rd, sub, copiedHere);
					deleted = true;
				}
			}
			if (deleted)
				b.internal.set(itr, Nop.instance);
			else
				addInstruction(i, calcHere);
		}

		for (CFGVertex v : new CFGVertex[]{b.unconditionalNext, b.branchIfFalse})
			if (v != null)
				for (int i = 0; i < v.phiBlock.size(); ++i)
					v.phiBlock.set(i, substituteWithCopies(v.phiBlock.get(i)));

		b.children.forEach(this::eliminate);
		for (VirtualRegister r : copiedHere)
			copyTable.get(r).pop();
		for (IRInstruction i : calcHere)
			expressionTable.get(i).pop();
	}

	public IRInstruction substituteWithCopies(IRInstruction i) {
		i = i.clone();
		IRRegister rs[] = i.getAllUse();
		for (int itr = 0; itr < rs.length; ++itr) {
			IRRegister r = rs[itr];
			if (r == null || !(r instanceof VirtualRegister))
				continue;
			IRRegister rp = getCopyFrom((VirtualRegister) r);
			if (rp != null)
				rs[itr] = rp;
		}
		i.setAllUse(rs);
		return i;
	}

	public boolean isForbidden(IRInstruction instruction) {
		if (instruction instanceof Call)
			return true;
		if (instruction instanceof Def)
			return true;
		if (instruction instanceof FetchReturn)
			return true;
		if (instruction instanceof Nop)
			return true;
		if (instruction instanceof NopForBranch)
			return true;
		if (instruction instanceof PushStack)
			return true;
		//noinspection SimplifiableIfStatement
		if (instruction instanceof SetReturn)
			return true;
		return instruction instanceof WriteArray;
	}

	public void addInstruction(IRInstruction instruction, ArrayList<IRInstruction> backup) {
		if (isForbidden(instruction))
			return;
		IRInstruction newInstruction = instruction.clone();
		VirtualRegister rd = newInstruction.rd;
		newInstruction.rd = fakeNullVirtualRegister;
		if (!expressionTable.containsKey(newInstruction))
			expressionTable.put(newInstruction, new Stack<>());
		expressionTable.get(newInstruction).push(rd);
		backup.add(newInstruction);
	}

	public void addCopy(VirtualRegister rd, IRRegister rs, ArrayList<VirtualRegister> backup) {
		rd = rd.clone();
		rs = rs.clone();
		if (!copyTable.containsKey(rd))
			copyTable.put(rd, new Stack<>());
		copyTable.get(rd).push(rs);
		backup.add(rd);
	}

	public VirtualRegister getInstructionFrom(IRInstruction instruction) {
		if (isForbidden(instruction))
			return null;
		IRInstruction newInstruction = instruction.clone();
		newInstruction.rd = fakeNullVirtualRegister;
		if (!expressionTable.containsKey(newInstruction))
			return null;
		Stack<VirtualRegister> stack = expressionTable.get(newInstruction);
		return stack.isEmpty() ? null : stack.peek();
	}

	public IRRegister getCopyFrom(VirtualRegister x) {
		if (!copyTable.containsKey(x))
			return null;
		Stack<IRRegister> stack = copyTable.get(x);
		return stack.isEmpty() ? null : stack.peek();
	}
}
