package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The square root function. (genus sqrt)
 * @author Brandon Oubre
 */
public class SquareRoot extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new square root function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public SquareRoot(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return Math.sqrt(arg.getResult());
	}
}
