package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.AST.Type.IntType;

/**
 * a == b
 */
public class EqualTo extends BinaryExpression {
	public EqualTo(Expression left, Expression right) {
		super(left, right);
		this.type = new IntType();
	}

	@Override
	public String getOperator() {
		return "==";
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		a1 = CastExpression.castToNumeric(a1);
		a2 = CastExpression.castToNumeric(a2);

		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(v1.equals(v2) ? 1 : 0);
		if (!(a1.type instanceof IntType))
			a1 = new CastExpression(new IntType(), a1);
		if (!(a2.type instanceof IntType))
			a2 = new CastExpression(new IntType(), a2);
		return new EqualTo(a1, a2);
	}
}
