package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd = func()
 * If return type of func is void, rd is useless.
 */
public class Call extends IRInstruction {
	public IRRegister func;
	public int memoryVersion = 0;

	public Call(IRRegister func) {
		this.rd = null;
		this.func = func.clone();
	}

	@Override
	public String toString() {
		return "Call " + func;
	}

	@Override
	public int[] getAllDef() { // a function call might change memory
		return new int[]{0};
	}

	@Override
	public int[] getAllUse() {
		return new int[]{func.getUId()};
	}

	@Override
	public void setAllDefVersion(int[] version) {
		memoryVersion = version[0];
	}

	@Override
	public void setAllUseVersion(int[] version) {
		if (func instanceof VirtualRegister)
			((VirtualRegister) func).setVersion(version[0]);
	}

	@Override
	public VirtualRegister[] getAllSSADef() {
		return new VirtualRegister[]{new VirtualRegister(0, memoryVersion)};
	}

	@Override
	public VirtualRegister[] getAllSSAUse() {
		return new VirtualRegister[]{detectVirtualRegister(func)};
	}
}
