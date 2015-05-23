package Compiler2015.IR.CFG;

import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.*;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.FunctionType;
import Compiler2015.Type.StructOrUnionType;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Panel;
import Compiler2015.Utility.Utility;

import java.util.*;
import java.util.stream.Collectors;

public class ControlFlowGraph {

	public static HashSet<CFGVertex> vertices;

	public static CFGVertex root, outBody;
	public static int nowUId;
	public static int tempVertexCount;
	public static CompoundStatement scope;
	public static Type returnType;

	public static HashMap<Integer, VirtualRegister> globalNonArrayVariables;

	public static HashMap<Integer, Integer> tempDelta;
	public static HashMap<Integer, Integer> parameterDelta;
	public static int frameSize;

	static {
		vertices = new HashSet<>();
		root = outBody = null;
		nowUId = 0;
	}

	public static void classifyVirtualRegister(int uId) {
		if (uId == -1 || uId == 0)
			return;
		SymbolTableEntry e = Environment.symbolNames.table.get(uId);
		if (e.scope <= 1)
			return;
		if (!scope.parametersUId.contains(uId))
			tempDelta.put(uId, -1);
	}

	public static void scanVirtualRegister() {
		tempDelta = new HashMap<>();
		parameterDelta = new HashMap<>();
		for (CFGVertex vertex : vertices) {
			for (IRInstruction ins : vertex.internal) {
				classifyVirtualRegister(ins.getRd());
				if (ins instanceof NonSource) {
					continue;
				}
				if (ins instanceof SingleSource) {
					classifyVirtualRegister(((SingleSource) ins).getRs());
				} else if (ins instanceof DoubleSource) {
					classifyVirtualRegister(((DoubleSource) ins).getRs());
					classifyVirtualRegister(((DoubleSource) ins).getRt());
				} else if (ins instanceof TripleSource) {
					classifyVirtualRegister(((TripleSource) ins).getA());
					classifyVirtualRegister(((TripleSource) ins).getB());
					classifyVirtualRegister(((TripleSource) ins).getC());
				} else
					throw new CompilationError("Internal Error.");
			}
		}
		frameSize = Panel.getRegisterSize() * 2; // [0, 3] for $fp, [4, 7] for $ra
		for (Map.Entry<Integer, Integer> entry : tempDelta.entrySet()) {
			entry.setValue(frameSize);
//			System.err.printf("delta[%d] = %d\n", entry.getKey(), entry.getValue());
			frameSize += Panel.getRegisterSize();
		}
		frameSize += Math.max(4, returnType.sizeof()); // for return value
		for (int uId : scope.parametersUId) {
			parameterDelta.put(uId, frameSize);
//			System.err.printf("delta[%d] = %d\n", uId, frameSize);
			frameSize += Math.max(4, ((Type) Environment.symbolNames.table.get(uId).ref).sizeof());
		}
	}

	public static CFGVertex getNewVertex() {
		CFGVertex ret = new CFGVertex();
		vertices.add(ret);
		ret.id = tempVertexCount++;
		return ret;
	}

	public static void findGoto(CFGVertex x) {
		if (x == outBody)
			return;
		if (x.unconditionalNext != null && x.unconditionalNext != outBody && x.unconditionalNext.internal.isEmpty()) {
			findGoto(x.unconditionalNext);
			x.unconditionalNext = x.unconditionalNext.unconditionalNext;
		}
		if (x.branchIfFalse != null && x.branchIfFalse != outBody && x.branchIfFalse.internal.isEmpty()) {
			findGoto(x.branchIfFalse);
			x.branchIfFalse = x.branchIfFalse.unconditionalNext;
		}
	}

	public static void process(CompoundStatement scope, CompoundStatement body, int uId) {
		nowUId = uId;
		tempVertexCount = 0;
		vertices.clear();
		ControlFlowGraph.scope = scope;
		returnType = ((FunctionType) Environment.symbolNames.table.get(uId).ref).returnType;

		root = null;
		outBody = ControlFlowGraph.getNewVertex();

		// collect useful information: global variables used in the function
		globalNonArrayVariables = new HashMap<>();
		body.collectGlobalNonArrayVariablesUsed(globalNonArrayVariables);

		// emit control flow graph
		body.emitCFG();
		root = body.beginCFGBlock;
		if (body.endCFGBlock.unconditionalNext == null)
			body.endCFGBlock.unconditionalNext = outBody;

		// load global variables before entering the function
		{
			ArrayList<IRInstruction> ins = new ArrayList<>();
			for (Map.Entry<Integer, VirtualRegister> element : ControlFlowGraph.globalNonArrayVariables.entrySet()) {
				SymbolTableEntry entry = Environment.symbolNames.table.get(element.getKey());
				int size = entry.ref instanceof StructOrUnionType || entry.ref instanceof ArrayPointerType ? Panel.getPointerSize() : ((Type) entry.ref).sizeof();
				ins.add(new ReadArray(element.getValue(), new ArrayRegister(new VirtualRegister(entry.uId), ImmediateValue.zero, size)));
			}
			ins.addAll(root.internal);
			root.internal = ins;
		}

		// store back global variables after function completes
		{
			for (Map.Entry<Integer, VirtualRegister> element : ControlFlowGraph.globalNonArrayVariables.entrySet()) {
				SymbolTableEntry entry = Environment.symbolNames.table.get(element.getKey());
				int size = entry.ref instanceof StructOrUnionType || entry.ref instanceof ArrayPointerType ? Panel.getPointerSize() : ((Type) entry.ref).sizeof();
				outBody.internal.add(new WriteArray(new ArrayRegister(new VirtualRegister(entry.uId), ImmediateValue.zero, size), element.getValue()));
			}
			outBody.internal.add(Pop.instance);
		}

		// remove unnecessary jumps
		vertices.stream().forEach(ControlFlowGraph::findGoto);
		vertices.stream().forEach(x -> {
			if (x.branchIfFalse != null && x.branchRegister == null) {
				if (x.internal.isEmpty())
					System.err.println("fuck id = " + x.id);
				x.branchRegister = x.internal.get(x.internal.size() - 1).rd;
			}
		});

		// eliminate unreachable vertices
		// warning: if out is unreachable, it will be labelled -1
		int n = DepthFirstSearcher.process(vertices, root);
		List<CFGVertex> unreachable = vertices.stream().filter(x -> x.id == -1).collect(Collectors.toList());
		unreachable.stream().forEach(vertices::remove);
		if (vertices.size() != n)
			throw new CompilationError("Internal Error.");

/*
		// build dominator tree, calculate dominance frontiers
		LengauerTarjan dominatorTreeSolver = new LengauerTarjan(vertices, root);
		dominatorTreeSolver.process(n);

		// insert phi-functions
		RegisterManager rm = new RegisterManager(body.givenVariables, root);
		PhiInserter phiInserter = new PhiInserter(vertices, rm);
		phiInserter.process();
*/
	}

	public static String toStr() {
		StringBuilder sb = new StringBuilder();
		sb.append("CFG of Function #").append(nowUId).append(Utility.NEW_LINE);
		sb.append(Utility.getIndent(1)).
				append("in = ").append(root.id).
				append(", out = ").append(outBody.id).
				append(Utility.NEW_LINE);
		DepthFirstSearcher.getReachable(vertices, root).stream().forEach(x -> sb.append(x.toString()));
		return sb.toString();
	}
}
