package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.IRRegister;
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

	public void pushStack(ExpressionCFGBuilder builder, Expression e) {
		if (e.type instanceof StructOrUnionType) {
			// copy into heap, copy from t2 to t1
			IRRegister t1 = Environment.getTemporaryRegister(), t2 = e.tempRegister.clone();
			int size = e.type.sizeof(), registerSize = Panel.getRegisterSize();
			builder.addInstruction(new AllocateHeap((VirtualRegister) t1, size)); // allocate memory
			for (int i = 0; i < size; i += registerSize) {
				VirtualRegister t = Environment.getTemporaryRegister();
				builder.addInstruction(new ReadArray(t, new ArrayRegister(t2, new ImmediateValue(i), registerSize)));
				builder.addInstruction(new WriteArray(new ArrayRegister(t1, new ImmediateValue(i), registerSize), t));
			}
		} else {
			builder.addInstruction(new PushStack(e.tempRegister));
		}
	}

	// TODO: call malloc
	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		function.emitCFG(builder);
		for (Expression e : argumentExpressionList) {
			e.emitCFG(builder);
			e.eliminateArrayRegister(builder);
		}
		for (Expression e : vaList) {
			e.emitCFG(builder);
			e.eliminateArrayRegister(builder);
		}
		for (Map.Entry<Integer, VirtualRegister> element : ControlFlowGraph.globalNonArrayVariables.entrySet()) {
			SymbolTableEntry entry = Environment.symbolNames.table.get(element.getKey());
			int uId = entry.uId;
			builder.addInstruction(new WriteArray(new ArrayRegister(new VirtualRegister(uId), new ImmediateValue(0), ((Type) entry.ref).sizeof()), element.getValue()));
		}
		for (Expression e : argumentExpressionList)
			pushStack(builder, e);
		for (Expression e : vaList)
			pushStack(builder, e);
		tempRegister = Environment.getTemporaryRegister();
		builder.addInstruction(new Call(function.tempRegister));
		builder.addInstruction(new FetchReturn((VirtualRegister) tempRegister, function.type));
		for (Map.Entry<Integer, VirtualRegister> element : ControlFlowGraph.globalNonArrayVariables.entrySet()) {
			SymbolTableEntry entry = Environment.symbolNames.table.get(element.getKey());
			int uId = entry.uId;
			builder.addInstruction(new ReadArray(element.getValue(), new ArrayRegister(new VirtualRegister(uId), new ImmediateValue(0), ((Type) entry.ref).sizeof())));
		}
	}
}
