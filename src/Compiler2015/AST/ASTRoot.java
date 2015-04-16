package Compiler2015.AST;

import java.util.ArrayList;

/**
 */
public class ASTRoot extends ASTNode {
	public ArrayList<ASTNode> instructions;

	public ASTRoot(ArrayList<ASTNode> instructions) {
		this.instructions = instructions;
	}
}
