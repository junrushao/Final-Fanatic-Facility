package Compiler2015.IR.Analyser;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.Utility.Utility;

import java.util.HashSet;

public final class DataFlow {

	public static void analyse() {
		// memory is taken into consideration
		int n = ControlFlowGraph.vertices.size();
		Utility.SetOperation<VirtualRegister> setOperation = new Utility.SetOperation<>();

		CFGVertex vertices[] = new CFGVertex[n + 1];
		for (CFGVertex v : ControlFlowGraph.vertices) {
			vertices[v.id] = v;
			v.uEVar = new HashSet<>();
			v.varKill = new HashSet<>();
			v.liveOut = new HashSet<>();

			for (IRInstruction ins : v.phiBlock) {
				for (VirtualRegister x : ins.getAllSSAUse())
					if (x != null && !v.varKill.contains(x))
						v.uEVar.add(x);
				for (VirtualRegister x : ins.getAllSSADef())
					if (x != null)
						v.varKill.add(x);
			}

			for (IRInstruction ins : v.internal) {
				for (VirtualRegister x : ins.getAllSSAUse())
					if (x != null && !v.varKill.contains(x))
						v.uEVar.add(x);
				for (VirtualRegister x : ins.getAllSSADef())
					if (x != null)
						v.varKill.add(x);
			}
		}
		boolean changed = true;
		while (changed) {
			changed = false;
			for (int i = n; i >= 1; --i) {
				CFGVertex v = vertices[i];
				HashSet<VirtualRegister> originalLiveOut = v.liveOut;
				v.liveOut = new HashSet<>();
				for (CFGVertex m : new CFGVertex[]{v.unconditionalNext, v.branchIfFalse}) {
					if (m != null) {
						setOperation.unionInto(v.liveOut, m.uEVar);
						setOperation.unionInto(v.liveOut, setOperation.kickOut(m.liveOut, m.varKill));
					}
				}
				if (!setOperation.equal(originalLiveOut, v.liveOut))
					changed = true;
			}
		}
/*
		for (int i = 1; i <= n; ++i) {
			System.err.println("Vertex~" + i);
			System.err.println("\tLiveOut = " + vertices[i].liveOut);
		}
*/
//		checkUndefined();
	}

/*
	public static void checkUndefined() {
		HashSet<Integer> allVariables = new HashSet<>();
		for (CFGVertex v : ControlFlowGraph.vertices)
			for (IRInstruction ins : v.internal) {
				for (int x : ins.getAllDef())
					if (x != -1)
						allVariables.add(x);
				for (int x : ins.getAllUse())
					if (x != -1)
						allVariables.add(x);
			}
		HashSet<Integer> definedInRoot = new HashSet<>();
		for (IRInstruction ins : ControlFlowGraph.root.internal)
			for (int x : ins.getAllDef())
				if (x != -1)
					definedInRoot.add(x);
		for (int x : allVariables) {
			if (definedInRoot.contains(x))
				continue;
			if (ControlFlowGraph.root.liveOut.contains(new VirtualRegister(x)))
				throw new CompilationError("Internal Error.");
		}
	}
*/
}
