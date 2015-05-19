package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Panel;

public class FetchReturn extends IRInstruction implements NonSource {
	public int bitLen;

	public FetchReturn(VirtualRegister rd, Type t) {
		this.rd = rd.clone();
		this.bitLen = t instanceof StructOrUnionType ? Panel.getPointerSize() : t.sizeof();
	}

	@Override
	public String toString() {
		return "Fetch Return: " + rd;
	}
}
