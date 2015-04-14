package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-11.
 *
 * a ? b : c
 */
public class ConditionalExpression extends Expression {
	public Expression condition, ifTrue, ifFalse;
	public ConditionalExpression(Expression condition, Expression ifTrue, Expression ifFalse, Type type, boolean isLValue) {
		super(type, isLValue);
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}
}
