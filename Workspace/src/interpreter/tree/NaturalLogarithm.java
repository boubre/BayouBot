package interpreter.tree;

import codeblocks.Block;

/**
 * The natural logarithm function. (genus ln)
 * @author Brandon Oubre
 */
public class NaturalLogarithm extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new natural logarithm function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public NaturalLogarithm(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() {
		return Math.log(arg.getResult());
	}
}
