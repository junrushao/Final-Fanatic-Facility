package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * Created by junrushao on 15-4-11.
 *
 * ++e
 */
public class PrefixSelfInc extends UnaryExpression {
	public PrefixSelfInc(Expression e) {
		super(e);
	}
}
