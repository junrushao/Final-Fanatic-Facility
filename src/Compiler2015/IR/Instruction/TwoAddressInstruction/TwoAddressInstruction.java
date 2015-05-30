package Compiler2015.IR.Instruction.TwoAddressInstruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.Instruction.IRInstruction;

public abstract class TwoAddressInstruction extends IRInstruction {
	public IRRegister rs;

	public abstract String toMIPSName();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TwoAddressInstruction that = (TwoAddressInstruction) o;
		return rd.equals(that.rd) && rs.equals(that.rs);
	}

	@Override
	public int hashCode() {
		int result = getClass().hashCode();
		result = result * 31 + rd.hashCode();
		result = result * 31 + rs.hashCode();
		return result;
	}

	@Override
	public IRInstruction clone() {
		TwoAddressInstruction ret = (TwoAddressInstruction) super.clone();
		ret.rd = ret.rd.clone();
		ret.rs = ret.rs.clone();
		return ret;
	}
}
