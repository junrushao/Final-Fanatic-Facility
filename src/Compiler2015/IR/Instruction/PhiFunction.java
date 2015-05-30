package Compiler2015.IR.Instruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

public class PhiFunction extends IRInstruction {
	public IRRegister rs[];

	public PhiFunction(int n, VirtualRegister rd) {
		this.rd = rd.clone();
		this.rs = new IRRegister[n];
		for (int i = 0; i < n; ++i)
			rs[i] = rd.clone();
	}

	@Override
	public IRInstruction getExpression() {
		return this;
	}

	@Override
	public int[] getAllDefUId() {
		return new int[]{rd.getUId()};
	}

	@Override
	public int[] getAllUseUId() {
		int use[] = new int[rs.length];
		for (int i = 0; i < rs.length; ++i)
			use[i] = rs[i].getUId();
		return use;
	}

	public void setUse(int i, IRRegister newOne) {
		if (newOne == null)
			throw new CompilationError("Internal Error.");
		rs[i] = newOne;
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[]{detectVirtualRegister(rd)};
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		VirtualRegister ret[] = new VirtualRegister[rs.length];
		for (int i = 0; i < rs.length; ++i)
			ret[i] = detectVirtualRegister(rs[i]);
		return ret;
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[]{rd.clone()};
	}

	@Override
	public void setAllDef(IRRegister[] version) {
		if (version[0] != null)
			rd = (VirtualRegister) version[0];
	}

	@Override
	public IRRegister[] getAllUse() {
		IRRegister ret[] = new IRRegister[rs.length];
		for (int i = 0; i < rs.length; ++i)
			ret[i] = rs[i].clone();
		return ret;
	}

	@Override
	public void setAllUse(IRRegister[] version) {
		for (int i = 0; i < rs.length; ++i)
			if (version[i] != null)
				rs[i] = version[i];
	}

	@Override
	public PhiFunction clone() {
		PhiFunction ret = (PhiFunction) super.clone();
		ret.rd = ret.rd.clone();
		ret.rs = new IRRegister[rs.length];
		for (int i = 0; i < ret.rs.length; ++i)
			ret.rs[i] = rs[i].clone();
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

	@Override
	public int hashCode() {
//		System.err.println("Calculating hashCode of PhiFunction " + toString());
		int result = rd.hashCode();
		for (IRRegister r : rs)
			result = result * 31 + r.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PhiFunction that = (PhiFunction) o;
		if (!rd.equals(that.rd))
			return false;
		for (int i = 0; i < rs.length; ++i)
			if (!rs[i].equals(that.rs[i]))
				return false;
		return true;
	}
}
