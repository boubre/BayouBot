package interpreter.tree;

import codeblocks.Block;

/**
 * The trigonometric arctangent function, with the result in <i>degrees</i>. (genus atan)
 * @author Brandon Oubre
 */
public class ArcTangent extends BinaryOperator<NumberResult, NumberResult> implements NumberResult {
	/**
	 * Create a new arctangent function.
	 * @param block The associated block.
	 * @param arg1 The first (x) argument.
	 * @param arg2 The second (y) argument.
	 */
	public ArcTangent(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public double getResult() {
		//Remember that atan2 is (y,x). ie arg2 is the first argument.
		return Math.toDegrees(Math.atan2(arg2.getResult(), arg1.getResult()));
	}
}
