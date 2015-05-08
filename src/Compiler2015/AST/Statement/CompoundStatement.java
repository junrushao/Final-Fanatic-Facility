package Compiler2015.AST.Statement;

import Compiler2015.AST.Initializer;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.IR.Instruction.WriteArray;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.FunctionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;

/**
 * { ... }
 */
public class CompoundStatement extends Statement {
	public ArrayList<Integer> variables;
	public ArrayList<Statement> statements;

	public CompoundStatement(ArrayList<Integer> variables, ArrayList<Statement> statements) {
		this.variables = variables;
		this.statements = statements;
		Environment.definedVariableInCurrentFrame.addAll(variables);
	}

	public void youAreAFrame(int currentScope) {
		int last = 0;
		while (!Environment.definedVariableInCurrentFrame.isEmpty()) {
			int uId = Environment.definedVariableInCurrentFrame.peek();
			SymbolTableEntry e = Environment.symbolNames.table.get(uId);
			if (e.scope < currentScope)
				break;
			Environment.definedVariableInCurrentFrame.pop();
			if (e.ref instanceof FunctionType)
				continue;
			Environment.variableDelta.put(uId, last);
			last += ((Type) e.ref).sizeof();
		}
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("{").append(Utility.NEW_LINE);
		StringBuilder indent = Utility.getIndent(depth + 1);
		for (int x : variables) {
			SymbolTableEntry e = Environment.symbolNames.table.get(x);
			Type t = (Type) e.ref;
			String name = e.name;
			sb.append(indent).append(String.format("Variable(#%d, %s, %s)", x, t.toString(), name));

			if (e.info != null) {
				if (e.ref instanceof FunctionType)
					sb.append(Utility.NEW_LINE).append(((CompoundStatement) e.info).deepToString(depth + 1));
				else
					sb.append(" init = ").append(e.info.toString());
			}
			if (e.info == null || !(e.ref instanceof FunctionType))
				sb.append(Utility.NEW_LINE);
		}
		for (Statement s : statements)
			sb.append(s.deepToString(depth + 1));
		sb.append(Utility.getIndent(depth)).append("}").append(Utility.NEW_LINE);
		return sb.toString();
	}

	@Override
	public String toString() {
		return deepToString(0);
	}

	@Override
	public void emitCFG() {
		ExpressionCFGBuilder builder = new ExpressionCFGBuilder();
		// initialize
		for (int x : variables) {
			SymbolTableEntry e = Environment.symbolNames.table.get(x);
			Type tp = (Type) e.ref;
			if (tp instanceof FunctionType) // functions are processed in global scope
				continue;
			if (e.info == null)
				continue;
			Initializer init = (Initializer) e.info;
			if (init.entries == null)
				continue;
			for (Initializer.InitEntry entry : init.entries) {
				VirtualRegister rx = new VirtualRegister(x);
				if (entry.position.length == 0) { // single variable
					entry.value.emitCFG(builder);
					entry.value.eliminateArrayRegister(builder);
					if (entry.value.tempRegister instanceof ArrayRegister) {
						builder.addInstruction(new ReadArray(rx, (ArrayRegister) entry.value.tempRegister));
					} else {
						builder.addInstruction(new Move(rx, entry.value.tempRegister));
					}
				}
				else {
					ArrayPointerType t = (ArrayPointerType) tp;
					int pos = 0, mul = 1;
					for (int i = entry.position.length - 1; i >= 0; --i) {
						pos += entry.position[i] * mul;
						mul *= t.dimensions.get(i);
					}
					entry.value.emitCFG(builder);
					entry.value.eliminateArrayRegister(builder);
					builder.addInstruction(new WriteArray(new ArrayRegister(rx, new ImmediateValue(pos)), entry.value.tempRegister));
				}
			}
		}
		// statement
		for (Statement statement : statements) {
			statement.emitCFG();
			builder.addBlock(statement.beginCFGBlock, statement.endCFGBlock);
		}
		beginCFGBlock = builder.s;
		endCFGBlock = builder.t;
	}
}
