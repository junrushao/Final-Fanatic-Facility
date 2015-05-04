package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.IntType;
import Compiler2015.Type.Type;

/**
 * +e
 */
public class Positive extends UnaryExpression {
	public Positive(Expression e) {
		super(e);
		this.type = new IntType();
	}

	@Override
	public String getOperator() {
		return "+";
	}

	public static Expression getExpression(Expression e) {
		if (!Type.isNumeric(e.type))
			throw new CompilationError("Cannot put unary plus before this type");
		e.isLValue = false;
		return e;
	}

	@Override
	public void emitCFG() {

	}
}
