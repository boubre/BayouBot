package interpreter.tree;

import codeblocks.Block;

/**
 * An integer truncation function. (genus int)
 * Returns the whole part of a number.
 * @author Brandon Oubre
 */
public class Int extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new int function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public Int(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() {
		return (int)arg.getResult();
	}
}
