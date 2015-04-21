package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.Pointer;

/**
 * *e
 */
public class AddressAccess extends UnaryExpression {
	public AddressAccess(Expression e) {
		super(e);
		this.type = Pointer.getPointTo(e.type);
		this.isLValue = true;
	}

	public String getOperator() {
		return "*";
	}

	public static Expression getExpression(Expression e) {
		return new AddressAccess(e);
	}
}
