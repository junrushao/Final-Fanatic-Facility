package Compiler2015.Environment;

import Compiler2015.AST.Type.StructOrUnionType;
import Compiler2015.AST.Type.Type;
import Compiler2015.AST.Type.VoidType;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * For function
 *     ref = FunctionType
 *     info = statement
 * For variable
 *     ref = type
 *     info = initializer
 * For typedefName
 *     ref = type
 *     info = null
 * For struct / union
 *     ref = isUnion
 *     info = definition
 *
 * The operations in symbol table could be considered to has two main steps:
 *     + declaration
 *         declare some symbol exists for some use
 *     + definition
 *         define clearly what the use is
 *
 * 1. TypedefName is a special case, which always has a single step.
 * 2. In global scope, a variable could be declared several times but defined only once(or never)
 * 3. In fact, function is a special type of variable, so I do not take it into consideration
 *
 * In my implementation, "info = null" means something is declared but not defined.
 */
public class SymbolTable {
	public int currentScope = 0;
	public int lastUId = 0;
	public ArrayList<SymbolTableEntry> table = new ArrayList<SymbolTableEntry>() {{
		add(null); // table[0] remains null
	}};
	public HashMap<String, Stack<Integer>> name2UIds = new HashMap<>();
	public Stack<HashSet<Integer>> scopes = new Stack<>();

	public SymbolTable() {
		enterScope();
	}

	public Stack<Integer> getUId(String name) {
		Stack<Integer> ret = name2UIds.get(name);
		if (ret == null) {
			Stack<Integer> newOne = new Stack<>();
			name2UIds.put(name, newOne);
			return newOne;
		}
		return ret;
	}

	public int enterScope() {
		++currentScope;
		scopes.push(new HashSet<>());
		return currentScope;
	}

	public void exitScope() {
		HashSet<Integer> peek = scopes.pop();
		for (Integer uId : peek) {
			SymbolTableEntry e = table.get(uId);
			String name = e.name;
			int pop = getUId(name).pop();
			if (pop != uId)
				throw new CompilationError("Internal Error.");
		}
		--currentScope;
	}

	public SymbolTableEntry queryName(String name) {
		if (name == null)
			return null;
		try {
			return table.get(name2UIds.get(name).peek());
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * @param uId uId of the variable
	 * @param t type of the variable
	 * @return uId of the function
	 */
	public int defineVariable(int uId, Type t, Object init) {
		SymbolTableEntry e = table.get(uId);
		if (e.type != Tokens.VARIABLE)
			throw new CompilationError("Symbol already defined as other types.");
		if (e.info != null && init != null)
			throw new CompilationError("Variable already defined.");
		if (!e.ref.equals(t))
			throw new CompilationError("Already defined as another variable type.");
		e.info = init;
		return e.uId;
	}

	/**
	 * TODO : misbehave in local scope in function declaration
	 * Attention: global variables allows redefinition if there is at most one place that it is assigned the initial value.
	 *
	 * @param name name of the variable
	 * @param t    type of the variable
	 * @return uId of the function
	 */
	public int defineVariable(String name, Type t, Object init) {
		if (currentScope == 1) { // global
			if (name == null || name.equals(""))
				throw new CompilationError("Name of variable is not allowed to be empty.");
			if (t instanceof VoidType)
				throw new CompilationError("Void-type variable is not allowed.");
			SymbolTableEntry e = queryName(name);
			int uId;
			if (e != null && scopes.peek().contains(e.uId)) {
				uId = e.uId;
				defineVariable(uId, t, init);
			} else {
				uId = ++lastUId;
				table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.VARIABLE, t, null));
				defineVariable(uId, t, init);
			}
			return uId;
		} else { // local
			if (name == null || name.equals(""))
				throw new CompilationError("Name of variable is not allowed to be empty.");
			if (t instanceof VoidType)
				throw new CompilationError("Void-type variable is not allowed.");
			SymbolTableEntry e = queryName(name);
			int uId;
			if (e != null && scopes.peek().contains(e.uId)) {
				throw new CompilationError("Symbol already defined.");
			} else {
				uId = ++lastUId;
				table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.VARIABLE, t, null));
				defineVariable(uId, t, init);
			}
			return uId;
		}
	}

	/**
	 * @param uId uId of the struct / union
	 * @param isUnion is union or struct
	 * @param t type of the struct
	 */
	public int defineStructOrUnion(int uId, boolean isUnion, StructOrUnionType t) {
		SymbolTableEntry e = table.get(uId);
		if (e.type != Tokens.STRUCT_OR_UNION)
			throw new CompilationError("Symbol already defined as other types.");
		if ((boolean) e.ref != isUnion)
			throw new CompilationError("Struct / Union tag mismatch.");
		if (!t.equals(e.info))
			throw new CompilationError("Struct / Union already defined");
		return e.uId;
	}

	/**
	 * @param name name of the struct / union
	 * @param isUnion is union or struct
	 * @param type type of the struct
	 */
	public int defineStructOrUnion(String name, boolean isUnion, Type type) {
		if (!(type instanceof StructOrUnionType))
			throw new CompilationError("Should be a function type.");
		StructOrUnionType t = (StructOrUnionType) type;
		if (name == null || name.equals(""))
			throw new CompilationError("Name of function is not allowed to be empty.");
		SymbolTableEntry e = queryName(name);
		int uId;
		if (e != null && scopes.peek().contains(e.uId)) {
			uId = e.uId;
			defineStructOrUnion(uId, isUnion, t);
		} else {
			uId = ++lastUId;
			table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.STRUCT_OR_UNION, t, null));
			defineStructOrUnion(uId, isUnion, t);
		}
		return uId;
	}

	/**
	 * @param name typedef name
	 * @param ref the original type name
	 * @return uId of the name
	 */
	public int defineTypedefName(String name, Type ref) {
		if (name == null || name.equals(""))
			throw new CompilationError("Typedef name should be non-empty.");
		SymbolTableEntry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			throw new CompilationError("Symbol already used.");
		} else {
			int uId = ++lastUId;
			table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.STRUCT_OR_UNION, ref, null));
			scopes.peek().add(uId);
			getUId(name).push(uId);
			return uId;
		}
	}

	/**
	 * @return variables in current scope
	 */
	public ArrayList<Integer> getVariablesInCurrentScope() {
		return scopes.peek().stream().filter(x -> table.get(x).type == Tokens.VARIABLE)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (SymbolTableEntry e : table) {
			if (e == null) continue;
			if (e.scope != 1) continue;
			if (e.type == Tokens.VARIABLE) {
				sb.append('(').append(e.ref).append(", ").append(e.name).append(")");
				if (e.info != null)
					sb.append(" init = ").append(e.info.toString());
				sb.append(Utility.NEW_LINE);
			} else if (e.type == Tokens.STRUCT_OR_UNION)
				sb.append(e.ref.toString());
//			else if (e.type == Tokens.FUNCTION)
//				sb.append(e.ref.toString());
		}
		return sb.toString();
	}

	// TODO
	public void checkIncompleteVariableTypeInCurrentScope() {
	}
}
