package Compiler2015.Translate.Naive.MIPS;

import Compiler2015.AST.Initializer;
import Compiler2015.AST.Statement.ExpressionStatement.Constant;
import Compiler2015.AST.Statement.ExpressionStatement.IdentifierExpression;
import Compiler2015.AST.Statement.ExpressionStatement.StringConstant;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Type.*;
import Compiler2015.Utility.Tokens;

import java.io.PrintWriter;
import java.util.ArrayList;

public final class Translator {

	public static String getFunctionLabel() {
		return String.format("___function___uId_%d___name_%s:",
				ControlFlowGraph.nowUId,
				Environment.symbolNames.table.get(ControlFlowGraph.nowUId).name);
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

	public static String getVertexLabel(int id) {
		return String.format("___function___uId_%d___vertex_%d: ", ControlFlowGraph.nowUId, id);
	}

	public static void generateGlobalVariables(PrintWriter out) {
		out.println(".data");
		for (SymbolTableEntry entry : Environment.symbolNames.table) {
			if (entry == null)
				continue;
			if (entry.scope == 1 && entry.type == Tokens.STRING_CONSTANT) {
				out.println(getStringConstantLabel(entry.uId));
				out.printf("\t.ascii \"%s\"", StringConstant.toPrintableString((String) entry.ref));
				out.println();
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
				} else {
					class ValuePairs {
						int position, value;

						public ValuePairs(int position, int value) {
							this.position = position;
							this.value = value;
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
						pairs.add(new ValuePairs(pos, Constant.toInt(_entry.value)));
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
							out.printf("%s %d", prefix, pairs.get(pointer).value);
							out.println();
							++pointer;
						} else {
							out.printf("%s 0", prefix);
							out.println();
						}
					}
				}
			} else if (type instanceof CharType) {
				out.println(getGlobalVariableLabel(uId));
				Integer value = entry.info == null ? Integer.valueOf(0) : Constant.toInt(((Initializer) entry.info).entries.get(0).value);
				out.printf("\t.byte %d", value);
				out.println();
			} else if (type instanceof FunctionPointerType) {
				out.println(getGlobalVariableLabel(uId));
				out.printf("\t.word %d", entry.info == null ? 0 :
						Constant.toInt(((Initializer) entry.info).entries.get(0).value));
				out.println();
			} else if (type instanceof IntType) {
				out.println(getGlobalVariableLabel(uId));
				out.printf("\t.word %d", entry.info == null ? 0 :
						Constant.toInt(((Initializer) entry.info).entries.get(0).value));
				out.println();
			} else if (type instanceof StructOrUnionType) {
				out.println(getGlobalVariableLabel(uId));
				out.printf("\t.space %d", type.sizeof());
				out.println();
			} else if (type instanceof VariablePointerType) {
				if (type.equals(new VariablePointerType(CharType.instance)) && entry.info != null && ((Initializer) entry.info).entries.get(0).value instanceof StringConstant) {
					StringConstant sc = ((StringConstant) ((Initializer) entry.info).entries.get(0).value);
					out.println(getGlobalVariableLabel(uId));
					out.printf("\t.word %s", getStringConstantLabelName(sc.uId));
					out.println();
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
				}
			} else
				throw new CompilationError("Internal Error.");
		}
	}

	public static void generateFunction(PrintWriter out) {

	}
}
