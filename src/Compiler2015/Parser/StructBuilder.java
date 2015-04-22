package Compiler2015.Parser;

import Compiler2015.AST.Type.StructOrUnionType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;

import java.util.ArrayList;
import java.util.Stack;

public final class StructBuilder {
	public static Stack<StructOrUnionType> stack = new Stack<>();
	public static Stack<String> names = new Stack<>();

	public static void enter(String name, boolean isUnion) {
		StructOrUnionType p = new StructOrUnionType();
		p.isUnion = isUnion;
		stack.push(p);
		names.push(name);
	}

	public static Type exit() {
		StructOrUnionType top = stack.pop();
		return (Type) Environment.classNames.table.get(Environment.classNames.defineStructOrUnion(names.pop(), top.isUnion, top)).info;
	}

	public static Type decalreDirectly(String name, boolean isUnion) {
		return (Type) Environment.classNames.table.get(Environment.classNames.defineStructOrUnion(name, isUnion, null)).info;
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
