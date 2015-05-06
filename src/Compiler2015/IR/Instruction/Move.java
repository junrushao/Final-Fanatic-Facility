package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;

/**
 * rd = rs
 */
public class Move extends IRInstruction {
	public IRRegister rd, rs;

	public Move(IRRegister rd, IRRegister rs) {
		this.rd = rd;
		this.rs = rs;
	}

	@Override
	public String toString() {
		return "Move " + rd + " = " + rs;
	}
}
