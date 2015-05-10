package Compiler2015.IR.Instruction.Arithmetic;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.SingleSource;

/**
 * rd = -rs
 */
public class NegateReg extends Arithmetic implements SingleSource {
	public IRRegister rs;

	public NegateReg(VirtualRegister rd, IRRegister rs) {
		this.rd = rd;
		this.rs = rs;
	}

	@Override
	public String toString() {
		return String.format("%s = -%s", rd, rs);
	}

	@Override
	public int getRs() {
		return rs.getValue();
	}

	@Override
	public void setRsVersion(int x) {
		if (rs instanceof VirtualRegister)
			((VirtualRegister) rs).setVersion(x);
	}
}
