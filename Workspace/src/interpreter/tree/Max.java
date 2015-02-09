package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The maximum function. (genus max)
 * @author Brandon Oubre
 */
public class Max extends BinaryOperator<NumberResult, NumberResult> implements NumberResult {
	/**
	 * Create a new max function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public Max(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return Math.max(arg1.getResult(), arg2.getResult());
	}
}
