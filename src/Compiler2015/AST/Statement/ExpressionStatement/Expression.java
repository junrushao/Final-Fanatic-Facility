package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Statement.Statement;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

public abstract class Expression extends Statement {
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

	public abstract void emitCFG(ExpressionCFGBuilder builder);

	public void eliminateArrayRegister(ExpressionCFGBuilder builder) {
		if (this.tempRegister == null)
			throw new CompilationError("Internal Error.");
		if (this.tempRegister instanceof ArrayRegister)
			throw new CompilationError("Internal Error.");
	}

	@Override
	public void emitCFG() {
		ExpressionCFGBuilder builder = new ExpressionCFGBuilder();
		emitCFG(builder);
		eliminateArrayRegister(builder);

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
}
