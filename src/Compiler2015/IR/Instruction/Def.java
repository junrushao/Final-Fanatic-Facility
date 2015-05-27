package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.VirtualRegister;

public class Def extends IRInstruction {

	public Def(VirtualRegister rd) {
		this.rd = rd;
	}

	@Override
	public int[] getAllDef() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUse() {
		return new int[0];
	}

	@Override
	public void setAllDefVersion(int[] version) {
		rd.setVersion(version[0]);
	}

	@Override
	public void setAllUseVersion(int[] version) {
	}

	@Override
	public VirtualRegister[] getAllSSADef() {
		return new VirtualRegister[]{detectVirtualRegister(rd)};
	}

	@Override
	public VirtualRegister[] getAllSSAUse() {
		return new VirtualRegister[0];
	}

	@Override
	public String toString() {
		return "Def " + rd;
	}
}
