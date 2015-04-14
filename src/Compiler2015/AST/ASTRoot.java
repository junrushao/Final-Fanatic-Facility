package Compiler2015.AST;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-13.
 */
public class ASTRoot extends ASTNode {
	public ArrayList<ASTNode> instructions;

	public ASTRoot(ArrayList<ASTNode> instructions) {
		this.instructions = instructions;
	}
}
