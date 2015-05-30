package Compiler2015.Translate;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.FunctionTableEntry;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.*;
import Compiler2015.IR.Instruction.ThreeAddressInstruction.ThreeAddressInstruction;
import Compiler2015.IR.Instruction.TwoAddressInstruction.TwoAddressInstruction;
import Compiler2015.RegisterAllocator.MachineRegister;
import Compiler2015.Translate.Naive.MIPS.NaiveSequentializer;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.VoidType;
import Compiler2015.Utility.Panel;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.io.PrintWriter;
import java.util.ArrayList;

public final class SimpleTranslator extends BaseTranslator {

	public SimpleTranslator(ControlFlowGraph graph, PrintWriter out) {
		super(graph, out);
	}

	/**
	 * @param reg       virtual register that need to be mapped to physical register
	 * @param candidate candidate physical register, if reg is spilled, return this
	 * @return a physical register for writing
	 */
	public MachineRegister getRegisterToWrite(IRRegister reg, MachineRegister candidate) {
		if (reg instanceof VirtualRegister) {
			VirtualRegister r = (VirtualRegister) reg;
			MachineRegister ret = allocator.mapping.get(r.uId);
			return ret == null ? candidate : ret;
		} else
			throw new CompilationError("Internal Error.");
	}

	/**
	 * @param reg       virtual register / immediate value that need to be mapped to physical register
	 * @param candidate candidate physical register, if reg is spilled, read value from memory to this register and return
	 * @return a physical register for reading
	 */
	public MachineRegister getRegisterToRead(IRRegister reg, MachineRegister candidate) {
		if (reg instanceof VirtualRegister) {
			VirtualRegister readIn = ((VirtualRegister) reg);
			MachineRegister ret = allocator.mapping.get(readIn.uId);
			if (ret != null)
				return ret;
			SymbolTableEntry e = Environment.symbolNames.table.get(readIn.uId);
			if (e.scope == 1) { // global variables: all global variables are arrays, read a pointer to it
				if (e.type == Tokens.STRING_CONSTANT)
					out.printf("\tla %s, %s%s", candidate, getStringConstantLabelName(readIn.uId), Utility.NEW_LINE);
				else if (e.type == Tokens.VARIABLE)
					out.printf("\tla %s, %s%s", candidate, getGlobalVariableLabelName(readIn.uId), Utility.NEW_LINE);
				else
					throw new CompilationError("Internal Error.");
			} else { // local variables
				if (e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType)
					out.printf("\taddu %s, $sp, %d%s", candidate, getDelta(readIn.uId), Utility.NEW_LINE);
				else
					out.printf("\tlw %s, %d($sp)%s", candidate, getDelta(readIn.uId), Utility.NEW_LINE);
			}
			return candidate;
		} else if (reg instanceof ImmediateValue) {
			out.printf("\tli %s, %d%s", candidate, ((ImmediateValue) reg).a, Utility.NEW_LINE);
			return candidate;
		} else
			throw new CompilationError("Internal Error.");
	}

	/**
	 * @param reg   virtual register to be written back
	 * @param value if reg is spilled, value should be written back
	 */
	public void writeBackIfSpilled(IRRegister reg, MachineRegister value) {
		if (reg instanceof VirtualRegister) {
			VirtualRegister writeBack = ((VirtualRegister) reg);
			if (allocator.mapping.get(writeBack.uId) != null)
				return;
			SymbolTableEntry e = Environment.symbolNames.table.get(writeBack.uId);
			if (e.scope == 1) // global variables: all global variables are arrays
				throw new CompilationError("Internal Error.");
			out.printf("\tsw %s, %d($sp)%s", value, getDelta(writeBack.uId), Utility.NEW_LINE);
		} else
			throw new CompilationError("Internal Error.");
	}

	public void moveFromPhysicalRegisterToVirtualRegister(String from, IRRegister to) {
		if (to instanceof VirtualRegister) {
			VirtualRegister writeBack = ((VirtualRegister) to);
			if (allocator.mapping.get(writeBack.uId) != null)
				out.printf("\tmove %s, %s%s", allocator.mapping.get(writeBack.uId), from, Utility.NEW_LINE);
			else {
				SymbolTableEntry e = Environment.symbolNames.table.get(writeBack.uId);
				if (e.scope == 1) // global variables: all global variables are arrays
					throw new CompilationError("Internal Error.");
				out.printf("\tsw %s, %d($sp)%s", from, getDelta(writeBack.uId), Utility.NEW_LINE);
			}
		} else
			throw new CompilationError("Internal Error.");
	}

	public void moveFromIRRegisterToPhysicalRegister(IRRegister from, String to) {
		if (from instanceof VirtualRegister) {
			VirtualRegister readIn = ((VirtualRegister) from);
			MachineRegister ret = allocator.mapping.get(readIn.uId);
			if (ret != null) {
				if (!ret.name.equals(to)) {
					out.printf("\tmove %s, %s%s", to, ret.name, Utility.NEW_LINE);
				}
				return;
			}
			SymbolTableEntry e = Environment.symbolNames.table.get(readIn.uId);
			if (e.scope == 1) { // global variables: all global variables are arrays, read a pointer to it
				if (e.type == Tokens.STRING_CONSTANT)
					out.printf("\tla %s, %s%s", to, getStringConstantLabelName(readIn.uId), Utility.NEW_LINE);
				else if (e.type == Tokens.VARIABLE)
					out.printf("\tla %s, %s%s", to, getGlobalVariableLabelName(readIn.uId), Utility.NEW_LINE);
				else
					throw new CompilationError("Internal Error.");
			} else { // local variables
				if (e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType)
					out.printf("\taddu %s, $sp, %d%s", to, getDelta(readIn.uId), Utility.NEW_LINE);
				else
					out.printf("\tlw %s, %d($sp)%s", to, getDelta(readIn.uId), Utility.NEW_LINE);
			}
		} else if (from instanceof ImmediateValue) {
			out.printf("\tli %s, %d%s", to, ((ImmediateValue) from).a, Utility.NEW_LINE);
		} else
			throw new CompilationError("Internal Error.");
	}

	@Override
	public void generateFunction() {
		if (Environment.symbolNames.table.get(graph.functionTableEntry.uId).name.equals("main")) {
			out.println("main:");
			out.printf("\taddu $sp, $sp, -%d%s", graph.frameSize, Utility.NEW_LINE);
		}

		// enter a function
		out.println(getFunctionLabel());
		out.println("\tsw $ra, 128($sp)");

		// save registers
		for (MachineRegister reg : allocator.physicalRegistersUsed)
			out.printf("\tsw %s %d($sp)%s", reg, reg.order * Panel.getRegisterSize(), Utility.NEW_LINE);

		// put blocks in a sequence
		ArrayList<CFGVertex> sequence = NaiveSequentializer.process(graph);

		for (int itrBlock = 0, itrEnd = sequence.size(); itrBlock < itrEnd; ++itrBlock) {
			CFGVertex vertex = sequence.get(itrBlock);
			out.println(getVertexLabel(vertex.id));

			NopForBranch lastNopForBranch = null;
			for (int itrIns = 0, itrInsEnd = vertex.internal.size(); itrIns < itrInsEnd; ++itrIns) {
				IRInstruction ins = vertex.internal.get(itrIns);
				out.println(Utility.NEW_LINE + "#\t" + ins);

				lastNopForBranch = null;

				if (ins instanceof ThreeAddressInstruction) {
					ThreeAddressInstruction i = ((ThreeAddressInstruction) ins);
					// rd = rs op rt
					if (i.rt instanceof ImmediateValue) {
						MachineRegister pRs = getRegisterToRead(i.rs, MachineRegister.tmp1);
						MachineRegister pRd = getRegisterToWrite(i.rd, MachineRegister.tmp2);
						out.printf("\t%s %s, %s, %d%s", i.toMIPSName(), pRd, pRs, ((ImmediateValue) i.rt).a, Utility.NEW_LINE);
						writeBackIfSpilled(i.rd, pRd);
					} else {
						MachineRegister pRs = getRegisterToRead(i.rs, MachineRegister.tmp1);
						MachineRegister pRt = getRegisterToRead(i.rt, MachineRegister.tmp2);
						MachineRegister pRd = getRegisterToWrite(i.rd, MachineRegister.tmp2);
						out.printf("\t%s %s, %s, %s%s", i.toMIPSName(), pRd, pRs, pRt, Utility.NEW_LINE);
						writeBackIfSpilled(i.rd, pRd);
					}
				} else if (ins instanceof TwoAddressInstruction) {
					TwoAddressInstruction i = (TwoAddressInstruction) ins;
					MachineRegister pRs = getRegisterToRead(i.rs, MachineRegister.tmp1);
					MachineRegister pRd = getRegisterToWrite(i.rd, MachineRegister.tmp1);
					out.printf("\t%s %s, %s%s", i.toMIPSName(), pRd, pRs, Utility.NEW_LINE);
					writeBackIfSpilled(i.rd, pRd);
				} else if (ins instanceof Move) {
					Move i = (Move) ins;
					MachineRegister pRs = getRegisterToRead(i.rs, MachineRegister.tmp1);
					moveFromPhysicalRegisterToVirtualRegister(pRs.name, i.rd);
				} else if (ins instanceof ReadArray) {
					ReadArray i = (ReadArray) ins;
					String instruction = i.rs.bitLen == 1 ? "lb" : "lw";
					MachineRegister pC = getRegisterToWrite(i.rd, MachineRegister.tmp1);

					VirtualRegister a = i.rs.a;
					ImmediateValue b = i.rs.b;

					if (Environment.symbolNames.table.get(a.uId).scope == 1) {
						// global array
						MachineRegister pA = getRegisterToRead(a, MachineRegister.tmp2);
						out.printf("\t%s %s, %d(%s)%s", instruction, pC, b.a, pA, Utility.NEW_LINE);
					} else {
						SymbolTableEntry e = Environment.symbolNames.table.get(a.uId);
						// local array
						if (e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType) {
							out.printf("\t%s %s, %d(%s)%s", instruction, pC, getDelta(a.uId) + b.a, "$sp", Utility.NEW_LINE);
						} else if (allocator.mapping.get(a.uId) == null) {
							out.printf("\tlw %s, %d($sp)%s", MachineRegister.tmp2, getDelta(a.uId), Utility.NEW_LINE);
							out.printf("\t%s %s, %d(%s)%s", instruction, pC, b.a, MachineRegister.tmp2, Utility.NEW_LINE);
						} else {
							out.printf("\t%s %s, %d(%s)%s", instruction, pC, b.a, allocator.mapping.get(a.uId), Utility.NEW_LINE);
						}
					}
					writeBackIfSpilled(i.rd, pC);
				} else if (ins instanceof WriteArray) {
					WriteArray i = (WriteArray) ins;
					String instruction = i.rd.bitLen == 1 ? "sb" : "sw";
					MachineRegister pC = getRegisterToRead(i.rs, MachineRegister.tmp1);
					VirtualRegister a = i.rd.a;
					ImmediateValue b = i.rd.b;
					if (Environment.symbolNames.table.get(a.uId).scope == 1) {
						// global array
						MachineRegister pA = getRegisterToRead(a, MachineRegister.tmp2);
						out.printf("\t%s %s, %d(%s)%s", instruction, pC, b.a, pA, Utility.NEW_LINE);
					} else {
						SymbolTableEntry e = Environment.symbolNames.table.get(a.uId);
						// local array
						if (e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType) {
							out.printf("\t%s %s, %d(%s)%s", instruction, pC, getDelta(a.uId) + b.a, "$sp", Utility.NEW_LINE);
						} else if (allocator.mapping.get(a.uId) == null) {
							// spilled
							out.printf("\tlw %s, %d($sp)%s", MachineRegister.tmp2, getDelta(a.uId), Utility.NEW_LINE);
							out.printf("\t%s %s, %d(%s)%s", instruction, pC, b.a, MachineRegister.tmp2, Utility.NEW_LINE);
						} else {
							out.printf("\t%s %s, %d(%s)%s", instruction, pC, b.a, allocator.mapping.get(a.uId), Utility.NEW_LINE);
						}
					}
				} else if (ins instanceof SetReturn) {
					SetReturn i = (SetReturn) ins;
					moveFromIRRegisterToPhysicalRegister(i.v0, "$v0");
				} else if (ins instanceof Nop) {
					// do nothing
					out.printf("");
				} else if (ins instanceof Def) {
					Def i = (Def) ins;
					VirtualRegister readIn = i.rd;
					if (readIn.uId > 0 && allocator.mapping.get(readIn.uId) != null) {
						// not spilled, should be read in
						MachineRegister to = allocator.mapping.get(readIn.uId);
						SymbolTableEntry e = Environment.symbolNames.table.get(readIn.uId);
						if (e.scope == 1) { // global variables: all global variables are arrays, read a pointer to it
							if (e.type == Tokens.STRING_CONSTANT)
								out.printf("\tla %s, %s%s", to, getStringConstantLabelName(readIn.uId), Utility.NEW_LINE);
							else if (e.type == Tokens.VARIABLE)
								out.printf("\tla %s, %s%s", to, getGlobalVariableLabelName(readIn.uId), Utility.NEW_LINE);
							else
								throw new CompilationError("Internal Error.");
						} else { // local variables
							if (e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType)
								out.printf("\taddu %s, $sp, %d%s", to, getDelta(readIn.uId), Utility.NEW_LINE);
							else
								out.printf("\tlw %s, %d($sp)%s", to, getDelta(readIn.uId), Utility.NEW_LINE);
						}
					}
				} else if (ins instanceof NopForBranch) {
					lastNopForBranch = (NopForBranch) ins;
				} else if (ins instanceof PushStack || ins instanceof Call) {
					// scan the whole part of a function call
					int positionOfCall = itrIns;
					while (!(vertex.internal.get(positionOfCall) instanceof Call))
						++positionOfCall;
					if (!(vertex.internal.get(positionOfCall + 1) instanceof FetchReturn))
						throw new CompilationError("Internal Error.");

					Call call = (Call) vertex.internal.get(positionOfCall);
					if (!(call.func instanceof VirtualRegister))
						throw new CompilationError("Internal Error.");
					generateFunction(call, vertex.internal, itrIns, positionOfCall);
					itrIns = positionOfCall;
				} else if (ins instanceof FetchReturn) {
					FetchReturn i = (FetchReturn) ins;
					Call call = (Call) vertex.internal.get(itrIns - 1);
					int uId = call.func.getUId();
					if (!(Environment.functionTable.get(uId).definition.returnType instanceof VoidType))
						moveFromPhysicalRegisterToVirtualRegister("$v0", i.rd);
				} else
					throw new CompilationError("Internal Error.");
			}

			if (vertex.branchIfFalse != null) {
				if (lastNopForBranch == null)
					throw new CompilationError("Internal Error.");
				out.println();
				out.println("#\tbranchIfFalse " + lastNopForBranch);
				moveFromIRRegisterToPhysicalRegister(lastNopForBranch.rs, MachineRegister.tmp1.name);
				out.printf("\tbeq %s, $0, %s%s", MachineRegister.tmp1, getVertexLabelName(vertex.branchIfFalse.id), Utility.NEW_LINE);
			}
			if (vertex.unconditionalNext != null && !(itrBlock + 1 < itrEnd && sequence.get(itrBlock + 1).id == vertex.unconditionalNext.id)) {
				out.println();
				out.println("#\tunconditionalNext");
				out.printf("\tj %s%s", getVertexLabelName(vertex.unconditionalNext.id), Utility.NEW_LINE);
			}
		}

		for (MachineRegister reg : allocator.physicalRegistersUsed)
			out.printf("\tlw %s, %d($sp)%s", reg, reg.order * Panel.getRegisterSize(), Utility.NEW_LINE);

		// exit a function
		out.println("\tlw $ra, 128($sp)");

		if (Environment.symbolNames.table.get(graph.functionTableEntry.uId).name.equals("main")) {
			out.printf("\taddu $sp, $sp, %d%s", graph.frameSize, Utility.NEW_LINE);
		}

		out.println("\tjr $ra");
	}

	public void generateFunction(Call call, ArrayList<IRInstruction> internal, int beginPosition, int positionOfCall) {
		int funcUId = call.func.getUId();
		if (Environment.isLibraryFunctions(funcUId)) {
			// automatically inline library functions
			if (funcUId == Environment.uIdOfPutInt) {
				PushStack i = (PushStack) internal.get(positionOfCall - 1);
				out.println(Utility.NEW_LINE + "#\t" + i);
				moveFromIRRegisterToPhysicalRegister(i.push, "$a0");
				out.println("\tli $v0, 1");
				out.println("\tsyscall");
			} else if (funcUId == Environment.uIdOfPutString) {
				PushStack i = (PushStack) internal.get(positionOfCall - 1);
				out.println(Utility.NEW_LINE + "#\t" + i);
				moveFromIRRegisterToPhysicalRegister(i.push, "$a0");
				out.println("\tli $v0, 4");
				out.println("\tsyscall");
			} else if (funcUId == Environment.uIdOfMalloc) {
				PushStack i = (PushStack) internal.get(positionOfCall - 1);
				out.println(Utility.NEW_LINE + "#\t" + i);
				moveFromIRRegisterToPhysicalRegister(i.push, "$a0");
				out.println("\tli $v0, 9");
				out.println("\tsyscall");
			} else if (funcUId == Environment.uIdOfGetChar) {
				out.println("\tli $v0, 12");
				out.println("\tsyscall");
			} else if (funcUId == Environment.uIdOfPutChar) {
				PushStack i = (PushStack) internal.get(positionOfCall - 1);
				moveFromIRRegisterToPhysicalRegister(i.push, "$a0");
				out.println("\tli $v0, 11");
				out.println("\tsyscall");
			} else throw new CompilationError("Internal Error.");
		} else {
			FunctionTableEntry caller = graph.functionTableEntry;
			FunctionTableEntry callee = Environment.functionTable.get(funcUId);
//			HashSet<MachineRegister> bothUsedRegister = new Utility.SetOperation<MachineRegister>().intersect(caller.allocator.physicalRegistersUsed, callee.allocator.physicalRegistersUsed);

			int sizeOfAllArguments = 0;
			int sizeOfExtraArguments = 0;

//			Stack<Integer> parametersUId = new Stack<>();
//			callee.scope.parametersUId.forEach(parametersUId::push);
			for (int itr = beginPosition; itr < positionOfCall; ++itr) {
				PushStack i = (PushStack) internal.get(itr);
//				int pUId = i.isExtra ? -1 : parametersUId.pop();
				int size = i.pushType.sizeof();
				sizeOfAllArguments += Math.max(Panel.getRegisterSize(), size);
				if (i.isExtra)
					sizeOfExtraArguments += Math.max(4, size);
				if (i.pushType instanceof StructOrUnionType) {
					int registerSize = Panel.getRegisterSize();
					moveFromIRRegisterToPhysicalRegister(i.push, MachineRegister.tmp1.name);
					for (int j = 0; j < size; j += registerSize) {
						out.printf("\tlw %s, %d(%s)%s", MachineRegister.tmp2, j, MachineRegister.tmp1, Utility.NEW_LINE);
						out.printf("\tsw %s, -%d($sp)%s", MachineRegister.tmp2, sizeOfAllArguments - j, Utility.NEW_LINE);
					}
				} /*else if (pUId == -1 || callee.allocator.mapping.get(pUId) == null) { // spilled
					String insName = size == 1 ? "sb" : "sw";
					MachineRegister pPush = getRegisterToRead(i.push, MachineRegister.tmp1);
					out.printf("\t%s %s, -%d($sp)%s", insName, pPush, sizeOfAllArguments, Utility.NEW_LINE);
				} else {
					MachineRegister to = callee.allocator.mapping.get(pUId);
					moveFromIRRegisterToPhysicalRegister(i.push, to.name);
				}*/ else {
					String insName = size == 1 ? "sb" : "sw";
					MachineRegister pPush = getRegisterToRead(i.push, MachineRegister.tmp1);
					out.printf("\t%s %s, -%d($sp)%s", insName, pPush, sizeOfAllArguments, Utility.NEW_LINE);
				}
			}

			out.printf("\taddu $sp, $sp, -%d%s", callee.cfg.frameSize + sizeOfExtraArguments, Utility.NEW_LINE);
			out.printf("\tjal %s%s", getFunctionLabelName(funcUId), Utility.NEW_LINE);
			out.printf("\taddu $sp, $sp, %d%s", callee.cfg.frameSize + sizeOfExtraArguments, Utility.NEW_LINE);

		}
	}

}
