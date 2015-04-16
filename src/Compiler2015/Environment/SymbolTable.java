package Compiler2015.Environment;

import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Tokens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * TODO: Totally wrong, re-designation is needed
 *
 * Record the symbols that have been declared ( **not necessary defined** )
 *
 * "struct x" will not conflict with "int x",
 * however,
 * "struct x" conflicts with "union x"
 * Warning: error may occur when name is struct / union and meanwhile variable, should be resolved in parsing phase
 */
public class SymbolTable {
	public int nestedDepth = 0;
	public int nextUId = 0;
	/*
	 * Record the identifiers used in each scope.
	 */
	public Stack<HashSet<String>> scope = new Stack<HashSet<String>>();
	/*
	 * For each name, record which scope it exists and the corresponding uId it holds
	 */
	public HashMap<String, Stack<int[]>> name2ScopeUId = new HashMap<String, Stack<int[]>>();
	/*
	 * For each uId, record the name and type of uId.
	 */
	public ArrayList<Object[]> uId2NameTypeStatus = new ArrayList<Object[]>();

	public SymbolTable() {
	}

	public void beginScope() {
		++nestedDepth;
		scope.push(new HashSet<String>());
	}

	public void endScope() {
		for (String name : scope.pop()) {
			int pair[] = name2ScopeUId.get(name).pop();
			if (pair[0] != nestedDepth)
				throw new CompilationError("Internal Error!");
			// nothing wrong when size = 1
		}
		--nestedDepth;
	}

	public int declareName(Tokens type, String name) {
		if (name.equals("")) name = null;
		if (name != null) { // check whether the name exists in the current scope
			if (scope.peek().contains(name))
				throw new CompilationError(String.format("The identifier %s has been used.", name));
		}
		int uId = nextUId++;
		if (name != null) {
			scope.peek().add(name);
			if (!name2ScopeUId.containsKey(name))
				name2ScopeUId.put(name, new Stack<int[]>());
			name2ScopeUId.get(name).push(new int[]{nestedDepth, uId});
		}
		uId2NameTypeStatus.add(new Object[]{name, type, Tokens.DECLARED});
		return uId;
	}

	public int defineName(Tokens type, String name) {
		// TODO: warning: wrong here
		if (name.equals("")) name = null;
		int uId = 0;
		if (name != null) {
			if (scope.peek().contains(name)) {
				uId = name2ScopeUId.get(name).peek()[1];
				uId2NameTypeStatus.get(uId)[2] = Tokens.DEFINED;
			}
		}
		else {
			uId = nextUId++;
			scope.peek().add(name);
			if (!name2ScopeUId.containsKey(name))
				name2ScopeUId.put(name, new Stack<int[]>());
			name2ScopeUId.get(name).push(new int[]{nestedDepth, uId});
			uId2NameTypeStatus.add(new Object[]{name, type, Tokens.DEFINED});
		}
		return uId;
	}

	public Tokens getType(String name) {
		if (name == null || name.equals(""))
			throw new CompilationError("Internal Error");
		if (!name2ScopeUId.containsKey(name))
			return Tokens.UNUSED;
		return (Tokens) uId2NameTypeStatus.get(name2ScopeUId.get(name).peek()[1])[1];
	}

}
