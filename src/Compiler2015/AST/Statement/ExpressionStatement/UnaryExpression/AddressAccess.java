package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.ArrayPointerType;
import Compiler2015.AST.Type.FunctionType;
import Compiler2015.AST.Type.Pointer;
import Compiler2015.AST.Type.VoidType;

/**
 * *e
 */
public class AddressAccess extends UnaryExpression {
	public AddressAccess(Expression e) {
		super(e);
		this.type = Pointer.getPointTo(e.type);
		this.isLValue = !(type instanceof ArrayPointerType) && !(type instanceof FunctionType) && !(type instanceof VoidType);
	}

	public String getOperator() {
		return "*";
	}

	public static Expression getExpression(Expression e) {
		return new AddressAccess(e);
	}
}
