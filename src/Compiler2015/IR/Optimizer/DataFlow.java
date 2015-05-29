package Compiler2015.IR.Optimizer;

import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.Utility.Utility;

import java.util.HashSet;

public final class DataFlow {

	public static void livenessAnalysis(ControlFlowGraph graph) {
		// memory is taken into consideration
		int n = graph.vertices.size();
		Utility.SetOperation<VirtualRegister> setOperation = new Utility.SetOperation<>();

		CFGVertex vertices[] = new CFGVertex[n + 1];
		for (CFGVertex v : graph.vertices) {
			vertices[v.id] = v;
			v.uEVar = new HashSet<>();
			v.varKill = new HashSet<>();
			v.liveOut = new HashSet<>();

			for (IRInstruction ins : v.phiBlock) {
				for (VirtualRegister x : ins.getAllUseVR())
					if (x != null && !v.varKill.contains(x))
						v.uEVar.add(x);
				for (VirtualRegister x : ins.getAllDefVR())
					if (x != null)
						v.varKill.add(x);
			}

			for (IRInstruction ins : v.internal) {
				for (VirtualRegister x : ins.getAllUseVR())
					if (x != null && !v.varKill.contains(x))
						v.uEVar.add(x);
				for (VirtualRegister x : ins.getAllDefVR())
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
						setOperation.unionInto(v.liveOut, setOperation.subtract(m.liveOut, m.varKill));
					}
				}
				if (!setOperation.equal(originalLiveOut, v.liveOut))
					changed = true;
			}
		}
	}
}
