package interpreter.tree;

import codeblocks.Block;

/**
 * The absolute value function. (genus abs)
 * @author Brandon Oubre
 */
public class AbsoluteValue extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new absolute value function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public AbsoluteValue(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() {
		return Math.abs(arg.getResult());
	}
}
