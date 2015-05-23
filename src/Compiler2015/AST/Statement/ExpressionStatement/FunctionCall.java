package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.*;
import Compiler2015.Type.*;
import Compiler2015.Utility.Panel;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

	@Override
	public void collectGlobalNonArrayVariablesUsed(HashMap<Integer, VirtualRegister> dumpTo) {
		function.collectGlobalNonArrayVariablesUsed(dumpTo);
		for (Expression e : argumentExpressionList)
			e.collectGlobalNonArrayVariablesUsed(dumpTo);
		for (Expression e : vaList)
			e.collectGlobalNonArrayVariablesUsed(dumpTo);
	}

	public void pushStack(ExpressionCFGBuilder builder, Expression e, boolean isExtra) {
		builder.addInstruction(new PushStack(e.tempRegister, isExtra, e.type));
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		boolean isLib = function instanceof IdentifierExpression && Environment.isLibraryFunctions(((IdentifierExpression) function).uId);
		function.emitCFG(builder);
		for (Expression e : argumentExpressionList) {
			e.emitCFG(builder);
			e.eliminateArrayRegister(builder);
		}
		for (Expression e : vaList) {
			e.emitCFG(builder);
			e.eliminateArrayRegister(builder);
		}
		if (!isLib)
			for (Map.Entry<Integer, VirtualRegister> element : ControlFlowGraph.globalNonArrayVariables.entrySet()) {
				SymbolTableEntry entry = Environment.symbolNames.table.get(element.getKey());
				int uId = entry.uId;
				int size = entry.ref instanceof StructOrUnionType || entry.ref instanceof ArrayPointerType ? Panel.getPointerSize() : ((Type) entry.ref).sizeof();
				builder.addInstruction(new WriteArray(new ArrayRegister(new VirtualRegister(uId), new ImmediateValue(0), size), element.getValue()));
			}

		// push in reverse order
		for (int i = vaList.length - 1; i >= 0; --i)
			pushStack(builder, vaList[i], true);
		for (int i = argumentExpressionList.length - 1; i >= 0; --i)
			pushStack(builder, argumentExpressionList[i], false);

		tempRegister = Environment.getVirtualRegister();
		builder.addInstruction(new Call(function.tempRegister));
		builder.addInstruction(new FetchReturn((VirtualRegister) tempRegister, function.type));
		if (!isLib)
			for (Map.Entry<Integer, VirtualRegister> element : ControlFlowGraph.globalNonArrayVariables.entrySet()) {
				SymbolTableEntry entry = Environment.symbolNames.table.get(element.getKey());
				int uId = entry.uId;
				int size = entry.ref instanceof StructOrUnionType || entry.ref instanceof ArrayPointerType ? Panel.getPointerSize() : ((Type) entry.ref).sizeof();
				builder.addInstruction(new ReadArray(element.getValue(), new ArrayRegister(new VirtualRegister(uId), new ImmediateValue(0), size)));
			}
	}
}
