package Compiler2015.AST.Type;

import java.util.ArrayList;

/**
 * Created by junrushao on 15-4-11.
 */
public class ArrayPointerTypeType extends PointerType {
	public ArrayList<Integer> dimensions;

	public ArrayPointerTypeType(Type pointTo, ArrayList<Integer> dimensions) {
		super(pointTo);
		super.isConstant = true;
		super.isCallable = false;
		this.dimensions = dimensions;
	}

	@Override
	public int sizeof() { // TODO
		return 0;
	}
}
