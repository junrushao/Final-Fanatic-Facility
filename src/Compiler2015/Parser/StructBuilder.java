package Compiler2015.Parser;

import Compiler2015.AST.Type.StructOrUnionType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Tokens;

import java.util.ArrayList;
import java.util.Stack;

public final class StructBuilder {
	public static Stack<StructOrUnionType> stack = new Stack<>();

	public static void enter(String name, boolean isUnion) {
		int uId = Environment.classNames.declareStructOrUnion(name, isUnion);
		SymbolTableEntry e = Environment.classNames.table.get(uId);
		if (e.info == Tokens.DEFINED)
			throw new CompilationError("Struct / Union redefined: " + e.name);
		if (e.info == Tokens.IN_PROCESS)
			throw new CompilationError("Struct / Union nested redefined: " + e.name);
		e.info = Tokens.IN_PROCESS;
		stack.push((StructOrUnionType) e.ref);
	}

	public static Type exit() {
		StructOrUnionType top = stack.pop();
		int uId = top.uId;
		Environment.classNames.defineStructOrUnion(uId);
		return (Type) Environment.classNames.table.get(uId).ref;
	}

	public static Type declareDirectly(String name, boolean isUnion) {
		int uId = Environment.classNames.declareStructOrUnion(name, isUnion);
		SymbolTableEntry e = Environment.classNames.table.get(uId);
		return (Type) e.ref;
	}

	public static void addAttributes(ArrayList<Type> types, ArrayList<String> names) {
		int n = names.size();
		for (int i = 0; i < n; ++i) {
			String name = names.get(i);
			Type type = types.get(i);
			StructOrUnionType top = stack.peek();
			int size1 = top.directlyAccessableMembers.size();
			top.names.add(name);
			top.types.add(type);
			if (name == null || name.equals("")) {
				if (!(type instanceof StructOrUnionType))
					throw new CompilationError("Name should exist for this type.");
				StructOrUnionType me = (StructOrUnionType) type;
				int size2 = me.directlyAccessableMembers.size();
				top.directlyAccessableMembers.putAll(me.directlyAccessableMembers);
				if (top.directlyAccessableMembers.size() != size1 + size2)
					throw new CompilationError("Duplicate member.");
			} else {
				top.directlyAccessableMembers.put(name, type);
				if (top.directlyAccessableMembers.size() != size1 + 1)
					throw new CompilationError("Duplicate member.");
			}
		}
	}
}
