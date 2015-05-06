package Compiler2015.IR.Instruction.Arithmetic;

/**
 * rd = rs - rt
 */
public class SubtractReg extends Arithmetic {
	public int rd, rs, rt;

	public SubtractReg(int rd, int rs, int rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
