package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * for (a; b; c) d
 */
public class ForStatement extends Statement {
	public Expression a, b, c;
	public Statement d;
	public ForStatement(Expression a, Expression b, Expression c, Statement d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
}
