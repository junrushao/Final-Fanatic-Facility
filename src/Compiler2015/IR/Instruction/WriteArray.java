package Compiler2015.IR.Instruction;

import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd <- rs , i.e. a[b] = c
 */
public class WriteArray extends IRInstruction implements TripleSource {
	public ArrayRegister rd;
	public IRRegister rs;
	public int memoryVersion = 0;

	public WriteArray(ArrayRegister rd, IRRegister rs) {
		this.rd = rd.clone();
		this.rs = rs.clone();
	}

	@Override
	public String toString() {
		return "WriteArray " + rd + " = " + rs + " -> M = " + memoryVersion;
	}

	@Override
	public int getRd() {
		return 0;
	}

	@Override
	public int getA() {
		return rd.a.getValue();
	}

	@Override
	public int getB() {
		return rd.b.getValue();
	}

	@Override
	public int getC() {
		return rs.getValue();
	}

	@Override
	public void setRdVersion(int x) {
		memoryVersion = x;
	}

	@Override
	public void setAVersion(int x) {
		if (rd.a instanceof VirtualRegister)
			((VirtualRegister) rd.a).setVersion(x);
	}

	@Override
	public void setBVersion(int x) {
		if (rd.b instanceof VirtualRegister)
			((VirtualRegister) rd.b).setVersion(x);
	}

	@Override
	public void setCVersion(int x) {
		if (rs instanceof VirtualRegister)
			((VirtualRegister) rs).setVersion(x);
	}
}
