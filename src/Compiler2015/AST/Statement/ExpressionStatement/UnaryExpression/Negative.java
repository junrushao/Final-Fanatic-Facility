package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.NegateReg;
import Compiler2015.Type.IntType;
import Compiler2015.Type.Type;

/**
 * -e
 */
public class Negative extends UnaryExpression {
	public Negative(Expression e) {
		super(e);
		this.type = IntType.instance;
	}

	public static Expression getExpression(Expression e) {
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put - before this type");
		Integer v = Expression.toInt(e);
		if (v != null)
			return new IntConstant(-v);
		return new Negative(e);
	}

	@Override
	public String getOperator() {
		return "-";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		e.readInArrayRegister(builder);
		tempRegister = Environment.getVirtualRegister();
		builder.addInstruction(new NegateReg((VirtualRegister) tempRegister, e.tempRegister));
	}

	@Override
	public Negative clone() {
		return (Negative) super.clone();
	}

	@Override
	public Expression rebuild() {
		return new Negative(e.rebuild());
	}
}
