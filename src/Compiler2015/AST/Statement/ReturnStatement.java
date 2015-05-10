package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression.Assign;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IdentifierExpression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.Instruction.Pop;
import Compiler2015.Utility.Utility;

/**
 * return e
 */
public class ReturnStatement extends Statement {
	public Expression e;
	public ReturnStatement(Expression e) {
		this.e = e;
	}

	@Override
	public String deepToString(int depth) {
		return Utility.getIndent(depth).append(toString()).toString();
	}

	@Override
	public String toString() {
		String s = e == null ? "null" : e.toString();
		return "return " + s + Utility.NEW_LINE;
	}

	@Override
	public void emitCFG() {
		if (e == null) {
			beginCFGBlock = endCFGBlock = ControlFlowGraph.getNewVertex();
			endCFGBlock.internal.add(Pop.instance);
		}
		else {
			ExpressionCFGBuilder builder = new ExpressionCFGBuilder();
			Expression ee = new Assign(IdentifierExpression.getExpression(ControlFlowGraph.scope.returnUId), e);
			ee.emitCFG(builder);
			beginCFGBlock = builder.s;
			endCFGBlock = builder.t;
			if (endCFGBlock.unconditionalNext != null || endCFGBlock.branchIfFalse != null)
				throw new CompilationError("Internal Error.");
			endCFGBlock.internal.add(Pop.instance);
		}
		endCFGBlock.unconditionalNext = ControlFlowGraph.outBody;
	}
}
