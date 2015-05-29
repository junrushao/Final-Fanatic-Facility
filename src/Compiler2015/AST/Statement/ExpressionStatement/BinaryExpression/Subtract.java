package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.ThreeAddressInstruction.SubtractReg;
import Compiler2015.Type.*;

/**
 * a - b
 */
public class Subtract extends BinaryExpression {
	public Subtract(Expression left, Expression right) {
		super(CastExpression.getExpression(IntType.instance, left), CastExpression.getExpression(IntType.instance, right));
		this.type = IntType.instance;
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Cannot operate add on void type.");
		if (a1.type instanceof StructOrUnionType || a2.type instanceof StructOrUnionType)
			throw new CompilationError("Cannot operator on struct / union.");
		if (a1.type instanceof IntType) {
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof CharType) {
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
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof FunctionPointerType) {
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof ArrayPointerType) {
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof VariablePointerType) {
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
		}
		// calculate immediately
		Integer v1 = Expression.toInt(a1), v2 = Expression.toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(v1 - v2);

		if (a1.type instanceof CharType)
			a1 = CastExpression.getExpression(IntType.instance, a1);
		if (a2.type instanceof CharType)
			a2 = CastExpression.getExpression(IntType.instance, a2);
		if (a1.type instanceof IntType && a2.type instanceof IntType)
			return new Subtract(a1, a2);
		if (a1.type instanceof FunctionType)
			return new Subtract(a1, a2);
		if (a1.type instanceof FunctionPointerType)
			return new Subtract(a1, a2);
		if (a1.type instanceof ArrayPointerType)
			a1 = CastExpression.getExpression(((ArrayPointerType) a1.type).toVariablePointerType(), a1);
		if (a1.type instanceof VariablePointerType) {
			if (a2.type instanceof IntType)
				return CastExpression.getExpression(a1.type,
						new Subtract(a1,
								Multiply.getExpression(a2, new IntConstant(((VariablePointerType) a1.type).pointTo.sizeof())))
				);
			if (a2.type instanceof ArrayPointerType)
				a2 = CastExpression.getExpression(((ArrayPointerType) a2.type).toVariablePointerType(), a2);
			if (a2.type instanceof VariablePointerType)
				return new Subtract(a1, a2);
		}
		throw new CompilationError("Internal Error.");
	}

	@Override
	public String getOperator() {
		return "-";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		left.emitCFG(builder);
		left.readInArrayRegister(builder);
		right.emitCFG(builder);
		right.readInArrayRegister(builder);
		tempRegister = Environment.getVirtualRegister();
		builder.addInstruction(new SubtractReg((VirtualRegister) tempRegister, left.tempRegister, right.tempRegister));
	}

	@Override
	public Subtract clone() {
		return (Subtract) super.clone();
	}

	@Override
	public Expression rebuild() {
		return new Subtract(left.rebuild(), right.rebuild());
	}
}
