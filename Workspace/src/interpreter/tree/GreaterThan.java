package interpreter.tree;

import codeblocks.Block;

/**
 * The greater than (>) function. (genus greaterthan)
 * @author Brandon Oubre
 */
public class GreaterThan extends BinaryOperator<NumberResult, NumberResult> implements BooleanResult {
	/**
	 * Create a new greater than function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public GreaterThan(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() {
		return arg1.getResult() > arg2.getResult();
	}
}
