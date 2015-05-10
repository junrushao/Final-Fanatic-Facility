package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.VirtualRegister;

public abstract class IRInstruction {

	public VirtualRegister rd;

	public int getRd() {
		return rd.getValue();
	}

	public void setRdVersion(int x) {
		rd.setVersion(x);
	}

	@Override
	public abstract String toString();
}
