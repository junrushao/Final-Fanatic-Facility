package Compiler2015.Environment;

import Compiler2015.Utility.Tokens;

/**
 */
public class Environment {
	public static SymbolTable structUnion = new SymbolTable();
	public static SymbolTable others = new SymbolTable();

	static {
		others.defineName(Tokens.FUNCTION, "printf");
		others.defineName(Tokens.FUNCTION, "malloc");
	}

	public static boolean isFunction(String name) {
		return others.getType(name) == Tokens.FUNCTION;
	}

	public static boolean isVariable(String name) {
		return others.getType(name) == Tokens.VARIABLE;
	}
}
