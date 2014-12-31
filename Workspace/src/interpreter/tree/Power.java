package interpreter.tree;

import codeblocks.Block;

/**
 * The exponentiation function. (power genus)
 * @author Brandon Oubre
 */
public class Power extends ArithmeticOperation {
	/**
	 * Create a new power function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public Power(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public double getResult() {
		return Math.pow(arg1.getResult(), arg2.getResult());
	}
}
