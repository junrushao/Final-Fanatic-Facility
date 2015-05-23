package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.AddReg;
import Compiler2015.IR.Instruction.Arithmetic.MultiplyReg;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.Type.*;

/**
 * a[b]
 */
public class ArrayAccess extends BinaryExpression {

	public ArrayAccess(Expression left, Expression right, Type pointTo, boolean isLValue) {
		super(left, right);
		this.type = pointTo;
		this.isLValue = isLValue;
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Type Error");
		if (a1.type instanceof IntType) {
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
		}
		if (a1.type instanceof CharType) {
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
		if (Type.isNumeric(a1.type)) {
			Expression tmp = a1;
			a1 = a2;
			a2 = tmp;
		}
		if (a1.type instanceof VariablePointerType)
			return new ArrayAccess(a1, a2, ((VariablePointerType) a1.type).pointTo, true);
		if (a1.type instanceof ArrayPointerType)
			return new ArrayAccess(a1, a2, ((ArrayPointerType) a1.type).lower(), true);
		throw new CompilationError("Incompatible type.");
	}

	@Override
	public String getOperator() {
		return "[]";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		left.emitCFG(builder);
		left.eliminateArrayRegister(builder);
		right.emitCFG(builder);
		right.eliminateArrayRegister(builder);

		VirtualRegister leftRegister;
		// for a pointer which is not array pointer, we should load it in to know what it refers to
		if (left.type instanceof ArrayPointerType) {
			leftRegister = (VirtualRegister) left.tempRegister;
		} else { // other pointer types
/*
			if (!(left.type instanceof Pointer))
				throw new CompilationError("Internal Error.");
			leftRegister = Environment.getVirtualRegister();
			builder.addInstruction(new ReadArray(leftRegister, new ArrayRegister((VirtualRegister) left.tempRegister, ImmediateValue.zero, Panel.getPointerSize())));
*/
			leftRegister = (VirtualRegister) left.tempRegister;
		}

		// for a pointer to a struct, we should do nothing
		if (type instanceof StructOrUnionType) {
			// left[right] <=> *(leftRegister + right.tempRegister * struct.sizeof)
			tempRegister = Environment.getVirtualRegister();
			VirtualRegister r = Environment.getVirtualRegister();
			builder.addInstruction(new MultiplyReg(r, right.tempRegister, new ImmediateValue(type.sizeof())));
			builder.addInstruction(new AddReg((VirtualRegister) tempRegister, leftRegister, r));
		}

		if (right.tempRegister instanceof VirtualRegister) {
			// in fact it is not ArrayAccess but (left + right * size)[0]
			// r1 = right * type.size
			VirtualRegister r1 = Environment.getVirtualRegister();
			builder.addInstruction(new MultiplyReg(r1, right.tempRegister, new ImmediateValue(type.sizeof())));
			// r2 = left + r1
			VirtualRegister r2 = Environment.getVirtualRegister();
			builder.addInstruction(new AddReg(r2, leftRegister, r1));
			// tempRegister = r2[0]
			tempRegister = new ArrayRegister(r2, ImmediateValue.zero, type.classifiedSizeof());
		} else if (right.tempRegister instanceof ImmediateValue) {
			int delta = ((ImmediateValue) right.tempRegister).a * type.sizeof();
			tempRegister = new ArrayRegister(leftRegister, new ImmediateValue(delta), type.classifiedSizeof());
		} else
			throw new CompilationError("Internal Error.");
	}

	@Override
	public void eliminateArrayRegister(ExpressionCFGBuilder builder) {
		if (tempRegister instanceof ArrayRegister) {
			VirtualRegister newReg = Environment.getVirtualRegister();
			builder.addInstruction(new ReadArray(newReg, (ArrayRegister) tempRegister));
			tempRegister = newReg;
		}
	}
}
