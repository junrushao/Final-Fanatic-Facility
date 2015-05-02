package Compiler2015.IR.Arithmetic;

public class DivideReg extends Arithmetic {
	public int a1, a2, to;

	public DivideReg(int a1, int a2, int to) {
		this.a1 = a1;
		this.a2 = a2;
		this.to = to;
	}
}
