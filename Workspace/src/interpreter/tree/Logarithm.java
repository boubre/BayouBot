package interpreter.tree;

import codeblocks.Block;

/**
 * The base-10 logarithm function. (genus log)
 * @author Brandon Oubre
 */
public class Logarithm extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new base-10 logarithm function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public Logarithm(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() {
		return Math.log10(arg.getResult());
	}
}
