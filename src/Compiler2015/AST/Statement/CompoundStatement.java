package Compiler2015.AST.Statement;

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
}
