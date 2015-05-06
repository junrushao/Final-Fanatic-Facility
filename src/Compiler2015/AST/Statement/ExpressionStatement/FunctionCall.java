package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.Instruction.Call;
import Compiler2015.IR.Instruction.PushStack;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.FunctionPointerType;
import Compiler2015.Type.FunctionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * f(...)
 */
public class FunctionCall extends Expression {
	public Expression function;
	public Expression argumentExpressionList[];
	public Expression vaList[];

	public FunctionCall(Expression e1, Expression[] argumentExpressionList, Expression[] VaList, Type returnType) {
		this.type = returnType;
		this.isLValue = false;
		this.function = e1;
		this.argumentExpressionList = argumentExpressionList;
		this.vaList = VaList;
	}

	public static Expression getExpression(Expression e1, ArrayList<Expression> parameters) {
		if (parameters == null) parameters = new ArrayList<>();

		Type ff = e1.type;
		if (ff instanceof FunctionPointerType)
			ff = ((FunctionPointerType) ff).pointTo;
		if (!(ff instanceof FunctionType))
			throw new CompilationError("Not a function or a pointer to a function.");
		FunctionType f = (FunctionType) ff;
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
				argumentExpressionList == null ? "null" : Utility.toString(Arrays.asList(argumentExpressionList)),
				vaList == null ? "null" : Utility.toString(Arrays.asList(vaList)));
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		function.emitCFG(builder);
		for (Expression e : argumentExpressionList) {
			e.emitCFG(builder);
			e.eliminateLValue(builder);
		}
		for (Expression e : vaList) {
			e.emitCFG(builder);
			e.eliminateLValue(builder);
		}
		for (Expression e : argumentExpressionList)
			builder.addInstruction(new PushStack(e.tempRegister));
		for (Expression e : vaList)
			builder.addInstruction(new PushStack(e.tempRegister));

		tempRegister = Environment.getTemporaryRegister();
		builder.addInstruction(new Call(tempRegister, function.tempRegister));
	}
}
