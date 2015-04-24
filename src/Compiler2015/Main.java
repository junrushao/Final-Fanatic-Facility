package Compiler2015;

import Compiler2015.Environment.Environment;
import Compiler2015.Panel.Panel;
import Compiler2015.Parser.Compiler2015Lexer;
import Compiler2015.Parser.Compiler2015Parser;
import Compiler2015.Parser.ParseErrorListener;
import Compiler2015.Parser.PrettyPrinterListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;

public class Main {
	public InputStream inputFile = System.in;

	public void showSymbolTableAndASTTree() {
		System.out.println(Environment.toStr());
	}

	public void argumentsInspect(String args[]) {
		boolean hasInputFile = false;
		if (Panel.DEBUG) {
			hasInputFile = true;
			try {
				inputFile = new BufferedInputStream(new FileInputStream("input.c"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		for (String s : args) {
			if (s.equals(Panel.msPrinter)) {
				Panel.prettyPrinterType = Panel.msPrinter;
			} else if (s.equals(Panel.krPrinter)) {
				Panel.prettyPrinterType = Panel.krPrinter;
			}
			else if (!s.startsWith("-") && !hasInputFile) {
				hasInputFile = true;
				try {
					inputFile = new BufferedInputStream(new FileInputStream(s));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				System.err.println("Unknown arg: " + s);
			}
		}
	}

	public void compile(String args[]) {
		argumentsInspect(args);
		ANTLRInputStream input = null;
		try {
			input = new ANTLRInputStream(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Compiler2015Lexer lexer = new Compiler2015Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		tokens.fill();
		Compiler2015Parser parser = new Compiler2015Parser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(new ParseErrorListener());
		RuleContext tree = parser.compilationUnit();

		showSymbolTableAndASTTree();
		if (Panel.prettyPrinterType != null) {
			ParseTreeWalker walker = new ParseTreeWalker();
			walker.walk(new PrettyPrinterListener(tokens), tree);
		}
	}

	public static void main(String args[]) {
		new Main().compile(args);
	}
}
