package Compiler2015.IR.Instruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public class SetReturn extends IRInstruction {
	public IRRegister v0;

	public SetReturn(IRRegister v0) {
		if (v0 instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		this.rd = null;
		this.v0 = v0.clone();
	}

	@Override
	public SetReturn clone() {
		SetReturn ret = (SetReturn) super.clone();
		ret.v0 = ret.v0.clone();
		return ret;
	}

	@Override
	public String toString() {
		return "SetReturn " + v0;
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
		return new int[]{v0.getUId()};
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[0];
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[]{detectVirtualRegister(v0)};
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
		return new IRRegister[]{v0.clone()};
	}

	@Override
	public void setAllUse(IRRegister[] version) {
		if (version[0] != null)
			v0 = version[0];
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SetReturn setReturn = (SetReturn) o;
		return v0.equals(setReturn.v0);
	}

	@Override
	public int hashCode() {
		return v0.hashCode();
	}
}
