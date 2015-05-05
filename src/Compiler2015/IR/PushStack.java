package Compiler2015.IR;

public class PushStack extends IRInstruction {
	public int rd;

	public PushStack(int rd) {
		this.rd = rd;
	}
}
