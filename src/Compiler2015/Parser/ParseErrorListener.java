package Compiler2015.Parser;

import Compiler2015.Exception.CompilationError;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ParseErrorListener extends BaseErrorListener {
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
		if (e != null)
			e.printStackTrace();
		throw new CompilationError("Parse Error " + line + " " + charPositionInLine + " " + msg);
	}
}
