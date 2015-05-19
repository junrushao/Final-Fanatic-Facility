package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.VirtualRegister;

public class AllocateHeap extends IRInstruction implements NonSource {
	public int bitLen;

	public AllocateHeap(VirtualRegister rd, int bitLen) {
		this.rd = rd.clone();
		this.bitLen = bitLen;
	}

	@Override
	public String toString() {
		return "Allocate Heap: " + bitLen;
	}
}
