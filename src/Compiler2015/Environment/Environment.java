package Compiler2015.Environment;

import Compiler2015.AST.Type.FunctionType;
import Compiler2015.AST.Type.IntType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Utility.Tokens;

import java.util.ArrayList;

public class Environment {
	public static SymbolTable classNames = new SymbolTable();
	public static SymbolTable symbolNames = new SymbolTable();

	static {
		symbolNames.defineVariable(
				".putchar",
				new FunctionType(
						new IntType(),
						new ArrayList<Type>() {{
							add(new IntType());
						}},
						new ArrayList<String>() {{
							add("c");
						}},
						false
				),
				null
		);
		symbolNames.defineVariable(
				".getchar",
				new FunctionType(
						new IntType(),
						new ArrayList<Type>() {{
							add(new IntType());
						}},
						new ArrayList<String>() {{
							add("c");
						}},
						false
				),
				null
		);
		symbolNames.defineVariable(
				".malloc",
				new FunctionType(
						new IntType(),
						new ArrayList<Type>() {{
							add(new IntType());
						}},
						new ArrayList<String>() {{
							add("c");
						}},
						false),
				null
		);
	}

	public static void enterScope() {
		classNames.enterScope();
		symbolNames.enterScope();
	}

	public static void exitScope() {
		classNames.exitScope();
		classNames.exitScope();
	}

	public static boolean isVariable(String name) {
		SymbolTableEntry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.VARIABLE;
	}

	public static boolean isTypedefName(String name) {
		SymbolTableEntry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.TYPEDEF_NAME;
	}
}
