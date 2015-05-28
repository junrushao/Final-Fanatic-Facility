package Compiler2015.IR.StaticSingleAssignment;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.LowerSemiLattice;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.IR.Instruction.Nop;
import Compiler2015.IR.Instruction.PhiFunction;
import Compiler2015.IR.Optimizer.DataFlow;
import Compiler2015.IR.Optimizer.EliminateNop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public final class PhiPlacer {
/*
	public static HashSet<Integer> globals;
	public static HashMap<Integer, HashSet<CFGVertex>> blocks;
	public static HashMap<Integer, Integer> counter;
	public static HashMap<Integer, Stack<Integer>> stack;

	public static int getSubscript(int n) {
		if (!counter.containsKey(n)) {
			counter.put(n, 0);
			stack.put(n, new Stack<>());
		}
		int i = counter.get(n);
		counter.put(n, i + 1);
		stack.get(n).push(i);
		return i;
	}

	public static int getTopStack(int x) {
		return stack.get(x).peek();
	}

	public static void rename(CFGVertex b) {
		for (IRInstruction phi : b.phiBlock) {
			if (!(phi instanceof PhiFunction))
				throw new CompilationError("Internal Error.");
			int x = phi.getAllDefUId()[0];
			phi.setAllDefVersion(new int[]{ getSubscript(x) });
		}
		for (IRInstruction ins : b.internal) {
			// set subscripts of use
			int use[] = ins.getAllUseUId();
			int useSub[] = new int[use.length];
			for (int i = 0; i < use.length; ++i)
				if (use[i] != -1)
					useSub[i] = getTopStack(use[i]);
			ins.setAllUseVersion(useSub);

			// set subscripts of def
			int def[] = ins.getAllDefUId();
			int defSub[] = new int[def.length];
			for (int i = 0; i < def.length; ++i)
				if (def[i] != -1)
					defSub[i] = getSubscript(def[i]);
			ins.setAllDefVersion(defSub);
		}
		for (CFGVertex y : new CFGVertex[] { b.unconditionalNext, b.branchIfFalse } ) {
			if (y == null)
				continue;
			int j = y.predecessor.get(b);
			for (IRInstruction ins : y.phiBlock) {
				if (!(ins instanceof PhiFunction))
					throw new CompilationError("Internal Error.");
				PhiFunction phi = (PhiFunction) ins;
				int a = phi.getAllDefUId()[0];
				if (!stack.containsKey(a))
					throw new CompilationError("Internal Error.");
				phi.setUse(j, getTopStack(a));
			}
		}
		b.children.forEach(PhiPlacer::rename);
		for (IRInstruction ins : b.phiBlock)
			for (int x : ins.getAllDefUId())
				stack.get(x).pop();
		for (IRInstruction ins : b.internal)
			for (int x : ins.getAllDefUId())
				stack.get(x).pop();
	}

	public static void process() {
		LengauerTarjan.process(ControlFlowGraph.vertices, ControlFlowGraph.source, ControlFlowGraph.vertices.size());

		globals = new HashSet<>();
		blocks = new HashMap<>();

		// find global names: all upward exposed uses
		for (CFGVertex block : ControlFlowGraph.vertices) {
			HashSet<Integer> varKill = new HashSet<>();
			for (IRInstruction ins : block.internal) {
				for (int x : ins.getAllUseUId())
					if (x != -1 && !varKill.contains(x))
						globals.add(x);
				for (int x : ins.getAllDefUId())
					if (x != -1) {
						varKill.add(x);
						if (!blocks.containsKey(x))
							blocks.put(x, new HashSet<>());
						blocks.get(x).add(block);
					}
			}
		}

		// rewrite the code, only variables that is live across blocks need phi-functions
		for (int x : globals) {
			// assume blocks.get(x) != null
			ArrayList<CFGVertex> worklist = new ArrayList<>(blocks.get(x));
			HashSet<CFGVertex> alreadyInWorklist = new HashSet<>(worklist);
			HashSet<CFGVertex> alreadyContainPhi = new HashSet<>();
			for (int i = 0; i < worklist.size(); ++i) {
				CFGVertex b = worklist.get(i);
				b.dominanceFrontier.stream().filter(d -> !alreadyContainPhi.contains(d)).forEach(d -> {
					d.addPhi(x);
					alreadyContainPhi.add(d);
					if (!alreadyInWorklist.contains(d)) {
						alreadyInWorklist.add(d);
						worklist.add(d);
					}
				});
			}
		}
		// prune my SSA
//		NaiveDeadCodeElimination.process(false);

		counter = new HashMap<>();
		stack = new HashMap<>();
		rename(ControlFlowGraph.source);
	}
*/

	public static HashSet<Integer> allVariables;
	public static HashMap<Integer, Stack<IRRegister>> stack;
	public static HashMap<Integer, Integer> count;

	public static int getNewSubscript(int uId) {
		int i = count.get(uId);
		count.put(uId, i + 1);
		return i;
	}

	public static IRRegister getTopStack(int uId) {
		return stack.get(uId).peek();
	}

	public static IRRegister[] copyVRArray(VirtualRegister[] from) {
		IRRegister ret[] = new IRRegister[from.length];
		for (int i = 0; i < from.length; ++i)
			ret[i] = from[i] == null ? null : from[i].clone();
		return ret;
	}

	public static void renameUse(ArrayList<IRInstruction> block, int i) {
		IRInstruction a = block.get(i);
		if (a instanceof PhiFunction)
			return;
		VirtualRegister uses[] = a.getAllUseVR();
		IRRegister replaceUse[] = copyVRArray(uses);
		for (int itr = 0; itr < uses.length; ++itr) {
			VirtualRegister v = uses[itr];
			if (v == null)
				continue;
			replaceUse[itr] = getTopStack(v.uId);
		}
		a.setAllUse(replaceUse);
	}

	public static void renameDef(ArrayList<IRInstruction> block, int i, ArrayList<Integer> pushSequence) {
		IRInstruction a = block.get(i);
		if (a instanceof Move) { // copy folding
			VirtualRegister v = a.rd;
			IRRegister vr = ((Move) a).rs;
			stack.get(v.uId).push(vr.clone());
			pushSequence.add(v.uId);
			block.set(i, Nop.instance);
			return;
		}
		VirtualRegister defs[] = a.getAllDefVR();
		IRRegister replaceDef[] = copyVRArray(defs);
		for (int itr = 0; itr < defs.length; ++itr) {
			VirtualRegister v = defs[itr];
			if (v == null)
				continue;
			int uId = v.uId;
			int subscript = getNewSubscript(uId);
			VirtualRegister vNew = new VirtualRegister(uId, subscript);
			stack.get(uId).push(vNew.clone());
			pushSequence.add(uId);
			replaceDef[itr] = vNew.clone();
		}
		a.setAllDef(replaceDef);
	}

	// rename with copy folding
	public static void renameVariables(CFGVertex x) {
		ArrayList<Integer> pushSequence = new ArrayList<>();
		for (int i = 0, size = x.phiBlock.size(); i < size; ++i) {
			renameUse(x.phiBlock, i);
			renameDef(x.phiBlock, i, pushSequence);
		}
		for (int i = 0, size = x.internal.size(); i < size; ++i) {
			renameUse(x.internal, i);
			renameDef(x.internal, i, pushSequence);
		}

		for (CFGVertex y : new CFGVertex[]{x.unconditionalNext, x.branchIfFalse}) {
			if (y == null)
				continue;
			int j = y.predecessor.get(x);
			for (IRInstruction ins : y.phiBlock) {
				if (!(ins instanceof PhiFunction))
					throw new CompilationError("Internal Error.");
				PhiFunction phi = (PhiFunction) ins;
				int a = phi.getAllDefUId()[0];
				if (!stack.containsKey(a))
					throw new CompilationError("Internal Error.");
				phi.setUse(j, getTopStack(a));
			}
		}
		x.children.forEach(PhiPlacer::renameVariables);
		for (int uId : pushSequence)
			stack.get(uId).pop();
	}

	public static void renameVariables() {
		stack = new HashMap<>();
		count = new HashMap<>();
		for (int x : allVariables) {
			stack.put(x, new Stack<IRRegister>() {{
				push(LowerSemiLattice.instance);
			}});
			count.put(x, 0);
		}
		renameVariables(ControlFlowGraph.source);
	}

	public static void insertPhiFunctions() {
		DataFlow.livenessAnalysis();
		HashMap<CFGVertex, Integer> inserted = new HashMap<>();
		HashMap<CFGVertex, Integer> work = new HashMap<>();
		for (CFGVertex b : ControlFlowGraph.vertices) {
			inserted.put(b, -1);
			work.put(b, -1);
		}

		Stack<CFGVertex> worklist = new Stack<>();
		for (int v : allVariables) {
			VirtualRegister rv = new VirtualRegister(v);
			for (CFGVertex x : ControlFlowGraph.vertices) {
				boolean defV = x.varKill.contains(rv);
				if (defV) {
					worklist.push(x);
					work.put(x, v);
				}
			}
			while (!worklist.isEmpty()) {
				CFGVertex x = worklist.pop();
				for (CFGVertex y : x.dominanceFrontier) {
					if (inserted.get(y) == v)
						continue;
					if (y.uEVar.contains(rv) || (!y.varKill.contains(rv) && y.liveOut.contains(rv))) {
						// v is live into y
						y.addPhi(v);
						inserted.put(y, v);
					}
					if (work.get(y) != v) {
						worklist.push(y);
						work.put(y, v);
					}
				}
			}
		}
	}

	public static void process() {
		LengauerTarjan.process(ControlFlowGraph.vertices, ControlFlowGraph.source, ControlFlowGraph.vertices.size());
		collectRegistersUsed();
		insertPhiFunctions();
		renameVariables();
		EliminateNop.process();
	}

	public static void collectRegistersUsed() {
		allVariables = new HashSet<>();
		for (CFGVertex b : ControlFlowGraph.vertices)
			for (IRInstruction ins : b.internal) {
				for (int x : ins.getAllDefUId())
					if (x >= 0)
						allVariables.add(x);
				for (int x : ins.getAllUseUId())
					if (x >= 0)
						allVariables.add(x);
			}
	}
}
