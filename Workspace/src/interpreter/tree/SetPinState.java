package interpreter.tree;

import interpreter.ProgramExecutionException;
import bayoubot.core.BayouBot;
import bayoubot.core.PinState;
import codeblocks.Block;

/**
 * Set a BayouBot output pin to a low (0V) or high (5V) value. (genera setPinHigh, setPinLow)
 * @author Brandon Oubre
 */
public class SetPinState extends Command {
	private BayouBot bot;
	private PinResult pin;
	private PinState state;

	/**
	 * 
	 * Create a new Set Pin State command.
	 * @param block The associated block.
	 * @param bot The BayouBot to send the command to.
	 * @param pin The pin to set.
	 * @param state Should the pin be set low or high?
	 */
	public SetPinState(Block block, BayouBot bot, PinResult pin, PinState state) {
		super(block);
		this.bot = bot;
		this.pin = pin;
		this.state = state;
	}
	
	@Override
	public void execute() throws ProgramExecutionException {
		bot.setPinState(pin.getResult(), state);
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		if (pin instanceof BlockNode) {
			((BlockNode)pin).parseDump(sb, indent + DUMP_INDENT);
		}
	}
}
