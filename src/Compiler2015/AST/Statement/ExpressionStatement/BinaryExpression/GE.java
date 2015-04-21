package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.AST.Type.IntType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Exception.CompilationError;

/**
 * a >= b
 */
public class GE extends BinaryExpression {
	public GE(Expression left, Expression right) {
		super(left, right);
		this.type = new IntType();
	}

	@Override
	public String getOperator() {
		return ">=";
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError(">= must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(v1 >= v2 ? 1 : 0);
		if (!(a1.type instanceof IntType))
			a1 = new CastExpression(new IntType(), a1);
		if (!(a2.type instanceof IntType))
			a2 = new CastExpression(new IntType(), a2);
		return new GE(a1, a2);
	}
}
