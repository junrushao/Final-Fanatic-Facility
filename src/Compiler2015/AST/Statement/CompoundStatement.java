package Compiler2015.AST.Statement;

import Compiler2015.AST.ASTNode;

import java.util.ArrayList;

/**
 * { blockItem[0]; blockItem[1]; ... }
 */
public class CompoundStatement extends Statement {
	public ArrayList<ASTNode> blockItem; // declaration or statement
	public CompoundStatement(ArrayList<ASTNode> blockItem) {
		this.blockItem = blockItem;
	}
}
