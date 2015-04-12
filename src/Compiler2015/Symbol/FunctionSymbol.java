package Compiler2015.Symbol;

import Compiler2015.AST.Type.Type;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-12.
 */
public class FunctionSymbol {
	String name;
	Type returnType;
	ArrayList<Type> paremeters;

	public FunctionSymbol(String name, Type returnType, ArrayList<Type> paremeters) {
		this.name = name;
		this.returnType = returnType;
		this.paremeters = paremeters;
	}

}
