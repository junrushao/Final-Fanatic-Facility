package Compiler2015.IR.Instruction.Arithmetic;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.DoubleSource;

/**
 * rd = rs - rt
 */
public class SubtractReg extends Arithmetic implements DoubleSource {
	public IRRegister rs, rt;

	public SubtractReg(VirtualRegister rd, IRRegister rs, IRRegister rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}

	@Override
	public String toString() {
		return String.format("%s = %s - %s", rd, rs, rt);
	}

	@Override
	public int getRs() {
		return rs.getValue();
	}

	@Override
	public int getRt() {
		return rt.getValue();
	}

	@Override
	public void setRsVersion(int x) {
		if (rs instanceof VirtualRegister)
			((VirtualRegister) rs).setVersion(x);
	}

	@Override
	public void setRtVersion(int x) {
		if (rt instanceof VirtualRegister)
			((VirtualRegister) rt).setVersion(x);
	}
}
