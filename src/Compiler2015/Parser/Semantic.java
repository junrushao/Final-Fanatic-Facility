package Compiler2015.Parser;

import Compiler2015.AST.ASTNode;
import Compiler2015.AST.Type.Type;

/**
 * Created by junrushao on 15-4-13.
 */

public class Semantic {
	ASTNode ret;
	boolean isLValue;
	Type type;

	public Semantic(ASTNode ret, boolean isLValue, Type type) {
		this.ret = ret;
		this.isLValue = isLValue;
		this.type = type;
	}
}
