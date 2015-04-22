package Compiler2015.AST.Type;

/**
 */
public class VariablePointerType extends Pointer {
	public Type pointTo;
	public VariablePointerType(Type pointTo) {
		this.pointTo = pointTo;
	}

	@Override
	public int sizeof() {
		return 8;
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
}
