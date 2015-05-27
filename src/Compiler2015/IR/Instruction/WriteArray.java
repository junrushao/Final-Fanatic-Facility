package Compiler2015.IR.Instruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd <- rs , i.e. a[b] = c
 */
public class WriteArray extends IRInstruction {
	public ArrayRegister rd;
	public IRRegister rs;
	public int memoryVersion = 0;

	public WriteArray(ArrayRegister rd, IRRegister rs) {
		if (rs instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	@Override
	public String toString() {
		return "WriteArray " + rd + " = " + rs + " -> M = " + memoryVersion;
	}

	@Override
	public int[] getAllDef() {
		return new int[]{0};
	}

	@Override
	public int[] getAllUse() {
		return new int[]{rd.a.getUId(), rd.b.getUId(), rs.getUId()};
	}

	@Override
	public void setAllDefVersion(int[] version) {
		memoryVersion = version[0];
	}

	@Override
	public void setAllUseVersion(int[] version) {
		rd.a.setVersion(version[0]);
		if (rs instanceof VirtualRegister)
			((VirtualRegister) rs).setVersion(version[0]);
	}

	@Override
	public VirtualRegister[] getAllSSADef() {
		return new VirtualRegister[]{new VirtualRegister(0, memoryVersion)};
	}

	@Override
	public VirtualRegister[] getAllSSAUse() {
		return new VirtualRegister[]{detectVirtualRegister(rd.a), detectVirtualRegister(rd.b), detectVirtualRegister(rs)};
	}
}
