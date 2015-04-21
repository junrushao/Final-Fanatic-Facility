package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Utility.Utility;

public abstract class UnaryExpression extends Expression {
	public Expression e;

	public UnaryExpression(Expression e) {
		this.e = e;
	}

	public abstract String getOperator();

	@Override
	public String toString(int depth) {
		return Utility.getIndent(depth).append(getOperator()).append(Utility.NEW_LINE).append(e.toString(depth + 1)).toString();
	}
}
