package Compiler2015.AST;

import Compiler2015.AST.Statement.ExpressionStatement.Expression;

import java.util.ArrayList;

public class Initializer {
	public Expression single;
	public ArrayList<Initializer> list;

	public Initializer(Expression single) {
		this.single = single;
		this.list = null;
	}

	public Initializer(ArrayList<Initializer> list) {
		this.single = null;
		this.list = list;
	}
}
