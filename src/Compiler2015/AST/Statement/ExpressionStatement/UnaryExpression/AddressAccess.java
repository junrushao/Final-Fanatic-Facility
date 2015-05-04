package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;

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
		if (e.type instanceof VoidType) {
			throw new CompilationError("Type Error");
		}
		if (e.type instanceof IntType) {
			throw new CompilationError("Type Error");
		}
		if (e.type instanceof CharType) {
			throw new CompilationError("Type Error");
		}
		if (e.type instanceof StructOrUnionType) {
			throw new CompilationError("Type Error");
		}
		return new AddressAccess(e);
	}

	@Override
	public void emitCFG() {

	}
}
