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
 * if (e) ifTrue else ifFalse
 */
public class IfStatement extends Statement {
	public Expression e;
	public Statement ifTrue, ifFalse;

	public IfStatement(Expression e, Statement ifTrue, Statement ifFalse) {
		if (!CastExpression.castable(e.type, IntType.instance))
			throw new CompilationError("Expression inside if statement could not be converted to int type.");
		this.e = e;
		this.ifTrue = ifTrue != null ? ifTrue : new CompoundStatement(new ArrayList<>(0), new ArrayList<>(0), new ArrayList<>(0), new ArrayList<>(0));
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
		Integer v = Constant.toInt(e);
		if (v != null) {
			if (v == 0) {
				if (ifFalse == null) {
					beginCFGBlock = endCFGBlock = ControlFlowGraph.instance.getNewVertex();
				} else {
					ifFalse.emitCFG();
					beginCFGBlock = ifFalse.beginCFGBlock;
					endCFGBlock = ifFalse.endCFGBlock;
				}
			} else {
				ifTrue.emitCFG();
				beginCFGBlock = ifTrue.beginCFGBlock;
				endCFGBlock = ifTrue.endCFGBlock;
			}
		} else {
			CFGVertex out = endCFGBlock = ControlFlowGraph.instance.getNewVertex();
			ifTrue.emitCFG();
			if (ifTrue.endCFGBlock.unconditionalNext == null)
				ifTrue.endCFGBlock.unconditionalNext = out;

			CFGVertex Then = ifTrue.beginCFGBlock;
			CFGVertex Else = out;
			if (ifFalse != null) {
				ifFalse.emitCFG();
				if (ifFalse.endCFGBlock.unconditionalNext == null)
					ifFalse.endCFGBlock.unconditionalNext = out;
				Else = ifFalse.beginCFGBlock;
			}
			if (e instanceof Logical) {
				Logical ep = (Logical) e;
				ep.emitCFG(Then, Else);
				beginCFGBlock = ep.getStartNode();
			} else {
				e.emitCFG();
				beginCFGBlock = e.beginCFGBlock;
				e.endCFGBlock.unconditionalNext = Then;
				e.endCFGBlock.branchIfFalse = Else;
			}
		}
	}

	@Override
	public Statement rebuild() {
		if (e != null)
			e = e.rebuild();
		if (ifTrue != null)
			ifTrue = ifTrue.rebuild();
		if (ifFalse != null)
			ifFalse = ifFalse.rebuild();
		return this;
	}
}
