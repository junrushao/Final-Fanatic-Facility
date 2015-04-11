package Compiler2015.AST.Statement.ExpressionStatement;

/**
 * Created by junrushao on 15-4-11.
 *
 * a.b
 */
public class MemberAccess extends Expression {
	public String identifierName;
	public String memberName;

	public MemberAccess(String identifierName, String memberName) {
		this.identifierName = identifierName;
		this.memberName = memberName;
	}
}
