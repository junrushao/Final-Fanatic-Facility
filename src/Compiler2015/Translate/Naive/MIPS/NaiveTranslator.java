package Compiler2015.Translate.Naive.MIPS;

import Compiler2015.AST.Initializer;
import Compiler2015.AST.Statement.ExpressionStatement.Constant;
import Compiler2015.AST.Statement.ExpressionStatement.IdentifierExpression;
import Compiler2015.AST.Statement.ExpressionStatement.StringConstant;
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
import Compiler2015.Type.*;
import Compiler2015.Utility.Panel;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class NaiveTranslator {

	public ControlFlowGraph graph;

	public NaiveTranslator(ControlFlowGraph graph) {
		this.graph = graph;
	}

	public static String getGlobalVariableLabel(int uId) {
		return String.format("___global___uId_%d___name_%s:", uId, Environment.symbolNames.table.get(uId).name);
	}

	public static String getGlobalVariableLabelName(int uId) {
		return String.format("___global___uId_%d___name_%s", uId, Environment.symbolNames.table.get(uId).name);
	}

	public static String getStringConstantLabel(int uId) {
		return String.format("___global___uId_%d:", uId);
	}

	public static String getStringConstantLabelName(int uId) {
		return String.format("___global___uId_%d", uId);
	}

	public static void generateGlobalVariables(PrintWriter out) {
		out.println(".data");
		for (SymbolTableEntry entry : Environment.symbolNames.table) {
			if (entry == null)
				continue;
			if (entry.scope == 1 && entry.type == Tokens.STRING_CONSTANT) {
				out.println(getStringConstantLabel(entry.uId));
				out.printf("\t.asciiz \"%s\"%s", StringConstant.toMIPSString((String) entry.ref), Utility.NEW_LINE);
				out.println("\t.align 2");
			}
			if (entry.scope != 1 || entry.type != Tokens.VARIABLE || entry.ref instanceof FunctionType)
				continue;
			Type type = (Type) entry.ref;
			int uId = entry.uId;
			if (type instanceof ArrayPointerType) {
				Type pointTo = ((ArrayPointerType) type).pointTo;
				if (entry.info == null) {
					out.println(getGlobalVariableLabel(uId));
					out.printf("\t.space %d", type.sizeof());
					out.println();
					out.println("\t.align 2");
				} else {
					class ValuePairs {
						int position;
						Integer value;
						Integer refUId;

						public ValuePairs(int position, Integer value, Integer refUId) {
							this.position = position;
							this.value = value;
							this.refUId = refUId;
						}
					}

					ArrayPointerType t = (ArrayPointerType) type;
					ArrayList<ValuePairs> pairs = new ArrayList<>();
					for (Initializer.InitEntry _entry : ((Initializer) entry.info).entries) {
						int pos = 0, mul = 1;
						for (int i = _entry.position.length - 1; i >= 0; --i) {
							pos += _entry.position[i] * mul;
							mul *= t.dimensions.get(i);
						}
//						if (pointTo instanceof VariablePointerType && ((VariablePointerType) pointTo).pointTo instanceof CharType)
						Integer value = Constant.toInt(_entry.value);
						Integer refUId = null;
						if (value == null) {
							if (_entry.value instanceof StringConstant)
								refUId = ((StringConstant) _entry.value).uId;
							else
								throw new CompilationError("Not supported.");
						}
						pairs.add(new ValuePairs(pos, value, refUId));
					}
					pairs.sort((o1, o2) -> o1.position - o2.position);

					out.println(getGlobalVariableLabel(uId));
					int size = type.sizeof() / pointTo.sizeof();
					String prefix;
					if (pointTo.sizeof() == 1)
						prefix = "\t.byte ";
					else if (pointTo.sizeof() == 4)
						prefix = "\t.word ";
					else
						throw new CompilationError("Internal Error.");
					int pointer = 0;
					for (int i = 0; i < size; ++i) {
						if (pointer < pairs.size() && pairs.get(pointer).position == i) {
							if (pairs.get(pointer).value != null) {
								out.printf("%s %d", prefix, pairs.get(pointer).value);
								out.println();
								out.println("\t.align 2");
								++pointer;
							} else {
								out.printf("\t.word %s", getStringConstantLabelName(pairs.get(pointer).refUId));
								out.println();
								out.println("\t.align 2");
								++pointer;
							}
						} else {
							out.printf("%s 0", prefix);
							out.println();
							out.println("\t.align 2");
						}
					}
				}
			} else if (type instanceof CharType) {
				out.println(getGlobalVariableLabel(uId));
				Integer value = entry.info == null ? Integer.valueOf(0) : Constant.toInt(((Initializer) entry.info).entries.get(0).value);
				out.printf("\t.byte %d", value);
				out.println();
				out.println("\t.align 2");
			} else if (type instanceof FunctionPointerType) {
				out.println(getGlobalVariableLabel(uId));
				out.printf("\t.word %d", entry.info == null ? 0 :
						Constant.toInt(((Initializer) entry.info).entries.get(0).value));
				out.println();
				out.println("\t.align 2");
			} else if (type instanceof IntType) {
				out.println(getGlobalVariableLabel(uId));
				out.printf("\t.word %d", entry.info == null ? 0 :
						Constant.toInt(((Initializer) entry.info).entries.get(0).value));
				out.println();
				out.println("\t.align 2");
			} else if (type instanceof StructOrUnionType) {
				out.println(getGlobalVariableLabel(uId));
				out.printf("\t.space %d", type.sizeof());
				out.println();
				out.println("\t.align 2");
			} else if (type instanceof VariablePointerType) {
				if (type.equals(new VariablePointerType(CharType.instance)) && entry.info != null && ((Initializer) entry.info).entries.get(0).value instanceof StringConstant) {
					StringConstant sc = ((StringConstant) ((Initializer) entry.info).entries.get(0).value);
					out.println(getGlobalVariableLabel(uId));
					out.printf("\t.word %s", getStringConstantLabelName(sc.uId));
					out.println();
					out.println("\t.align 2");
				} else {
					out.println(getGlobalVariableLabel(uId));
					Integer value = entry.info == null ? Integer.valueOf(0) : Constant.toInt(((Initializer) entry.info).entries.get(0).value);
					if (value != null)
						out.printf("\t.word %d", value);
					else if (((Initializer) entry.info).entries.get(0).value instanceof IdentifierExpression) {
						out.printf("\t.word %s", getGlobalVariableLabelName(((IdentifierExpression) ((Initializer) entry.info).entries.get(0).value).uId));
					} else
						throw new CompilationError("Not supported now.");
					out.println();
					out.println("\t.align 2");
				}
			} else
				throw new CompilationError("Internal Error.");
		}
		out.println("new_line:\n" +
				"\t.asciiz \"____\\n\"\n");
		out.println("\t.align 2");
	}

	public static void generateLibraryFunction(int uId, PrintWriter out) {
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

	public String getFunctionLabel() {
		return String.format("___global___uId_%d___name_%s:",
				graph.functionTableEntry.uId, Environment.symbolNames.table.get(graph.functionTableEntry.uId).name);
	}

	public String getFunctionLabelName(int uId) {
		return String.format("___global___uId_%d___name_%s",
				uId, Environment.symbolNames.table.get(uId).name);
	}

	public String getVertexLabel(int id) {
		if (id < 0)
			return String.format("___function___uId_%d___vertex_neg_%d:", graph.functionTableEntry.uId, -id);
		return String.format("___function___uId_%d___vertex_%d:", graph.functionTableEntry.uId, id);
	}

	public String getVertexLabelName(int id) {
		if (id < 0)
			return String.format("___function___uId_%d___vertex_neg_%d", graph.functionTableEntry.uId, -id);
		return String.format("___function___uId_%d___vertex_%d", graph.functionTableEntry.uId, id);
	}

	public int getDelta(int uId) {
		if (graph.tempDelta.containsKey(uId))
			return graph.tempDelta.get(uId);
		if (graph.parameterDelta.containsKey(uId))
			return graph.parameterDelta.get(uId);
		throw new CompilationError("Internal Error.");
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
				out.printf("\tla $v1, %s%s", getGlobalVariableLabelName(uId), Utility.NEW_LINE);
				if (!(e.ref instanceof FunctionType || e.ref instanceof ArrayPointerType || e.ref instanceof StructOrUnionType))
					out.printf("\tsw %s, 0($v1)%s", reg, Utility.NEW_LINE);
				else
					throw new CompilationError("Internal Error.");
			} else
				throw new CompilationError("Internal Error.");
		} else
			out.printf("\tsw %s, %d($sp)%s", reg, getDelta(uId), Utility.NEW_LINE);
	}

	public void generateFunction(PrintWriter out) {
		scanVirtualRegister(); // calculate space of stack frame

		if (Environment.symbolNames.table.get(graph.functionTableEntry.uId).name.equals("main"))
			out.println("main:");

		out.println(getFunctionLabel());
		out.println("\taddu $sp, $sp, " + (-graph.frameSize));
		out.println("\tsw $ra, 4($sp)");

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
				if (ins instanceof Call) {
					if (!(((Call) ins).func instanceof VirtualRegister))
						throw new CompilationError("Internal Error.");
					VirtualRegister func = (VirtualRegister) ((Call) ins).func;
					if (Environment.isLibraryFunctions(func.uId))
						generateLibraryFunction(func.uId, out);
					else {
						if (sizeOfExtraArguments != 0)
							out.printf("\taddiu $sp, $sp, -%d%s", sizeOfExtraArguments, Utility.NEW_LINE);
						if (Environment.symbolNames.table.get(func.uId).type == Tokens.VARIABLE) {
							out.printf("\tjal %s%s", getFunctionLabelName(func.uId), Utility.NEW_LINE);
						} else {
							loadFromIRRegisterToTRegister(func, 0, out);
							out.printf("\tjal $t0%s", Utility.NEW_LINE);
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
				} else if (ins instanceof AddReg) {
					loadFromIRRegisterToTRegister(((AddReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((AddReg) ins).rt, 1, out);
					out.println("\taddu $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof BitwiseAndReg) {
					loadFromIRRegisterToTRegister(((BitwiseAndReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((BitwiseAndReg) ins).rt, 1, out);
					out.println("\tand $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof BitwiseNotReg) {
					loadFromIRRegisterToTRegister(((BitwiseNotReg) ins).rs, 0, out);
					out.println("\tnot $t1, $t0");
					storeFromTRegisterToIRRegister(1, ins.getAllDefUId()[0], out);
				} else if (ins instanceof BitwiseOrReg) {
					loadFromIRRegisterToTRegister(((BitwiseOrReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((BitwiseOrReg) ins).rt, 1, out);
					out.println("\tor $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof BitwiseXORReg) {
					loadFromIRRegisterToTRegister(((BitwiseXORReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((BitwiseXORReg) ins).rt, 1, out);
					out.println("\txor $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof DivideReg) {
					loadFromIRRegisterToTRegister(((DivideReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((DivideReg) ins).rt, 1, out);
					out.println("\tdiv $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof ModuloReg) {
					loadFromIRRegisterToTRegister(((ModuloReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((ModuloReg) ins).rt, 1, out);
					out.println("\trem $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof MultiplyReg) {
					loadFromIRRegisterToTRegister(((MultiplyReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((MultiplyReg) ins).rt, 1, out);
					out.println("\tmul $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof NegateReg) {
					loadFromIRRegisterToTRegister(((NegateReg) ins).rs, 0, out);
					out.println("\tneg $t1, $t0");
					storeFromTRegisterToIRRegister(1, ins.getAllDefUId()[0], out);
				} else if (ins instanceof SetEqualTo) {
					loadFromIRRegisterToTRegister(((SetEqualTo) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((SetEqualTo) ins).rt, 1, out);
					out.println("\tseq $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof SetGE) {
					loadFromIRRegisterToTRegister(((SetGE) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((SetGE) ins).rt, 1, out);
					out.println("\tsge $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof SetGreaterThan) {
					loadFromIRRegisterToTRegister(((SetGreaterThan) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((SetGreaterThan) ins).rt, 1, out);
					out.println("\tsgt $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof SetLE) {
					loadFromIRRegisterToTRegister(((SetLE) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((SetLE) ins).rt, 1, out);
					out.println("\tsle $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof SetLessThan) {
					loadFromIRRegisterToTRegister(((SetLessThan) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((SetLessThan) ins).rt, 1, out);
					out.println("\tslt $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof SetNotEqual) {
					loadFromIRRegisterToTRegister(((SetNotEqual) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((SetNotEqual) ins).rt, 1, out);
					out.println("\tsne $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof ShiftLeftReg) {
					loadFromIRRegisterToTRegister(((ShiftLeftReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((ShiftLeftReg) ins).rt, 1, out);
					out.println("\tsll $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof ShiftRightReg) { // sra
					loadFromIRRegisterToTRegister(((ShiftRightReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((ShiftRightReg) ins).rt, 1, out);
					out.println("\tsra $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
				} else if (ins instanceof SubtractReg) {
					loadFromIRRegisterToTRegister(((SubtractReg) ins).rs, 0, out);
					loadFromIRRegisterToTRegister(((SubtractReg) ins).rt, 1, out);
					out.println("\tsubu $t2, $t0, $t1");
					storeFromTRegisterToIRRegister(2, ins.getAllDefUId()[0], out);
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
		out.println("\tlw $ra, 4($sp)");
		out.println("\taddu $sp, $sp, " + (graph.frameSize));
		out.println("\tjr $ra");
	}

	public void scanVirtualRegister() {
		graph.tempDelta = new HashMap<>();
		graph.parameterDelta = new HashMap<>();
		for (CFGVertex vertex : graph.vertices) {
			for (IRInstruction ins : vertex.internal) {
				for (int uId : ins.getAllDefUId())
					classifyVirtualRegister(uId);
				for (int uId : ins.getAllUseUId())
					classifyVirtualRegister(uId);
			}
		}
		graph.frameSize = Panel.getRegisterSize() * 2; // [0, 3] for $fp, [4, 7] for $ra
		for (Map.Entry<Integer, Integer> entry : graph.tempDelta.entrySet()) {
			entry.setValue(graph.frameSize);
			int uId = entry.getKey();
			SymbolTableEntry e = Environment.symbolNames.table.get(uId);
			if (e.type == Tokens.VARIABLE)
				graph.frameSize += Utility.align(((Type) e.ref).sizeof());
			else if (e.type == Tokens.TEMPORARY_REGISTER)
				graph.frameSize += Panel.getRegisterSize();
			else
				throw new CompilationError("Internal Error.");
		}
		graph.frameSize += Utility.align(graph.functionTableEntry.definition.returnType.sizeof());
		for (int uId : graph.functionTableEntry.scope.parametersUId) {
			graph.parameterDelta.put(uId, graph.frameSize);
			graph.frameSize += Utility.align(((Type) Environment.symbolNames.table.get(uId).ref).sizeof());
		}
	}

	public void classifyVirtualRegister(int uId) {
		if (uId == -1 || uId == 0 || uId == -2 || uId == -3)
			return;
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e.scope <= 1)
			return;
		if (!graph.functionTableEntry.scope.parametersUId.contains(uId))
			graph.tempDelta.put(uId, -1);
	}
}
