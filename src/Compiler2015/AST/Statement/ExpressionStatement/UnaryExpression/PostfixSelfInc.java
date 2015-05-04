package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CharConstant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.VoidType;

/**
 * e++
 */
public class PostfixSelfInc extends UnaryExpression {
	public PostfixSelfInc(Expression e) {
		super(e);
		this.type = e.type;
	}

	@Override
	public String getOperator() {
		return "Postfix ++";
	}

	public static Expression getExpression(Expression a1) {
		if (!a1.isLValue)
			throw new CompilationError("Not LValue.");
		if (a1.type instanceof VoidType || a1.type instanceof StructOrUnionType)
			throw new CompilationError("Self-increment is not supported in such type.");
		if (a1 instanceof IntConstant)
			return new IntConstant(((IntConstant) a1).c + 1);
		if (a1 instanceof CharConstant)
			return new CharConstant((char) (((CharConstant) a1).c + 1));
		return new PostfixSelfInc(a1);
	}

	@Override
	public void emitCFG() {

	}
}
