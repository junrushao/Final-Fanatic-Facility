package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.Type;

public class PushStack extends IRInstruction {
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
	public int[] getAllDef() {
		return new int[0];
	}

	@Override
	public int[] getAllUse() {
		return new int[]{push.getUId()};
	}

	@Override
	public void setAllDefVersion(int[] version) {
	}

	@Override
	public void setAllUseVersion(int[] version) {
		if (push instanceof VirtualRegister)
			((VirtualRegister) push).setVersion(version[0]);
	}
}
