package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.List;

import codeblocks.Block;

/**
 * An if-else program flow command. (genera if, ifelse)
 * @author Brandon Oubre
 */
public class If extends Command {
	private BooleanResult condition;
	private List<Command> trueCase;
	private List<Command> falseCase;
	
	/**
	 * Create a new if-else command.
	 * @param block The associated block.
	 * @param condition The test condition for the statement.
	 * @param trueCase The commands to execute if the condition is true.
	 * @param falseCase The commands to execute if the condition is false. (May be <tt>null</tt> if nothing is executed.)
	 */
	public If(Block block, BooleanResult condition, List<Command> trueCase, List<Command> falseCase) {
		super(block);
		assert condition != null : "The test condition must exist!";
		assert trueCase != null : "True case must exist!";
		this.condition = condition;
		this.trueCase = trueCase;
		this.falseCase = falseCase;
	}
	
	@Override
	public void execute() throws ProgramExecutionException {
		if (condition.getResult()) {
			for (Command c : trueCase) {
				c.execute();
			}
		} else if (falseCase != null) {
			for (Command c : falseCase) {
				c.execute();
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
		sb.append(">TRUE CASE: \n");
		for (BlockNode node : trueCase) {
			node.parseDump(sb, indent + DUMP_INDENT);
		}
		
		if (falseCase != null) {
			indent(sb, indent);
			sb.append(">FALSE CASE: \n");
			for (BlockNode node : falseCase) {
				node.parseDump(sb, indent + DUMP_INDENT);
			}
		}
	}
}
