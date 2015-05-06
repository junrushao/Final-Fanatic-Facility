package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.AddReg;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.VoidType;

/**
 * e++
 */
public class PostfixSelfInc extends UnaryExpression {
	public PostfixSelfInc(Expression e) {
		super(e);
		this.type = e.type;
	}

	@Override
	public String getOperator() {
		return "Postfix ++";
	}

	public static Expression getExpression(Expression a1) {
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType || a1.type instanceof ArrayPointerType)
			throw new CompilationError("Self-increment is not supported in such type.");
/*
		if (a1 instanceof IntConstant)
			return new IntConstant(((IntConstant) a1).c + 1);
		if (a1 instanceof CharConstant)
			return new CharConstant((char) (((CharConstant) a1).c + 1));
*/
		return new PostfixSelfInc(a1);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		e.eliminateLValue(builder);
		tempRegister = Environment.getTemporaryRegister();
		builder.addInstruction(new Move(tempRegister, e.tempRegister));
		VirtualRegister tp = Environment.getTemporaryRegister();
		builder.addInstruction(new AddReg(tp, tempRegister, new ImmediateValue(1)));
		builder.addInstruction(new Move(e.tempRegister, tp));
	}
}
