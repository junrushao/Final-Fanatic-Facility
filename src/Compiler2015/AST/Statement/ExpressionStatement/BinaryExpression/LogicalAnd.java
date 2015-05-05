package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.Type.IntType;
import Compiler2015.Type.Type;

/**
 * a && b
 */
public class LogicalAnd extends BinaryExpression {
	public LogicalAnd(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String getOperator() {
		return "&&";
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("&& must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(((v1 != 0) && (v2 != 0)) ? 1 : 0);
		if (v1 != null && v1 == 0)
			return new IntConstant(0);
		if (!(a1.type instanceof IntType))
			a1 = new CastExpression(new IntType(), a1);
		if (!(a2.type instanceof IntType))
			a2 = new CastExpression(new IntType(), a2);
		return new LogicalAnd(a1, a2);
	}

	@Override
	public void emitCFG(CFGVertex fromHere) {
		// TODO
	}
}
