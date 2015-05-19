package Compiler2015.IR.IRRegister;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Tokens;

public class VirtualRegister implements IRRegister {
	public int uId, version;

	public VirtualRegister(int uId) {
		this.uId = uId;
		this.version = -1;
	}

	public void setVersion(int x) {
		if (Environment.symbolNames.table.get(uId).scope != 1)
			version = x;
	}

	@Override
	public int getValue() {
		return uId;
	}

	@Override
	public String toString() {
		String res;
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e == null)
			res = "M";
		else if (e.type == Tokens.VARIABLE)
			res = "#" + Integer.toString(uId);
		else if (e.type == Tokens.STRING_CONSTANT)
			res = "%" + Integer.toString(uId);
		else
			res = "$" + Integer.toString(uId);
		if (version != -1)
			res = res + "_" + Integer.toString(version);
		return res;
	}

	@Override
	public VirtualRegister clone() {
		try {
			return (VirtualRegister) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new CompilationError("Internal Error.");
		}
	}
}
