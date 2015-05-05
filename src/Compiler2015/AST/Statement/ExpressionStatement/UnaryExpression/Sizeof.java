package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.Type.IntType;

public class Sizeof extends UnaryExpression {
	public Sizeof(Expression e) {
		super(e);
		this.type = new IntType();
	}

	@Override
	public String getOperator() {
		return "Sizeof";
	}

	@Override
	public void emitCFG(CFGVertex fromHere) {
		// TODO
	}
}
