package Compiler2015.Environment;

import Compiler2015.AST.Initializers;
import Compiler2015.AST.SimpleInitializerList;
import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.AST.Statement.ExpressionStatement.StringConstant;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.*;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.util.*;

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
 *     ref = definition
 *     info = status
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
		if (name == null || name.equals(""))
			return null;
		try {
			return table.get(getUId(name).peek());
		} catch (NullPointerException e) {
			return null;
		} catch (EmptyStackException e) {
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
		if (e.ref != null && !e.ref.equals(t))
			throw new CompilationError("Already defined as another variable type.");
		if (!(e.ref instanceof FunctionType) && init != null) {
			SimpleInitializerList ini = (SimpleInitializerList) init;
			ArrayList<Integer> dimensions;
			Type type;
			if (e.ref instanceof ArrayPointerType) {
				if (ini.single != null) {
					if (ini.single instanceof StringConstant) {
						ini.list = new ArrayList<SimpleInitializerList>() {{
							add(new SimpleInitializerList(ini.single));
						}};
						ini.single = null;
					} else
						throw new CompilationError("Initializer dimension does not match.");
				}
				dimensions = ((ArrayPointerType) e.ref).dimensions;
				type = ((ArrayPointerType) e.ref).pointTo;
			} else {
				dimensions = new ArrayList<>();
				type = t;
			}
			e.info = new Initializers.Constructor().get(dimensions, ini, type);
			if (e.ref instanceof IntType && ((Initializers) e.info).entries.get(0).value.type instanceof ArrayPointerType && ((ArrayPointerType) ((Initializers) e.info).entries.get(0).value.type).pointTo instanceof CharType) {
				throw new CompilationError("FXXK");
			}
			if (e.ref instanceof ArrayPointerType && ((ArrayPointerType) e.ref).dimensions.get(0) == -1) {
				int max = -1;
				for (Initializers.InitEntry i : ((Initializers) e.info).entries) {
					max = Math.max(max, i.position[0]);
				}
				((ArrayPointerType) e.ref).dimensions.set(0, max + 1);
			}
		} else {
			e.info = init;
		}
		return e.uId;
	}

	/**
	 * Attention: global variables allows redefinition if there is at most one place that it is assigned the initial value.
	 *
	 * @param name name of the variable
	 * @param t    type of the variable
	 * @return uId of the function
	 */
	public int defineVariable(String name, Type t, Object init) {
		if (t instanceof VoidType)
			throw new CompilationError("Void-type variable is not allowed.");
		if (currentScope > 1 && t instanceof FunctionType && init == null && !name.equals("")) {
			// Special case: declare that there is a global function named $name
			SymbolTableEntry e = queryName(name);
			if (e != null)
				return e.uId;
			int uId = ++lastUId;
			table.add(new SymbolTableEntry(uId, name, 1, Tokens.VARIABLE, t, null));
			scopes.get(0).add(uId);
			getUId(name).push(uId);
			defineVariable(uId, t, null);
			return uId;
		}
		if (currentScope == 1) { // global
			SymbolTableEntry e = queryName(name);
			int uId;
			if (e != null && scopes.peek().contains(e.uId)) {
				uId = e.uId;
				defineVariable(uId, t, init);
			} else {
				uId = ++lastUId;
				table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.VARIABLE, t, null));
				scopes.peek().add(uId);
				if (name != null && !name.equals(""))
					getUId(name).push(uId);
				defineVariable(uId, t, init);
			}
			return uId;
		} else { // local
			SymbolTableEntry e = queryName(name);
			int uId;
			if (e != null && scopes.peek().contains(e.uId)) {
				throw new CompilationError("Symbol already defined.");
			} else {
				if (t instanceof StructOrUnionType && Environment.classTable.get(((StructOrUnionType) t).uId).info != Tokens.DEFINED)
					throw new CompilationError("Storage size is not known.");
				uId = ++lastUId;
				table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.VARIABLE, t, null));
				scopes.peek().add(uId);
				if (name != null && !name.equals(""))
					getUId(name).push(uId);
				defineVariable(uId, t, init);
			}
			return uId;
		}
	}

	/**
	 * @param name name of the function
	 * @param t    type of the function
	 * @return uId of the function
	 */
	public int defineLocalFunction(String name, Type t, Object init) {
		if (!(t instanceof FunctionType))
			throw new CompilationError("Internal Error.");
		SymbolTableEntry e = queryName(name);
		int uId;
		if (e != null && scopes.peek().contains(e.uId)) {
			throw new CompilationError("Symbol already defined.");
		} else {
			uId = ++lastUId;
			table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.VARIABLE, t, null));
			scopes.peek().add(uId);
			if (name != null && !name.equals(""))
				getUId(name).push(uId);
			defineVariable(uId, t, init);
		}
		return uId;
	}

	/**
	 * @param uId uId of the struct / union
	 */
	public int defineStructOrUnion(int uId) {
		SymbolTableEntry e = table.get(uId);
		if (e.type != Tokens.STRUCT_OR_UNION)
			throw new CompilationError("Symbol already defined as other types.");
		if (e.info == Tokens.DEFINED)
			throw new CompilationError("Struct / Union already defined: " + e.name);
		e.info = Tokens.DEFINED;
		return e.uId;
	}

	/**
	 * @param name name of the struct / union
	 * @param isUnion is it union or struct
	 */
	public int declareStructOrUnion(String name, boolean isUnion) {
		if (name == null || name.equals(""))
			name = "";
		SymbolTableEntry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			if (e.type != Tokens.STRUCT_OR_UNION)
				throw new CompilationError("Symbol already defined as other types.");
			if (((StructOrUnionType) e.ref).isUnion != isUnion)
				throw new CompilationError("Struct / Union tag mismatch.");
			return e.uId;
		} else {
			int uId = ++lastUId;
			StructOrUnionType newOne = new StructOrUnionType(uId, isUnion);
			table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.STRUCT_OR_UNION, newOne, Tokens.DECLARED));
			scopes.peek().add(uId);
			getUId(name).push(uId);
			return uId;
		}
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
			if (!e.ref.equals(ref))
				throw new CompilationError("Symbol already used.");
			return e.uId;
		} else {
			int uId = ++lastUId;
			table.add(new SymbolTableEntry(uId, name, currentScope, Tokens.TYPEDEF_NAME, ref, null));
			scopes.peek().add(uId);
			getUId(name).push(uId);
			return uId;
		}
	}

	/**
	 * @return variables in current scope
	 * FXXK Functional programming is not fully supported in Open JDK 1.8
	 */
	public ArrayList<Integer> getVariablesInCurrentScope() {
		ArrayList<Integer> ret = new ArrayList<>();
		for (int x : scopes.peek())
			if (table.get(x).type == Tokens.VARIABLE)
				ret.add(x);
		return ret;
//		return scopes.peek().stream().filter(x -> table.get(x).type == Tokens.VARIABLE)
//				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (SymbolTableEntry e : table) {
			if (e == null) continue;
			if (e.scope != 1) continue;
			if (e.type != Tokens.STRUCT_OR_UNION)
				sb.append(Utility.getIndent(1)).append("#").append(e.uId).append(": ");
			if (e.type == Tokens.VARIABLE) { // Variables
				sb.append(String.format("Variable(name = %s, type = %s)", e.name, e.ref.toString()));
				if (e.info != null) {
					if (e.ref instanceof FunctionType)
						sb.append(Utility.NEW_LINE).append(((CompoundStatement) e.info).deepToString(1));
					else
						sb.append(" init = ").append(e.info.toString());
				}
				if (e.info == null || !(e.ref instanceof FunctionType))
					sb.append(Utility.NEW_LINE);
			} else if (e.type == Tokens.STRUCT_OR_UNION) // Struct / Unions
				sb.append(((StructOrUnionType) e.ref).deepToString(1)).append(Utility.NEW_LINE);
			else if (e.type == Tokens.TYPEDEF_NAME) // Typedef Names
				sb.append(e.name).append(" -> ").append(e.ref.toString()).append(Utility.NEW_LINE);
		}
		return sb.toString();
	}


}
