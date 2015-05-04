package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;

/**
 * a = b
 */
public class Assign extends BinaryExpression {
	public Assign(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String getOperator() {
		return "=";
	}

	public static Expression getExpression(Expression a1, Expression a2, String operator) {
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");

		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");

		if (a1.type instanceof IntType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof CharType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof StructOrUnionType) {
			if (a2.type instanceof IntType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof CharType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof FunctionType) {
			if (a2.type instanceof IntType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof CharType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof FunctionPointerType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof ArrayPointerType) {
			if (a2.type instanceof IntType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof CharType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof VariablePointerType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}

		if ((a1.type instanceof StructOrUnionType) != (a2.type instanceof StructOrUnionType))
			throw new CompilationError("Incompatible type.");
		if (a1.type instanceof StructOrUnionType && (!operator.equals("=") || !a1.type.equals(a2.type)))
			throw new CompilationError("Incompatible type.");

		if (!(a1.type instanceof StructOrUnionType || a2.type instanceof StructOrUnionType)
				&& (!CastExpression.castable(a1.type, new IntType()) || !CastExpression.castable(a2.type, new IntType())))
			throw new CompilationError("Incompatible type.");

		if (operator.equals("="))
			return new Assign(a1, a2);
		if (operator.equals("*="))
			return new Assign(a1, new Multiply(a1, a2));
		if (operator.equals("/="))
			return new Assign(a1, new Divide(a1, a2));
		if (operator.equals("%="))
			return new Assign(a1, new Modulo(a1, a2));
		if (operator.equals("+="))
			return new Assign(a1, Add.getExpression(a1, a2));
		if (operator.equals("-="))
			return new Assign(a1, Subtract.getExpression(a1, a2));
		if (operator.equals("<<="))
			return new Assign(a1, new ShiftLeft(a1, a2));
		if (operator.equals(">>="))
			return new Assign(a1, new ShiftRight(a1, a2));
		if (operator.equals("&="))
			return new Assign(a1, new BitwiseAnd(a1, a2));
		if (operator.equals("^="))
			return new Assign(a1, new BitwiseXOR(a1, a2));
		if (operator.equals("|="))
			return new Assign(a1, new BitwiseOr(a1, a2));
		throw new CompilationError("Internal Error");
	}

}
