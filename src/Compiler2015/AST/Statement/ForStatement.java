package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Constant;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.Type.IntType;
import Compiler2015.Utility.Utility;

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
		return loop;
	}

	@Override
	public CFGVertex getOut() {
		return endCFGBlock;
	}

	@Override
	public String deepToString(int depth) {
		StringBuilder sb = Utility.getIndent(depth).append("FOR").append(Utility.NEW_LINE);
		StringBuilder indent = Utility.getIndent(depth + 1);
		String aa = a == null ? indent.append("null").toString() : a.deepToString(depth + 1);
		String bb = b == null ? indent.append("null").toString() : b.deepToString(depth + 1);
		String cc = c == null ? indent.append("null").toString() : c.deepToString(depth + 1);
		String dd = d == null ? indent.append("null").toString() : d.deepToString(depth + 1);
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
		d.emitCFG();
		if (c != null) {
			c.emitCFG();
			d.endCFGBlock.unconditionalNext = c.beginCFGBlock;
			c.endCFGBlock = loop;
		}
		else {
			d.endCFGBlock.unconditionalNext = loop;
		}
		if (b != null) {
			if (b instanceof Logical) {
				Logical bp = (Logical) b;
				ExpressionCFGBuilder builder = new ExpressionCFGBuilder();
				bp.emitCFG(d.beginCFGBlock, out, builder);
				loop.unconditionalNext = builder.s;
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
}
