package Compiler2015.Environment;

import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.RegisterAllocator.BaseAllocator;
import Compiler2015.Type.FunctionType;

public class FunctionTableEntry {
	public int uId;
	public String name;
	public FunctionType definition;
	public SymbolTableEntry symbolTableEntry;
	public CompoundStatement scope;
	public ControlFlowGraph cfg;
	public BaseAllocator allocator;
	public boolean isLeaf;

	public FunctionTableEntry(int uId, String name, FunctionType definition, SymbolTableEntry symbolTableEntry, CompoundStatement scope) {
		this.uId = uId;
		this.name = name;
		this.definition = definition;
		this.symbolTableEntry = symbolTableEntry;
		this.scope = scope;
		this.cfg = null;
		this.allocator = null;
		this.isLeaf = true;
	}
}
