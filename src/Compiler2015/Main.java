package Compiler2015;

import Compiler2015.Environment.Environment;
import Compiler2015.Environment.FunctionTableEntry;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ControlFlowGraph;
import Compiler2015.Parser.Compiler2015Lexer;
import Compiler2015.Parser.Compiler2015Parser;
import Compiler2015.Parser.ParseErrorListener;
import Compiler2015.Parser.PrettyPrinterListener;
import Compiler2015.Translate.ASTModifier;
import Compiler2015.Translate.Naive.MIPS.NaiveTranslator;
import Compiler2015.Utility.Panel;
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
			Panel.emitAST = true;
			Panel.emitCFG = true;
			Panel.checkMain = false;
			return;
		}
		for (String s : args) {
			if (s.equals(Panel.msPrinter)) {
				Panel.prettyPrinterType = Panel.msPrinter;
			} else if (s.equals(Panel.krPrinter)) {
				Panel.prettyPrinterType = Panel.krPrinter;
			} else if (s.equals("-emit-ast")) {
				Panel.emitAST = true;
			} else if (!s.startsWith("-")) {
				try {
					inputFile = new BufferedInputStream(new FileInputStream(s));
				} catch (FileNotFoundException e) {
					System.err.println("File not exist, will use stdin as input.");
					inputFile = System.in;
				}
			} else if (s.equals(Panel.MIPS)) {
				Panel.architecture = Panel.MIPS;
			} else if (s.equals(Panel.JVM)) {
				Panel.architecture = Panel.JVM;
			} else if (s.equals(Panel.X86)) {
				Panel.architecture = Panel.X86;
			} else {
				System.err.println("Unknown arg: " + s);
			}
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

		// final check
		if (Panel.checkMain)
			Environment.finalCheck();

		ASTModifier.process();

		// emit AST
		if (Panel.emitAST) {
			System.out.println(Environment.toStr());
		}

		// pretty printer
		if (Panel.prettyPrinterType != null) {
			ParseTreeWalker walker = new ParseTreeWalker();
			PrettyPrinterListener printer = new PrettyPrinterListener(tokens);
			walker.walk(printer, tree);
			System.out.println(printer.toString());
		}

		// construct function table & control flow graph, optimize
		Environment.generateFunctionTable();
		for (Map.Entry<Integer, FunctionTableEntry> e : Environment.functionTable.entrySet()) {
			e.getValue().cfg = new ControlFlowGraph(e.getValue());
			if (Panel.emitCFG)
				System.out.println(e.getValue().cfg);
		}

		// translate to MIPS
		PrintWriter out = new PrintWriter(outputFile);
		NaiveTranslator.generateGlobalVariables(out);
		out.println(".text");
		for (Map.Entry<Integer, FunctionTableEntry> e : Environment.functionTable.entrySet()) {
			FunctionTableEntry function = e.getValue();
			new NaiveTranslator(function.cfg).generateFunction(out);
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
	}
}
