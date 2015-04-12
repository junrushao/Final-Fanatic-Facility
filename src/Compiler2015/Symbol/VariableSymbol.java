package Compiler2015.Symbol;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-12.
 */
public class VariableSymbol {
	String name;
	Type type;

	public VariableSymbol(String name, Type type) {
		this.name = name;
		this.type = type;
	}
}
