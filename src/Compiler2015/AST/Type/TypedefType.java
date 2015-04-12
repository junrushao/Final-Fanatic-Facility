package Compiler2015.AST.Type;

import Compiler2015.AST.Declaration.Declaration;
import Compiler2015.AST.SizeMeasurable;

/**
 * Created by junrushao on 15-4-11.
 */
public class TypedefType extends Declaration implements SizeMeasurable {
	public Type definedType;

	public TypedefType(Type definedType) {
		this.definedType = definedType;
	}

	@Override
	public int sizeof() {
		return definedType.sizeof();
	}
}
