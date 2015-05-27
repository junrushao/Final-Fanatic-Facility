package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public abstract class IRInstruction {

	public VirtualRegister rd;

	public abstract int[] getAllDef();

	public abstract int[] getAllUse();

	public abstract void setAllDefVersion(int version[]);

	public abstract void setAllUseVersion(int version[]);

	public abstract VirtualRegister[] getAllSSADef();

	public abstract VirtualRegister[] getAllSSAUse();

	public VirtualRegister detectVirtualRegister(IRRegister r) {
		if (r instanceof VirtualRegister)
			return (VirtualRegister) r.clone();
		return null;
	}

	@Override
	public abstract String toString();
}
