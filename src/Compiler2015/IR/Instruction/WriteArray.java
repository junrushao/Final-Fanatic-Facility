package Compiler2015.IR.Instruction;

/**
 * a[b] = rs
 */
public class WriteArray extends IRInstruction {
	public int a, b, rs;

	public WriteArray(int a, int b, int rs) {
		this.a = a;
		this.b = b;
		this.rs = rs;
	}
}
