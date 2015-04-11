package Compiler2015.AST.Statement;

import Compiler2015.AST.ASTNode;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-11.
 *
 * { a; b; c; }
 */
public class CompoundStatement extends Statement {
	public ArrayList<ASTNode> blockItem; // declaration or statement
	public CompoundStatement(ArrayList<ASTNode> blockItem) {
		this.blockItem = blockItem;
	}
}
