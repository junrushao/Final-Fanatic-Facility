package Compiler2015.AST.Statement.ExpressionStatement;

/**
 * Created by junrushao on 15-4-11.
 *
 * a.b
 */
public class MemberAccess extends Expression {
	public Expression memeda;
	public String memberName;

	public MemberAccess(Expression memeda, String memberName) {
		this.memeda = memeda;
		this.memberName = memberName;
	}
}
