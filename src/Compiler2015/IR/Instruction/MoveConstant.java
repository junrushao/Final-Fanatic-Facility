package Compiler2015.IR.Instruction;

/**
 * rd = imm
 */
public class MoveConstant extends IRInstruction {
	public int rd, imm;

	public MoveConstant(int rd, int imm) {
		this.rd = rd;
		this.imm = imm;
	}
}
