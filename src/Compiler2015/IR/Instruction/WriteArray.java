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
	public int memoryVersion = -1;

	public WriteArray(ArrayRegister rd, IRRegister rs) {
		if (rs instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	@Override
	public WriteArray clone() {
		WriteArray ret = (WriteArray) super.clone();
		ret.rd = ret.rd.clone();
		ret.rs = ret.rs.clone();
		return ret;
	}

	@Override
	public String toString() {
		return "WriteArray " + rd + " = " + rs + " -> M = " + memoryVersion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WriteArray that = (WriteArray) o;
		return memoryVersion == that.memoryVersion && rd.equals(that.rd) && rs.equals(that.rs);
	}

	@Override
	public int hashCode() {
		int result = rd.hashCode();
		result = 31 * result + rs.hashCode();
		result = 31 * result + memoryVersion;
		return result;
	}

	@Override
	public IRInstruction getExpression() {
		return this;
	}

	@Override
	public int[] getAllDefUId() {
		return new int[]{0};
	}

	@Override
	public int[] getAllUseUId() {
		return new int[]{rd.a.getUId(), rd.b.getUId(), rs.getUId()};
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[]{new VirtualRegister(0, memoryVersion)};
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[]{detectVirtualRegister(rd.a), detectVirtualRegister(rd.b), detectVirtualRegister(rs)};
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[]{new VirtualRegister(0, memoryVersion)};
	}

	@Override
	public void setAllDef(IRRegister[] version) {
		if (version[0] != null)
			memoryVersion = ((VirtualRegister) version[0]).version;
	}

	@Override
	public IRRegister[] getAllUse() {
		return new IRRegister[]{rd.a, rd.b, rs};
	}

	@Override
	public void setAllUse(IRRegister[] version) {
		if (version[0] != null)
			rd.a = (VirtualRegister) version[0].clone();
		if (version[2] != null)
			rs = version[2].clone();
	}
}
