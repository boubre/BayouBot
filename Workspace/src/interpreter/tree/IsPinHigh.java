package interpreter.tree;

import interpreter.ProgramExecutionException;
import bayoubot.core.BayouBot;
import bayoubot.core.PinState;
import codeblocks.Block;

/**
 * A function that returns true if the specified pin is high on the robot. (genus isHigh)
 * @author Brandon Oubre
 */
public class IsPinHigh extends UnaryOperator<PinResult> implements BooleanResult {
	private BayouBot bot;
	
	/**
	 * Create a new Is Pin High? function.
	 * @param block The associated block.
	 * @param bot The BayouBot to query.
	 * @param arg The pin to check the state of.
	 */
	public IsPinHigh(Block block, BayouBot bot, PinResult arg) {
		super(block, arg);
	}
	
	@Override
	public boolean getResult() throws ProgramExecutionException {
		return bot.queryPinState(arg.getResult()) == PinState.HIGH;
	}
}
