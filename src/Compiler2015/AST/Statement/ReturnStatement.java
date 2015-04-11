package Compiler2015.AST.Statement;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

/**
 * Created by junrushao on 15-4-11.
 *
 * return e
 */
public class ReturnStatement extends Statement {
	public Expression e;
	public ReturnStatement(Expression e) {
		this.e = e;
	}
}
