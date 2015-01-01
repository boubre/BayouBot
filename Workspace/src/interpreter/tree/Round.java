package interpreter.tree;

import codeblocks.Block;

/**
 * A rounding function. (genus round)
 * Rounds the argument to the nearest integer.
 * @author Brandon Oubre
 */
public class Round extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new round function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public Round(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() {
		return Math.round(arg.getResult());
	}
}
