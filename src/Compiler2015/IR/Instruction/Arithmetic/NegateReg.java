package Compiler2015.IR.Instruction.Arithmetic;

/**
 * rd = -rs
 */
public class NegateReg extends Arithmetic {
	public int rd, rs;

	public NegateReg(int rd, int rs) {
		this.rd = rd;
		this.rs = rs;
	}
}
