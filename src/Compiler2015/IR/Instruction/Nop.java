package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.VirtualRegister;

public class Nop extends IRInstruction {

	public static Nop instance = new Nop();

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

	@Override
	public VirtualRegister[] getAllSSADef() {
		return new VirtualRegister[0];
	}

	@Override
	public VirtualRegister[] getAllSSAUse() {
		return new VirtualRegister[0];
	}

	@Override
	public String toString() {
		return "Nop";
	}
}
