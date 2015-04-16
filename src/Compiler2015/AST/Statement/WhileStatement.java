package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * while (e) a;
 */
public class WhileStatement extends Statement {
	public Expression e;
	public Statement a;
	public WhileStatement(Expression e, Statement a) {
		this.e = e;
		this.a = a;
	}
}
