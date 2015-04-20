package Compiler2015.AST.Statement.ExpressionStatement;

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
}
