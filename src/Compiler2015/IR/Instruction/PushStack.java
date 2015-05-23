package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.Type;

public class PushStack extends IRInstruction implements SingleSource {
	public IRRegister push;
	public boolean isExtra;
	public Type pushType;

	public PushStack(IRRegister push, boolean isExtra, Type pushType) {
		this.rd = null;
		this.push = push.clone();
		this.isExtra = isExtra;
		this.pushType = pushType;
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
		return push.getUId();
	}

	@Override
	public void setRsVersion(int x) {
		if (push instanceof VirtualRegister)
			((VirtualRegister) push).setVersion(x);
	}
}
