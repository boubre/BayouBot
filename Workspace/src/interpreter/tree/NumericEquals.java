package interpreter.tree;

import codeblocks.Block;

/**
 * The equals function, with numeric arguments. (genus num-equals)
 * @author Brandon Oubre
 */
public class NumericEquals extends BinaryOperator<NumberResult, NumberResult> implements BooleanResult {
	/**
	 * Create a new not equals function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public NumericEquals(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() {
		return arg1.getResult() == arg2.getResult();
	}
}
