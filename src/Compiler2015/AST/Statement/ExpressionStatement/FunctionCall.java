package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.ArrayPointerType;
import Compiler2015.AST.Type.FunctionPointerType;
import Compiler2015.AST.Type.FunctionType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Exception.CompilationError;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * f(...)
 */
public class FunctionCall extends Expression {
	public Expression function;
	public Expression argumentExpressionList[];
	public Expression VaList[];

	public FunctionCall(Expression e1, Expression[] argumentExpressionList, Expression[] VaList, Type returnType) {
		this.type = returnType;
		this.isLValue = false;
		this.function = e1;
		this.argumentExpressionList = argumentExpressionList;
		this.VaList = VaList;
	}

	public static Expression getExpression(Expression e1, ArrayList<Expression> parameters) {
		if (parameters == null) parameters = new ArrayList<>();

		Type ff = e1.type;
		if (ff instanceof FunctionPointerType)
			ff = ((FunctionPointerType) ff).pointTo;
		if (!(ff instanceof FunctionType))
			throw new CompilationError("Not a function or a pointer to a function.");
		FunctionType f = (FunctionType) e1.type;
		int size = f.parameterNames.size(), sizeR = parameters.size();
		if (f.hasVaList) {
			if (size > sizeR)
				throw new CompilationError("Parameter number mismatch.");
		} else {
			if (size != sizeR)
				throw new CompilationError("Parameter number mismatch.");
		}
		for (int i = 0; i < sizeR; ++i) {
			Expression e = parameters.get(i);
			Type t = e.type;
			if (t instanceof ArrayPointerType) {
				e.type = ((ArrayPointerType) t).toVariablePointerType();
				parameters.set(i, e);
			}
		}
		for (int i = 0; i < size; ++i)
			if (!Type.suitable(f.parameterTypes.get(i), parameters.get(i).type))
				throw new CompilationError("Parameter type mismatch");
		Expression argList[] = new Expression[size];
		Expression vaList[] = null;
		for (int i = 0; i < size; ++i)
			argList[i] = parameters.get(i);
		if (f.hasVaList) {
			vaList = new Expression[sizeR - size];
			for (int i = size; i < sizeR; ++i)
				vaList[i - size] = parameters.get(i);
		}
		return new FunctionCall(e1, argList, vaList, f.returnType);
	}

	@Override
	public String toString() {
		return String.format("(Call %s %s %s)",
				function.toString(),
				Utility.toString(Arrays.asList(argumentExpressionList)),
				Utility.toString(Arrays.asList(VaList)));
	}
}
