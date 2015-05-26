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
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Move;
import Compiler2015.Type.*;

/**
 * a || b
 */
public class LogicalOr extends BinaryExpression implements Logical {
	public LogicalOr(Expression left, Expression right) {
		super(left, right);
	}

	public static Expression getExpression(Expression a1, Expression a2) {
		if (a1.type instanceof VoidType || a2.type instanceof VoidType)
			throw new CompilationError("Type Error.");
		if (a1.type instanceof IntType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof CharType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof StructOrUnionType) {
			if (a2.type instanceof IntType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof CharType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof FunctionPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof ArrayPointerType)
				throw new CompilationError("Type Error");
			if (a2.type instanceof VariablePointerType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof FunctionType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof FunctionPointerType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof ArrayPointerType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		if (a1.type instanceof VariablePointerType) {
			if (a2.type instanceof StructOrUnionType)
				throw new CompilationError("Type Error");
		}
		Integer v1 = toInt(a1), v2 = toInt(a2);
		if (v1 != null && v2 != null)
			return new IntConstant(((v1 != 0) || (v2 != 0)) ? 1 : 0);
		if (v1 != null && v1 != 0)
			return new IntConstant(1);
		if (!(a1.type instanceof IntType))
			a1 = new CastExpression(IntType.instance, a1);
		if (!(a2.type instanceof IntType))
			a2 = new CastExpression(IntType.instance, a2);
		return new LogicalOr(a1, a2);
	}

	@Override
	public String getOperator() {
		return "||";
	}

	@Override
	public void emitCFG(CFGVertex trueTo, CFGVertex falseTo) {
		if (left instanceof Logical && right instanceof Logical) {
			Logical leftP = (Logical) left;
			Logical rightP = (Logical) right;
			rightP.emitCFG(trueTo, falseTo);
			leftP.emitCFG(trueTo, rightP.getStartNode());
			beginCFGBlock = leftP.getStartNode();
		}
		else if (left instanceof Logical) { // left is Logical but right is not
			Logical leftP = (Logical) left;
			right.emitCFG();
			right.endCFGBlock.unconditionalNext = trueTo;
			right.setBranch(falseTo, right.tempRegister);
//			right.endCFGBlock.branchIfFalse = falseTo;
			leftP.emitCFG(trueTo, right.beginCFGBlock);
			beginCFGBlock = leftP.getStartNode();
		}
		else if (right instanceof Logical) { // left is not Logical but right is
			Logical rightP = (Logical) right;
			rightP.emitCFG(trueTo, falseTo);
			left.emitCFG();
			left.endCFGBlock.unconditionalNext = trueTo;
			left.setBranch(right.beginCFGBlock, left.tempRegister);
//			left.endCFGBlock.branchIfFalse = right.beginCFGBlock;
			beginCFGBlock = left.beginCFGBlock;
		}
		else { // left is not Logical while right is not neither
			left.emitCFG();
			right.emitCFG();
			left.endCFGBlock.unconditionalNext = trueTo;
			left.setBranch(right.beginCFGBlock, left.tempRegister);
//			left.endCFGBlock.branchIfFalse = right.beginCFGBlock;
			right.endCFGBlock.unconditionalNext = trueTo;
			right.setBranch(falseTo, right.tempRegister);
//			right.endCFGBlock.branchIfFalse = falseTo;
			beginCFGBlock = left.beginCFGBlock;
		}
	}

	@Override
	public CFGVertex getStartNode() {
		return beginCFGBlock;
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		CFGVertex out = endCFGBlock = ControlFlowGraph.getNewVertex();
		CFGVertex trueTo = ControlFlowGraph.getNewVertex();
		CFGVertex falseTo = ControlFlowGraph.getNewVertex();

		tempRegister = Environment.getVirtualRegister();
		trueTo.internal.add(new Move((VirtualRegister) tempRegister, new ImmediateValue(1)));
		falseTo.internal.add(new Move((VirtualRegister) tempRegister, new ImmediateValue(0)));

		trueTo.unconditionalNext = out;
		falseTo.unconditionalNext = out;
		emitCFG(trueTo, falseTo);

		builder.addBlock(beginCFGBlock, endCFGBlock);
	}

	@Override
	public Expression rebuild() {
		return new LogicalOr(left.rebuild(), right.rebuild());
	}
}
