package Compiler2015;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.FunctionTableEntry;
import Compiler2015.Environment.SymbolTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Parser.Compiler2015Lexer;
import Compiler2015.Parser.Compiler2015Parser;
import Compiler2015.Parser.ParseErrorListener;
import Compiler2015.Parser.PrettyPrinterListener;
import Compiler2015.RegisterAllocator.InterferenceGraphColoring;
import Compiler2015.Translate.ASTModifier;
import Compiler2015.Translate.Naive.MIPS.NaiveTranslator;
import Compiler2015.Translate.SimpleTranslator;
import Compiler2015.Type.FunctionType;
import Compiler2015.Utility.Panel;
import Compiler2015.Utility.Tokens;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.Map;

public class Main {
	public InputStream inputFile = System.in;
	public OutputStream outputFile = System.out;

	public static void main(String args[]) {
		try {
			new Main().compile(args);
		} catch (CompilationError e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}

	public void argumentsInspect(String args[]) {
		if (Panel.DEBUG) {
			try {
				inputFile = new BufferedInputStream(new FileInputStream("./input.c"));
			} catch (FileNotFoundException e) {
				inputFile = System.in;
			}
			try {
				outputFile = new BufferedOutputStream(new FileOutputStream("./test.s"));
			} catch (FileNotFoundException e) {
				outputFile = System.out;
			}
			Panel.prettyPrinterType = null;
			return;
		}
		for (String s : args) {
			boolean fetch = false;
			switch (s) {
				case Panel.msPrinter:
					Panel.prettyPrinterType = Panel.msPrinter;
					fetch = true;
					break;
				case Panel.krPrinter:
					Panel.prettyPrinterType = Panel.krPrinter;
					fetch = true;
					break;
				case "-emit-ast":
					Panel.emitAST = true;
					fetch = true;
					break;
				case "-emit-cfg":
					Panel.emitCFG = true;
					fetch = true;
					break;
				case "-emit-ssa":
					Panel.emitSSA = true;
					fetch = true;
					break;
				case "-emit-optimized-ssa":
					Panel.emitOptimizedSSA = true;
					fetch = true;
					break;
				case "-emit-optimized-cfg":
					Panel.emitOptimizedCFG = true;
					fetch = true;
					break;
				default:
					System.err.println("Unknown arg: " + s);
					break;
			}
			if (fetch)
				break;
		}
	}

	public void compile(String args[]) {
		// analyze the arguments
		argumentsInspect(args);

		loadLibraries();

		// open input file
		ANTLRInputStream input = null;
		try {
			input = new ANTLRInputStream(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// parse & AST construction
		Compiler2015Lexer lexer = new Compiler2015Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();
		Compiler2015Parser parser = new Compiler2015Parser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ParseErrorListener());
		RuleContext tree = parser.compilationUnit();

		ASTModifier.process();

		// emit AST
		if (Panel.emitAST) {
			System.out.println(Environment.toStr());
			return;
		}

		// pretty printer
		if (Panel.prettyPrinterType != null) {
			ParseTreeWalker walker = new ParseTreeWalker();
			PrettyPrinterListener printer = new PrettyPrinterListener(tokens);
			walker.walk(printer, tree);
			System.out.println(printer.toString());
			return;
		}

		// construct function table & control flow graph, optimize
		Environment.generateFunctionTable();
		for (Map.Entry<Integer, FunctionTableEntry> e : Environment.functionTable.entrySet()) {
			if (e.getValue().scope == null)
				continue;
			FunctionTableEntry entry = e.getValue();
			entry.cfg = new ControlFlowGraph(entry);
			entry.allocator = new InterferenceGraphColoring(entry.cfg);
		}

		if (Panel.emitSSA || Panel.emitCFG || Panel.emitOptimizedSSA || Panel.emitOptimizedCFG)
			return;

		// translate to MIPS
		PrintWriter out = new PrintWriter(outputFile);
		NaiveTranslator.generateGlobalVariables(out);
		out.println(".text");
		for (Map.Entry<Integer, FunctionTableEntry> e : Environment.functionTable.entrySet()) {
			FunctionTableEntry function = e.getValue();
			if (function.scope != null)
				new SimpleTranslator(function.cfg, out).generateFunction();
		}
		out.close();
	}

	public void loadLibraries() {
		// open input file
		ANTLRInputStream input;
		try {
			input = new ANTLRInputStream(new BufferedInputStream(new FileInputStream("./StdLib.c")));
		} catch (IOException e) {
			e.printStackTrace();
			throw new CompilationError("Cannot find StdLib.c");
		}
		// parse & AST construction
		Compiler2015Lexer lexer = new Compiler2015Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();
		Compiler2015Parser parser = new Compiler2015Parser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ParseErrorListener());
		parser.compilationUnit();
		for (int i = 1, size = Environment.symbolNames.table.size(); i < size; ++i) {
			SymbolTableEntry e = Environment.symbolNames.table.get(i);
			if (e.scope == 1 && e.name.equals("printf") && e.type == Tokens.VARIABLE && e.ref instanceof FunctionType) {
				Environment.uIdOfPrintf = e.uId;
			}
		}
	}
}