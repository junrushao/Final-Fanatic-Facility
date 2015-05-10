package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Constant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Type.IntType;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;

/**
 * while (e) a;
 */
public class WhileStatement extends Statement implements Loop {
	public Expression e;
	public Statement a;

	public WhileStatement(Expression e) {
		if (!CastExpression.castable(e.type, IntType.instance))
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
		Integer v = Constant.toInt(e);
		if (a == null)
			a = new CompoundStatement(new ArrayList<>(0), new ArrayList<>(0), new ArrayList<>(0));
		if (v != null) {
			if (v == 0) {
				beginCFGBlock = endCFGBlock = ControlFlowGraph.getNewVertex();
			} else {
				CFGVertex loop = beginCFGBlock = ControlFlowGraph.getNewVertex();
				endCFGBlock = ControlFlowGraph.getNewVertex();
				a.emitCFG();
				beginCFGBlock.unconditionalNext = a.beginCFGBlock;
				if (a.endCFGBlock.unconditionalNext == null)
					a.endCFGBlock.unconditionalNext = loop;
			}
			return;
		}
		CFGVertex loop = beginCFGBlock = ControlFlowGraph.getNewVertex();
		CFGVertex out = endCFGBlock = ControlFlowGraph.getNewVertex();
		a.emitCFG();
		if (a.endCFGBlock.unconditionalNext == null)
			a.endCFGBlock.unconditionalNext = loop;
		if (e instanceof Logical) {
			Logical ep = (Logical) e;
			ep.emitCFG(a.beginCFGBlock, out);
			loop.unconditionalNext = ep.getStartNode();
		}
		else {
			e.emitCFG();
			e.endCFGBlock.unconditionalNext = a.beginCFGBlock;
			e.endCFGBlock.branchIfFalse = out;
			loop.unconditionalNext = e.beginCFGBlock;
		}
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
