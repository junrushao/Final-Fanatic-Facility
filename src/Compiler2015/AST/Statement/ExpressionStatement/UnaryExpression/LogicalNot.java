package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.IntType;
import Compiler2015.Type.StructOrUnionType;

/**
 * !e
 */
public class LogicalNot extends UnaryExpression {
	public LogicalNot(Expression e) {
		super(e);
		this.type = new IntType();
	}

	@Override
	public String getOperator() {
		return "!";
	}

	public static Expression getExpression(Expression e) {
		if (e.type instanceof StructOrUnionType)
			throw new CompilationError("Incompatible type.");
		if (e.type instanceof ArrayPointerType)
			return new IntConstant(0);
		Integer v = Expression.toInt(e);
		if (v != null)
			return new IntConstant(v == 0 ? 1 : 0);
		return new LogicalNot(e);
	}
}
