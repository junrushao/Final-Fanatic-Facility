package Compiler2015.Translate.Naive.MIPS;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.*;
import Compiler2015.IR.Instruction.ThreeAddressInstruction.ThreeAddressInstruction;
import Compiler2015.IR.Instruction.TwoAddressInstruction.TwoAddressInstruction;
import Compiler2015.Translate.BaseTranslator;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.FunctionType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Utility.Panel;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.io.PrintWriter;
import java.util.ArrayList;

public final class NaiveTranslator extends BaseTranslator {

	public NaiveTranslator(ControlFlowGraph graph, PrintWriter out) {
		super(graph, out);
	}

	public void generateLibraryFunction(int uId, PrintWriter out) {
		if (uId == Environment.uIdOfPutInt) {
			out.println("\tlw $a0, -4($sp)");
			out.println("\tli $v0, 1");
			out.println("\tsyscall");
		} else if (uId == Environment.uIdOfPutString) {
			out.println("\tlw $a0, -4($sp)");
			out.println("\tli $v0, 4");
			out.println("\tsyscall");
		} else if (uId == Environment.uIdOfMalloc) {
			out.println("\tlw $a0, -4($sp)");
			out.println("\tli $v0, 9");
			out.println("\tsyscall");
		} else if (uId == Environment.uIdOfGetChar) {
			out.println("\tli $v0, 12");
			out.println("\tsyscall");
		} else if (uId == Environment.uIdOfPutChar) {
			out.println("\tlw $a0, -4($sp)");
			out.println("\tli $v0, 11");
			out.println("\tsyscall");
		}
	}

	public void loadFromIRRegisterToTRegister(IRRegister from, int reg, PrintWriter out) {
		if (from instanceof ImmediateValue) {
			out.printf("\tli $t%d, %d%s", reg, ((ImmediateValue) from).a, Utility.NEW_LINE);
		} else if (from instanceof VirtualRegister) {
			int uId = from.getUId();
			if (uId <= 0)
				throw new CompilationError("Internal Error.");
			SymbolTableEntry e = Environment.symbolNames.table.get(uId);
			if (e.scope < 1)
				throw new CompilationError("Internal Error.");
			if (e.scope == 1) { // global variables: all global variables are arrays
				if (e.type == Tokens.STRING_CONSTANT)
					out.printf("\tla $t%d, %s%s", reg, getStringConstantLabelName(uId), Utility.NEW_LINE);
				else if (e.type == Tokens.VARIABLE) {
					out.printf("\tla $t%d, %s%s", reg, getGlobalVariableLabelName(uId), Utility.NEW_LINE);
					if (!(e.ref instanceof FunctionType || e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType))
						out.printf("\tlw $t%d, 0($t%d)%s", reg, reg, Utility.NEW_LINE);
				} else
					throw new CompilationError("Internal Error.");
			} else { // local variables
				if (e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType) {
					int delta = getDelta(uId);
					if (-32768 <= delta && delta <= 32767)
						out.printf("\taddiu $t%d, $sp, %d%s", reg, getDelta(uId), Utility.NEW_LINE);
					else {
						out.printf("\tli $t%d, %d%s", reg, delta, Utility.NEW_LINE);
						out.printf("\taddu $t%d, $sp, $t%d%s", reg, reg, Utility.NEW_LINE);
					}
				} else
					out.printf("\tlw $t%d, %d($sp)%s", reg, getDelta(uId), Utility.NEW_LINE);
			}
		} else if (from instanceof ArrayRegister) {
			loadFromIRRegisterToTRegister(((ArrayRegister) from).a, reg, out);
			String loadInstruction;
			if (((ArrayRegister) from).bitLen == 1)
				loadInstruction = "lb";
			else if (((ArrayRegister) from).bitLen == 4)
				loadInstruction = "lw";
			else
				throw new CompilationError("Internal Error.");
			out.printf("\t%s $t%d, %d($t%d)%s", loadInstruction, reg, ((ArrayRegister) from).b.a, reg, Utility.NEW_LINE);
		} else
			throw new CompilationError("Internal Error.");
	}

	public void storeFromTRegisterToIRRegister(int reg, int uId, PrintWriter out) {
		storeFromPhysicalRegisterToIRRegister("$t" + reg, uId, out);
	}

	public void storeFromPhysicalRegisterToIRRegister(String reg, int uId, PrintWriter out) {
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e.scope == 1) { // global variables: all global variables are arrays
			if (e.type == Tokens.STRING_CONSTANT)
				throw new CompilationError("Internal Error.");
			else if (e.type == Tokens.VARIABLE) {
				throw new CompilationError("Internal Error.");
/*
				out.printf("\tla $v1, %s%s", getGlobalVariableLabelName(uId), Utility.NEW_LINE);
				if (!(e.ref instanceof FunctionType || e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType))
					out.printf("\tsw %s, 0($v1)%s", reg, Utility.NEW_LINE);
				else
					throw new CompilationError("Internal Error.");
*/
			} else
				throw new CompilationError("Internal Error.");
		} else
			out.printf("\tsw %s, %d($sp)%s", reg, getDelta(uId), Utility.NEW_LINE);
	}

	public void generateFunction() {
		if (Environment.symbolNames.table.get(graph.functionTableEntry.uId).name.equals("main"))
			out.println("main:");

		out.println(getFunctionLabel());
		out.println("\taddu $sp, $sp, " + (-graph.frameSize));
		out.println("\tsw $ra, 128($sp)");

		ArrayList<CFGVertex> sequence = NaiveSequentializer.process(graph);
		int sizeOfAllArguments = 0;
		int sizeOfExtraArguments = 0;
		IRRegister lastNop = null;
		for (int itr = 0, itrEnd = sequence.size(); itr < itrEnd; ++itr) {
			CFGVertex vertex = sequence.get(itr);
			out.println(getVertexLabel(vertex.id));
			for (IRInstruction ins : vertex.internal) {
				lastNop = null;
				out.println(Utility.NEW_LINE + "#\t" + ins);
				if (ins instanceof ThreeAddressInstruction) {
					loadFromIRRegisterToTRegister(((ThreeAddressInstruction) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((ThreeAddressInstruction) ins).rt, 1, out);
					out.printf("\t%s $t2, $t0, $t1%s", ((ThreeAddressInstruction) ins).toMIPSName(), Utility.NEW_LINE);
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof TwoAddressInstruction) {
					loadFromIRRegisterToTRegister(((TwoAddressInstruction) ins).rs, 0, out);
					out.printf("\t%s $t1, $t0%s", ((TwoAddressInstruction) ins).toMIPSName(), Utility.NEW_LINE);
					storeFromTRegisterToIRRegister(1, ins.getAllDefUId()[0], out);
				} else if (ins instanceof Call) {
					if (!(((Call) ins).func instanceof VirtualRegister))
						throw new CompilationError("Internal Error.");
					VirtualRegister func = (VirtualRegister) ((Call) ins).func;
					if (Environment.isLibraryFunctions(func.uId))
						generateLibraryFunction(func.uId, out);
					else {
						if (sizeOfExtraArguments != 0)
							out.printf("\taddiu $sp, $sp, -%d%s", sizeOfExtraArguments, Utility.NEW_LINE);
						if (Environment.symbolNames.table.get(func.uId).type == Tokens.VARIABLE && Environment.symbolNames.table.get(func.uId).ref instanceof FunctionType) {
							out.printf("\tjal %s%s", getFunctionLabelName(func.uId), Utility.NEW_LINE);
						} else {
							loadFromIRRegisterToTRegister(func, 0, out);
							out.printf("\tjalr $t0%s", Utility.NEW_LINE);
						}
						if (sizeOfExtraArguments != 0)
							out.printf("\taddiu $sp, $sp, %d%s", sizeOfExtraArguments, Utility.NEW_LINE);
					}
				} else if (ins instanceof FetchReturn) {
					storeFromPhysicalRegisterToIRRegister("$v0", ins.getAllDefUId()[0], out);
					sizeOfAllArguments = sizeOfExtraArguments = 0;
				} else if (ins instanceof Move) {
					loadFromIRRegisterToTRegister(((Move) ins).rs, 0, out);
					storeFromTRegisterToIRRegister(0, ins.getAllDefUId()[0], out);
				} else if (ins instanceof PushStack) {
					PushStack pushIns = (PushStack) ins;
					int size = pushIns.pushType.sizeof();
					sizeOfAllArguments += Math.max(4, size);
					if (pushIns.isExtra)
						sizeOfExtraArguments += Math.max(4, size);
					if (pushIns.pushType instanceof StructOrUnionType) {
						int registerSize = Panel.getRegisterSize();
						loadFromIRRegisterToTRegister(pushIns.push, 0, out);
						for (int i = 0; i < size; i += registerSize) {
							out.printf("\tlw $t1, %d($t0)%s", i, Utility.NEW_LINE);
							out.printf("\tsw $t1, -%d($sp)%s", sizeOfAllArguments - i, Utility.NEW_LINE);
						}
					} else {
						String insName;
						if (size == 1)
							insName = "sb";
						else if (size == 4)
							insName = "sw";
						else
							throw new CompilationError("Internal Error.");
						if (pushIns.push instanceof VirtualRegister) {
							loadFromIRRegisterToTRegister(((PushStack) ins).push, 0, out);
							out.printf("\t%s $t0, -%d($sp)%s", insName, sizeOfAllArguments, Utility.NEW_LINE);
						} else if (pushIns.push instanceof ImmediateValue) {
							out.printf("\tli $t0, %d%s", ((ImmediateValue) pushIns.push).a, Utility.NEW_LINE);
							out.printf("\t%s $t0, -%d($sp)%s", insName, sizeOfAllArguments, Utility.NEW_LINE);
						} else
							throw new CompilationError("Internal Error.");
					}
				} else if (ins instanceof ReadArray) {
					loadFromIRRegisterToTRegister(((ReadArray) ins).rs, 0, out);
					storeFromTRegisterToIRRegister(0, ins.getAllDefUId()[0], out);
				} else if (ins instanceof WriteArray) {
					loadFromIRRegisterToTRegister(((WriteArray) ins).rs, 1, out);
					loadFromIRRegisterToTRegister(((WriteArray) ins).rd.a, 0, out);
					String loadInstruction;
					if (((WriteArray) ins).rd.bitLen == 1)
						loadInstruction = "sb";
					else if (((WriteArray) ins).rd.bitLen == 4)
						loadInstruction = "sw";
					else
						throw new CompilationError("Internal Error.");
					out.printf("\t%s $t1, %d($t0)%s", loadInstruction, ((WriteArray) ins).rd.b.a, Utility.NEW_LINE);
				} else if (ins instanceof SetReturn) {
					loadFromIRRegisterToTRegister(((SetReturn) ins).v0, 0, out);
					out.println("\tmove $v0, $t0");
				} else if (ins instanceof NopForBranch) {
					lastNop = ((NopForBranch) ins).rs;
				} else if (ins instanceof Def) {
					// do nothing
					out.printf("");
				} else if (ins instanceof Nop) {
					// do nothing
					out.printf("");
				} else
					throw new CompilationError("Internal Error.");
			}
			if (vertex.branchIfFalse != null) {
				if (lastNop == null)
					throw new CompilationError("Internal Error.");
				out.println();
				out.println("#\tbranchIfFalse " + lastNop);
				loadFromIRRegisterToTRegister(lastNop, 0, out);
				out.printf("\tbeq $t0, $0, %s%s", getVertexLabelName(vertex.branchIfFalse.id), Utility.NEW_LINE);
			}
			if (vertex.unconditionalNext != null && !(itr + 1 < itrEnd && sequence.get(itr + 1).id == vertex.unconditionalNext.id)) {
				out.println();
				out.println("#\tunconditionalNext");
				out.printf("\tj %s%s", getVertexLabelName(vertex.unconditionalNext.id), Utility.NEW_LINE);
			}
		}
		out.println("\tlw $ra, 128($sp)");
		out.println("\taddu $sp, $sp, " + graph.frameSize);
		out.println("\tjr $ra");
	}
}
