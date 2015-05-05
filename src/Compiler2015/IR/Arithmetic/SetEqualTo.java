package Compiler2015.IR.Arithmetic;

/**
 * rd = rs == rt
 */
public class SetEqualTo extends Arithmetic {
	public int rd, rs, rt;

	public SetEqualTo(int rd, int rs, int rt) {
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}
}
