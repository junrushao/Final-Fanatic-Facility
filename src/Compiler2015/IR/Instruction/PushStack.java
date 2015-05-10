package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public class PushStack extends IRInstruction implements SingleSource {
	public IRRegister push;

	public PushStack(IRRegister push) {
		this.rd = null;
		this.push = push.clone();
	}

	@Override
	public String toString() {
		return "Push " + push;
	}

	@Override
	public int getRd() {
		return -1;
	}

	@Override
	public int getRs() {
		return push.getValue();
	}

	@Override
	public void setRsVersion(int x) {
		if (push instanceof VirtualRegister)
			((VirtualRegister) push).setVersion(x);
	}
}
