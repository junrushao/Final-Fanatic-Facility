package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;

/**
 * rd <- rs , i.e. a[b] = c
 */
public class WriteArray extends IRInstruction {
	public ArrayRegister rd;
	public IRRegister rs;

	public WriteArray(ArrayRegister rd, IRRegister rs) {
		this.rd = rd;
		this.rs = rs;
	}

	@Override
	public String toString() {
		return "WriteArray " + rd +  " = " + rs;
	}
}
