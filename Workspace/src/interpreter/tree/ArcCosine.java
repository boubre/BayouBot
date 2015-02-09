package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The trigonometric arccosine function, with the result in <i>degrees</i>. (genus acos)
 * @author Brandon Oubre
 */
public class ArcCosine extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new arccosine function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public ArcCosine(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return Math.toDegrees(Math.acos(arg.getResult()));
	}
}
