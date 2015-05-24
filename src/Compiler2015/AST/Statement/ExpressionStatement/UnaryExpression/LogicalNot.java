package Compiler2015.AST.Statement.ExpressionStatement.UnaryExpression;

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
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.IntType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.VoidType;

/**
 * !e
 */
public class LogicalNot extends UnaryExpression implements Logical {
	public LogicalNot(Expression e) {
		super(e);
		this.type = IntType.instance;
	}

	public static Expression getExpression(Expression e) {
		if (e.type instanceof VoidType || e.type instanceof StructOrUnionType)
			throw new CompilationError("Incompatible type.");
		if (e.type instanceof ArrayPointerType)
			return new IntConstant(0);
		Integer v = Expression.toInt(e);
		if (v != null)
			return new IntConstant(v == 0 ? 1 : 0);
		return new LogicalNot(e);
	}

	@Override
	public String getOperator() {
		return "!";
	}

	@Override
	public void emitCFG(CFGVertex trueTo, CFGVertex falseTo) {
		if (e instanceof Logical) {
			Logical ep = (Logical) e;
			ep.emitCFG(falseTo, trueTo);
			beginCFGBlock = ep.getStartNode();
		}
		else {
			e.emitCFG();
			e.endCFGBlock.unconditionalNext = falseTo;
			e.endCFGBlock.branchIfFalse = trueTo;
			beginCFGBlock = e.beginCFGBlock;
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
	public LogicalNot clone() {
		return (LogicalNot) super.clone();
	}
}
