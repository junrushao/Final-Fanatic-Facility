package Compiler2015.IR.Instruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public class NopForBranch extends IRInstruction {

	public IRRegister rs;

	public NopForBranch(IRRegister rs) {
		if (rs instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		this.rd = null;
		this.rs = rs.clone();
	}

	@Override
	public IRInstruction getExpression() {
		return this;
	}

	@Override
	public int[] getAllDefUId() {
		return new int[0];
	}

	@Override
	public int[] getAllUseUId() {
		return new int[]{rs instanceof VirtualRegister ? rs.getUId() : -1};
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[0];
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[]{detectVirtualRegister(rs)};
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[0];
	}

	@Override
	public void setAllDef(IRRegister[] version) {
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
		return "NopForBranch " + rs;
	}

	@Override
	public int hashCode() {
		return rs.hashCode();
	}

	@Override
	public NopForBranch clone() {
		NopForBranch nop = (NopForBranch) super.clone();
		nop.rs = nop.rs.clone();
		return nop;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NopForBranch that = (NopForBranch) o;
		return rs.equals(that.rs);
	}
}
