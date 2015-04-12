package Compiler2015.AST.Declaration;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class VariableDeclaration extends Declaration {
	Type type;
	String name;

	public VariableDeclaration(Type type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public int sizeof() { // TODO
		return 0;
	}
}
