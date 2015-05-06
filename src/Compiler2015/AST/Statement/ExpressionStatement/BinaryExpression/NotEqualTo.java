package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Environment.Environment;
import Compiler2015.IR.Instruction.Arithmetic.SetNotEqual;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.Type.IntType;

/**
 * a != b
 */
public class NotEqualTo extends BinaryExpression {
	public NotEqualTo(Expression left, Expression right) {
		super(left, right);
		this.type = new IntType();
	}

	@Override
	public String getOperator() {
		return "!=";
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		a1 = CastExpression.castToNumeric(a1);
		a2 = CastExpression.castToNumeric(a2);

		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(!v1.equals(v2) ? 1 : 0);
		if (!(a1.type instanceof IntType))
			a1 = new CastExpression(new IntType(), a1);
		if (!(a2.type instanceof IntType))
			a2 = new CastExpression(new IntType(), a2);
		return new NotEqualTo(a1, a2);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		left.emitCFG(builder);
		right.emitCFG(builder);
		tempRegister = Environment.getTemporaryRegister();
		builder.addInstruction(new SetNotEqual(tempRegister, left.tempRegister, right.tempRegister));
	}

}
