package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.AddReg;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.Type.Pointer;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;

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
		Type memberType = leftType.directlyAccessableMembers.get(a2);
		if (memberType == null)
			throw new CompilationError("No such member.");
		return new PointerMemberAccess(a1, a2, memberType);
	}

	@Override
	public String toString() {
		return String.format("(->%s %s)", memberName, su.toString());
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		if (!(su.type instanceof StructOrUnionType))
			throw new CompilationError("Internal Error.");
		su.emitCFG(builder);
//		su.eliminateArrayRegister(builder);
		StructOrUnionType type = (StructOrUnionType) su.type;
		int delta = type.memberDelta.get(memberName);
		if (delta == 0) {
			tempRegister = su.tempRegister;
		}
		else if (su.tempRegister instanceof ArrayRegister) {
			VirtualRegister t = Environment.getTemporaryRegister();
			builder.addInstruction(new AddReg(t, ((ArrayRegister) su.tempRegister).b, new ImmediateValue(delta)));
			tempRegister = new ArrayRegister(((ArrayRegister) su.tempRegister).a, t);
		}
		else {
			tempRegister = new ArrayRegister(su.tempRegister, new ImmediateValue(delta));
		}
	}

	@Override
	public void eliminateArrayRegister(ExpressionCFGBuilder builder) {
		if (tempRegister instanceof ArrayRegister) {
			VirtualRegister newReg = Environment.getTemporaryRegister();
			builder.addInstruction(new ReadArray(newReg, (ArrayRegister) tempRegister));
			tempRegister = newReg;
		}
	}
}
