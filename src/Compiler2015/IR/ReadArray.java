package Compiler2015.IR;

import Compiler2015.IR.Arithmetic.Arithmetic;

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
