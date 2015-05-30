package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Call;
import Compiler2015.IR.Instruction.FetchReturn;
import Compiler2015.IR.Instruction.PushStack;
import Compiler2015.Type.*;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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
		this.argumentExpressionList = argumentExpressionList == null ? new Expression[0] : argumentExpressionList;
		this.vaList = VaList == null ? new Expression[0] : VaList;
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
				argumentExpressionList == null ? "" : Utility.toString(Arrays.asList(argumentExpressionList)),
				vaList == null ? "" : Utility.toString(Arrays.asList(vaList)));
	}

	public void pushStack(ExpressionCFGBuilder builder, Expression e, boolean isExtra) {
		builder.addInstruction(new PushStack(e.tempRegister, isExtra, e.type));
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		function.emitCFG(builder);
		for (Expression e : argumentExpressionList) {
			e.emitCFG(builder);
			e.readInArrayRegister(builder);
		}
		for (Expression e : vaList) {
			e.emitCFG(builder);
			e.readInArrayRegister(builder);
		}
		// push in reverse order
		for (int i = vaList.length - 1; i >= 0; --i)
			pushStack(builder, vaList[i], true);
		for (int i = argumentExpressionList.length - 1; i >= 0; --i)
			pushStack(builder, argumentExpressionList[i], false);

		tempRegister = Environment.getVirtualRegister();
		builder.addInstruction(new Call(function.tempRegister));
		builder.addInstruction(new FetchReturn((VirtualRegister) tempRegister));
	}

	@Override
	public FunctionCall clone() {
		FunctionCall ret = (FunctionCall) super.clone();
		ret.function = ret.function.clone();
		ret.argumentExpressionList = ret.argumentExpressionList.clone();
		ret.vaList = ret.vaList.clone();
		return ret;
	}

	public void putString(String c, int begin, int end, Stack<FunctionCall> callSequence) {
		// [begin, end)
		if (begin == end - 1) {
			char chr = c.charAt(begin);
			callSequence.push(new FunctionCall(
							new IdentifierExpression(Environment.uIdOfPutChar, VoidType.instance),
							new Expression[]{new CharConstant(chr)},
							null,
							VoidType.instance)
			);
		} else if (begin < end - 1) {
			String sub = c.substring(begin, end);
			StringConstant toPrint = new StringConstant(sub);
			callSequence.push(new FunctionCall(
							new IdentifierExpression(Environment.uIdOfPutString, VoidType.instance),
							new Expression[]{toPrint},
							null,
							VoidType.instance)
			);
		} else
			throw new CompilationError("Internal Error.");
	}

	@Override
	public Expression rebuild() {
		function = function.rebuild();
		for (int i = 0; i < argumentExpressionList.length; ++i)
			argumentExpressionList[i] = argumentExpressionList[i].rebuild();
		for (int i = 0; i < vaList.length; ++i)
			vaList[i] = vaList[i].rebuild();

		if (function instanceof IdentifierExpression && ((IdentifierExpression) function).uId == Environment.uIdOfPrintf && argumentExpressionList[0] instanceof StringConstant) {
			// do printf analysis
			String c = ((StringConstant) argumentExpressionList[0]).c;
			for (int i = 0; i <= 9; ++i)
				if (c.contains("%." + Integer.toString(i) + "d"))
					return this;
				else if (c.contains("%0" + Integer.toString(i) + "d"))
					return this;
			Stack<FunctionCall> callSequence = new Stack<>();
			int prev = 0, ptr = 0, cnt = 0;
			for (; ptr < c.length(); ++ptr)
				if (c.charAt(ptr) == '%') {
					if (prev < ptr)
						putString(c, prev, ptr, callSequence);
					++ptr;
					if (c.charAt(ptr) == 'd') {
						callSequence.push(new FunctionCall(
										new IdentifierExpression(Environment.uIdOfPutInt, VoidType.instance),
										new Expression[]{vaList[cnt++]},
										null,
										VoidType.instance)
						);
					} else if (c.charAt(ptr) == 'c') {
						callSequence.push(new FunctionCall(
										new IdentifierExpression(Environment.uIdOfPutChar, VoidType.instance),
										new Expression[]{vaList[cnt++]},
										null,
										VoidType.instance)
						);
					} else if (c.charAt(ptr) == 's') {
						callSequence.push(new FunctionCall(
										new IdentifierExpression(Environment.uIdOfPutString, VoidType.instance),
										new Expression[]{vaList[cnt++]},
										null,
										VoidType.instance)
						);
					} else {
						throw new CompilationError("Not supported printf analysis.");
//						return this;
					}
					prev = ptr + 1;
				}
			if (prev < c.length())
				putString(c, prev, c.length(), callSequence);
			if (callSequence.size() == 0)
				return this;
			if (callSequence.size() == 1)
				return callSequence.pop();
			Expression t2 = callSequence.pop();
			Expression t1 = callSequence.pop();
			Expression ret = new CommaExpression(t1, t2);
			while (!callSequence.isEmpty()) {
				t1 = callSequence.pop();
				ret = new CommaExpression(t1, ret);
			}
			return ret;
		}
		return this;
	}
}
