package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-11.
 *
 * a *= b
 */
public class MultiplyAssign extends BinaryExpression {
	public MultiplyAssign(Expression left, Expression right, Type type, boolean isLValue) {
		super(left, right, type, isLValue);
	}
}
