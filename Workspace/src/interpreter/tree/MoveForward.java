package interpreter.tree;

import java.util.function.BooleanSupplier;

import interpreter.ProgramExecutionException;
import bayoubot.core.BayouBot;
import codeblocks.Block;

/**
 * Instruct the BayouBot to move forward. (genus moveForward)
 * @author Brandon Oubre
 */
public class MoveForward extends Command {
	private BayouBot bot;
	
	/**
	 * Create a new move forward command.
	 * @param block The associated block.
	 * @param bot The BayouBot to send the command to.
	 */
	public MoveForward(Block block, BayouBot bot) {
		super(block);
		this.bot = bot;
	}
	
	@Override
	public void execute(BooleanSupplier testStop) throws ProgramExecutionException {
		bot.moveForward();
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
	}
}
