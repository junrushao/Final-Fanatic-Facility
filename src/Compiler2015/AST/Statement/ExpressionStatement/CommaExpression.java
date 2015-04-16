package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.Type;

import java.util.ArrayList;

/**
 * a, b, c
 */
public class CommaExpression extends Expression {
	public ArrayList<Expression> expressions;
	public CommaExpression(ArrayList<Expression> expressions, Type type, boolean isLValue) {
		super(type, isLValue);
		this.expressions = expressions;
	}
}
