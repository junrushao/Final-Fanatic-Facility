package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Utility.Utility;

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
	public String toString(int depth) {
		return Utility.getIndent(depth).append("Conditional Expression").append(Utility.NEW_LINE).append(condition.toString(depth + 1)).append(ifTrue.toString(depth + 1)).append(ifFalse.toString(depth + 1)).append(Utility.NEW_LINE).toString();
	}
}
