package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Type.Type;

public class Sizeof extends UnaryExpression {
	public Sizeof(Expression e, Type type, boolean isLValue) {
		super(e, type, isLValue);
	}
}
