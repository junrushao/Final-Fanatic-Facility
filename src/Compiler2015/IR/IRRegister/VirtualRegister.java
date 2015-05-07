package Compiler2015.IR.IRRegister;

public class VirtualRegister implements IRRegister {
	public int uId;

	public VirtualRegister(int uId) {
		this.uId = uId;
	}

	@Override
	public int getValue() {
		return uId;
	}

	@Override
	public String toString() {
		return "#" + Integer.toString(uId);
	}
}
