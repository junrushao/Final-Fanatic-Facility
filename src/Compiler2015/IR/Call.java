package Compiler2015.IR;

/**
 * rd = func()
 * If return type of func is void, rd is useless.
 */
public class Call extends IRInstruction {
	public int rd, func;

	public Call(int rd, int func) {
		this.rd = rd;
		this.func = func;
	}
}
