package Compiler2015.Environment;

import Compiler2015.AST.Declaration.FunctionDeclaration;
import Compiler2015.AST.Declaration.StructOrUnionDeclaration;
import Compiler2015.AST.Type.Type;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Tokens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Record the symbols that have been declared / defined
 */
public class SymbolTable {
	public int currentScope = 0;
	public int lastUId = 0;
	public ArrayList<Entry> table = new ArrayList<Entry>() {{
		add(new Entry(0, null, 0, Tokens.UNUSED, Tokens.DECLARED, null));
	}};
	public HashMap<String, Stack<Integer>> name2UIds = new HashMap<String, Stack<Integer>>();
	public Stack<HashSet<Integer>> scopes = new Stack<HashSet<Integer>>();
//	public final static String basicTypes[] = {"int", "void", "char"};

	public SymbolTable() {
		enterScope();
	}

	public int enterScope() {
		++currentScope;
		scopes.push(new HashSet<Integer>());
		return currentScope;
	}

	public void exitScope(boolean injectSU) {
		HashSet<Integer> peek = scopes.pop();
		for (Integer x : peek) {
			Entry e = table.get(x);
			String name = e.name;
			if (name == null)
				continue;
			if (table.get(x).type == Tokens.STRUCT_OR_UNION && injectSU)
				continue;
			int pop = name2UIds.get(name).pop();
			if (pop != x)
				throw new CompilationError("Internal Error.");
		}
		--currentScope;
	}

	public Entry queryName(String name) {
		if (name == null)
			return null;
		try {
			return table.get(name2UIds.get(name).peek());
		} catch (NullPointerException e) {
			return null;
		}
	}

	public boolean isFunction(String name) {
		Entry e = queryName(name);
		return e != null && e.type == Tokens.FUNCTION;
	}

	public boolean isVariable(String name) {
		Entry e = queryName(name);
		return e != null && e.type == Tokens.VARIABLE;
	}

	public boolean isTypedefName(String name) {
		Entry e = queryName(name);
		return e != null && e.type == Tokens.TYPEDEF_NAME;
	}

	public boolean isStructOrUnion(String name) {
		Entry e = queryName(name);
		return e != null && e.type == Tokens.STRUCT_OR_UNION;
	}

	public boolean isUnused(String name) {
		Entry e = queryName(name);
		return e == null || scopes.peek().contains(e.uId);
	}

	/**
	 * @param name name of the function
	 * @param ref  reference to the declaration
	 * @return -1 if already declared / defined
	 * @throws CompilationError if type mismatch
	 */
	public int declareFunction(String name, FunctionDeclaration ref) {
		if (name == null || name.equals(""))
			throw new CompilationError("Internal Error");
		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			// have been declared or defined in the current scope
			FunctionDeclaration lastRef = (FunctionDeclaration) e.ref;
			if (!Type.sameType(lastRef.returnType, ref.returnType))
				throw new CompilationError("Mismatch declaration.");
			int size = lastRef.parameterTypes.size();
			if (size != ref.parameterTypes.size())
				throw new CompilationError("Mismatch parameter type.");
			for (int i = 0; i < size; ++i)
				if (!Type.sameType(lastRef.parameterTypes.get(i), ref.parameterTypes.get(i)))
					throw new CompilationError("Mismatch declaration.");
			// do nothing.
			return -1;
		} else {
			int uId = ++lastUId;
			table.add(new Entry(uId, name, currentScope, Tokens.FUNCTION, Tokens.DECLARED, ref));
			scopes.peek().add(uId);
			name2UIds.get(name).push(uId);
			return uId;
		}
	}

	/**
	 * @param name name of the function
	 * @param ref  reference to the definition
	 * @return uId of the function
	 * @throws CompilationError if type mismatch or already defined
	 */
	public int defineFunction(String name, FunctionDeclaration ref) {
		if (name == null || name.equals(""))
			throw new CompilationError("Internal Error");
		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			// have been declared or defined in the current scope
			if (e.status == Tokens.DEFINED)
				throw new CompilationError("Function already defined.");
			FunctionDeclaration lastRef = (FunctionDeclaration) e.ref;
			if (!Type.sameType(lastRef.returnType, ref.returnType))
				throw new CompilationError("Mismatch declaration.");
			int size = lastRef.parameterTypes.size();
			if (size != ref.parameterTypes.size())
				throw new CompilationError("Mismatch parameter type.");
			for (int i = 0; i < size; ++i)
				if (!Type.sameType(lastRef.parameterTypes.get(i), ref.parameterTypes.get(i)))
					throw new CompilationError("Mismatch declaration.");
			// Warning: pointer?
			e.status = Tokens.DEFINED;
			return e.uId;
		} else {
			int uId = ++lastUId;
			table.add(new Entry(uId, name, currentScope, Tokens.FUNCTION, Tokens.DEFINED, ref));
			scopes.peek().add(uId);
			name2UIds.get(name).push(uId);
			return uId;
		}
	}

	/**
	 * @param name name of the variable
	 * @param ref  reference to the definition
	 * @return uId of the variable
	 * @throws CompilationError if type mismatch or already defined
	 */
	public int defineVariable(String name, VariableDefinition ref) {
		if (name == null || name.equals(""))
			throw new CompilationError("Internal Error");
		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			// have been declared or defined in the current scope
			throw new CompilationError("Variable already defined.");
		}
		int uId = ++lastUId;
		table.add(new Entry(uId, name, currentScope, Tokens.VARIABLE, Tokens.DEFINED, ref));
		scopes.peek().add(uId);
		name2UIds.get(name).push(uId);
		return uId;
	}

	/**
	 * @param name name of the structure / union
	 * @param isUnion the exact type, union or struct
	 * @return uId if first time define, -1 if already declared / defined
	 */
	public StructOrUnionDeclaration declareStructOrUnion(String name, boolean isUnion) {
		if (name != null && name.equals("")) name = null;
		StructOrUnionDeclaration ref = new StructOrUnionDeclaration(isUnion, null, null);
		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			if (ref.isUnion != ((StructOrUnionDeclaration) e.ref).isUnion) {
				throw new CompilationError("Defined as wrong kind of tag");
			}
			return (StructOrUnionDeclaration) e.ref;
		} else {
			int uId = ref.uId = ++lastUId;
			table.add(new Entry(uId, name, currentScope, Tokens.STRUCT_OR_UNION, Tokens.DECLARED, ref));
			scopes.peek().add(uId);
			if (name != null)
				name2UIds.get(name).push(uId);
			return ref;
		}
	}

	/**
	 * @param name name of the structure / union
	 * @param ref  ref to the definition of s / u
	 * @return uId
	 */
	public int defineStructOrUnion(String name, StructOrUnionDeclaration ref) {
		if (name != null && name.equals("")) name = null;
		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			throw new CompilationError("Struct / Union could be defined twice.");
		} else {
			int uId = ref.uId = ++lastUId;
			table.add(new Entry(uId, name, currentScope, Tokens.STRUCT_OR_UNION, Tokens.DEFINED, ref));
			scopes.peek().add(uId);
			if (name != null)
				name2UIds.get(name).push(uId);
			return uId;
		}
	}

	/**
	 * @param name typedef name
	 * @param ref  the original type name
	 * @return uId of the name
	 */
	public int defineTypedefName(String name, Type ref) {
		if (name == null || name.equals(""))
			throw new CompilationError("Internal Error");
		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			throw new CompilationError("Already defined");
		} else {
			int uId = ++lastUId;
			table.add(new Entry(uId, name, currentScope, Tokens.STRUCT_OR_UNION, Tokens.DEFINED, ref));
			scopes.peek().add(uId);
			name2UIds.get(name).push(uId);
			return uId;
		}
	}
}
