package interpreter.tree;

import java.util.function.BooleanSupplier;

import interpreter.ProgramExecutionException;
import bayoubot.core.BayouBot;
import codeblocks.Block;

/**
 * Instruct the BayouBot to turn left. (genus turnLeft)
 * @author Brandon Oubre
 */
public class TurnLeft extends Command {
	private BayouBot bot;
	
	/**
	 * Create a new turn left command.
	 * @param block The associated block.
	 * @param bot The BayouBot to send the command to.
	 */
	public TurnLeft(Block block, BayouBot bot) {
		super(block);
		this.bot = bot;
	}
	
	@Override
	public void execute(BooleanSupplier testStop) throws ProgramExecutionException {
		bot.turnLeft();
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
	}
}
