package Compiler2015.IR.Instruction;

public class PushStack extends IRInstruction {
	public int rd;

	public PushStack(int rd) {
		this.rd = rd;
	}
}
