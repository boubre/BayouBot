package interpreter.tree;

public class Program {
	public Setup setup;
	
	public Program(Setup setup) {
		this.setup = setup;
	}
	
	/**
	 * @return A string representing the parse tree.
	 */
	public String parseDump() {
		StringBuilder sb = new StringBuilder("Program\n");
		setup.parseDump(sb, BlockNode.DUMP_INDENT);
		return sb.toString();
	}
}
