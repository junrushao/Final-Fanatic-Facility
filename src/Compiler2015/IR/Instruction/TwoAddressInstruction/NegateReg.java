package Compiler2015.IR.Instruction.TwoAddressInstruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;

/**
 * rd = -rs
 */
public class NegateReg extends TwoAddressInstruction {

	private NegateReg(VirtualRegister rd, IRRegister rs) {
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	public static IRInstruction getExpression(VirtualRegister rd, IRRegister rs) {
		if (rs instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		if (rs instanceof ImmediateValue)
			return new Move(rd, new ImmediateValue(-((ImmediateValue) rs).a));
		return new NegateReg(rd, rs);
	}

	@Override
	public IRInstruction getExpression() {
		return getExpression(rd, rs);
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
		return new VirtualRegister[0];
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[]{rd.clone()};
	}

	@Override
	public void setAllDef(IRRegister[] version) {
		rd = (VirtualRegister) version[0];
	}

	@Override
	public IRRegister[] getAllUse() {
		return new IRRegister[0];
	}

	@Override
	public void setAllUse(IRRegister[] version) {
	}

	@Override
	public String toString() {
		return String.format("%s = -%s", rd, rs);
	}

	@Override
	public String toMIPSName() {
		return "neg";
	}
}
