package interpreter.tree;

/**
 * The root node of the program parse tree.
 * Represents an entire, runnable program.
 * @author Brandon Oubre
 */
public class Program {
	public Procedure setup,  mainLoop;
	
	/**
	 * Create a new program.
	 * @param setup The unique setup procedure.
	 * @param mainLoop The unique main loop procedure.
	 */
	public Program(Procedure setup, Procedure mainLoop) {
		this.setup = setup;
		this.mainLoop = mainLoop;
	}
	
	/**
	 * @return A string representing the parse tree.
	 */
	public String parseDump() {
		StringBuilder sb = new StringBuilder("Program\n");
		if (setup != null) 
			setup.parseDump(sb, BlockNode.DUMP_INDENT);
		if (mainLoop != null) 
			mainLoop.parseDump(sb, BlockNode.DUMP_INDENT);
		return sb.toString();
	}
}
