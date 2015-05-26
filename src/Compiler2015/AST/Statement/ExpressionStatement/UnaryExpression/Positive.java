package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.Type.IntType;
import Compiler2015.Type.Type;

/**
 * +e
 */
public class Positive extends UnaryExpression {
	public Positive(Expression e) {
		super(e);
		this.type = IntType.instance;
	}

	public static Expression getExpression(Expression e) {
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		e.isLValue = false;
		return e;
	}

	@Override
	public String getOperator() {
		return "+";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		tempRegister = e.tempRegister.clone();
		builder.addInstruction(new Move((VirtualRegister) tempRegister, e.tempRegister));
	}

	@Override
	public Positive clone() {
		return (Positive) super.clone();
	}

	@Override
	public Expression rebuild() {
		return new Positive(e.rebuild());
	}
}
