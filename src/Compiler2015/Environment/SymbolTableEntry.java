package Compiler2015.Environment;

import Compiler2015.Utility.Tokens;

public class SymbolTableEntry {
	public int uId;
	public String name;
	public int scope;
	public Tokens type;
	public Object ref;
	public Object info;

	public SymbolTableEntry(int uId, String name, int scope, Tokens type, Object ref, Object info) {
		this.uId = uId;
		this.name = name;
		this.scope = scope;
		this.type = type;
		this.ref = ref;
		this.info = info;
	}
}

