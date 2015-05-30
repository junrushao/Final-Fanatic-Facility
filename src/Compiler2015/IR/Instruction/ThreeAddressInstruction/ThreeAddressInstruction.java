package Compiler2015.IR.Instruction.ThreeAddressInstruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.Instruction.IRInstruction;

public abstract class ThreeAddressInstruction extends IRInstruction {
	public IRRegister rs, rt;

	public abstract String toMIPSName();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ThreeAddressInstruction that = (ThreeAddressInstruction) o;
		return rd.equals(that.rd) && rs.equals(that.rs) && rt.equals(that.rt);
	}

	@Override
	public int hashCode() {
		int result = getClass().hashCode();
		result = result * 31 + rd.hashCode();
		result = result * 31 + rs.hashCode();
		result = result * 31 + rt.hashCode();
		return result;
	}

	@Override
	public IRInstruction clone() {
		ThreeAddressInstruction ret = (ThreeAddressInstruction) super.clone();
		ret.rd = ret.rd.clone();
		ret.rs = ret.rs.clone();
		ret.rt = ret.rt.clone();
		return ret;
	}
}
