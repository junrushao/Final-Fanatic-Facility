package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.AddReg;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;

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
		if (!(su.tempRegister instanceof VirtualRegister))
			throw new CompilationError("Internal Error.");
		StructOrUnionType structType = (StructOrUnionType) su.type;
		int delta = structType.memberDelta.get(memberName);
		if (this.type instanceof ArrayPointerType || this.type instanceof StructOrUnionType) {
			tempRegister = Environment.getVirtualRegister();
			builder.addInstruction(new AddReg((VirtualRegister) tempRegister, su.tempRegister, new ImmediateValue(delta)));
		} else {
			tempRegister = new ArrayRegister((VirtualRegister) su.tempRegister, new ImmediateValue(delta), type.sizeof());
		}
	}

	@Override
	public void readInArrayRegister(ExpressionCFGBuilder builder) {
		if (tempRegister instanceof ArrayRegister) {
			VirtualRegister newReg = Environment.getVirtualRegister();
			builder.addInstruction(new ReadArray(newReg, (ArrayRegister) tempRegister));
			tempRegister = newReg.clone();
		}
	}

	@Override
	public MemberAccess clone() {
		MemberAccess ret = (MemberAccess) super.clone();
		ret.su = ret.su.clone();
		return ret;
	}

	@Override
	public Expression rebuild() {
		return new MemberAccess(su.rebuild(), memberName, this.type);
	}
}
