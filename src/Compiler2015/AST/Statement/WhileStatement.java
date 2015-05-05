package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Type.IntType;
import Compiler2015.Utility.Utility;

/**
 * while (e) a;
 */
public class WhileStatement extends Statement implements Loop {
	public Expression e;
	public Statement a;

	public WhileStatement(Expression e) {
		if (!CastExpression.castable(e.type, new IntType()))
			throw new CompilationError("Expression inside while statement could not be converted to int type.");
		this.e = e;
		this.a = null;
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("WHILE").append(Utility.NEW_LINE);
		String aa = e.deepToString(depth + 1);
		String bb = a.deepToString(depth + 1);
		return sb.append(aa).append(bb).toString();
	}

	@Override
	public String toString() {
		return null;
	}

	@Override
	public void emitCFG() {
		// TODO
		e.emitCFG();
		beginCFGBlock = e.beginCFGBlock;
		endCFGBlock = ControlFlowGraph.getNewVertex();
		a.emitCFG();

		beginCFGBlock.unconditionalNext = a.beginCFGBlock;
		beginCFGBlock.branchIfFalse = endCFGBlock;

		a.endCFGBlock.unconditionalNext = beginCFGBlock;
		a.endCFGBlock.branchIfFalse = null;
	}

	@Override
	public CFGVertex getLoop() {
		return beginCFGBlock;
	}

	@Override
	public CFGVertex getOut() {
		return endCFGBlock;
	}
}
