package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;

/**
 * a + b
 */
public class Add extends BinaryExpression {
	public Add(Expression left, Expression right, Type type) {
		super(left, right);
		this.type = type;
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate add on void type.");
		if (a1.type instanceof StructOrUnionType || a2.type instanceof StructOrUnionType)
			throw new CompilationError("Cannot operator on struct / union.");
		if (!Type.isNumeric(a1.type) && !Type.isNumeric(a2.type))
			throw new CompilationError("Incompatible types");
		if (a1.type instanceof CharType) {
			Integer v = Expression.toInt(a1);
			a1 = v != null ? new IntConstant(v) : new CastExpression(new IntType(), a1);
		}
		if (a2.type instanceof CharType) {
			Integer v = Expression.toInt(a2);
			a1 = v != null ? new IntConstant(v) : new CastExpression(new IntType(), a2);
		}
		if (a1.type instanceof IntType && a2.type instanceof IntType) {
			Integer v1 = Expression.toInt(a1), v2 = Expression.toInt(a2);
			if (v1 != null && v2 != null)
				return new IntConstant(v1 + v2);
			return new Add(a1, a2, new IntType());
		}
		return new Add(a1, a2, a1.type instanceof IntType ? a2.type : a1.type);
	}

	@Override
	public String getOperator() {
		return "+";
	}
}
