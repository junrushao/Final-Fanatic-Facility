package Compiler2015.IR;

public class LoadImm extends IRInstruction {
	public int to, imm, bitLen;

	public LoadImm(int to, int imm) {
		this.to = to;
		this.imm = imm;
		this.bitLen = 32;
	}

	public LoadImm(int to, int imm, int bitLen) {
		this.to = to;
		this.imm = imm;
		this.bitLen = bitLen;
	}
}
