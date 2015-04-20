package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.StructOrUnionType;
import Compiler2015.AST.Type.Type;
import Compiler2015.AST.Type.VoidType;
import Compiler2015.Exception.CompilationError;

/**
 * (castTo)e
 */
public class CastExpression extends Expression {
	public Type castTo;
	public Expression e;

	public CastExpression(Type castTo, Expression e) {
		this.type = castTo;
		this.castTo = castTo;
		this.e = e;
	}

	public static Expression getExpression(Type t, Expression c) {
		if (t instanceof VoidType)
			throw new CompilationError("Cannot cast to void");
		if (t.equals(c.type))
			return c;
		if (t instanceof StructOrUnionType || c.type instanceof StructOrUnionType)
			throw new CompilationError("Cast failed");
		if (c instanceof CharConstant) {
			char v = ((CharConstant) c).c;
			return new IntConstant((int) v);
		}
		return new CastExpression(t, c);
	}
}
