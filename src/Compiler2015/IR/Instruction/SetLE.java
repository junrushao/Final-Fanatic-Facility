package Compiler2015.IR.Instruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd = rs <= rt
 */
public class SetLE extends IRInstruction {
	public IRRegister rs, rt;

	private SetLE(VirtualRegister rd, IRRegister rs, IRRegister rt) {
		this.rd = rd.clone();
		this.rs = rs.clone();
		this.rt = rt.clone();
	}

	public static IRInstruction getExpression(VirtualRegister rd, IRRegister rs, IRRegister rt) {
		if (rs instanceof ArrayRegister || rt instanceof ArrayRegister)
			throw new CompilationError("Internal Error");
		if (rs instanceof ImmediateValue && rt instanceof ImmediateValue)
			return new Move(rd, new ImmediateValue((((ImmediateValue) rs).a <= ((ImmediateValue) rt).a) ? 1 : 0));
		if (rs instanceof ImmediateValue) {
			IRRegister tmp = rs;
			rs = rt;
			rt = tmp;
		}
		return new SetLE(rd, rs, rt);
	}

	public IRInstruction getExpression() {
		return getExpression(rd, rs, rt);
	}

	@Override
	public int[] getAllDefUId() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUseUId() {
		return new int[]{rs.getUId(), rt.getUId()};
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[]{detectVirtualRegister(rd)};
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[]{detectVirtualRegister(rs), detectVirtualRegister(rt)};
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[]{rd.clone()};
	}

	@Override
	public void setAllDef(IRRegister[] version) {
		if (version[0] != null)
			rd = (VirtualRegister) version[0].clone();
	}

	@Override
	public IRRegister[] getAllUse() {
		return new IRRegister[]{rs.clone(), rt.clone()};
	}

	@Override
	public void setAllUse(IRRegister[] version) {
		if (version[0] != null)
			rs = version[0].clone();
		if (version[1] != null)
			rt = version[1].clone();
	}

	@Override
	public String toString() {
		return String.format("%s = %s <= %s", rd, rs, rt);
	}
}
