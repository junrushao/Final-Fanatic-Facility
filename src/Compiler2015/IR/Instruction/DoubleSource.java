package Compiler2015.IR.Instruction;

public interface DoubleSource {
	int getRs();

	int getRt();

	void setRsVersion(int x);

	void setRtVersion(int x);
}
