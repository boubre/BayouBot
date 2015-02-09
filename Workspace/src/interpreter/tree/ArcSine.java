package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The trigonometric arcsine function, with the result in <i>degrees</i>. (genus asin)
 * @author Brandon Oubre
 */
public class ArcSine extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new arcsine function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public ArcSine(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return Math.toDegrees(Math.asin(arg.getResult()));
	}
}
