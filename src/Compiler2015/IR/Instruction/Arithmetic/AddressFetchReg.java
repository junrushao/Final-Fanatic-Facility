package Compiler2015.IR.Instruction.Arithmetic;

import Compiler2015.IR.IRRegister.IRRegister;

/**
 * rd = &rs
 */
public class AddressFetchReg extends Arithmetic {
	public IRRegister rd, rs;

	public AddressFetchReg(IRRegister rd, IRRegister rs) {
		this.rd = rd;
		this.rs = rs;
	}

	@Override
	public String toString() {
		return String.format("%s = &%s", rd, rs);
	}

}
