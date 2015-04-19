package Compiler2015.Environment;

import Compiler2015.Utility.Tokens;

public class Entry {
	public int uId;
	public String name;
	public int scope;
	public Tokens type;
	public Tokens status;
	public Object ref;
	public Object info;

	public Entry(int uId, String name, int scope, Tokens type, Tokens status, Object ref, Object info) {
		this.uId = uId;
		this.name = name;
		this.scope = scope;
		this.type = type;
		this.status = status;
		this.ref = ref;
		this.info = info;
	}
}

