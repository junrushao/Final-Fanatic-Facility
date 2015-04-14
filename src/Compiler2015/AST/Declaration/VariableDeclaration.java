package Compiler2015.AST.Declaration;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-11.
 */
public class VariableDeclaration extends Declaration {
	public int uId;
	public Type type;
	public String name;

	public VariableDeclaration(int uId, Type type, String name) {
		this.uId = uId;
		this.type = type;
		this.name = name;
	}

}
