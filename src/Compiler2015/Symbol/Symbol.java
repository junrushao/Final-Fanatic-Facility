package Compiler2015.Symbol;

import Compiler2015.Exception.CompilationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * Created by junrushao on 15-4-12.
 */

public class Symbol {
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
	public ArrayList<Object[]> uId2NameType = new ArrayList<Object[]>();

	public Symbol() {
	}

	public void beginScope() {
		++nestedDepth;
		scope.push(new HashSet<String>());
	}

	public void endScope() throws CompilationException {
		for (String name : scope.pop()) {
			int pair[] = name2ScopeUId.get(name).pop();
			if (pair[0] != nestedDepth)
				throw new CompilationException("Internal Error!");
			// nothing wrong when size = 1
		}
		--nestedDepth;
	}

	public int addName(IdentifierType type, String name) throws CompilationException {
		boolean notAnonymous = name != null && !name.equals("");
		if (notAnonymous) {
			if (scope.peek().contains(name))
				throw new CompilationException(String.format("The identifier %s has been used.", name));
		}
		int uId = nextUId++;
		if (notAnonymous) {
			scope.peek().add(name);
			if (!name2ScopeUId.containsKey(name))
				name2ScopeUId.put(name, new Stack<int[]>());
			name2ScopeUId.get(name).push(new int[]{nestedDepth, uId});
		}
		uId2NameType.add(new Object[]{(notAnonymous ? name : null), type});
		return uId;
	}
}
