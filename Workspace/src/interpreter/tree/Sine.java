package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The trigonometric sine function, with argument in <i>degrees</i>. (genus sin)
 * @author Brandon Oubre
 */
public class Sine extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new sine function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public Sine(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return Math.sin(Math.toRadians(arg.getResult()));
	}
}
