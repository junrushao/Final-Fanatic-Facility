package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.IR.Instruction.SubtractReg;
import Compiler2015.IR.Instruction.WriteArray;
import Compiler2015.Type.*;

/**
 * --e
 */
public class PrefixSelfDec extends UnaryExpression {
	public PrefixSelfDec(Expression e) {
		super(e);
		this.type = e.type;
	}

	public static Expression getExpression(Expression a1) {
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType || a1.type instanceof ArrayPointerType)
			throw new CompilationError("Such type supports no self-decrement.");
		return new PrefixSelfDec(a1);
	}

	@Override
	public String getOperator() {
		return "Prefix --";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		ImmediateValue v;
		if (Type.isNumeric(e.type))
			v = new ImmediateValue(1);
		else if (e.type instanceof Pointer)
			v = new ImmediateValue(Pointer.getPointTo(e.type).sizeof());
		else
			throw new CompilationError("Internal Error.");
		if (e.tempRegister instanceof VirtualRegister) {
			tempRegister = e.tempRegister.clone();
			builder.addInstruction(new SubtractReg((VirtualRegister) tempRegister, e.tempRegister, v));
		} else if (e.tempRegister instanceof ArrayRegister) {
			tempRegister = Environment.getVirtualRegister();
			builder.addInstruction(new ReadArray((VirtualRegister) tempRegister, (ArrayRegister) e.tempRegister));
			builder.addInstruction(new SubtractReg((VirtualRegister) tempRegister, tempRegister, v));
			builder.addInstruction(new WriteArray((ArrayRegister) e.tempRegister, tempRegister));
		} else
			throw new CompilationError("Internal Error.");
	}

	@Override
	public PrefixSelfDec clone() {
		return (PrefixSelfDec) super.clone();
	}

	@Override
	public Expression rebuild() {
		return new PrefixSelfDec(e.rebuild());
	}
}
