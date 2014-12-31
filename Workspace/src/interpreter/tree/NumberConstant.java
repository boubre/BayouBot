package interpreter.tree;

import codeblocks.Block;

/**
 * A numeric constant. (Double or Integer) (number genus)
 * @author Brandon Oubre
 */
public abstract class NumberConstant extends Constant implements NumberResult {
	/**
	 * Create a new numeric constant.
	 * @param block The associated block.
	 */
	protected NumberConstant(Block block) {
		super(block);
	}
	
	/**
	 * Creates an appropriate number constant for the block.
	 * @param b The block to parse.
	 * @return An {@link IntegerConstant} or a {@link DoubleConstant}, depending on the value of the block label.
	 */
	public static NumberConstant createNumberConstant(Block b) {
		double value = Double.parseDouble(b.getBlockLabel());
		if ((value == Math.floor(value)) && !Double.isInfinite(value)) {
			return new IntegerConstant(b, (int)value);
		} else {
			return new DoubleConstant(b, value);
		}
	}
}
