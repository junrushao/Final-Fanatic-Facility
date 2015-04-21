package Compiler2015.Environment;

import Compiler2015.AST.Declaration.FunctionDeclaration;
import Compiler2015.AST.Declaration.StructOrUnionDeclaration;
import Compiler2015.AST.Initializer;
import Compiler2015.AST.Statement.Statement;
import Compiler2015.AST.Type.ArrayPointerType;
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
 * Record the symbols that have been declared / defined
 */
public class SymbolTable {
	public int currentScope = 0;
	public int lastUId = 0;
	public ArrayList<Entry> table = new ArrayList<Entry>() {{
		add(null);
	}};
	public HashMap<String, Stack<Integer>> name2UIds = new HashMap<>();
	public Stack<HashSet<Integer>> scopes = new Stack<>();
//	public final static String basicTypes[] = {"int", "void", "char"};

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

	public void exitScope(boolean injectSU) {
		HashSet<Integer> peek = scopes.pop();
		for (Integer x : peek) {
			Entry e = table.get(x);
			String name = e.name;
			if (name == null)
				continue;
			if (table.get(x).type == Tokens.STRUCT_OR_UNION && injectSU)
				continue;
			int pop = getUId(name).pop();
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

	/**
	 * @param name function name
	 * @param returnType return type
	 * @param types type of each parameter
	 * @param names name of each parameter
	 * @param hasVaList whether the function has a VaList
	 * @param state compoundStatement
	 * @return uId of the function
	 * @throws CompilationError if type mismatch or already defined
	 */
	public int defineFunction(String name, Type returnType, ArrayList<Type> types, ArrayList<String> names, boolean hasVaList, Statement state) {
		if (name == null || name.equals(""))
			throw new CompilationError("Internal Error.");
		if (returnType instanceof ArrayPointerType)
			throw new CompilationError("Cannot return an array.");

		for (int i = 0, size = types.size(); i < size; ++i) {
			Type t = types.get(i);
			if (t instanceof ArrayPointerType) {
				types.set(i, ((ArrayPointerType) t).toVariablePointerType());
			}
		}

		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			// have been declared or defined in the current scope
			if (e.status == Tokens.DEFINED)
				throw new CompilationError("Function already defined.");
			FunctionDeclaration lastRef = (FunctionDeclaration) e.ref;

			if (!lastRef.returnType.equals(returnType))
				throw new CompilationError("Mismatch return type.");

			int size = lastRef.parameterTypes.size();
			if (size != types.size() || hasVaList != lastRef.hasVaList)
				throw new CompilationError("Mismatch parameter type.");

			for (int i = 0; i < size; ++i)
				if (!lastRef.parameterTypes.get(i).equals(types.get(i)))
					throw new CompilationError("Mismatch declaration.");

			e.status = Tokens.DEFINED;
			return e.uId;
		} else {
			int uId = ++lastUId;
			table.add(new Entry(uId, name, currentScope, Tokens.FUNCTION, Tokens.DEFINED,
					new FunctionDeclaration(uId, returnType, types, names, hasVaList, state),
					null
			));
			scopes.peek().add(uId);
			getUId(name).push(uId);
			return uId;
		}
	}

	/**
	 * @param name name of the variable
	 * @param type reference to the defined type
	 * @return uId of the variable
	 * @throws CompilationError if type mismatch or already defined
	 */
	public int defineVariable(String name, Type type, Initializer init) {
		if (name == null || name.equals(""))
			throw new CompilationError("Internal Error");
		if (type instanceof VoidType)
			throw new CompilationError("Cannot create void type variables.");
		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			// have been declared or defined in the current scope
			throw new CompilationError("Variable already defined.");
		}
		int uId = ++lastUId;
		table.add(new Entry(uId, name, currentScope, Tokens.VARIABLE, Tokens.DEFINED, type, init));
		scopes.peek().add(uId);
		getUId(name).push(uId);
		return uId;
	}

	/**
	 * @param name name of the structure / union
	 * @param isUnion the exact type, union or struct
	 * @return uId if first time define, -1 if already declared / defined
	 */
	public int declareStructOrUnion(String name, boolean isUnion) {
		if (name != null && name.equals("")) name = null;
		Entry e = queryName(name);
		if (e != null && scopes.peek().contains(e.uId)) {
			if (isUnion != ((StructOrUnionDeclaration) e.ref).isUnion) {
				throw new CompilationError("Defined as wrong kind of tag");
			}
			return e.uId;
		} else {
			int uId = ++lastUId;
			table.add(new Entry(uId, name, currentScope, Tokens.STRUCT_OR_UNION, Tokens.DECLARED, null, isUnion));
			scopes.peek().add(uId);
			if (name != null)
				getUId(name).push(uId);
			return uId;
		}
	}

	/**
	 * @param uId              uId of the structure / union
	 * @param isUnion          the tag of structure / union
	 * @param members          named members
	 * @param anonymousMembers anonymous members
	 * @return uId
	 */
	public int defineStructOrUnion(int uId, boolean isUnion, HashMap<String, Type> members, ArrayList<StructOrUnionType> anonymousMembers) {
		Entry e = table.get(uId);
		if (e.ref != null)
			throw new CompilationError("Struct / Union could not be defined twice.");
		if (!e.info.equals(isUnion))
			throw new CompilationError("Struct / Union tag mismatch.");
		e.status = Tokens.DEFINED;
		e.ref = new StructOrUnionDeclaration(uId, isUnion, members, anonymousMembers);
		return uId;
	}

	/**
	 * @param name typedef name
	 * @param ref the original type name
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
			table.add(new Entry(uId, name, currentScope, Tokens.STRUCT_OR_UNION, Tokens.DEFINED, ref, null));
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
		for (Entry e : table) {
			if (e == null) continue;
			if (e.scope != 1) continue;
			if (e.type == Tokens.VARIABLE) {
				sb.append('(').append((Type) e.ref).append(", ").append(e.name).append(")");
				if (e.info != null)
					sb.append(" init = ").append(e.info.toString());
				sb.append(Utility.NEW_LINE);
			} else if (e.type == Tokens.STRUCT_OR_UNION)
				sb.append(e.ref.toString());
			else if (e.type == Tokens.FUNCTION)
				sb.append(e.ref.toString());
		}
		return sb.toString();
	}
}
