package Compiler2015.Environment;

import Compiler2015.AST.Statement.ExpressionStatement.CastExpression;
import Compiler2015.AST.Statement.Loop;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.*;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class Environment {
	public static SymbolTable classNames;
	public static SymbolTable symbolNames;

	public static Stack<Loop> loopStack;
	public static Stack<Type> functionReturnStack;
	public static Stack<Integer> definedVariableInCurrentFrame;

	public static ArrayList<SymbolTableEntry> classTable;
	public static int _pretend_being_private_sizeof;
	public static int uIdOfPutChar;
	public static int uIdOfGetChar;
	public static int uIdOfMalloc;
	public static int uIdOfPutString;
	public static int uIdOfPutInt;
	public static HashSet<Integer> globalNonArrayVariablesAndLocalAddressFetchedVariables;

	static {
		init();
	}

	public static VirtualRegister getVirtualRegister() {
		return symbolNames.defineVirtualRegister();
	}

	public static Loop getTopLoop() {
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

	public static boolean isLibraryFunctions(int uId) {
		return uId == uIdOfPutChar || uId == uIdOfGetChar || uId == uIdOfMalloc || uId == uIdOfPutString || uId == uIdOfPutInt;
	}

	public static void init() {
		classNames = new SymbolTable();
		symbolNames = new SymbolTable();
		loopStack = new Stack<>();
		functionReturnStack = new Stack<>();
		definedVariableInCurrentFrame = new Stack<>();
		classTable = classNames.table;
		globalNonArrayVariablesAndLocalAddressFetchedVariables = new HashSet<>();

		uIdOfPutChar = symbolNames.defineVariable(
				"putchar",
				new FunctionType(
						IntType.instance,
						new ArrayList<Type>() {{
							add(IntType.instance);
						}},
						new ArrayList<String>() {{
							add("c");
						}},
						false
				)
		);
		uIdOfGetChar = symbolNames.defineVariable(
				"getchar",
				new FunctionType(
						IntType.instance,
						new ArrayList<>(),
						new ArrayList<>(),
						false
				)
		);
		uIdOfMalloc = symbolNames.defineVariable(
				"malloc",
				new FunctionType(
						IntType.instance,
						new ArrayList<Type>() {{
							add(IntType.instance);
						}},
						new ArrayList<String>() {{
							add("c");
						}},
						false)
		);
		uIdOfPutString = symbolNames.defineVariable(
				"___yzgysjr_lib_putstring",
				new FunctionType(
						VoidType.instance,
						new ArrayList<Type>() {{
							add(new VariablePointerType(CharType.instance));
						}},
						new ArrayList<String>() {{
							add("c");
						}},
						false)
		);
		uIdOfPutInt = symbolNames.defineVariable(
				"___yzgysjr_lib_putint",
				new FunctionType(
						VoidType.instance,
						new ArrayList<Type>() {{
							add(new VariablePointerType(CharType.instance));
						}},
						new ArrayList<String>() {{
							add("c");
						}},
						false)
		);
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
		if (e != null && e.type == Tokens.TYPEDEF_NAME) {
			_pretend_being_private_sizeof = ((Type) e.ref).sizeof();
			return true;
		}
		return false;
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
