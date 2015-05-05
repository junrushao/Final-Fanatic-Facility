package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Statement.Statement;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

public abstract class Expression extends Statement {
	public Type type = null;
	public boolean isLValue = false;

	@Override
	public void emitCFG() {
		emitCFG(ControlFlowGraph.getNewVertex());
	}

	public abstract void emitCFG(CFGVertex fromHere);

	/*
		public int tempRegister = -1;
		public void eliminateLValue(IRStream stream) {
			if (this.isLValue) {
				if (this.type instanceof StructOrUnionType)
					throw new CompilationError("Internal Error.");
				stream.pool.add(new Load(tempRegister, ++Environment.totalTempRegisters, this.type.sizeof()));
				tempRegister = Environment.totalTempRegisters;
			}
		}

		public void loadImm(IRStream stream) {
			if (this instanceof IntConstant) {
				this.tempRegister = ++Environment.totalTempRegisters;
				stream.pool.add(new LoadImm(tempRegister, Constant.toInt(this), 4));
			}
			else if (this instanceof CharConstant) {
				this.tempRegister = ++Environment.totalTempRegisters;
				stream.pool.add(new LoadImm(tempRegister, Constant.toInt(this), 1));
			}
		}

	*/
	public static Integer toInt(Expression x) {
		if (x instanceof IntConstant)
			return ((IntConstant) x).c;
		if (x instanceof CharConstant)
			return (int) (((CharConstant) x).c);
		return null;
	}

	@Override
	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).append(Utility.NEW_LINE).toString();
	}
}
