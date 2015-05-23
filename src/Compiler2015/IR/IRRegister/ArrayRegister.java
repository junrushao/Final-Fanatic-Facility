package Compiler2015.IR.IRRegister;

import Compiler2015.Exception.CompilationError;

public class ArrayRegister implements IRRegister {
	public VirtualRegister a;
	public ImmediateValue b;
	public int bitLen;

	public ArrayRegister(VirtualRegister a, ImmediateValue b, int bitLen) {
		if (bitLen != 1 && bitLen != 4)
			throw new CompilationError("Internal Error.");
		this.a = a.clone();
		this.b = b.clone();
		this.bitLen = bitLen;
	}

	@Override
	public int getUId() {
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
