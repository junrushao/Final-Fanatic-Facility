package Compiler2015.IR.Instruction.ThreeAddressInstruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.Instruction.IRInstruction;

public abstract class ThreeAddressInstruction extends IRInstruction {
	public IRRegister rs, rt;

	public abstract String toMIPSName();
}
