package Compiler2015.AST.Statement.ExpressionStatement.BinaryExpression;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.ExpressionStatement.Expression;
import Compiler2015.AST.Statement.ExpressionStatement.IntConstant;
import Compiler2015.AST.Statement.Logical;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.MoveConstant;
import Compiler2015.Type.IntType;
import Compiler2015.Type.Type;

/**
 * a || b
 */
public class LogicalOr extends BinaryExpression implements Logical {
	public LogicalOr(Expression left, Expression right) {
		super(left, right);
	}

	@Override
	public String getOperator() {
		return "||";
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (!Type.isNumeric(a1.type) || !Type.isNumeric(a2.type))
			throw new CompilationError("|| must be operated on numeric types");
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(((v1 != 0) || (v2 != 0)) ? 1 : 0);
		if (v1 != null && v1 != 0)
			return new IntConstant(1);
		if (!(a1.type instanceof IntType))
			a1 = new CastExpression(new IntType(), a1);
		if (!(a2.type instanceof IntType))
			a2 = new CastExpression(new IntType(), a2);
		return new LogicalOr(a1, a2);
	}

	@Override
	public void emitCFG(CFGVertex trueTo, CFGVertex falseTo, ExpressionCFGBuilder builder) {
		if (left instanceof Logical && right instanceof Logical) {
			Logical leftP = (Logical) left;
			Logical rightP = (Logical) right;
			rightP.emitCFG(trueTo, falseTo, builder);
			leftP.emitCFG(trueTo, rightP.getStartNode(), builder);
		}
		else if (left instanceof Logical) { // left is Logical but right is not
			Logical leftP = (Logical) left;
			right.emitCFG(builder);
			right.endCFGBlock.unconditionalNext = trueTo;
			right.endCFGBlock.branchIfFalse = falseTo;
			leftP.emitCFG(trueTo, right.beginCFGBlock, builder);
		}
		else if (right instanceof Logical) { // left is not Logical but right is
			Logical rightP = (Logical) right;
			rightP.emitCFG(trueTo, falseTo, builder);
			left.emitCFG(builder);
			left.endCFGBlock.unconditionalNext = trueTo;
			left.endCFGBlock.branchIfFalse = right.beginCFGBlock;
		}
		else { // left is not Logical while right is not neither
			left.emitCFG(builder);
			right.emitCFG(builder);
			left.endCFGBlock.unconditionalNext = trueTo;
			left.endCFGBlock.branchIfFalse = right.beginCFGBlock;
			right.endCFGBlock.unconditionalNext = trueTo;
			right.endCFGBlock.branchIfFalse = falseTo;
		}
	}

	@Override
	public CFGVertex getStartNode() {
		return null;
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		CFGVertex out = endCFGBlock = ControlFlowGraph.getNewVertex();
		CFGVertex trueTo = ControlFlowGraph.getNewVertex();
		CFGVertex falseTo = ControlFlowGraph.getNewVertex();

		tempRegister = Environment.getTemporaryRegister();
		trueTo.internal.add(new MoveConstant(tempRegister, 1));
		falseTo.internal.add(new MoveConstant(tempRegister, 0));

		trueTo.unconditionalNext = out;
		falseTo.unconditionalNext = out;
		emitCFG(trueTo, falseTo, builder);

		builder.addBlock(endCFGBlock);
	}
}
