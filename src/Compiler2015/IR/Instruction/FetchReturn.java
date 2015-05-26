package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.VirtualRegister;

public class FetchReturn extends IRInstruction {

	public FetchReturn(VirtualRegister rd) {
		this.rd = rd.clone();
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
	public String toString() {
		return "Fetch Return: " + rd;
	}
}
