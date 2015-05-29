package Compiler2015.IR.StaticSingleAssignment;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.*;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Instruction.Nop;
import Compiler2015.IR.Instruction.NopForBranch;
import Compiler2015.IR.Instruction.PhiFunction;
import Compiler2015.IR.Instruction.TwoAddressInstruction.Move;
import Compiler2015.IR.Optimizer.EliminateNop;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Utility.Tokens;

import java.util.HashMap;

public final class SSADestroyer {
	public static ControlFlowGraph graph;
	public static HashMap<VirtualRegister, Integer> mapFromSSARegisterToSimpleRegister;

	public static void placeBeforeNopForBranch(CFGVertex b, IRInstruction ins) {
		int size = b.internal.size();
		if (size >= 1 && b.internal.get(size - 1) instanceof NopForBranch) {
			if (b.branchIfFalse == null)
				throw new CompilationError("Internal Error.");
			IRInstruction backup = b.internal.get(size - 1);
			b.internal.set(size - 1, ins);
			b.internal.add(backup);
		} else {
			if (b.branchIfFalse != null)
				throw new CompilationError("Internal Error.");
			b.internal.add(ins);
		}
	}

	public static void destroyPhiFunctions() {
		for (CFGVertex v : graph.vertices)
			for (int instructionCount = 0, size = v.phiBlock.size(); instructionCount < size; ++instructionCount) {
				if (v.phiBlock.get(instructionCount) instanceof PhiFunction) {
					PhiFunction ins = (PhiFunction) v.phiBlock.get(instructionCount);
					IRRegister use[] = ins.getAllUse();
					VirtualRegister rd = ins.rd;
					if (rd.uId != 0) {
						for (HashMap.Entry<CFGVertex, Integer> predEntry : v.predecessor.entrySet()) {
							CFGVertex pred = predEntry.getKey();
							int i = predEntry.getValue();
							placeBeforeNopForBranch(pred, new Move(rd, use[i]));
						}
					}
					v.phiBlock.set(instructionCount, Nop.instance);
				}
			}
		EliminateNop.process(graph);
	}

	public static IRRegister renameVirtualRegisters(IRRegister reg) {
		if (reg instanceof LowerSemiLattice)
			throw new CompilationError("Internal Error.");
		reg = reg.clone();
		if (reg instanceof ImmediateValue)
			return reg;
		else if (reg instanceof ArrayRegister) {
			((ArrayRegister) reg).a = (VirtualRegister) renameVirtualRegisters(((ArrayRegister) reg).a);
			return reg;
		} else if (reg instanceof VirtualRegister) {
			VirtualRegister r = (VirtualRegister) reg;
			int uId = r.uId;
			if (uId == 0)
				return new VirtualRegister(0);
			SymbolTableEntry e = Environment.symbolNames.table.get(uId);
			if (e.type != Tokens.STRING_CONSTANT && e.type != Tokens.VARIABLE && e.type != Tokens.TEMPORARY_REGISTER)
				throw new CompilationError("Internal Error.");
			if (e.type != Tokens.TEMPORARY_REGISTER) {
				if (e.type == Tokens.STRING_CONSTANT || e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType) {
					if (r.version != 0)
						throw new CompilationError("Internal Error.");
					return new VirtualRegister(uId);
				}
				if (r.version == 0) { // original definition
					r.version = -1;
					return r;
				}
			}
//			VirtualRegister vr = new VirtualRegister(r);
			if (mapFromSSARegisterToSimpleRegister.containsKey(r))
				return new VirtualRegister(mapFromSSARegisterToSimpleRegister.get(r));
			VirtualRegister newRegister = Environment.getVirtualRegister();
			mapFromSSARegisterToSimpleRegister.put(r, newRegister.uId);
			return newRegister;
		}
		throw new CompilationError("Internal Error.");
	}

	public static void renameVirtualRegisters(IRInstruction ins) {
		if (ins instanceof PhiFunction)
			throw new CompilationError("Internal Error.");
		if (ins instanceof Nop)
			return;
		IRRegister use[] = ins.getAllUse();
		for (int i = 0; i < use.length; ++i)
			use[i] = renameVirtualRegisters(use[i]);
		ins.setAllUse(use);
		IRRegister def[] = ins.getAllDef();
		for (int i = 0; i < def.length; ++i)
			def[i] = renameVirtualRegisters(def[i]);
		ins.setAllDef(def);
	}

	public static void renameVirtualRegisters() {
		mapFromSSARegisterToSimpleRegister = new HashMap<>();
		for (CFGVertex v : graph.vertices) {
			v.phiBlock.forEach(SSADestroyer::renameVirtualRegisters);
			v.internal.forEach(SSADestroyer::renameVirtualRegisters);
		}
	}

	public static void process(ControlFlowGraph graph) {
		SSADestroyer.graph = graph;

		EliminateNop.process(graph);
		destroyPhiFunctions();
		renameVirtualRegisters();

		mapFromSSARegisterToSimpleRegister = null;
	}
}
