package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The Remainder (modulo) function. (genus remainder)
 * @author Brandon Oubre
 */
public class Remainder extends BinaryOperator<NumberResult, NumberResult> implements NumberResult {
	/**
	 * Create a new remainder function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public Remainder(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return arg1.getResult() % arg2.getResult();
	}
}
