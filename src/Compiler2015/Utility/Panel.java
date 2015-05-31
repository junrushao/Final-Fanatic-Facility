package Compiler2015.Utility;

public final class Panel {
	public final static String msPrinter = "-printer=ms";
	public final static String krPrinter = "-printer=kr";

	public final static boolean DEBUG = false;
	public static boolean emitCFG = false;
	public static boolean checkMain = true;
	public static boolean emitAST = false;
	public static boolean emitSSA = false;
	public static boolean emitOptimizedSSA = false;
	public static boolean emitOptimizedCFG = false;
	public static String prettyPrinterType = null;

	public static int getRegisterSize() {
		return 4;
	}

	public static int getPointerSize() {
		return 4;
	}
}