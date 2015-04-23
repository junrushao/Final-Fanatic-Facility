package Compiler2015;

import Compiler2015.Environment.Environment;
import Compiler2015.Panel.Panel;
import Compiler2015.Parser.Compiler2015Lexer;
import Compiler2015.Parser.Compiler2015Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

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
			if (s.equals(Panel.prettyPrinter))
				Panel.isPrettyPrinter = true;
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
		Compiler2015Parser parser = new Compiler2015Parser(tokens);
		ParseTree tree = parser.compilationUnit();
		showSymbolTableAndASTTree();
	}

	public static void main(String args[]) {
		new Main().compile(args);
	}
}
