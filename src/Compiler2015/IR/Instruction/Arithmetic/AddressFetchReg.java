package Compiler2015.IR.Instruction.Arithmetic;

/**
 * rd = &rs
 */
public class AddressFetchReg extends Arithmetic {
	public int rd, rs;

	public AddressFetchReg(int rd, int rs) {
		this.rd = rd;
		this.rs = rs;
	}
}
