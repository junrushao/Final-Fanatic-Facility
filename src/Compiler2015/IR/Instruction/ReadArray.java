package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.Instruction.Arithmetic.Arithmetic;

/**
 * rd <- rs, i.e. c = a[b]
 */
public class ReadArray extends Arithmetic {
	public IRRegister rd;
	public ArrayRegister rs;

	public ReadArray(IRRegister rd, ArrayRegister rs) {
		this.rd = rd;
		this.rs = rs;
	}

	@Override
	public String toString() {
		return "ReadArray " + rd +  " = " + rs;
	}
}
