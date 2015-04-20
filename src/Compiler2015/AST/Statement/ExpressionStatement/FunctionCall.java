package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.AST.Type.ArrayPointerType;
import Compiler2015.AST.Type.FunctionPointerType;
import Compiler2015.AST.Type.Type;
import Compiler2015.Exception.CompilationError;

import java.util.ArrayList;

/**
 * f(...)
 */
public class FunctionCall extends Expression {
	public FunctionPointerType function;
	public Expression argumentExpressionList[];
	public Expression VaList[];

	public FunctionCall(FunctionPointerType function, Expression[] argumentExpressionList, Expression[] VaList) {
		this.type = function.returnType;
		this.isLValue = false;
		this.function = function;
		this.argumentExpressionList = argumentExpressionList;
		this.VaList = VaList;
	}

	public static Expression getExpression(Expression e1, ArrayList<Expression> parameters) {
		if (parameters == null) parameters = new ArrayList<Expression>();

		if (!(e1.type instanceof FunctionPointerType))
			throw new CompilationError("Not a pointer to function.");
		FunctionPointerType f = (FunctionPointerType) e1.type;
		int size = f.parameterName.size(), sizeR = parameters.size();
		if (f.hasVarList) {
			if (size > sizeR)
				throw new CompilationError("Parameter number mismatch");
		} else {
			if (size != sizeR)
				throw new CompilationError("Parameter number mismatch");
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
			if (!Type.suitable(f.parameterType.get(i), parameters.get(i).type))
				throw new CompilationError("Parameter type mismatch");
		Expression argList[] = new Expression[size];
		Expression vaList[] = null;
		for (int i = 0; i < size; ++i)
			argList[i] = parameters.get(i);
		if (f.hasVarList) {
			vaList = new Expression[sizeR - size];
			for (int i = size; i < sizeR; ++i)
				vaList[i - size] = parameters.get(i);
		}
		return new FunctionCall(f, argList, vaList);
	}
}
