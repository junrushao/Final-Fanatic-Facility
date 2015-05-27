package Compiler2015.Utility;

import java.util.HashSet;
import java.util.List;

public final class Utility {

	public final static String NEW_LINE = System.lineSeparator();

	public static int align(int x) {
		int size = Panel.getRegisterSize();
		return (x + size - 1) / size * size;
	}

	public static StringBuilder getSeveralSameCharacter(char c, int n) {
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; ++i)
			sb.append(c);
		return sb;
	}

	public static StringBuilder getIndent(int n) {
		return getSeveralSameCharacter('\t', n);
	}

	public static String toString(List l) {
		if (l.isEmpty())
			return "{}";
		StringBuilder sb = new StringBuilder();
		String sep = "{";
		for (Object o : l) {
			sb.append(sep).append(o.toString());
			sep = ", ";
		}
		return sb.append("}").toString();
	}

	public static String toString(List a, List b) {
		int sizeA = a.size(), sizeB = b.size();
		int n = Math.max(sizeA, sizeB);
		if (n == 0) return "{}";
		StringBuilder sb = new StringBuilder();
		String sep = "{";
		for (int i = 0; i < n; ++i) {
			String s1 = i < sizeA ? a.get(i).toString() : "%";
			String s2 = i < sizeB ? b.get(i).toString() : "%";
			sb.append(sep).append("<").append(s1).append(", ").append(s2).append(">");
			sep = ", ";
		}
		return sb.append("}").toString();
	}

	public static class SetOperation<T> {
		public HashSet<T> kickOut(HashSet<T> a, HashSet<T> b) {
			HashSet<T> result = new HashSet<>(a);
			b.stream().filter(result::contains).forEach(result::remove);
			return result;
		}

		public HashSet<T> unionInto(HashSet<T> into, HashSet<T> from) {
			from.forEach(into::add);
			return into;
		}

		public boolean equal(HashSet<T> a, HashSet<T> b) {
			if (a.size() != b.size())
				return false;
			for (T x : a)
				if (!b.contains(x))
					return false;
			return true;
		}
	}
}
