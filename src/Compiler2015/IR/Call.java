package Compiler2015.IR;

public class Call extends IRInstruction {
	public int calleeAddressReg;
	public int regs[];
	public int returnReg;

	public Call(int calleeAddressReg, int[] regs, int returnReg) {
		this.calleeAddressReg = calleeAddressReg;
		this.regs = regs;
		this.returnReg = returnReg;
	}
}
