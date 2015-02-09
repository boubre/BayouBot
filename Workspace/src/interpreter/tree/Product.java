package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The multiplication function. (product genus)
 * @author Brandon Oubre
 */
public class Product extends BinaryOperator<NumberResult, NumberResult> implements NumberResult {
	/**
	 * Create a new product function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public Product(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return arg1.getResult() * arg2.getResult();
	}
}
