package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The minimum function. (genus min)
 * @author Brandon Oubre
 */
public class Min extends BinaryOperator<NumberResult, NumberResult> implements NumberResult {
	/**
	 * Create a new min function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public Min(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return Math.min(arg1.getResult(), arg2.getResult());
	}
}
