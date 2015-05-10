package Compiler2015.IR.Instruction;

public interface TripleSource {
	int getA();

	int getB();

	int getC();

	void setAVersion(int x);

	void setBVersion(int x);

	void setCVersion(int x);
}
