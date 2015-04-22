package Compiler2015.AST.Type;

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
}
