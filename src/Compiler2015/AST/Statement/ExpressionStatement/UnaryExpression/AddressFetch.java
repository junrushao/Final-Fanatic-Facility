package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.Arithmetic.AddressFetchReg;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.Type.VariablePointerType;
import Compiler2015.Type.VoidType;

/**
 * &e
 */
public class AddressFetch extends UnaryExpression {
	public AddressFetch(Expression e) {
		super(e);
		this.type = new VariablePointerType(e.type);
	}

	@Override
	public String getOperator() {
		return "&";
	}

	public static Expression getExpression(Expression e) {
		if (!e.isLValue)
			throw new CompilationError("Not LValue.");
		if (e.type instanceof VoidType)
			throw new CompilationError("Type Error");
		return new AddressFetch(e);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		e.emitCFG(builder);
		tempRegister = Environment.getTemporaryRegister();
		builder.addInstruction(new AddressFetchReg(tempRegister, e.tempRegister));
	}
}
