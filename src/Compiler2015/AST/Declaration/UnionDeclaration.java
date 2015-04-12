package Compiler2015.AST.Declaration;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-11.
 */
public class UnionDeclaration extends Declaration {
	/**
	 * begin with # if anonymous
	 */
	String name;

	/**
	 * Object[0] : Type
	 * Object[1] : String, name of the parameter, null if abstract
	 */
	ArrayList<Object[]> members;

	public UnionDeclaration(String name, ArrayList<Object[]> members) {
		this.name = name;
		this.members = members;
	}

	@Override
	public int sizeof() { // TODO
		return 0;
	}
}
