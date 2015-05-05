package Compiler2015.IR;

/**
 * rd = rs
 */
public class Move extends IRInstruction {
	public int rd, rs;

	public Move(int rd, int rs) {
		this.rd = rd;
		this.rs = rs;
	}
}
