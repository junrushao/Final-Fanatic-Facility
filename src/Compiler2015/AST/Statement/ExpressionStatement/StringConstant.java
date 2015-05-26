package Compiler2015.AST.Statement.ExpressionStatement;

import Compiler2015.Environment.Environment;
import Compiler2015.Exception.CompilationError;
import Compiler2015.IR.CFG.ExpressionCFGBuilder;
import Compiler2015.IR.IRRegister.VirtualRegister;
import Compiler2015.Type.ArrayPointerType;
import Compiler2015.Type.CharType;

import java.util.ArrayList;

public class StringConstant extends Expression {
	/**
	 * @see <a href="http://en.wikipedia.org/wiki/Escape_sequences_in_C">Escape Sequences in C</a>
	 * For hex number, if the resulting integer value is too large to fit in a single character, the actual numerical value assigned is implementation-defined.
	 * <p>
	 * In my implementation, I ignored this situation.
	 * <p>
	 * Also, in format strings, we should pay attention to '%', which is also ignored here.
	 */
//	public static boolean isToAppendBack = true;
	public String c;
	public int uId;

	public StringConstant(final String c) {
//		if (!c.endsWith("\0"))
//			throw new CompilationError("Internal Error.");
		this.c = c;
		this.uId = Environment.symbolNames.defineStringConstant(c);
		this.isLValue = false;
		this.type = new ArrayPointerType(
				CharType.instance,
				new ArrayList<Integer>() {{
					add(c.length());
				}}
		);
	}

	public static String convert(String original) {
		int n = original.length();
		original = original.substring(1, n - 1);
		n -= 2;
		if (n == 0)
			return "";

		StringBuilder sb = new StringBuilder(original.length());
		if (sb.length() != 0)
			throw new CompilationError("Internal Error.");

		for (int i = 0; i < n; ) {
			char c = original.charAt(i);
			if (c != '\\') {
				sb.append(c);
				++i;
				continue;
			}
			if (i == n - 1) {
				// stray '\' is somewhat strange
				break;
			}
			char nextC = original.charAt(i + 1);
			if (nextC == 'x') { // hex
				int toC = 0;
				boolean done = false;
				for (i = i + 2; i < n; ++i) {
					char now = original.charAt(i);
					if (Character.isDigit(now))
						toC = (toC << 4) + (now - '0');
					else if ('a' <= now && now <= 'f')
						toC = (toC << 4) + (now - 'a' + 10);
					else if ('A' <= now && now <= 'F')
						toC = (toC << 4) + (now - 'A' + 10);
					else {
						done = true;
						sb.append((char) toC);
						break;
					}
				}
				if (i == n) {
					done = true;
					sb.append((char) toC);
				}
				if (!done)
					throw new CompilationError("\\x used with no following hex digits.");
			} else if ('0' <= nextC && nextC <= '7') {
				int toC = nextC - '0';
				int cnt = 1;
				for (i = i + 2; i < n && cnt < 3; ++i, ++cnt) {
					char now = original.charAt(i);
					if ('0' <= now && now <= '7')
						toC = (toC << 3) + (now - '0');
					else
						break;
				}
				sb.append((char) toC);
			} else if (Character.isDigit(nextC)) {
				++i;
			} else {
				char toC = 0;
				switch (nextC) {
					case 'a':
						toC = 7;
						break;
					case 'b':
						toC = 8;
						break;
					case 't':
						toC = 9;
						break;
					case 'n':
						toC = 0xA;
						break;
					case 'v':
						toC = 0xB;
						break;
					case 'f':
						toC = 0xC;
						break;
					case 'r':
						toC = 0xD;
						break;
					case '\"':
						toC = 0x22;
						break;
					case '\'':
						toC = 0x27;
						break;
					case '?':
						toC = 0x3F;
						break;
					case '\\':
						toC = 0x5C;
						break;
				}
				if (toC == 0) {
					++i;
				} else {
					sb.append(toC);
					i += 2;
				}
			}
		}
//		if (isToAppendBack)
//			sb.append('\0');
		return sb.toString();
	}

	public static Expression getExpression(ArrayList<String> s) {
		StringBuilder sb = new StringBuilder();
//		isToAppendBack = false;
		for (String str : s) {
			sb.append(convert(str));
		}
//		isToAppendBack = true;
		return new StringConstant(sb.toString());
	}

	public static String toMIPSString(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c < 32 || c >= 127) {
				String str = Integer.toOctalString(c);
				while (str.length() < 3)
					str = "0" + str;
				sb.append("\\").append(str);
			} else if (c == '\"')
				sb.append("\\\"");
			else
				sb.append(c);
		}
		return sb.append("\\000").toString();
	}

	public static String toPrintableString(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c < 32 || c >= 127) {
				String str = Integer.toOctalString(c);
				while (str.length() < 3)
					str = "0" + str;
				sb.append("\\").append(str);
			} else if (c == '\'')
				sb.append("\\\'");
			else if (c == '\"')
				sb.append("\\\"");
			else
				sb.append(c);
		}
		return sb.append("\\000").toString();
	}

	@Override
	public String toString() {
		return "\"" + toPrintableString(c) + "\"";
	}

	@Override
	public void emitCFG(ExpressionCFGBuilder builder) {
		tempRegister = new VirtualRegister(uId);
	}

	@Override
	public Expression rebuild() {
		return this;
	}
}
