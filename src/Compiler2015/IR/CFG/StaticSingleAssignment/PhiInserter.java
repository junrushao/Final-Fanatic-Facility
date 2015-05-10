package Compiler2015.IR.CFG.StaticSingleAssignment;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.Instruction.*;

import java.util.HashMap;
import java.util.HashSet;

public class PhiInserter {
	public HashSet<CFGVertex> vertices;
	public RegisterManager rm;

	public PhiInserter(HashSet<CFGVertex> vertices, RegisterManager rm) {
		this.vertices = vertices;
		this.rm = rm;
	}

	public void process() {
		for (CFGVertex n : vertices)
			for (IRInstruction ins : n.internal) {
				rm.addVariable(ins.getRd(), n);
				if (ins instanceof NonSource) {
					continue;
				}
				if (ins instanceof SingleSource) {
					rm.addVariable(((SingleSource) ins).getRs(), null);
				} else if (ins instanceof DoubleSource) {
					rm.addVariable(((DoubleSource) ins).getRs(), null);
					rm.addVariable(((DoubleSource) ins).getRt(), null);
				} else if (ins instanceof TripleSource) {
					rm.addVariable(((TripleSource) ins).getA(), null);
					rm.addVariable(((TripleSource) ins).getB(), null);
					rm.addVariable(((TripleSource) ins).getC(), null);
				} else
					throw new CompilationError("Internal Error.");
			}
		rm.checkDefSite();
		rm.insertPhi();
		renaming(rm.root);
	}

	public void renaming(CFGVertex n) {
		// phi functions' definition
		for (HashMap.Entry<Integer, PhiFunction> s : n.phis.entrySet()) {
			int a = s.getKey();
			RegisterManager.RegisterManagerEntry re = rm.manager.get(a);
			int i = ++re.count;
			re.stack.push(i);
			s.getValue().vid[0] = i;
		}
		// normal statements' definition
		for (IRInstruction ins : n.internal) {
			// rename use
			int x;
			if (ins instanceof SingleSource) {
				x = ((SingleSource) ins).getRs();
				if (x != -1)
					((SingleSource) ins).setRsVersion(rm.getPeek(x));
			} else if (ins instanceof DoubleSource) {
				x = ((DoubleSource) ins).getRs();
				if (x != -1)
					((DoubleSource) ins).setRsVersion(rm.getPeek(x));

				x = ((DoubleSource) ins).getRt();
				if (x != -1)
					((DoubleSource) ins).setRtVersion(rm.getPeek(x));
			} else if (ins instanceof TripleSource) {
				x = ((TripleSource) ins).getA();
				if (x != -1)
					((TripleSource) ins).setAVersion(rm.getPeek(x));

				x = ((TripleSource) ins).getB();
				if (x != -1)
					((TripleSource) ins).setBVersion(rm.getPeek(x));

				x = ((TripleSource) ins).getC();
				if (x != -1)
					((TripleSource) ins).setCVersion(rm.getPeek(x));
			} else if (!(ins instanceof NonSource))
				throw new CompilationError("Internal Error.");

			// rename def
			int a = ins.getRd();
			if (a != -1) {
				RegisterManager.RegisterManagerEntry re = rm.manager.get(a);
				int i = ++re.count;
				re.stack.push(i);
				ins.setRdVersion(rm.getPeek(a));
			}
		}
		// define operands of succeeding phi-functions
		for (CFGVertex y : new CFGVertex[]{n.unconditionalNext, n.branchIfFalse}) {
			if (y == null)
				continue;
			int j = y.predecessor.get(n);
			for (HashMap.Entry<Integer, PhiFunction> phi : y.phis.entrySet()) {
				int a = phi.getKey();
				phi.getValue().vid[j] = rm.getPeek(a);
			}
		}
		// rename recursively
		n.children.forEach(this::renaming);
		// eliminate side-effect in stack: phi-functions
		for (HashMap.Entry<Integer, PhiFunction> s : n.phis.entrySet()) {
			int a = s.getKey();
			rm.manager.get(a).stack.pop();
		}
		// eliminate side-effect in stack: normal statements
		for (IRInstruction ins : n.internal) {
			int a = ins.getRd();
			if (a != -1)
				rm.manager.get(a).stack.pop();
		}
	}
}
