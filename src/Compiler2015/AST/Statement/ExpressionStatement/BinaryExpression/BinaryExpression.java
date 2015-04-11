package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * Created by junrushao on 15-4-11.
 */
public class BinaryExpression extends Expression {
	Expression left, right;
	BinaryExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
}
