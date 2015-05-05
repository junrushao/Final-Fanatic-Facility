package Compiler2015.IR.Arithmetic;

/**
 * rd = rs & rt
 */
public class BitwiseAndReg extends Arithmetic {
	public int rd, rs, rt;

	public BitwiseAndReg(int rd, int rs, int rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
