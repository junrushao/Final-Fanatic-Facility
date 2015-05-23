package Compiler2015.IR.IRRegister;

public interface IRRegister extends Cloneable {
	int getUId();
	@Override
	String toString();

	IRRegister clone();
}
