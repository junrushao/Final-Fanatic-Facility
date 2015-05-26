package Compiler2015.IR.Instruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd = rs <= rt
 */
public class SetLE extends IRInstruction {
	public IRRegister rs, rt;

	public SetLE(VirtualRegister rd, IRRegister rs, IRRegister rt) {
		if (rs instanceof ArrayRegister || rt instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		this.rd = rd.clone();
		this.rs = rs.clone();
		this.rt = rt.clone();
	}

	@Override
	public int[] getAllDef() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUse() {
		return new int[]{rs.getUId(), rt.getUId()};
	}

	@Override
	public void setAllDefVersion(int[] version) {
		rd.setVersion(version[0]);
	}

	@Override
	public void setAllUseVersion(int[] version) {
		if (rs instanceof VirtualRegister)
			((VirtualRegister) rs).setVersion(version[0]);
		if (rt instanceof VirtualRegister)
			((VirtualRegister) rt).setVersion(version[1]);
	}

	@Override
	public String toString() {
		return String.format("%s = %s <= %s", rd, rs, rt);
	}
}
