package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The trigonometric cosine function, with argument in <i>degrees</i>. (genus cos)
 * @author Brandon Oubre
 */
public class Cosine extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new cosine function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public Cosine(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return Math.cos(Math.toRadians(arg.getResult()));
	}
}
