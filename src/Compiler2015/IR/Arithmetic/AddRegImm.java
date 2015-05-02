package Compiler2015.IR.Arithmetic;

public class AddRegImm extends Arithmetic {
	public int a1, imm, to;

	public AddRegImm(int a1, int imm, int to) {
		this.a1 = a1;
		this.imm = imm;
		this.to = to;
	}
}
