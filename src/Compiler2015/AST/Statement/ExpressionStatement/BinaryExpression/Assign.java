package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.Arithmetic.AddRegImm;
import Compiler2015.IR.IRStream;
import Compiler2015.IR.Load;
import Compiler2015.IR.Store;
import Compiler2015.Type.IntType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.VoidType;

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

	@Override
	public void emitIR(IRStream stream) {
		left.emitIR(stream);
		right.emitIR(stream);
		if (left.type instanceof StructOrUnionType) {
			if (!left.type.equals(right.type))
				throw new CompilationError("Internal Error.");
			int size = left.type.sizeof();
			int t0 = this.tempRegister = ++Environment.totalTempRegisters;
			for (int i = 4; i < size; i += 4) {
				int t1 = ++Environment.totalTempRegisters;
				int t2 = ++Environment.totalTempRegisters;
				stream.pool.add(new AddRegImm(left.tempRegister, i, t1));
				stream.pool.add(new AddRegImm(right.tempRegister, i, t2));
				stream.pool.add(new Load(t2, t0));
				stream.pool.add(new Store(t0, t1));
			}
			stream.pool.add(new Load(right.tempRegister, t0));
			stream.pool.add(new Store(t0, left.tempRegister));
		}
		else {
			right.loadImm(stream);
			right.eliminateLValue(stream);
			stream.pool.add(new Store(right.tempRegister, left.tempRegister));
			this.tempRegister = right.tempRegister;
		}
	}
}
