package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd = rs
 */
public class Move extends IRInstruction {
	public IRRegister rs;

	public Move(VirtualRegister rd, IRRegister rs) {
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	@Override
	public int[] getAllDef() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUse() {
		return new int[]{rs.getUId()};
	}

	@Override
	public void setAllDefVersion(int[] version) {
		rd.setVersion(version[0]);
	}

	@Override
	public void setAllUseVersion(int[] version) {
		if (rs instanceof VirtualRegister)
			((VirtualRegister) rs).setVersion(version[0]);
	}

	@Override
	public String toString() {
		return "Move " + rd + " = " + rs;
	}
}
