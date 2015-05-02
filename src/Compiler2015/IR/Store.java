package Compiler2015.IR;

public class Store extends IRInstruction {
	public int from, to, bitLen;

	public Store(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public Store(int from, int to, int bitLen) {
		this.from = from;
		this.to = to;
		this.bitLen = bitLen;
	}
}
