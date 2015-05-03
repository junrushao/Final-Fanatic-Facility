package Compiler2015.Environment;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.Statement;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Environment {
	public static SymbolTable classNames;
	public static SymbolTable symbolNames;

	public static Stack<Statement> loopStack;
	public static Stack<Type> functionReturnStack;
	public static Stack<Integer> definedVariableInCurrentFrame;

	public static ArrayList<SymbolTableEntry> classTable;
	public static int totalTempRegisters;
	public static HashMap<Integer, Integer> variableDelta;

	public static Statement getTopLoop() {
		if (loopStack.isEmpty())
			throw new CompilationError("No loops(for / while) to manipulate.");
		return loopStack.peek();
	}

	public static void matchReturn(Type returnType) {
		if (functionReturnStack.isEmpty())
			throw new CompilationError("Could not return outside function scope.");
		Type x = functionReturnStack.peek();
		if (x instanceof VoidType && returnType instanceof VoidType)
			return;
		if (x instanceof VoidType || returnType instanceof VoidType)
			throw new CompilationError("Return type not match.");
		if (!CastExpression.castable(returnType, x))
			throw new CompilationError("Return type not match.");
	}

	public static void init() {
		classNames = new SymbolTable();
		symbolNames = new SymbolTable();
		loopStack = new Stack<>();
		functionReturnStack = new Stack<>();
		definedVariableInCurrentFrame = new Stack<>();
		classTable = classNames.table;
		totalTempRegisters = 0;
		variableDelta = new HashMap<>();

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
				"getchar",
				new FunctionType(
						new IntType(),
						new ArrayList<>(),
						new ArrayList<>(),
						false
				),
				null
		);
		symbolNames.defineVariable(
				"malloc",
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
		symbolNames.defineVariable(
				"printf",
				new FunctionType(
						new IntType(),
						new ArrayList<Type>() {{
							add(new VariablePointerType(new CharType()));
						}},
						new ArrayList<String>() {{
							add("format");
						}},
						true),
				null
		);
	}

	static {
		init();
	}

	public static void enterScope() {
		classNames.enterScope();
		symbolNames.enterScope();
	}

	public static void exitScope() {
		classNames.exitScope();
		symbolNames.exitScope();
	}

	public static boolean isVariable(String name) {
		SymbolTableEntry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.VARIABLE;
	}

	public static boolean isTypedefName(String name) {
		SymbolTableEntry e = symbolNames.queryName(name);
		return e != null && e.type == Tokens.TYPEDEF_NAME;
	}

	public static boolean isCompleteType(Type t) {
		if (t instanceof StructOrUnionType) {
			int uId = ((StructOrUnionType) t).uId;
			SymbolTableEntry e = classTable.get(uId);
			return e.info == Tokens.DEFINED;
		}
		return true;
	}

	public static String toStr() {
		return "Struct & Union:" + Utility.NEW_LINE + classNames.toString() + "Symbols:" + Utility.NEW_LINE + symbolNames.toString();
	}

	public static void finalCheck() {
		SymbolTableEntry e = symbolNames.queryName("main");
		if (e == null)
			throw new CompilationError("No main exists.");
	}
}
