package Compiler2015.IR.CFG;

import Compiler2015.AST.Statement.CompoundStatement;
import Compiler2015.Environment.Environment;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.StaticSingleAssignment.LengauerTarjan;
import Compiler2015.IR.CFG.StaticSingleAssignment.PhiInserter;
import Compiler2015.IR.CFG.StaticSingleAssignment.RegisterManager;
import Compiler2015.IR.IRRegister.ArrayRegister;
import Compiler2015.IR.IRRegister.ImmediateValue;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;
import Compiler2015.IR.Instruction.Pop;
import Compiler2015.IR.Instruction.ReadArray;
import Compiler2015.IR.Instruction.WriteArray;
import Compiler2015.Type.Type;
import Compiler2015.Utility.Utility;

import java.util.*;
import java.util.stream.Collectors;

public class ControlFlowGraph {

	public static HashSet<CFGVertex> vertices;

	public static CFGVertex root, outBody;
	public static int nowUId;
	public static int tempVertexCount;
	public static CompoundStatement scope;
	public static HashMap<Integer, VirtualRegister> globalNonArrayVariables;

	static {
		vertices = new HashSet<>();
		root = outBody = null;
		nowUId = 0;
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

/*		a test
		{
			vertices.clear();
			CFGVertex v = getNewVertex();
			root = getNewVertex();
			outBody = getNewVertex();
			root.unconditionalNext = v;
			v.unconditionalNext = outBody;
			v.branchIfFalse = v;

			root.internal.add(new Move(new VirtualRegister(9), new ImmediateValue(1)));
			root.internal.add(new Move(new VirtualRegister(10), new ImmediateValue(1)));

			v.internal.add(new Move(new VirtualRegister(11), new VirtualRegister(10)));
			v.internal.add(new Move(new VirtualRegister(10), new VirtualRegister(9)));
			v.internal.add(new Move(new VirtualRegister(9), new VirtualRegister(11)));
			vertices.add(root);
			vertices.add(outBody);
			vertices.add(v);
		}
*/
		// load global instructions before entering the function
		{
			ArrayList<IRInstruction> ins = new ArrayList<>();
			for (Map.Entry<Integer, VirtualRegister> element : ControlFlowGraph.globalNonArrayVariables.entrySet()) {
				SymbolTableEntry entry = Environment.symbolNames.table.get(element.getKey());
				ins.add(new ReadArray(element.getValue(), new ArrayRegister(new VirtualRegister(entry.uId), new ImmediateValue(0), ((Type) entry.ref).sizeof())));
			}
			ins.addAll(root.internal);
			root.internal = ins;
		}

		// store back global variables after function completes
		{
			for (Map.Entry<Integer, VirtualRegister> element : ControlFlowGraph.globalNonArrayVariables.entrySet()) {
				SymbolTableEntry entry = Environment.symbolNames.table.get(element.getKey());
				outBody.internal.add(new WriteArray(new ArrayRegister(new VirtualRegister(entry.uId), new ImmediateValue(0), ((Type) entry.ref).sizeof()), element.getValue()));
			}
			outBody.internal.add(Pop.instance);
		}

		// remove unnecessary jumps
		vertices.stream().forEach(ControlFlowGraph::findGoto);

		// eliminate unreachable vertices
		// warning: if out is unreachable, it will be labelled -1
		int n = DepthFirstSearcher.process(vertices, root);
		List<CFGVertex> unreachable = vertices.stream().filter(x -> x.id == -1).collect(Collectors.toList());
		unreachable.stream().forEach(vertices::remove);
		if (vertices.size() != n)
			throw new CompilationError("Internal Error.");

		// build dominator tree, calculate dominance frontiers
		LengauerTarjan dominatorTreeSolver = new LengauerTarjan(vertices, root);
		dominatorTreeSolver.process(n);

		// insert phi-functions
		RegisterManager rm = new RegisterManager(body.givenVariables, root);
		PhiInserter phiInserter = new PhiInserter(vertices, rm);
		phiInserter.process();
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
