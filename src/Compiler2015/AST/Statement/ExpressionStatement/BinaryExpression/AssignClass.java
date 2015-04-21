package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.CharConstant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.AST.Type.*;
import Compiler2015.Exception.CompilationError;

public abstract class AssignClass extends BinaryExpression {
	public AssignClass(Expression left, Expression right) {
		super(left, right);
	}

	public static Expression getExpression(Expression a1, Expression a2, String operator) {
		// Case 0
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");

		// Case 1
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");

		// Case 2
		if ((a1.type instanceof StructOrUnionType) != (a2.type instanceof StructOrUnionType))
			throw new CompilationError("Incompatible type.");
		if (a1.type instanceof StructOrUnionType && (!operator.equals("=")) || !a1.type.equals(a2.type))
			throw new CompilationError("Incompatible type.");

		// Case 3
		if (a1.type instanceof FunctionPointerType && a2.type instanceof ArrayPointerType)
			throw new CompilationError("Incompatible type.");

		// Case 4
		if ((!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type)) && !operator.equals("="))
			throw new CompilationError("Incompatible type.");

		// Case 5.1
		if (a1.type instanceof IntType && a2.type instanceof CharType) {
			Integer v = toInt(a2);
			a2 = v != null ? new IntConstant(v) : new CastExpression(new IntType(), a2);
		}

		// Case 5.2
		if (a1.type instanceof CharType && a2.type instanceof IntType) {
			Integer v = toInt(a2);
			a2 = v != null ? new CharConstant((char) v.intValue()) : new CastExpression(new CharType(), a2);
		}

		if (operator.equals("="))
			return new Assign(a1, a2);
		if (operator.equals("*="))
			return new MultiplyAssign(a1, a2);
		if (operator.equals("/="))
			return new DivideAssign(a1, a2);
		if (operator.equals("%="))
			return new ModuloAssign(a1, a2);
		if (operator.equals("+="))
			return new AddAssign(a1, a2);
		if (operator.equals("-="))
			return new SubtractAssign(a1, a2);
		if (operator.equals("<<="))
			return new ShiftLeftAssign(a1, a2);
		if (operator.equals(">>="))
			return new ShiftRightAssign(a1, a2);
		if (operator.equals("&="))
			return new BitwiseAndAssign(a1, a2);
		if (operator.equals("^="))
			return new BitwiseXORAssign(a1, a2);
		if (operator.equals("|="))
			return new BitwiseOrAssign(a1, a2);
		throw new CompilationError("Internal Error");
	}
}
