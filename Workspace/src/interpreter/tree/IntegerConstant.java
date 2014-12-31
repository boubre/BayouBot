package interpreter.tree;

import codeblocks.Block;

/**
 * An Integer-valued numeric constant.
 * @author Brandon Oubre
 */
public class IntegerConstant extends NumberConstant {
	private int value;
	
	/**
	 * Create a new integer constant.
	 * (Can be created by superclass {@link NumberConstant}.)
	 * @param block The associated block.
	 * @param value The value of the constant.
	 */
	public IntegerConstant(Block block, int value) {
		super(block);
		this.value = value;
	}

	@Override
	public boolean isInteger() {
		return true;
	}

	@Override
	public int getIntegerResult() {
		return value;
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
