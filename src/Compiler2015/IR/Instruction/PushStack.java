package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;

public class PushStack extends IRInstruction {
	public IRRegister rd;

	public PushStack(IRRegister rd) {
		this.rd = rd;
	}

	@Override
	public String toString() {
		return "Push " + rd;
	}
}
