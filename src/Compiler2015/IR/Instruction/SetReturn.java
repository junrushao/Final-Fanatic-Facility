package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public class SetReturn extends IRInstruction {
	public IRRegister v0;

	public SetReturn(IRRegister v0) {
		this.rd = null;
		this.v0 = v0.clone();
	}

	@Override
	public String toString() {
		return "SetReturn " + v0;
	}

	@Override
	public int[] getAllDef() {
		return new int[0];
	}

	@Override
	public int[] getAllUse() {
		return new int[]{v0.getUId()};
	}

	@Override
	public void setAllDefVersion(int[] version) {
	}

	@Override
	public void setAllUseVersion(int[] version) {
		if (v0 instanceof VirtualRegister)
			((VirtualRegister) v0).setVersion(version[0]);
	}
}
