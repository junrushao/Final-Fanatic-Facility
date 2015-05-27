package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public class PhiFunction extends IRInstruction {
	IRRegister rs[];

	public PhiFunction(int n, VirtualRegister rd) {
		this.rd = rd;
		this.rs = new VirtualRegister[n];
		for (int i = 0; i < n; ++i)
			rs[i] = rd.clone();
	}

	@Override
	public int[] getAllDef() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUse() {
		int use[] = new int[rs.length];
		for (int i = 0; i < rs.length; ++i)
			use[i] = rs[i].getUId();
		return use;
	}

	@Override
	public void setAllDefVersion(int[] version) {
		rd.setVersion(version[0]);
	}

	@Override
	public void setAllUseVersion(int[] version) {
		for (int i = 0; i < rs.length; ++i)
			if (rs[i] instanceof VirtualRegister)
				((VirtualRegister) rs[i]).setVersion(version[i]);
	}

	@Override
	public VirtualRegister[] getAllSSADef() {
		return new VirtualRegister[]{detectVirtualRegister(rd)};
	}

	@Override
	public VirtualRegister[] getAllSSAUse() {
		VirtualRegister ret[] = new VirtualRegister[rs.length];
		for (int i = 0; i < rs.length; ++i)
			ret[i] = detectVirtualRegister(ret[i]);
		return ret;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(rd).append(" = phi(");
		for (int i = 0; i < rs.length; ++i) {
			if (i != 0)
				sb.append(", ");
			sb.append(rs[i]);
		}
		return sb.append(")").toString();
	}
}
