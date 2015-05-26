package Compiler2015.IR.Instruction.Arithmetic;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.DoubleSource;

/**
 * rd = rs == rt
 */
public class SetEqualTo extends Arithmetic implements DoubleSource {
	public IRRegister rs, rt;

	public SetEqualTo(VirtualRegister rd, IRRegister rs, IRRegister rt) {
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
		return String.format("%s = %s == %s", rd, rs, rt);
	}

	@Override
	public int getRs() {
		return rs.getUId();
	}

	@Override
	public int getRt() {
		return rt.getUId();
	}

	@Override
	public void setRsVersion(int x) {
		if (rs instanceof VirtualRegister)
			((VirtualRegister) rs).setVersion(x);
	}

	@Override
	public void setRtVersion(int x) {
		if (rt instanceof VirtualRegister)
			((VirtualRegister) rt).setVersion(x);
	}
}
