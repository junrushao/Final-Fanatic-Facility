package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.Pointer;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Panel;

import java.util.HashMap;

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
		Type memberType = leftType.directlyAccessibleMembers.get(a2);
		if (memberType == null)
			throw new CompilationError("No such member.");
		return new PointerMemberAccess(a1, a2, memberType);
	}

	@Override
	public String toString() {
		return String.format("(->%s %s)", memberName, su.toString());
	}

	@Override
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {
		su.collectGlobalNonArrayVariablesUsed(dumpTo);
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		Type l = Pointer.getPointTo(su.type);
		if (!(l instanceof StructOrUnionType))
			throw new CompilationError("Must be a pointer to a certain struct/union");
		su.emitCFG(builder);
//		su.eliminateArrayRegister(builder);
		StructOrUnionType type = (StructOrUnionType) l;
		int delta = type.memberDelta.get(memberName);
		int size = this.type instanceof StructOrUnionType || this.type instanceof ArrayPointerType ? Panel.getPointerSize() : this.type.sizeof();
		if (su.tempRegister instanceof ArrayRegister) {
			ImmediateValue t = new ImmediateValue(((ArrayRegister) su.tempRegister).b.a + delta);
			tempRegister = new ArrayRegister(((ArrayRegister) su.tempRegister).a, t, size);
		}
		else {
			tempRegister = new ArrayRegister((VirtualRegister) su.tempRegister, new ImmediateValue(delta), size);
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
