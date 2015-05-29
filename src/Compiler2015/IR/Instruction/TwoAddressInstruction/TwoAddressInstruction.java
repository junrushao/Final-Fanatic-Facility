package Compiler2015.IR.Instruction.TwoAddressInstruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.Instruction.IRInstruction;

public abstract class TwoAddressInstruction extends IRInstruction {
	public IRRegister rs;

	public abstract String toMIPSName();
}
