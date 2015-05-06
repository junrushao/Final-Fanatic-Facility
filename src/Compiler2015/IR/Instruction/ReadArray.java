package Compiler2015.IR.Instruction;

import Compiler2015.IR.Instruction.Arithmetic.Arithmetic;

/**
 * rd = a[b]
 */
public class ReadArray extends Arithmetic {
	public int rd, a, b;

	public ReadArray(int rd, int a, int b) {
		this.rd = rd;
		this.a = a;
		this.b = b;
	}
}
