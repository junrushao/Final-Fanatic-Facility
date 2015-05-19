package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.AddReg;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Panel;

import java.util.HashMap;

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
		Type memberType = leftType.directlyAccessibleMembers.get(a2);
		if (memberType == null)
			throw new CompilationError("No such member.");
		return new MemberAccess(a1, a2, memberType);
	}

	@Override
	public String toString() {
		return String.format("(.%s %s)", memberName, su.toString());
	}

	@Override
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {
		su.collectGlobalNonArrayVariablesUsed(dumpTo);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		if (!(su.type instanceof StructOrUnionType))
			throw new CompilationError("Internal Error.");
		su.emitCFG(builder);
//		su.eliminateArrayRegister(builder);
		StructOrUnionType type = (StructOrUnionType) su.type;
		int delta = type.memberDelta.get(memberName);
		if (su.tempRegister instanceof ArrayRegister) {
			VirtualRegister t = Environment.getTemporaryRegister();
			builder.addInstruction(new AddReg(t, ((ArrayRegister) su.tempRegister).b, new ImmediateValue(delta)));
			tempRegister = new ArrayRegister(((ArrayRegister) su.tempRegister).a, t,
					this.type instanceof StructOrUnionType ? Panel.getPointerSize() : type.directlyAccessibleMembers.get(memberName).sizeof());
		}
		else {
			tempRegister = new ArrayRegister(su.tempRegister, new ImmediateValue(delta),
					this.type instanceof StructOrUnionType ? Panel.getPointerSize() : type.directlyAccessibleMembers.get(memberName).sizeof());
		}
	}

	@Override
	public void eliminateArrayRegister(ExpressionCFGBuilder builder) {
		if (tempRegister instanceof ArrayRegister) {
			VirtualRegister newReg = Environment.getTemporaryRegister();
			builder.addInstruction(new ReadArray(newReg, (ArrayRegister) tempRegister));
			tempRegister = newReg.clone();
		}
	}
}
