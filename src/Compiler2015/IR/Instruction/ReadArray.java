package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd <- rs, i.e. c = a[b]
 */
public class ReadArray extends IRInstruction {
	public ArrayRegister rs;
	public int memoryVersion = 0;

	public ReadArray(VirtualRegister rd, ArrayRegister rs) {
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	@Override
	public int[] getAllDef() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUse() {
		return new int[]{rs.a.getUId(), rs.b.getUId(), 0};
	}

	@Override
	public void setAllDefVersion(int[] version) {
		rd.setVersion(version[0]);
	}

	@Override
	public void setAllUseVersion(int[] version) {
		rs.a.setVersion(version[0]);
		memoryVersion = version[2];
	}

	@Override
	public VirtualRegister[] getAllSSADef() {
		return new VirtualRegister[]{detectVirtualRegister(rd)};
	}

	@Override
	public VirtualRegister[] getAllSSAUse() {
		return new VirtualRegister[]{detectVirtualRegister(rs.a), detectVirtualRegister(rs.b), new VirtualRegister(0, memoryVersion)};
	}

	@Override
	public String toString() {
		return "ReadArray " + rd + " = " + rs + " -> M = " + memoryVersion;
	}
}
