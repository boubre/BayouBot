package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.List;

import codeblocks.Block;

/**
 * A loop that repeats as long as the condition is true. (genus while)
 * @author Brandon Oubre
 */
public class RepeatWhile extends Command {
	private BooleanResult condition;
	private List<Command> body;

	/**
	 * Creates a new while loop command.
	 * @param block The associated block.
	 * @param condition The loop condition. The loop will execute as long as the condition is true.
	 * @param body The commands to execute during each iteration.
	 */
	protected RepeatWhile(Block block, BooleanResult condition, List<Command> body) {
		super(block);
		assert condition != null : "Loop condition must not be null.";
		assert body != null : "Loop body must not be null";
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void execute() throws ProgramExecutionException {
		while (condition.getResult()) {
			for (Command cmd : body) {
				cmd.execute();
			}
		}
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		
		indent(sb, indent);
		sb.append(">CONDITION: \n");
		if (condition instanceof BlockNode) {
			((BlockNode) condition).parseDump(sb, indent + DUMP_INDENT);
		}
		
		indent(sb, indent);
		sb.append(">BODY: \n");
		for (BlockNode node : body) {
			node.parseDump(sb, indent + DUMP_INDENT);
		}
	}
}