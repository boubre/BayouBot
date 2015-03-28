package interpreter.tree;

import interpreter.ProgramExecutionException;
import bayoubot.core.BayouBot;
import bayoubot.core.PinMode;
import codeblocks.Block;

/**
 * Set a BayouBot pin as an input pin or output pin. (genera setPinInput, setPinOutput)
 * @author Brandon Oubre
 */
public class SetPinMode extends Command {
	private BayouBot bot;
	private PinResult pin;
	private PinMode mode;

	/**
	 * 
	 * Create a new Set Pin Mode command.
	 * @param block The associated block.
	 * @param bot The BayouBot to send the command to.
	 * @param pin The pin to set.
	 * @param mode Should the pin be set as input or output?
	 */
	public SetPinMode(Block block, BayouBot bot, PinResult pin, PinMode mode) {
		super(block);
		this.bot = bot;
		this.pin = pin;
		this.mode = mode;
	}
	
	@Override
	public void execute() throws ProgramExecutionException {
		bot.setPinMode(pin.getResult(), mode);
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
