package Compiler2015.AST.Statement.ExpressionStatement;

public abstract class Constant extends Expression {
	public Constant() {
		this.isLValue = false;
	}
}
