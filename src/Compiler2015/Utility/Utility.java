package Compiler2015.Utility;

public final class Utility {

	public final static String NEW_LINE = System.lineSeparator();

	public static StringBuilder getSeveralSameCharacter(char c, int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; ++i)
			sb.append(c);
		return sb;
	}

	public static StringBuilder getIndent(int n) {
		return getSeveralSameCharacter('\t', n);
	}
}
