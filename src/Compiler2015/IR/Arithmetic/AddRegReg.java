package Compiler2015.IR.Arithmetic;

public class AddRegReg extends Arithmetic {
	public int a1, a2, to;

	public AddRegReg(int a1, int a2, int to) {
		this.a1 = a1;
		this.a2 = a2;
		this.to = to;
	}
}
