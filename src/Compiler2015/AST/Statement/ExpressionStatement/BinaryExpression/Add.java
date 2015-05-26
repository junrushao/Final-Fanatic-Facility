package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.AddReg;
import Compiler2015.Type.*;

/**
 * a + b
 */
public class Add extends BinaryExpression {
	public Add(Expression left, Expression right) {
		super(CastExpression.getExpression(IntType.instance, left), CastExpression.getExpression(IntType.instance, right));
		this.type = IntType.instance;
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate add on void type.");
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
		if (a1.type instanceof FunctionType) {
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
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof ArrayPointerType) {
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
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}

		// calculate immediately
		Integer v1 = Expression.toInt(a1), v2 = Expression.toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(v1 + v2);

		if (a1.type instanceof CharType)
			a1 = new CastExpression(IntType.instance, a1);
		if (a2.type instanceof CharType)
			a2 = new CastExpression(IntType.instance, a2);
		if (a2.type instanceof IntType && !(a1.type instanceof IntType)) {
			Expression tmp = a1;
			a1 = a2;
			a2 = tmp;
		}
		// a1 must be IntType, a2 might not
		if (a2.type instanceof IntType)
			return new Add(a1, a2);
		if (a2.type instanceof FunctionType)
			return CastExpression.getExpression(a2.type, new Add(a1, a2));
		if (a2.type instanceof ArrayPointerType)
			a2 = CastExpression.getExpression(((ArrayPointerType) a2.type).toVariablePointerType(), a2);
		if (a2.type instanceof VariablePointerType) {
			Type t = ((VariablePointerType) a2.type).pointTo;
			return CastExpression.getExpression(a2.type,
					new Add(a2,
							Multiply.getExpression(a1, new IntConstant(t.sizeof()))
					)
			);
		}
		if (a2.type instanceof FunctionPointerType) {
			return CastExpression.getExpression(a2.type,
					new Add(a2, new IntConstant(a2.type.sizeof()))
			);
		}
		throw new CompilationError("Internal Error.");
	}

	@Override
	public String getOperator() {
		return "+";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		left.emitCFG(builder);
		left.readInArrayRegister(builder);
		right.emitCFG(builder);
		right.readInArrayRegister(builder);
		tempRegister = Environment.getVirtualRegister();
		builder.addInstruction(new AddReg((VirtualRegister) tempRegister, left.tempRegister, right.tempRegister));
	}

	@Override
	public Expression rebuild() {
		return new Add(left.rebuild(), right.rebuild());
	}
}
