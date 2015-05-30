package Compiler2015.Translate;

import Compiler2015.AST.Initializer;
import Compiler2015.AST.Statement.Statement;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.FunctionType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Tokens;

import java.util.ArrayList;

public final class ASTModifier {
	public static void process() {
		Environment.symbolNames.table.stream().filter(e -> e != null && e.scope == 1 && e.type == Tokens.VARIABLE && !(e.ref instanceof ArrayPointerType || e.ref instanceof FunctionType || e.ref instanceof StructOrUnionType)).forEach(
				e -> Environment.globalNonArrayVariablesAndLocalAddressFetchedVariables.add(e.uId)
		);
		Environment.symbolNames.table.stream().filter(e -> e != null && Environment.globalNonArrayVariablesAndLocalAddressFetchedVariables.contains(e.uId)).forEach(
				e -> {
					e.ref = new ArrayPointerType((Type) e.ref, new ArrayList<Integer>() {{
						add(1);
					}});
					if (e.info != null) {
						Initializer init = (Initializer) e.info;
						if (init.entries.size() != 1)
							throw new CompilationError("Internal Error.");
						init.entries.get(0).position = new int[1];
					}
				}
		);
		// rebuild initializers for global variables
		Environment.symbolNames.table.stream().filter(e -> e != null && e.scope == 1 && e.type == Tokens.VARIABLE && !(e.ref instanceof FunctionType) && e.info != null).forEach(
				e -> {
					if (e.ref instanceof ArrayPointerType) {
						Initializer init = (Initializer) e.info;
						for (Initializer.InitEntry entry : init.entries)
							entry.value = entry.value.rebuild();
					} else
						throw new CompilationError("Internal Error.");
				}
		);
		// rebuild all compound statements of function
		for (int i = 1, size = Environment.symbolNames.table.size(); i < size; ++i) {
			SymbolTableEntry e = Environment.symbolNames.table.get(i);
			if (e.type == Tokens.VARIABLE && e.ref instanceof FunctionType && e.info != null) {
				e.info = ((Statement) e.info).rebuild();
			}
		}
	}
}