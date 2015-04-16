package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * a->b
 */
public class PointerMemberAccess extends Expression {
	public Expression memeda;
	public String memberName;

	public PointerMemberAccess(Expression memeda, String memberName, Type type, boolean isLValue) {
		super(type, isLValue);
		this.memeda = memeda;
		this.memberName = memberName;
	}
}
