package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.IntType;
import Compiler2015.AST.Type.StructOrUnionType;
import Compiler2015.AST.Type.VoidType;
import Compiler2015.Exception.CompilationError;

public abstract class AssignClass extends BinaryExpression {
	public AssignClass(Expression left, Expression right) {
		super(left, right);
	}

	public static Expression getExpression(Expression a1, Expression a2, String operator) {
		if (!a1.isLValue)
			throw new CompilationError("Expression to the left of = is not left-value.");

		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate on void.");

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
