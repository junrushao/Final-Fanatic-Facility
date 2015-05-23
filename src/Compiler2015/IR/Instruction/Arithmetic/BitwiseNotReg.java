package Compiler2015.IR.Instruction.Arithmetic;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.SingleSource;

/**
 * rd = ~rs
 */
public class BitwiseNotReg extends Arithmetic implements SingleSource {
	public IRRegister rs;

	public BitwiseNotReg(VirtualRegister rd, IRRegister rs) {
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	@Override
	public String toString() {
		return String.format("%s = ~%s", rd, rs);
	}

	@Override
	public int getRs() {
		return rs.getUId();
	}

	@Override
	public void setRsVersion(int x) {
		if (rs instanceof VirtualRegister)
			((VirtualRegister) rs).setVersion(x);
	}
}