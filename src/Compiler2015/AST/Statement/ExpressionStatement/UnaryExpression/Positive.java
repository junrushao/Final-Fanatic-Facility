package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.Type;

/**
 * +e
 */
public class Positive extends UnaryExpression {
	public Positive(Expression e, Type type, boolean isLValue) {
		super(e, type, isLValue);
	}
}
