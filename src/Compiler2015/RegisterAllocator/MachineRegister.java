package Compiler2015.RegisterAllocator;

public class MachineRegister {
	public final static MachineRegister[] availableMachineRegisters = new MachineRegister[]{
//			new MachineRegister(0, "$0"),
//			new MachineRegister(1, "$at"),
//			new MachineRegister(2, "$v0"),
			new MachineRegister(3, "$v1"),
//			new MachineRegister(4, "$a0"),
			new MachineRegister(5, "$a1"),
			new MachineRegister(6, "$a2"),
			new MachineRegister(7, "$a3"),
			new MachineRegister(8, "$t0"),
			new MachineRegister(9, "$t1"),
			new MachineRegister(10, "$t2"),
			new MachineRegister(11, "$t3"),
			new MachineRegister(12, "$t4"),
			new MachineRegister(13, "$t5"),
			new MachineRegister(14, "$t6"),
			new MachineRegister(15, "$t7"),
			new MachineRegister(16, "$s0"),
			new MachineRegister(17, "$s1"),
			new MachineRegister(18, "$s2"),
			new MachineRegister(19, "$s3"),
			new MachineRegister(20, "$s4"),
			new MachineRegister(21, "$s5"),
			new MachineRegister(22, "$s6"),
			new MachineRegister(23, "$s7"),
			new MachineRegister(24, "$t8"),
			new MachineRegister(25, "$t9"),
			new MachineRegister(26, "$k0"),
			new MachineRegister(27, "$k1"),
			new MachineRegister(28, "$gp"),
//			new MachineRegister(29, "$sp"),
			new MachineRegister(30, "$fp"),
//			new MachineRegister(31, "$ra")
	};
	public static int K;
	public static MachineRegister tmp1 = new MachineRegister(31, "$ra");
	public static MachineRegister tmp2 = new MachineRegister(2, "$v0");
	public static MachineRegister tmp3 = new MachineRegister(4, "$a0");

	static {
		K = availableMachineRegisters.length;
		for (int i = 0; i < availableMachineRegisters.length; ++i)
			availableMachineRegisters[i].order = i;
	}

	public int idInMIPS;
	public String name;
	public int order;

	private MachineRegister(int idInMIPS, String name) {
		this.idInMIPS = idInMIPS;
		this.name = name;
		this.order = Integer.MIN_VALUE / 4;
	}

	@Override
	public String toString() {
		return name;
	}
}
