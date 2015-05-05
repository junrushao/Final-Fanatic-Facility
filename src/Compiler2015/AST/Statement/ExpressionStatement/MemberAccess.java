package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.ReadArray;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;

/**
 * a.b
 */
public class MemberAccess extends Expression {
	public Expression su;
	public String memberName;

	public MemberAccess(Expression su, String memberName, Type type) {
		this.su = su;
		this.memberName = memberName;
		this.type = type;
		this.isLValue = true;
	}

	public static Expression getExpression(Expression a1, String a2) {
		if (!(a1.type instanceof StructOrUnionType))
			throw new CompilationError("Basic type cannot have members.");
		StructOrUnionType leftType = (StructOrUnionType) a1.type;
		Type memberType = leftType.directlyAccessableMembers.get(a2);
		if (memberType == null)
			throw new CompilationError("No such member.");
		return new MemberAccess(a1, a2, memberType);
	}

	@Override
	public String toString() {
		return String.format("(.%s %s)", memberName, su.toString());
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		if (!(su.type instanceof StructOrUnionType))
			throw new CompilationError("Internal Error.");
		StructOrUnionType type = (StructOrUnionType) su.type;
		int delta = type.memberDelta.get(memberName);
		if (delta == 0) {
			tempRegister = su.tempRegister;
		}
		else {
			tempRegister = Environment.getTemporaryRegister();
			builder.addInstruction(new ReadArray(tempRegister, su.tempRegister, Environment.getImmRegister(builder, delta)));
		}
	}
}
