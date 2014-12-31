package interpreter.tree;

import codeblocks.Block;

/**
 * A numeric constant. (number genus) (Also pi and e)
 * @author Brandon Oubre
 */
public class NumberConstant extends Constant implements NumberResult {
	private double value;
	
	/**
	 * Create a new numeric constant.
	 * The value of the constant is parsed from the label of the block.
	 * @param block The associated block.
	 */
	public NumberConstant(Block block) {
		super(block);
		value = Double.parseDouble(block.getBlockLabel());
	}
	
	/**
	 * Create a new numeric constant with the given value.
	 * Useful for constant-valued blocks such as pi and e.
	 * @param block The associated block.
	 * @param value The numeric value of the block.
	 */
	public NumberConstant(Block block, double value) {
		super(block);
		this.value = value;
	}

	@Override
	public double getResult() {
		return value;
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + " => " + getResult() + "\n");
	}
}
