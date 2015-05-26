package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.AddReg;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.IR.Instruction.WriteArray;
import Compiler2015.Type.*;

/**
 * e++
 */
public class PostfixSelfInc extends UnaryExpression {
	public PostfixSelfInc(Expression e) {
		super(e);
		this.type = e.type;
	}

	public static Expression getExpression(Expression a1) {
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType || a1.type instanceof ArrayPointerType)
			throw new CompilationError("Self-increment is not supported in such type.");
		return new PostfixSelfInc(a1);
	}

	@Override
	public String getOperator() {
		return "Postfix ++";
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
			tempRegister = Environment.getVirtualRegister();
			builder.addInstruction(new Move((VirtualRegister) tempRegister, e.tempRegister));
			builder.addInstruction(new AddReg((VirtualRegister) e.tempRegister, e.tempRegister, v));
		} else if (e.tempRegister instanceof ArrayRegister) {
			tempRegister = Environment.getVirtualRegister();
			VirtualRegister t2 = Environment.getVirtualRegister();
			builder.addInstruction(new ReadArray((VirtualRegister) tempRegister, (ArrayRegister) e.tempRegister));
			builder.addInstruction(new AddReg(t2, tempRegister, v));
			builder.addInstruction(new WriteArray((ArrayRegister) e.tempRegister, t2));
		} else
			throw new CompilationError("Internal Error.");
	}

	@Override
	public PostfixSelfInc clone() {
		return (PostfixSelfInc) super.clone();
	}

	@Override
	public Expression rebuild() {
		return new PostfixSelfInc(e.rebuild());
	}
}
