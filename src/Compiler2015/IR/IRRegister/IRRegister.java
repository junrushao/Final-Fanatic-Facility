package Compiler2015.IR.IRRegister;

public interface IRRegister extends Cloneable {
	int getValue();
	@Override
	String toString();

	IRRegister clone();
}
