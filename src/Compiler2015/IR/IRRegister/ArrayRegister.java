package Compiler2015.IR.IRRegister;

import Compiler2015.Exception.CompilationError;

public class ArrayRegister implements IRRegister {
	public IRRegister a, b;
	public int bitLen;

	public ArrayRegister(IRRegister a, IRRegister b, int bitLen) {
		this.a = a.clone();
		this.b = b.clone();
		this.bitLen = bitLen;
	}

	@Override
	public int getValue() {
		throw new CompilationError("Internal Error.");
	}

	@Override
	public String toString() {
		return String.format("%s[%s](len = %d)", a, b, bitLen);
	}

	@Override
	public ArrayRegister clone() {
		ArrayRegister ret;
		try {
			ret = (ArrayRegister) super.clone();
			ret.a = a.clone();
			ret.b = b.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new CompilationError("Internal Error.");
		}
		return ret;
	}
}
