package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * Created by junrushao on 15-4-11.
 *
 * a &= b
 */
public class BitwiseAndAssign extends BinaryExpression {
	public BitwiseAndAssign(Expression left, Expression right) {
		super(left, right);
	}

}
