package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Environment.Environment;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.Instruction.Arithmetic.SetGE;
import Compiler2015.Type.IntType;

/**
 * a >= b
 */
public class GE extends BinaryExpression {
	public GE(Expression left, Expression right) {
		super(left, right);
		this.type = IntType.instance;
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		a1 = CastExpression.castToNumeric(a1);
		a2 = CastExpression.castToNumeric(a2);

		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(v1 >= v2 ? 1 : 0);
		if (!(a1.type instanceof IntType))
			a1 = new CastExpression(IntType.instance, a1);
		if (!(a2.type instanceof IntType))
			a2 = new CastExpression(IntType.instance, a2);
		return new GE(a1, a2);
	}

	@Override
	public String getOperator() {
		return ">=";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		left.emitCFG(builder);
		left.eliminateArrayRegister(builder);
		right.emitCFG(builder);
		right.eliminateArrayRegister(builder);
		tempRegister = Environment.getTemporaryRegister();
		builder.addInstruction(new SetGE(tempRegister, left.tempRegister, right.tempRegister));
	}
}
