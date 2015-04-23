package Compiler2015.Environment;

import Compiler2015.AST.SimpleInitializerList;
import Compiler2015.AST.Type.Type;

public class VariableDefinition {
	Type type;
	SimpleInitializerList init;

	public VariableDefinition(Type type, SimpleInitializerList init) {
		this.type = type;
		this.init = init;
	}
}
