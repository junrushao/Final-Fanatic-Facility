package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.IR.IRRegister.VirtualRegister;

import java.util.HashMap;

public abstract class UnaryExpression extends Expression {
	public Expression e;

	public UnaryExpression(Expression e) {
		this.e = e;
	}

	public abstract String getOperator();

	@Override
	public String toString() {
		return String.format("(%s %s)", getOperator(), e.toString());
	}

	@Override
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {
		e.collectGlobalNonArrayVariablesUsed(dumpTo);
	}
}
