package Compiler2015.IR.Instruction.Arithmetic;

import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.SingleSource;

/**
 * The address of $uId should be $SP + Environment.variableDelta.get(uId)
 */
public class LocalAddressFetch extends Arithmetic implements SingleSource {
	public int uId;

	public LocalAddressFetch(VirtualRegister rd, int uId) {
		this.rd = rd.clone();
		this.uId = uId;
	}

	@Override
	public String toString() {
		return String.format("%s = & #%d", rd, uId);
	}

	@Override
	public int getRs() {
		return uId;
	}

	@Override
	public void setRsVersion(int x) {
	}
}
