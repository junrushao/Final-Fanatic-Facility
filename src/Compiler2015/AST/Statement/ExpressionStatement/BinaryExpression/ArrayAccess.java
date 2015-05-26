package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.AddReg;
import Compiler2015.IR.Instruction.MultiplyReg;
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

	// left[a[3]] => ReadArray(c, a + 3) => left[c]
	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		left.emitCFG(builder);
		right.emitCFG(builder);
		right.readInArrayRegister(builder);

		if (left.type instanceof ArrayPointerType && (this.type instanceof ArrayPointerType || this.type instanceof StructOrUnionType)) {
			left.convertArrayRegisterToPointer(builder);
			tempRegister = Environment.getVirtualRegister();
			VirtualRegister delta = getTypeDelta(builder, right.tempRegister, new ImmediateValue(type.sizeof()));
			builder.addInstruction(new AddReg((VirtualRegister) tempRegister, left.tempRegister, delta));
		} else if (this.type instanceof ArrayPointerType || this.type instanceof StructOrUnionType) {
			left.readInArrayRegister(builder);
			tempRegister = Environment.getVirtualRegister();
			VirtualRegister delta = getTypeDelta(builder, right.tempRegister, new ImmediateValue(type.sizeof()));
			builder.addInstruction(new AddReg((VirtualRegister) tempRegister, left.tempRegister, delta));
		} else if (right.tempRegister instanceof ImmediateValue) {
			left.readInArrayRegister(builder);
			tempRegister = new ArrayRegister((VirtualRegister) left.tempRegister,
					new ImmediateValue(((ImmediateValue) right.tempRegister).a * type.sizeof()),
					type.sizeof());
		} else if (right.tempRegister instanceof VirtualRegister) {
			left.readInArrayRegister(builder);
			VirtualRegister address = Environment.getVirtualRegister();
			VirtualRegister delta = getTypeDelta(builder, right.tempRegister, new ImmediateValue(type.sizeof()));
			builder.addInstruction(new AddReg(address, left.tempRegister, delta));
			tempRegister = new ArrayRegister(address, new ImmediateValue(0), type.sizeof());
		} else
			throw new CompilationError("Internal Error.");
	}

	@Override
	public void readInArrayRegister(ExpressionCFGBuilder builder) {
		if (tempRegister instanceof ArrayRegister) {
			if (this.type instanceof StructOrUnionType) {
				VirtualRegister newRegister = Environment.getVirtualRegister();
				VirtualRegister r = Environment.getVirtualRegister();
				builder.addInstruction(new MultiplyReg(r, right.tempRegister, new ImmediateValue(type.sizeof())));
				builder.addInstruction(new AddReg(newRegister, tempRegister, r));
				tempRegister = newRegister;
			} else {
				VirtualRegister newReg = Environment.getVirtualRegister();
				builder.addInstruction(new ReadArray(newReg, (ArrayRegister) tempRegister));
				tempRegister = newReg;
			}
		}
	}

	@Override
	public Expression rebuild() {
		return new ArrayAccess(left.rebuild(), right.rebuild(), type, isLValue);
	}
}
