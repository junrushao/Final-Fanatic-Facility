package Compiler2015.Utility;

import Compiler2015.Exception.CompilationError;

public final class Panel {
	public final static String msPrinter = "--ms";
	public final static String krPrinter = "--kr";

	public final static String MIPS = "-mips";
	public final static String X86 = "-x86";
	public final static String JVM = "-jvm";
	public final static boolean DEBUG = true;
	public static boolean emitCFG = false;
	public static boolean checkMain = false;
	public static boolean emitAST = false;
	public static String prettyPrinterType = null;
	public static String architecture = MIPS;

	public static int getRegisterSize() {
		if (architecture.equals(MIPS))
			return 4;
		if (architecture.equals(X86))
			return 4;
		if (architecture.equals(JVM))
			return 8;
		throw new CompilationError("Internal Error.");
	}

	public static int getPointerSize() {
		if (architecture.equals(MIPS))
			return 4;
		if (architecture.equals(X86))
			return 4;
		if (architecture.equals(JVM))
			return 8;
		throw new CompilationError("Internal Error.");
	}
}
