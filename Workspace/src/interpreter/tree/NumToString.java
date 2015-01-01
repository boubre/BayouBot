package interpreter.tree;

import codeblocks.Block;

/**
 * A Numeric To String function (num-to-string genus).
 * @author Brandon Oubre
 */
public class NumToString extends UnaryOperator<NumberResult> implements StringResult {
	/**
	 * Create a new number to string function.
	 * @param block The associated block.
	 * @param arg The argument to this function.
	 */
	public NumToString(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public String getResult() {
		return String.valueOf(arg.getResult());
	}
}
