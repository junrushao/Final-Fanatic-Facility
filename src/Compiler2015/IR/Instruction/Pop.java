package Compiler2015.IR.Instruction;

public class Pop extends IRInstruction implements NonSource {

	public static Pop instance = new Pop();

	private Pop() {
		rd = null;
	}

	public String toString() {
		return "Pop";
	}

	@Override
	public int getRd() {
		return -1;
	}

	@Override
	public int[] getAllDef() {
		return new int[0];
	}

	@Override
	public int[] getAllUse() {
		return new int[0];
	}

	@Override
	public void setAllDefVersion(int[] version) {
	}

	@Override
	public void setAllUseVersion(int[] version) {
	}
}
