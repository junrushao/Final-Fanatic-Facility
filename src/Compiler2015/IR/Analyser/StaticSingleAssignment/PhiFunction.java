package Compiler2015.IR.Analyser.StaticSingleAssignment;

import java.util.Arrays;

public class PhiFunction {
	public int vid[];

	public PhiFunction(int n) {
		vid = new int[n + 1];
		Arrays.fill(vid, -1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < vid.length; ++i) {
			if (i == 1) {
				sb.append("(");
			} else {
				sb.append(", ");
			}
			sb.append(vid[i]);
		}
		return sb.append(")").toString();
	}
}
