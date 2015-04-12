package Compiler2015.Symbol;

import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-12.
 */
public class TypeSymbol {
	String name;
	Type originalType;

	public TypeSymbol(String name, Type originalType) {
		this.name = name;
		this.originalType = originalType;
	}
}
