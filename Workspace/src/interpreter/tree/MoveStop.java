package interpreter.tree;

import interpreter.ProgramExecutionException;
import bayoubot.core.BayouBot;
import codeblocks.Block;

/**
 * Instruct the BayouBot to stop moving. (genus moveStop)
 * @author Brandon Oubre
 */
public class MoveStop extends Command {
	private BayouBot bot;
	
	/**
	 * Create a new stop moving command.
	 * @param block The associated block.
	 * @param bot The BayouBot to send the command to.
	 */
	public MoveStop(Block block, BayouBot bot) {
		super(block);
		this.bot = bot;
	}
	
	@Override
	public void execute() throws ProgramExecutionException {
		bot.stop();
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
	}
}
