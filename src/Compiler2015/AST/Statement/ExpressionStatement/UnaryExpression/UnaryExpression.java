package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.Type;

public class UnaryExpression extends Expression {
	public Expression e;
	public UnaryExpression(Expression e, Type type, boolean isLValue) {
		super(type, isLValue);
		this.e = e;
	}
}
