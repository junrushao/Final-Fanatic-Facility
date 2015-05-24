package Compiler2015.Type;

import Compiler2015.Utility.Panel;

public class FunctionPointerType extends Pointer {
	public FunctionType pointTo;

	public FunctionPointerType(FunctionType pointTo) {
		this.pointTo = pointTo;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof FunctionPointerType && pointTo.equals(((FunctionPointerType) obj).pointTo);
	}

	@Override
	public String toString() {
		return "FunctionPointerType(" + pointTo.toString() + ")";
	}

	@Override
	public int classifiedSizeof() {
		return Panel.getPointerSize();
	}

	@Override
	public FunctionPointerType clone() {
		return (FunctionPointerType) super.clone();
	}
}
