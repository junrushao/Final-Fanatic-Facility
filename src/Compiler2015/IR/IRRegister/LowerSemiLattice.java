package Compiler2015.IR.IRRegister;

public class LowerSemiLattice implements IRRegister {
	public final static LowerSemiLattice instance = new LowerSemiLattice();

	@Override
	public String toString() {
		return "LowerSemiLattice";
	}

	@Override
	public int getUId() {
		return -2;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	@Override
	public LowerSemiLattice clone() {
		return instance;
	}

	@Override
	public boolean equals(Object o) {
		return this == o || (o != null && getClass() == o.getClass());
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
