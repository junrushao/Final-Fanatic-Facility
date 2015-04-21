package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Pointer;
import Compiler2015.AST.Type.StructOrUnionType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Utility;

/**
 * a->b
 */
public class PointerMemberAccess extends Expression {
	public Expression su;
	public String memberName;

	public PointerMemberAccess(Expression su, String memberName, Type type) {
		this.su = su;
		this.memberName = memberName;
		this.type = type;
		this.isLValue = true;
	}

	public static Expression getExpression(Expression a1, String a2) {
		Type l = Pointer.getPointTo(a1.type);
		if (!(l instanceof StructOrUnionType))
			throw new CompilationError("Must be a pointer to a certain struct/union");
		StructOrUnionType leftType = (StructOrUnionType) l;
		Type memberType = leftType.ref.memberType(a2);
		if (memberType == null)
			throw new CompilationError("No such member.");
		return new PointerMemberAccess(a1, a2, memberType);
	}

	@Override
	public String toString(int depth) {
		return Utility.getIndent(depth).append("->").append(memberName).append(Utility.NEW_LINE).append(su.toString(depth + 1)).toString();
	}
}
