package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Statement.Statement;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Arithmetic.AddReg;
import Compiler2015.IR.Instruction.Arithmetic.MultiplyReg;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

public abstract class Expression extends Statement implements Cloneable {
	public Type type = null;
	public boolean isLValue = false;
	public IRRegister tempRegister = null;

	public static Integer toInt(Expression x) {
		if (x instanceof IntConstant)
			return ((IntConstant) x).c;
		if (x instanceof CharConstant)
			return (int) (((CharConstant) x).c);
		return null;
	}

	public static VirtualRegister getTypeDelta(ExpressionCFGBuilder builder, IRRegister left, IRRegister right) {
		if (left instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		if (right instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
		VirtualRegister ret = Environment.getVirtualRegister();
		if (left instanceof ImmediateValue && right instanceof ImmediateValue) {
			builder.addInstruction(new Move(ret, new ImmediateValue(((ImmediateValue) left).a * ((ImmediateValue) right).a)));
		} else {
			builder.addInstruction(new MultiplyReg(ret, left, right));
		}
		return ret;
	}

	public abstract void emitCFG(ExpressionCFGBuilder builder);

	public void readInArrayRegister(ExpressionCFGBuilder builder) {
		if (this.tempRegister == null)
			throw new CompilationError("Internal Error.");
		if (this.tempRegister instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
	}

	public void convertArrayRegisterToPointer(ExpressionCFGBuilder builder) {
		if (this.tempRegister == null)
			throw new CompilationError("Internal Error.");
		if (this.tempRegister instanceof ArrayRegister) {
			VirtualRegister newRegister = Environment.getVirtualRegister();
			builder.addInstruction(new AddReg(newRegister, ((ArrayRegister) tempRegister).a, ((ArrayRegister) tempRegister).b));
			tempRegister = newRegister;
		}
	}

	@Override
	public void emitCFG() {
		ExpressionCFGBuilder builder = new ExpressionCFGBuilder();
		emitCFG(builder);
		readInArrayRegister(builder);

		if (this instanceof IdentifierExpression)
			builder.addInstruction(new Move((VirtualRegister) tempRegister, tempRegister));
		if (!(this.tempRegister instanceof ImmediateValue) && builder.s.internal.isEmpty())
			throw new CompilationError("???");

		beginCFGBlock = builder.s;
		endCFGBlock = builder.t;
		if (endCFGBlock.branchIfFalse != null)
			endCFGBlock.branchRegister = this.tempRegister;
	}

	@Override
	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).append(Utility.NEW_LINE).toString();
	}

	@Override
	public Expression clone() {
		Expression ret = null;
		try {
			ret = (Expression) super.clone();
			ret.type = type.clone();
			if (ret.tempRegister != null)
				ret.tempRegister = ret.tempRegister.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
