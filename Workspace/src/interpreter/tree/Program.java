package interpreter.tree;

/**
 * The root node of the program parse tree.
 * Represents an entire, runnable program.
 * @author Brandon Oubre
 */
public class Program {
	public Setup setup;
	
	/**
	 * Create a new program.
	 * @param setup The unique setup procedure.
	 */
	public Program(Setup setup) {
		this.setup = setup;
	}
	
	/**
	 * @return A string representing the parse tree.
	 */
	public String parseDump() {
		StringBuilder sb = new StringBuilder("Program\n");
		if (setup != null) 
			setup.parseDump(sb, BlockNode.DUMP_INDENT);
		return sb.toString();
	}
}
