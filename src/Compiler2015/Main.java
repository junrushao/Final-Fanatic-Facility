package Compiler2015;

import Compiler2015.Parser.Compiler2015Lexer;
import Compiler2015.Parser.Compiler2015Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

/**
 * Created by junrushao on 15-4-12.
 */
public class Main {
	public static void main(String args[]) throws IOException {
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		Compiler2015Lexer lexer = new Compiler2015Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Compiler2015Parser parser = new Compiler2015Parser(tokens);
		ParseTree tree = parser.compilationUnit();
		System.out.println(tree.toString());
	}
}
