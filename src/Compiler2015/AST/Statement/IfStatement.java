package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Type.IntType;
import Compiler2015.Utility.Utility;

/**
 * if (e) ifTrue else ifFalse
 */
public class IfStatement extends Statement {
	public Expression e;
	public Statement ifTrue, ifFalse;

	public IfStatement(Expression e, Statement ifTrue, Statement ifFalse) {
		if (!CastExpression.castable(e.type, IntType.instance))
			throw new CompilationError("Expression inside if statement could not be converted to int type.");
		this.e = e;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("IF").append(Utility.NEW_LINE);
		StringBuilder indent = Utility.getIndent(depth + 1);
		String aa = e == null ? indent.append("null").toString() : e.deepToString(depth + 1);
		String bb = ifTrue == null ? indent.append("null").append(Utility.NEW_LINE).toString() : ifTrue.deepToString(depth + 1);
		String cc = ifFalse == null ? indent.append("null").append(Utility.NEW_LINE).toString() : ifFalse.deepToString(depth + 1);
		return sb.append(aa)
				.append(bb)
				.append(cc).toString();
	}

	@Override
	public String toString() {
		return deepToString(0);
	}

	@Override
	public void emitCFG() {
		// TODO
		e.emitCFG();
		ifTrue.emitCFG();
		if (ifFalse != null)
			ifFalse.emitCFG();

		beginCFGBlock = e.beginCFGBlock;
		endCFGBlock = ControlFlowGraph.getNewVertex();

		e.endCFGBlock.unconditionalNext = ifTrue.beginCFGBlock;
		if (ifFalse != null)
			e.endCFGBlock.branchIfFalse = ifFalse.beginCFGBlock;
		ifTrue.endCFGBlock.unconditionalNext = endCFGBlock;
		ifTrue.endCFGBlock.branchIfFalse = null;

		if (ifFalse != null) {
			ifFalse.endCFGBlock.unconditionalNext = endCFGBlock;
			ifFalse.endCFGBlock.branchIfFalse = null;
		}
	}
}
