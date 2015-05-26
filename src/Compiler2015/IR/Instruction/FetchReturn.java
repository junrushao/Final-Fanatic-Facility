package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.VirtualRegister;

public class FetchReturn extends IRInstruction implements NonSource {

	public FetchReturn(VirtualRegister rd) {
		this.rd = rd.clone();
	}

	@Override
	public String toString() {
		return "Fetch Return: " + rd;
	}
}
