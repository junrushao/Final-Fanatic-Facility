package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public class SetReturn extends IRInstruction implements SingleSource {
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
	public int getRd() {
		return -1;
	}

	@Override
	public int getRs() {
		return v0.getValue();
	}

	@Override
	public void setRsVersion(int x) {
		if (v0 instanceof VirtualRegister)
			((VirtualRegister) v0).setVersion(x);
	}
}
