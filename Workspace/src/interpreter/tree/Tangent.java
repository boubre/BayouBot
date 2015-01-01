package interpreter.tree;

import codeblocks.Block;

/**
 * The trigonometric tangent function, with argument in <i>degrees</i>. (genus tan)
 * @author Brandon Oubre
 */
public class Tangent extends UnaryOperator<NumberResult> implements NumberResult {
	/**
	 * Create a new tangent function.
	 * @param block The associated block.
	 * @param arg The argument.
	 */
	public Tangent(Block block, NumberResult arg) {
		super(block, arg);
	}

	@Override
	public double getResult() {
		return Math.tan(Math.toRadians(arg.getResult()));
	}
}
