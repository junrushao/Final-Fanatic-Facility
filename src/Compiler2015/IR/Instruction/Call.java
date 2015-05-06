package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;

/**
 * rd = func()
 * If return type of func is void, rd is useless.
 */
public class Call extends IRInstruction {
	public IRRegister rd, func;

	public Call(IRRegister rd, IRRegister func) {
		this.rd = rd;
		this.func = func;
	}

	@Override
	public String toString() {
		return "Call " + rd +  " = " + func;
	}
}
