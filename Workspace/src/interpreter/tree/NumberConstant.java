package interpreter.tree;

import codeblocks.Block;

/**
 * A numeric constant. (number genus)
 * @author Brandon Oubre
 */
public class NumberConstant extends Constant implements NumberResult {
	private double value;
	
	/**
	 * Create a new numeric constant.
	 * @param block The associated block.
	 */
	protected NumberConstant(Block block) {
		super(block);
		value = Double.parseDouble(block.getBlockLabel());
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
