package Compiler2015.IR.Instruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.Type;

public class PushStack extends IRInstruction {
	public IRRegister push;
	public boolean isExtra;
	public Type pushType;

	public PushStack(IRRegister push, boolean isExtra, Type pushType) {
		if (push instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		this.rd = null;
		this.push = push.clone();
		this.isExtra = isExtra;
		this.pushType = pushType.clone();
	}

	@Override
	public String toString() {
		return "Push " + push;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PushStack pushStack = (PushStack) o;
		return isExtra == pushStack.isExtra && !(push != null ? !push.equals(pushStack.push) : pushStack.push != null) && !(pushType != null ? !pushType.equals(pushStack.pushType) : pushStack.pushType != null);
	}

	@Override
	public int hashCode() {
		int result = push != null ? push.hashCode() : 0;
		result = 31 * result + (isExtra ? 1 : 0);
		result = 31 * result + (pushType != null ? pushType.hashCode() : 0);
		return result;
	}

	@Override
	public PushStack clone() {
		PushStack push = (PushStack) super.clone();
		push.push = push.push.clone();
		return push;
	}

	@Override
	public IRInstruction getExpression() {
		return this;
	}

	@Override
	public int[] getAllDefUId() {
		return new int[0];
	}

	@Override
	public int[] getAllUseUId() {
		return new int[]{push.getUId()};
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[0];
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[]{detectVirtualRegister(push)};
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[0];
	}

	@Override
	public void setAllDef(IRRegister[] version) {
	}

	@Override
	public IRRegister[] getAllUse() {
		return new IRRegister[]{push.clone()};
	}

	@Override
	public void setAllUse(IRRegister[] version) {
		if (version[0] != null)
			push = version[0];
	}
}
