package Compiler2015.IR.Analyser.StaticSingleAssignment;

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
				RegisterManager.addVariable(ins.getRd(), n);
				if (ins instanceof NonSource) {
					continue;
				}
				if (ins instanceof SingleSource) {
					RegisterManager.addVariable(((SingleSource) ins).getRs(), null);
				} else if (ins instanceof DoubleSource) {
					RegisterManager.addVariable(((DoubleSource) ins).getRs(), null);
					RegisterManager.addVariable(((DoubleSource) ins).getRt(), null);
				} else if (ins instanceof TripleSource) {
					RegisterManager.addVariable(((TripleSource) ins).getA(), null);
					RegisterManager.addVariable(((TripleSource) ins).getB(), null);
					RegisterManager.addVariable(((TripleSource) ins).getC(), null);
				} else
					throw new CompilationError("Internal Error.");
			}
		RegisterManager.checkDefSite();
		RegisterManager.insertPhi();
		renaming(RegisterManager.root);
	}

	public void renaming(CFGVertex n) {
		// phi functions' definition
		for (HashMap.Entry<Integer, PhiFunction> s : n.phis.entrySet()) {
			int a = s.getKey();
			RegisterManager.RegisterManagerEntry re = RegisterManager.manager.get(a);
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
					((SingleSource) ins).setRsVersion(RegisterManager.getPeek(x));
			} else if (ins instanceof DoubleSource) {
				x = ((DoubleSource) ins).getRs();
				if (x != -1)
					((DoubleSource) ins).setRsVersion(RegisterManager.getPeek(x));

				x = ((DoubleSource) ins).getRt();
				if (x != -1)
					((DoubleSource) ins).setRtVersion(RegisterManager.getPeek(x));
			} else if (ins instanceof TripleSource) {
				x = ((TripleSource) ins).getA();
				if (x != -1)
					((TripleSource) ins).setAVersion(RegisterManager.getPeek(x));

				x = ((TripleSource) ins).getB();
				if (x != -1)
					((TripleSource) ins).setBVersion(RegisterManager.getPeek(x));

				x = ((TripleSource) ins).getC();
				if (x != -1)
					((TripleSource) ins).setCVersion(RegisterManager.getPeek(x));
			} else if (!(ins instanceof NonSource))
				throw new CompilationError("Internal Error.");

			// rename def
			int a = ins.getRd();
			if (a != -1) {
				RegisterManager.RegisterManagerEntry re = RegisterManager.manager.get(a);
				int i = ++re.count;
				re.stack.push(i);
				ins.setRdVersion(RegisterManager.getPeek(a));
			}
		}
		// define operands of succeeding phi-functions
		for (CFGVertex y : new CFGVertex[]{n.unconditionalNext, n.branchIfFalse}) {
			if (y == null)
				continue;
			int j = y.predecessor.get(n);
			for (HashMap.Entry<Integer, PhiFunction> phi : y.phis.entrySet()) {
				int a = phi.getKey();
				phi.getValue().vid[j] = RegisterManager.getPeek(a);
			}
		}
		// rename recursively
		n.children.forEach(this::renaming);
		// eliminate side-effect in stack: phi-functions
		for (HashMap.Entry<Integer, PhiFunction> s : n.phis.entrySet()) {
			int a = s.getKey();
			RegisterManager.manager.get(a).stack.pop();
		}
		// eliminate side-effect in stack: normal statements
		for (IRInstruction ins : n.internal) {
			int a = ins.getRd();
			if (a != -1)
				RegisterManager.manager.get(a).stack.pop();
		}
	}
}
