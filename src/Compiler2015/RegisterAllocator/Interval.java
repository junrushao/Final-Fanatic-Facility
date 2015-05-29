package Compiler2015.RegisterAllocator;

public class Interval {
	int start, end, uId;

	public Interval(int uId) {
		this.start = Integer.MAX_VALUE;
		this.end = Integer.MIN_VALUE;
		this.uId = uId;
	}

	public void update(int i) {
		start = Math.min(start, i);
		end = Math.max(end, i);
	}
}
