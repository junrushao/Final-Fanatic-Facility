package Compiler2015.Environment;

import Compiler2015.AST.Type.*;
import Compiler2015.Utility.Tokens;

import java.util.ArrayList;

public class Environment {
	public static SymbolTable classNames = new SymbolTable();
	public static SymbolTable symbolNames = new SymbolTable();

	static {
		symbolNames.defineFunction(
				"...putchar...",
				new VoidType(),
				new ArrayList<Type>() {{
					add(new CharType());
				}},
				new ArrayList<String>() {{
					add("x");
				}},
				false,
				null
		);
		symbolNames.defineFunction(
				"...getchar...",
				new VoidType(),
				new ArrayList<Type>() {{
					add(new CharType());
				}},
				new ArrayList<String>() {{
					add("x");
				}},
				false,
				null
		);
		symbolNames.defineFunction(
				"...malloc...",
				new VariablePointerType(new VoidType()),
				new ArrayList<Type>() {{
					add(new IntType());
				}},
				new ArrayList<String>() {{
					add("x");
				}},
				false,
				null
		);
	}

	public static void enterScope() {
		classNames.enterScope();
		symbolNames.enterScope();
	}

	public static void exitScope(boolean isStructOrUnionScope) {
		classNames.exitScope(false);
		classNames.exitScope(isStructOrUnionScope);
	}

	public static boolean isVariable(String name) {
		Entry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.VARIABLE;
	}

	public static boolean isTypedefName(String name) {
		Entry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.TYPEDEF_NAME;
	}

}
