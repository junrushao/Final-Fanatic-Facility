package Compiler2015.Environment;

import Compiler2015.Utility.Tokens;

/**
 */
public class Environment {
	public static SymbolTable classNames = new SymbolTable();
	public static SymbolTable symbolNames = new SymbolTable();

	static {
		symbolNames.defineFunction("printf", null);
		symbolNames.defineFunction("malloc", null);
	}

	public static void enterScope() {
		classNames.enterScope();
		symbolNames.enterScope();
	}

	public static void exitScope(boolean isStructOrUnionScope) {
		classNames.exitScope(false);
		classNames.exitScope(isStructOrUnionScope);
	}

	public static boolean isFunction(String name) {
		Entry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.FUNCTION;
	}

	public static boolean isVariable(String name) {
		Entry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.VARIABLE;
	}

	public static boolean isTypedefName(String name) {
		Entry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.TYPEDEF_NAME;
	}

	public static boolean isStructOrUnion(String name) {
		Entry e = classNames.queryName(name);
		return e != null && e.type == Tokens.STRUCT_OR_UNION;
	}

	public static boolean isUnused(String name) {
		return symbolNames.queryName(name) == null;
	}
}
