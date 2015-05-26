package Compiler2015.AST.Statement;

import Compiler2015.AST.Initializer;
import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.ArrayAccess;
import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.Assign;
import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IdentifierExpression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.FunctionType;
import Compiler2015.Type.Type;
import Compiler2015.Type.VariablePointerType;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * { ... }
 */
public class CompoundStatement extends Statement {
	public ArrayList<Integer> variables;
	public ArrayList<Statement> statements;
	public ArrayList<Integer> givenVariables;

	public ArrayList<Integer> parametersUId;

	public CompoundStatement(ArrayList<Integer> variables, ArrayList<Statement> statements, ArrayList<Integer> givenVariables, ArrayList<Integer> parametersUId) {
		this.variables = variables;
		this.statements = statements;
		this.givenVariables = givenVariables;
		this.parametersUId = parametersUId;
		addInitializers();
		Environment.definedVariableInCurrentFrame.addAll(variables);
	}

	public void youAreAFrame(int currentScope) {
		while (!Environment.definedVariableInCurrentFrame.isEmpty()) {
			int uId = Environment.definedVariableInCurrentFrame.peek();
			SymbolTableEntry e = Environment.symbolNames.table.get(uId);
			if (e.scope < currentScope)
				break;
			Environment.definedVariableInCurrentFrame.pop();
			givenVariables.add(uId);
		}
	}

	public void addInitializers() {
		ArrayList<Statement> newStatements = new ArrayList<>();
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
				if (entry.position.length == 0) { // single variable
					newStatements.add(Assign.getExpression(IdentifierExpression.getExpression(x), entry.value, "="));
				} else {
					ArrayPointerType t = (ArrayPointerType) tp;
					int pos = 0, mul = 1;
					for (int i = entry.position.length - 1; i >= 0; --i) {
						pos += entry.position[i] * mul;
						mul *= t.dimensions.get(i);
					}
					// ( (Type *) x)[pos] = value;
					Type toType = new VariablePointerType(t.pointTo);
					Expression xx = CastExpression.getExpression(toType, IdentifierExpression.getExpression(x));
					Expression ll = ArrayAccess.getExpression(xx, new IntConstant(pos));
					Expression ww = Assign.getExpression(ll, entry.value, "=");
					newStatements.add(ww);
				}
			}
			e.info = null;
		}
		newStatements.addAll(statements);
		statements = newStatements;
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
		for (Statement statement : statements) {
			statement.emitCFG();
			builder.addBlock(statement.beginCFGBlock, statement.endCFGBlock);
		}
		beginCFGBlock = builder.s;
		endCFGBlock = builder.t;
	}

	@Override
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {
		for (Statement s : statements)
			s.collectGlobalNonArrayVariablesUsed(dumpTo);
	}

	@Override
	public Statement rebuild() {
		for (int i = 0, size = statements.size(); i < size; ++i)
			statements.set(i, statements.get(i).rebuild());
		return this;
	}
}
