package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd <- rs, i.e. c = a[b]
 */
public class ReadArray extends IRInstruction {
	public ArrayRegister rs;
	public int memoryVersion = -1;

	public ReadArray(VirtualRegister rd, ArrayRegister rs) {
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	@Override
	public int[] getAllDefUId() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUseUId() {
		return new int[]{rs.a.getUId(), rs.b.getUId(), 0};
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[]{detectVirtualRegister(rd)};
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[]{detectVirtualRegister(rs.a), detectVirtualRegister(rs.b), new VirtualRegister(0, memoryVersion)};
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
		return new IRRegister[]{rs.a.clone(), rs.b.clone(), new VirtualRegister(0, memoryVersion)};
	}

	@Override
	public void setAllUse(IRRegister[] version) {
		if (version[0] != null)
			rs.a = (VirtualRegister) version[0];
		memoryVersion = ((VirtualRegister) version[2]).version;
	}

	@Override
	public String toString() {
		return "ReadArray " + rd + " = " + rs + " -> M = " + memoryVersion;
	}
}
