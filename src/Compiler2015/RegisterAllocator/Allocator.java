package Compiler2015.RegisterAllocator;

import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.CFGVertex;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.IR.Instruction.IRInstruction;

import java.util.*;
import java.util.stream.Collectors;

class ActivePool {
	public boolean occupied[];
	public TreeSet<Interval> intervals;
	public HashMap<Integer, MachineRegister> physicalRegister; // uId -> physical register.

	public ActivePool() {
		occupied = new boolean[MachineRegister.K];
		intervals = new TreeSet<>((o1, o2) -> o1.end - o2.end);
		physicalRegister = new HashMap<>();
	}

	public int getFree() {
		for (int i = 0; i < occupied.length; ++i)
			if (!occupied[i])
				return i;
		throw new CompilationError("Internal Error.");
	}

	public void addInterval(Interval i) {
/*
		if (Environment.isArrayLike(i.uId)) {
			spillIt(i.uId);
			return;
		}
*/
		intervals.add(i);
		if (intervals.size() <= MachineRegister.K)
			allocateRegister(i.uId);
		else {
			Interval kicked = intervals.pollLast();
			spillIt(kicked.uId);
			if (i != kicked)
				allocateRegister(i.uId);
		}
	}

	public void removeInterval(int position) {
		List<Interval> toBeRemoved = intervals.stream().filter(x -> x.end == position).collect(Collectors.toList());
		for (Interval interval : toBeRemoved) {
			intervals.remove(interval);
			occupied[physicalRegister.get(interval.uId).order] = false;
		}
	}

	public void spillIt(int uId) {
		if (physicalRegister.containsKey(uId)) {
			MachineRegister reg = physicalRegister.get(uId);
			occupied[reg.order] = false;
		}
		physicalRegister.put(uId, null);
	}

	public void allocateRegister(int uId) {
		int registerId = getFree();
		occupied[registerId] = true;
		physicalRegister.put(uId, MachineRegister.availableMachineRegisters[registerId]);
	}
}

public class Allocator {
	public static HashMap<Integer, MachineRegister> mapping = new HashMap<>();
	public ActivePool activePool;
	public ArrayList<CFGVertex> blockList;
	public ArrayList<Interval> intervals;

	public Allocator() {
		activePool = new ActivePool();
		blockList = new ArrayList<>();
	}

	public static void updatePosition(int uId, int position, HashMap<Integer, Interval> intervals) {
		if (!intervals.containsKey(uId))
			intervals.put(uId, new Interval(uId));
		Interval me = intervals.get(uId);
		me.update(position);
	}

	public void process(ControlFlowGraph graph) {
		DFSInDominatorTree(graph.source);
		int positionCount = buildIntervals();
		int ptr = 0;
		for (int i = 1; i <= positionCount; ++i) {
			for (; ptr < intervals.size() && intervals.get(ptr).start == i; ++ptr)
				activePool.addInterval(intervals.get(ptr));
			activePool.removeInterval(i);
		}
		mapping.putAll(activePool.physicalRegister);
	}

	public int buildIntervals() {
		HashMap<Integer, Interval> intervals = new HashMap<>();
		int positionCount = 0;
		for (CFGVertex block : blockList) {
			if (!block.phiBlock.isEmpty())
				throw new CompilationError("Internal Error.");
			for (IRInstruction i : block.internal) {
				++positionCount;
				for (VirtualRegister r : i.getAllUseVR())
					if (r != null && r.uId > 0)
						updatePosition(r.uId, positionCount, intervals);
				++positionCount;
				for (VirtualRegister r : i.getAllDefVR())
					if (r != null && r.uId > 0)
						updatePosition(r.uId, positionCount, intervals);
			}
		}
		this.intervals = new ArrayList<>(intervals.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList()));
		this.intervals.sort((o1, o2) -> o1.start - o2.start);
		return positionCount;
	}

	public void DFSInDominatorTree(CFGVertex t) {
		blockList.add(t);
		t.children.forEach(this::DFSInDominatorTree);
	}
}