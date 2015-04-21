package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.VariablePointerType;
import Compiler2015.Exception.CompilationError;

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
		return new AddressFetch(e);
	}
}
