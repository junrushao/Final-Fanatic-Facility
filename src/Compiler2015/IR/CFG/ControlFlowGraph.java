package Compiler2015.IR.CFG;

import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.FunctionTableEntry;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.Def;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Instruction.NopForBranch;
import Compiler2015.IR.Instruction.ThreeAddressInstruction.AddReg;
import Compiler2015.IR.Optimizer.NaiveDeadCodeElimination;
import Compiler2015.IR.Optimizer.SSABased.CommonExpressionElimination;
import Compiler2015.IR.StaticSingleAssignment.PhiPlacer;
import Compiler2015.IR.StaticSingleAssignment.SSADestroyer;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Panel;
import Compiler2015.Utility.Tokens;
import Compiler2015.Utility.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class ControlFlowGraph {

	public static ControlFlowGraph instance = null;

	public FunctionTableEntry functionTableEntry;

	// for vertices allocation
	public int tempVertexCount;
	public HashSet<CFGVertex> vertices;
	public CFGVertex source, sink;

	public HashMap<Integer, Integer> tempDelta;
	public HashMap<Integer, Integer> parameterDelta;
	public int frameSize;

	public ControlFlowGraph(FunctionTableEntry functionTableEntry) {
		ControlFlowGraph.instance = this;
		this.functionTableEntry = functionTableEntry;
		this.tempVertexCount = 0;
		this.vertices = new HashSet<>();
		this.source = null;
		this.sink = getNewVertex();

		this.tempDelta = new HashMap<>();
		this.parameterDelta = new HashMap<>();
		this.frameSize = 0;

		// emit control flow graph
		functionTableEntry.scope.emitCFG();
		source = functionTableEntry.scope.beginCFGBlock;
		if (functionTableEntry.scope.endCFGBlock.unconditionalNext == null)
			functionTableEntry.scope.endCFGBlock.unconditionalNext = sink;

		// add if condition
		vertices.stream().forEach(x -> {
			if (x.branchIfFalse != null && x.branchRegister == null) {
				if (x.internal.isEmpty())
					System.err.println("fuck id = " + x.id);
				x.branchRegister = x.internal.get(x.internal.size() - 1).rd.clone();
			}
			if (x.branchIfFalse != null) {
				x.internal.add(new NopForBranch(x.branchRegister));
				x.branchRegister = null;
			}
		});

		// prune CFG
		GraphManipulate.mergeBlocks(this);
		GraphManipulate.splitEdges(this);
		GraphManipulate.reCalculateGraphInfo(this);

		// define external variables and parameters passed at source
		defineExternalVariables();

		if (Panel.emitCFG)
			System.out.println(this.toString());

		this.touchGraph();
		// do naive dead code elimination
		NaiveDeadCodeElimination.process(this, true);
		this.touchGraph();

		// insert phi functions
		PhiPlacer.process(this);

		if (Panel.emitSSA)
			System.out.println(this.toString());

		new CommonExpressionElimination(this);

		if (Panel.emitOptimizedSSA)
			System.out.println(this.toString());

		// destroy ssa
		SSADestroyer.process(this);

		NaiveDeadCodeElimination.process(this, true);
		this.touchGraph();

		if (Panel.emitOptimizedCFG)
			System.out.println(this.toString());

		scanVirtualRegister();
		ControlFlowGraph.instance = null;
	}

	public boolean isAddRegisterImmediate(IRInstruction i) {
		return i instanceof AddReg && ((AddReg) i).rt instanceof ImmediateValue;
	}

	public void touchGraph() {
		for (CFGVertex block : vertices) {
			for (int i = 0, size = block.phiBlock.size(); i < size; ++i) {
				IRInstruction before = block.phiBlock.get(i);
				IRInstruction after = before.getExpression();
				block.phiBlock.set(i, after);
			}
			AddReg lastIns = null;
			for (int i = 0, size = block.internal.size(); i < size; ++i) {
				IRInstruction before = block.internal.get(i);
				IRInstruction after = before.getExpression();

				if (lastIns != null && isAddRegisterImmediate(after)) {
					if (lastIns.rt.equals(((AddReg) after).rs)) {
						int i1 = ((ImmediateValue) lastIns.rt).a;
						int i2 = ((ImmediateValue) ((AddReg) after).rt).a;
						after = AddReg.getExpression(after.rd, lastIns.rs, new ImmediateValue(i1 + i2));
					}
				}

				if (isAddRegisterImmediate(after))
					lastIns = (AddReg) after;
				else
					lastIns = null;
				block.internal.set(i, after);
			}
		}
	}

	public void defineExternalVariables() {
		CompoundStatement scope = functionTableEntry.scope;
		ArrayList<IRInstruction> instructions = scope.givenVariables.stream().map(x -> new Def(new VirtualRegister(x))).collect(Collectors.toCollection(ArrayList::new));
		Environment.symbolNames.table.stream().filter(e -> e != null && e.scope == 1 && (e.type == Tokens.VARIABLE || e.type == Tokens.STRING_CONSTANT)).forEach(e -> instructions.add(new Def(new VirtualRegister(e.uId))));
		instructions.add(new Def(new VirtualRegister(0)));
		instructions.addAll(source.internal);
		source.internal = instructions;
	}

	public CFGVertex getNewVertex() {
		CFGVertex ret = new CFGVertex();
		vertices.add(ret);
		ret.id = tempVertexCount++;
		return ret;
	}

	public CFGVertex getNewTempVertex() {
		CFGVertex ret = new CFGVertex();
		ret.id = -1;
		return ret;
	}

	public void scanVirtualRegister() {
		for (CFGVertex vertex : vertices) {
			for (IRInstruction ins : vertex.internal) {
				for (int uId : ins.getAllDefUId())
					classifyVirtualRegister(uId);
				for (int uId : ins.getAllUseUId())
					classifyVirtualRegister(uId);
			}
		}
		frameSize = Panel.getRegisterSize() * 33; // [0, 3] for $ra
		for (Map.Entry<Integer, Integer> entry : tempDelta.entrySet()) {
			entry.setValue(frameSize);
			int uId = entry.getKey();
			SymbolTableEntry e = Environment.symbolNames.table.get(uId);
			if (e.type == Tokens.VARIABLE)
				frameSize += Utility.align(((Type) e.ref).sizeof());
			else if (e.type == Tokens.TEMPORARY_REGISTER)
				frameSize += Panel.getRegisterSize();
			else
				throw new CompilationError("Internal Error.");
		}
		frameSize += Utility.align(functionTableEntry.definition.returnType.sizeof());
		for (int uId : functionTableEntry.scope.parametersUId) {
			parameterDelta.put(uId, frameSize);
			frameSize += Utility.align(((Type) Environment.symbolNames.table.get(uId).ref).sizeof());
		}
	}

	public void classifyVirtualRegister(int uId) {
		if (uId == -1 || uId == 0 || uId == -2 || uId == -3)
			return;
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e.scope <= 1)
			return;
		if (!functionTableEntry.scope.parametersUId.contains(uId))
			tempDelta.put(uId, -1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CFG of Function #").append(functionTableEntry.uId).append(Utility.NEW_LINE);
		sb.append(Utility.getIndent(1)).
				append("in = ").append(source.id).
				append(", out = ").append(sink.id).
				append(Utility.NEW_LINE);
		DepthFirstSearcher.getReachable(vertices, source).stream().forEach(x -> sb.append(x.toString()));
		return sb.toString();
	}
}
