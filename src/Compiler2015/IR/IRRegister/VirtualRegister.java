package Compiler2015.IR.IRRegister;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Utility.Tokens;

public class VirtualRegister implements IRRegister {
	public int uId;

	public VirtualRegister(int uId) {
		this.uId = uId;
	}

	@Override
	public int getValue() {
		return uId;
	}

	@Override
	public String toString() {
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e.type == Tokens.VARIABLE)
			return "#" + Integer.toString(uId);
		if (e.type == Tokens.STRING_CONSTANT)
			return "%" + Integer.toString(uId);
		return "$" + Integer.toString(uId);
	}
}
