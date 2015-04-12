package Compiler2015.AST.Statement.ExpressionStatement;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-11.
 *
 * a, b, c
 */
public class CommaExpression extends Expression {
	public ArrayList<Expression> expressions;
	public CommaExpression(ArrayList<Expression> expressions) {
		this.expressions = expressions;
	}
	public CommaExpression(ArrayList<Expression> expressions, Expression another) {
		this.expressions = expressions; // TODO: Is copy needed here?
		this.expressions.add(another);
	}
}
