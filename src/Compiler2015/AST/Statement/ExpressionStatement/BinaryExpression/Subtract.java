package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;

/**
 * a - b
 */
public class Subtract extends BinaryExpression {
	public Subtract(Expression left, Expression right, Type type) {
		super(left, right);
		this.type = type;
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate add on void type.");
		if (a1.type instanceof StructOrUnionType || a2.type instanceof StructOrUnionType)
			throw new CompilationError("Cannot operator on struct / union.");
		if (a1.type instanceof CharType) {
			Integer v = Expression.toInt(a1);
			a1 = v != null ? new IntConstant(v) : new CastExpression(new IntType(), a1);
		}
		if (a2.type instanceof CharType) {
			Integer v = Expression.toInt(a2);
			a2 = v != null ? new IntConstant(v) : new CastExpression(new IntType(), a2);
		}
		if (a1.type instanceof ArrayPointerType) {
			if (a2.type instanceof FunctionPointerType || a2.type instanceof FunctionType)
				throw new CompilationError("Incompatible types.");
			if (a2.type instanceof ArrayPointerType)
				return new Subtract(a1, a2, new IntType());
			if (a2.type instanceof VariablePointerType)
				return new Subtract(a1, a2, new IntType());
			if (a2.type instanceof IntType)
				return new Subtract(a1, a2, ((ArrayPointerType) a1.type).toVariablePointerType());
			throw new CompilationError("Internal Error.");
		}
		if (a1.type instanceof FunctionPointerType) {
			if (a2.type instanceof ArrayPointerType || a2.type instanceof VariablePointerType)
				throw new CompilationError("Incompatible types.");
			if (a2.type instanceof FunctionPointerType || a2.type instanceof FunctionType)
				return new Subtract(a1, a2, new IntType());
			if (a2.type instanceof IntType)
				return new Subtract(a1, a2, a1.type);
			throw new CompilationError("Internal Error.");
		}
		if (a1.type instanceof VariablePointerType) {
			if (a2.type instanceof FunctionPointerType || a2.type instanceof FunctionType)
				throw new CompilationError("Incompatible types.");
			if (a2.type instanceof ArrayPointerType)
				return new Subtract(a1, a2, new IntType());
			if (a2.type instanceof VariablePointerType)
				return new Subtract(a1, a2, new IntType());
			if (a2.type instanceof IntType)
				return new Subtract(a1, a2, a1.type);
			throw new CompilationError("Internal Error.");
		}
		if (a1.type instanceof IntType) {
			if (a2.type instanceof VariablePointerType || a2.type instanceof ArrayPointerType || a2.type instanceof FunctionPointerType || a2.type instanceof FunctionType)
				throw new CompilationError("Incompatible types.");
			if (a2.type instanceof IntType) {
				Integer v1 = Expression.toInt(a1);
				Integer v2 = Expression.toInt(a2);
				if (v1 != null && v2 != null)
					return new IntConstant(v1 - v2);
				else
					return new Subtract(a1, a2, new IntType());
			}
			throw new CompilationError("Internal Error.");
		}
		throw new CompilationError("Internal Error.");
	}

	@Override
	public String getOperator() {
		return "-";
	}
}
