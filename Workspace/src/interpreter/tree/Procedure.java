package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.List;
import java.util.function.BooleanSupplier;

import codeblocks.Block;

/**
 * Represents a procedure. (ie, a list of commands to execute in order.)
 * @author Brandon Oubre
 */
public class Procedure extends BlockNode {
	private List<Command> commandList;
	private String name;
	
	/**
	 * Create a new procedure.
	 * @param block The associated block.
	 * @param name The name of this procedure.
	 * @param commands The commands associated with this procedure.
	 */
	protected Procedure(Block block, String name, List<Command> commands) {
		super(block);
		this.name = name;
		commandList = commands;
	}
	
	/**
	 * @return The name of this procedure.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Execute the procedure.
	 * @param testStop Will supply a <tt>true</tt> value if the program should stop execution and return immediately.
	 * @throws ProgramExecutionException An error occured with the program execution.
	 */
	public void execute(BooleanSupplier testStop) throws ProgramExecutionException {
		for (Command c : commandList) {
			if (testStop.getAsBoolean()) {
				return;
			}
			c.execute(testStop);
		}
	}
	
	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + " Name: " + name + "\n");
		for (BlockNode node : commandList) {
			node.parseDump(sb, indent + DUMP_INDENT);
		}
	}
}
