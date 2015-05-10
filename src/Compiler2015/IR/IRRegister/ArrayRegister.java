package Compiler2015.IR.IRRegister;

import Compiler2015.Exception.CompilationError;

public class ArrayRegister implements IRRegister {
	public IRRegister a, b;

	public ArrayRegister(IRRegister a, IRRegister b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int getValue() {
		throw new CompilationError("Internal Error.");
	}

	@Override
	public String toString() {
		return String.format("%s[%s]", a, b);
	}
}
