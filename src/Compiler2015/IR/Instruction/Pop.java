package Compiler2015.IR.Instruction;

public class Pop extends IRInstruction {

	public static Pop instance = new Pop();

	private Pop() {
	}

	public String toString() {
		return "Pop";
	}
}
