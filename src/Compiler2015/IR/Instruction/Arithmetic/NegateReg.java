package Compiler2015.IR.Instruction.Arithmetic;

import Compiler2015.IR.IRRegister.IRRegister;

/**
 * rd = -rs
 */
public class NegateReg extends Arithmetic {
	public IRRegister rd, rs;

	public NegateReg(IRRegister rd, IRRegister rs) {
		this.rd = rd;
		this.rs = rs;
	}

	@Override
	public String toString() {
		return String.format("%s = -%s", rd, rs);
	}

}
