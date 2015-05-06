package Compiler2015.IR.Instruction.Arithmetic;

/**
 * rd = ~rs
 */
public class BitwiseNotReg extends Arithmetic {
	public int rd, rs;

	public BitwiseNotReg(int rd, int rs) {
		this.rd = rd;
		this.rs = rs;
	}
}
