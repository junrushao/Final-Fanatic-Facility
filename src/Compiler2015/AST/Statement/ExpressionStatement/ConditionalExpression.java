package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.IR.CFG.CFGVertex;

/**
 * a ? b : c
 */
public class ConditionalExpression extends Expression {
	public Expression condition, ifTrue, ifFalse;

	public ConditionalExpression(Expression condition, Expression ifTrue, Expression ifFalse) {
		this.type = ifTrue.type;
		this.isLValue = ifTrue.isLValue;
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	@Override
	public String toString() {
		return String.format("(%s ? %s : %s)", condition, ifTrue, ifFalse);
	}

	@Override
	public void emitCFG(CFGVertex fromHere) {
		// TODO
	}
}
