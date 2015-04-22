package Compiler2015.AST.Statement;

import Compiler2015.AST.Type.Type;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
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
	public String toString(int depth) {
		StringBuilder sb = Utility.getIndent(depth);
		StringBuilder indent = Utility.getIndent(depth + 1);
		for (int x : variables) {
			SymbolTableEntry e = Environment.symbolNames.table.get(x);
			Type t = (Type) e.ref;
			String name = e.name;
			sb.append(indent).append(String.format("uId = %d, type = %s, name = %s", x, t.toString(), name)).append(Utility.NEW_LINE);
		}
		statements.forEach(sb::append);
		return sb.toString();
	}
}
