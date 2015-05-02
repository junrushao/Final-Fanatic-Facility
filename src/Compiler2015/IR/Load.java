package Compiler2015.IR;

public class Load extends IRInstruction {
	public int from, to, bitLen;

	public Load(int from, int to) {
		this.from = from;
		this.to = to;
		this.bitLen = 4;
	}

	public Load(int from, int to, int bitLen) {
		this.from = from;
		this.to = to;
		this.bitLen = bitLen;
	}
}
