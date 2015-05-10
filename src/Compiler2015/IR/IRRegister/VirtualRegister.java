package Compiler2015.IR.IRRegister;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Utility.Tokens;

public class VirtualRegister implements IRRegister {
	public int uId, version;

	public VirtualRegister(int uId) {
		this.uId = uId;
		this.version = -1;
	}

	public void setVersion(int x) {
		version = x;
	}

	@Override
	public int getValue() {
//		if (Environment.isVaraible(uId))
//			return uId;
//		return -1;
		return uId;
	}

	@Override
	public String toString() {
		String res;
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e.type == Tokens.VARIABLE)
			res = "#" + Integer.toString(uId);
		else if (e.type == Tokens.STRING_CONSTANT)
			res = "%" + Integer.toString(uId);
		else
			res = "$" + Integer.toString(uId);
		if (version != -1)
			res = res + "_" + Integer.toString(version);
		return res;
	}
}
