package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.Type;

public class BinaryExpression extends Expression {
	public Expression left, right;
	BinaryExpression(Expression left, Expression right, Type type, boolean isLValue) {
		super(type, isLValue);
		this.left = left;
		this.right = right;
	}
}
