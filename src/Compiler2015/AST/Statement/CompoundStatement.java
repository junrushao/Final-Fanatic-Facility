package Compiler2015.AST.Statement;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
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
			if (e.info != null)
				sb.append("  init = ").append(e.info.toString());
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
}
