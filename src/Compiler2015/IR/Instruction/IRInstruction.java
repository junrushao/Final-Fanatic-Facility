package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.VirtualRegister;

public abstract class IRInstruction {

	public VirtualRegister rd;

	public abstract int[] getAllDef();

	public abstract int[] getAllUse();

	public abstract void setAllDefVersion(int version[]);

	public abstract void setAllUseVersion(int version[]);

	@Override
	public abstract String toString();
}
