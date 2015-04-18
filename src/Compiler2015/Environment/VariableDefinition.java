package Compiler2015.Environment;

import Compiler2015.AST.Initializer;
import Compiler2015.AST.Type.Type;

public class VariableDefinition {
	Type type;
	Initializer init;

	public VariableDefinition(Type type, Initializer init) {
		this.type = type;
		this.init = init;
	}
}
