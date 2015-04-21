package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Utility.Utility;

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
	public String toString(int depth) {
		return Utility.getIndent(depth).append(getOperator()).append(Utility.NEW_LINE).append(left.toString(depth + 1)).append(right.toString(depth + 1)).toString();
	}
}
