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
	public int[] getAllDefUId() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUseUId() {
		return new int[]{rs.getUId()};
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[]{detectVirtualRegister(rd)};
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[]{detectVirtualRegister(rs)};
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[]{rd.clone()};
	}

	@Override
	public void setAllDef(IRRegister[] version) {
		if (version[0] != null)
			rd = (VirtualRegister) version[0];
	}

	@Override
	public IRRegister[] getAllUse() {
		return new IRRegister[]{rs.clone()};
	}

	@Override
	public void setAllUse(IRRegister[] version) {
		if (version[0] != null)
			rs = version[0];
	}

	@Override
	public String toString() {
		return "Move " + rd + " = " + rs;
	}
}
