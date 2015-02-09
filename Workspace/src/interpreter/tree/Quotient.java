package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The division function. (quotient genus)
 * @author Brandon Oubre
 */
public class Quotient extends BinaryOperator<NumberResult, NumberResult> implements NumberResult {
	/**
	 * Create a new quotient function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public Quotient(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return arg1.getResult() / arg2.getResult();
	}
}
