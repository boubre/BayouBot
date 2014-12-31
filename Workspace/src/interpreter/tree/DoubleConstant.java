package interpreter.tree;

import codeblocks.Block;

/**
 * A Double-valued numeric constant.
 * @author Brandon Oubre
 */
public class DoubleConstant extends NumberConstant {
	private double value;
	
	/**
	 * Create a new double constant.
	 * (Can be created by superclass {@link NumberConstant}.)
	 * @param block The associated block.
	 * @param value The value of the constant.
	 */
	public DoubleConstant(Block block, double value) {
		super(block);
		this.value = value;
	}

	@Override
	public boolean isInteger() {
		return false;
	}

	@Override
	public int getIntegerResult() {
		return (int)value;
	}

	@Override
	public double getDoubleResult() {
		return value;
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + " => " + value + "\n");
	}
}
