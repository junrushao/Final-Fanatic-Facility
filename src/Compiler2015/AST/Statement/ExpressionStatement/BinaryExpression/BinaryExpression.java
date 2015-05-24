package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.IR.IRRegister.VirtualRegister;

import java.util.HashMap;

public abstract class BinaryExpression extends Expression {
	public Expression left, right;

	public BinaryExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
		this.type = left.type;
		this.isLValue = false;
	}

	public abstract String getOperator();

	@Override
	public String toString() {
		return String.format("(%s %s %s)", getOperator(), left.toString(), right.toString());
	}

	@Override
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {
		left.collectGlobalNonArrayVariablesUsed(dumpTo);
		right.collectGlobalNonArrayVariablesUsed(dumpTo);
	}

	@Override
	public BinaryExpression clone() {
		BinaryExpression ret = (BinaryExpression) super.clone();
		ret.left = ret.left.clone();
		ret.right = ret.right.clone();
		return ret;
	}
}
