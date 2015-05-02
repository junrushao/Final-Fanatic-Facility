package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;
import sun.security.provider.JavaKeyStore;

/**
 * a[b]
 */
public class ArrayAccess extends BinaryExpression {
	public ArrayAccess(Expression left, Expression right, Type pointTo, boolean isLValue) {
		super(left, right);
		this.type = pointTo;
		this.isLValue = isLValue;
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (Type.isNumeric(a1.type)) {
			Expression tmp = a1;
			a1 = a2;
			a2 = tmp;
		}
		if (!Type.isNumeric(a2.type) || !(a1.type instanceof ArrayPointerType || a1.type instanceof VariablePointerType))
			throw new CompilationError("Type Error");
		return new Add(
				a1,
				Multiply.getExpression(
						a2,
						new IntConstant(Pointer.getPointTo(a1.type).sizeof())),
				a1.type
		);
	}

	@Override
	public String getOperator() {
		return "[]";
	}
}
