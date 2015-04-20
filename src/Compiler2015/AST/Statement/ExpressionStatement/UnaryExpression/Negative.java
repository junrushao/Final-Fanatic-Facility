package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.AST.Type.IntType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Exception.CompilationError;

/**
 * -e
 */
public class Negative extends UnaryExpression {
	public Negative(Expression e) {
		super(e);
		this.type = new IntType();
	}

	public static Expression getExpression(Expression e) {
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put - before this type");
		Integer v = Expression.toInt(e);
		if (v != null)
			return new IntConstant(-v);
		return new Negative(e);
	}
}
