package interpreter.tree;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * The root node of the program parse tree.
 * Represents an entire, runnable program.
 * @author Brandon Oubre
 */
public class Program {
	public Procedure setup,  mainLoop;
	public Map<String, Procedure> procedures;
	
	/**
	 * Create a new program.
	 * @param setup The unique setup procedure.
	 * @param mainLoop The unique main loop procedure.
	 * @param procedures A map from user-defined procedure names to the associated procedure.
	 */
	public Program(Procedure setup, Procedure mainLoop, Map<String, Procedure> procedures) {
		this.setup = setup;
		this.mainLoop = mainLoop;
		this.procedures = procedures;
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
		
		for (String procName : procedures.keySet().stream().sorted().collect(Collectors.toList())) {
			procedures.get(procName).parseDump(sb, BlockNode.DUMP_INDENT);
		}
		return sb.toString();
	}
}
