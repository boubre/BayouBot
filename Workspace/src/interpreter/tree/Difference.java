package interpreter.tree;

import codeblocks.Block;

/**
 * The difference function. (difference genus)
 * @author Brandon Oubre
 */
public class Difference extends ArithmeticOperation {
	/**
	 * Create a new difference function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public Difference(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public double getResult() {
		return arg1.getResult() - arg2.getResult();
	}
}
