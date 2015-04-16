package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * if (e) ifTrue else ifFalse
 */

public class IfStatement extends Statement {
	public Expression e;
	public Statement ifTrue, ifFalse;
	public IfStatement(Expression e, Statement ifTrue, Statement ifFalse) {
		this.e = e;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}
}
