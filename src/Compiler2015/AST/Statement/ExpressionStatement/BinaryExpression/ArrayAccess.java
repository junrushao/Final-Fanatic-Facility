package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.ArrayPointerType;
import Compiler2015.AST.Type.Type;
import Compiler2015.AST.Type.VariablePointerType;
import Compiler2015.Exception.CompilationError;

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
		if (!Type.isNumeric(a2.type))
			throw new CompilationError("Incompatible type.");
		if (a1.type instanceof VariablePointerType)
			return new ArrayAccess(a1, a2, ((VariablePointerType) a1.type).pointTo, true);
		if (a1.type instanceof ArrayPointerType)
			return new ArrayAccess(a1, a2, ((ArrayPointerType) a1.type).lower(), ((ArrayPointerType) a1.type).dimensions.size() == 1);
		throw new CompilationError("Incompatible type.");
	}
}
