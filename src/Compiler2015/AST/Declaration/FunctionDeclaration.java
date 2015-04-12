package Compiler2015.AST.Declaration;

import Compiler2015.AST.Type.Type;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-11.
 */
public class FunctionDeclaration extends Declaration {

	String name;

	/**
	 * IntType if not declared
	 */
	Type returnType;

	/**
	 * Object[0] : Type
	 * Object[1] : String, name of the parameter, null if abstract
	 */
	ArrayList<Object[]> parameters;

	public FunctionDeclaration(String name, Type returnType, ArrayList<Object[]> parameters) {
		this.name = name;
		this.returnType = returnType;
		this.parameters = parameters;
	}

	@Override
	public int sizeof() { // TODO
		return 0;
	}
}
