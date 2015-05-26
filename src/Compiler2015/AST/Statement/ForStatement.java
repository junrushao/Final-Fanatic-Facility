package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Constant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.IntType;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * for (a; b; c) d
 */
public class ForStatement extends Statement implements Loop {
	public Expression a, b, c;
	public Statement d;
	public CFGVertex loop;

	public ForStatement(Expression a, Expression b, Expression c) {
		if (b != null && !CastExpression.castable(b.type, IntType.instance))
			throw new CompilationError("Expression inside for statement could not be converted to int type.");
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = null;
	}

	@Override
	public CFGVertex getLoop() {
		return c != null ? c.beginCFGBlock : loop;
	}

	@Override
	public CFGVertex getOut() {
		return endCFGBlock;
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("FOR").append(Utility.NEW_LINE);
		String aa = a == null ? Utility.getIndent(depth + 1).append("null").append(Utility.NEW_LINE).toString() : a.deepToString(depth + 1);
		String bb = b == null ? Utility.getIndent(depth + 1).append("null").append(Utility.NEW_LINE).toString() : b.deepToString(depth + 1);
		String cc = c == null ? Utility.getIndent(depth + 1).append("null").append(Utility.NEW_LINE).toString() : c.deepToString(depth + 1);
		String dd = d == null ? Utility.getIndent(depth + 1).append("null").append(Utility.NEW_LINE).toString() : d.deepToString(depth + 1);
		return sb.append(aa)
				.append(bb)
				.append(cc)
				.append(dd).toString();
	}

	@Override
	public String toString() {
		return deepToString(0);
	}

	@Override
	public void emitCFG() {
		CFGVertex out = endCFGBlock = ControlFlowGraph.getNewVertex();
		loop = ControlFlowGraph.getNewVertex();
		Integer vb = Constant.toInt(b);
		if (vb != null) {
			if (vb == 0) {
				if (a != null) {
					a.emitCFG();
					beginCFGBlock = a.beginCFGBlock;
					endCFGBlock = a.endCFGBlock;
				} else {
					beginCFGBlock = endCFGBlock = ControlFlowGraph.getNewVertex();
				}
				return;
			} else {
				b = null;
			}
		}
		if (a != null) {
			a.emitCFG();
			beginCFGBlock = a.beginCFGBlock;
			a.endCFGBlock.unconditionalNext = loop;
		}
		else {
			beginCFGBlock = loop;
		}
		if (c != null)
			c.emitCFG();
		if (d == null)
			d = new CompoundStatement(new ArrayList<>(0), new ArrayList<>(0), new ArrayList<>(0), new ArrayList<>(0));
		d.emitCFG();
		if (c != null) {
//			c.emitCFG();
			if (d.endCFGBlock.unconditionalNext == null)
				d.endCFGBlock.unconditionalNext = c.beginCFGBlock;
			c.endCFGBlock.unconditionalNext = loop;
		}
		else {
			if (d.endCFGBlock.unconditionalNext == null)
				d.endCFGBlock.unconditionalNext = loop;
		}
		if (b != null) {
			if (b instanceof Logical) {
				Logical bp = (Logical) b;
				bp.emitCFG(d.beginCFGBlock, out);
				loop.unconditionalNext = bp.getStartNode();
			}
			else {
				b.emitCFG();
				b.endCFGBlock.unconditionalNext = d.beginCFGBlock;
				b.endCFGBlock.branchIfFalse = out;
				loop.unconditionalNext = b.beginCFGBlock;
			}
		}
		else {
			loop.unconditionalNext = d.beginCFGBlock;
		}
	}

	@Override
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {
		if (a != null)
			a.collectGlobalNonArrayVariablesUsed(dumpTo);
		if (b != null)
			b.collectGlobalNonArrayVariablesUsed(dumpTo);
		if (c != null)
			c.collectGlobalNonArrayVariablesUsed(dumpTo);
		if (d != null)
			d.collectGlobalNonArrayVariablesUsed(dumpTo);
	}
}
