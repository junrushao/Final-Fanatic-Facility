package Compiler2015.IR.Instruction;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.VirtualRegister;

/**
 * rd = func()
 * If return type of func is void, rd is useless.
 */
public class Call extends IRInstruction {
	public IRRegister func;
	public int memoryVersion = -1;

	public Call(IRRegister func) {
		if (func instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		this.rd = null;
		this.func = func.clone();
	}

	@Override
	public String toString() {
		return "Call " + func;
	}

	@Override
	public IRInstruction getExpression() {
		return this;
	}

	@Override
	public int[] getAllDefUId() { // a function call might change memory
		return new int[]{0};
	}

	@Override
	public int[] getAllUseUId() {
		return new int[]{func.getUId()};
	}

	@Override
	public VirtualRegister[] getAllDefVR() {
		return new VirtualRegister[]{new VirtualRegister(0, memoryVersion)};
	}

	@Override
	public VirtualRegister[] getAllUseVR() {
		return new VirtualRegister[]{detectVirtualRegister(func)};
	}

	@Override
	public IRRegister[] getAllDef() {
		return new IRRegister[]{new VirtualRegister(0, memoryVersion)};
	}

	@Override
	public void setAllDef(IRRegister[] version) {
		memoryVersion = ((VirtualRegister) version[0]).version;
	}

	@Override
	public IRRegister[] getAllUse() {
		return new IRRegister[]{func.clone()};
	}

	@Override
	public void setAllUse(IRRegister[] version) {
		if (version[0] != null)
			func = version[0];
	}
}
