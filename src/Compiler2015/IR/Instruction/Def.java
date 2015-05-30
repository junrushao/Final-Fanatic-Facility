package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public class Def extends IRInstruction {

	public Def(VirtualRegister rd) {
		this.rd = rd.clone();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Def def = (Def) o;
		return rd.equals(def.rd);
	}

	@Override
	public int hashCode() {
		return rd.hashCode();
	}

	@Override
	public Def clone() {
		Def ret = (Def) super.clone();
		ret.rd = ret.rd.clone();
		return ret;
	}

	@Override
	public IRInstruction getExpression() {
		return this;
	}

	@Override
	public int[] getAllDefUId() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUseUId() {
		return new int[0];
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[]{detectVirtualRegister(rd)};
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[0];
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[]{rd.clone()};
	}

	@Override
	public void setAllDef(IRRegister[] version) {
		if (version[0] != null)
			rd = (VirtualRegister) version[0];
	}

	@Override
	public IRRegister[] getAllUse() {
		return new IRRegister[0];
	}

	@Override
	public void setAllUse(IRRegister[] version) {
	}

	@Override
	public String toString() {
		return "Def " + rd;
	}
}
