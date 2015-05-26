package Compiler2015.Type;

import Compiler2015.Utility.Panel;

public class VariablePointerType extends Pointer {
	public Type pointTo;

	public VariablePointerType(Type pointTo) {
		this.pointTo = pointTo;
	}

	@Override
	public int sizeof() {
		return Panel.getPointerSize();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof VariablePointerType))
			return false;
		VariablePointerType other = (VariablePointerType) obj;
		return pointTo.equals(other.pointTo);
	}

	@Override
	public String toString() {
		return "Pointer(" + pointTo.toString() + ")";
	}

	@Override
	public VariablePointerType clone() {
		return (VariablePointerType) super.clone();
	}
}
