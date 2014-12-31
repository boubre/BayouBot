package interpreter.tree;

import codeblocks.Block;

/**
 * A Boolean Constant (true or false genera).
 * @author Brandon Oubre
 */
public class BooleanConstant extends Constant implements BooleanResult {
	private boolean value;
	
	/**
	 * Create new BooleanConstant.
	 * @param block The associated block.
	 */
	protected BooleanConstant(Block block) {
		super(block);
		value = block.getGenusName().equals("true");
	}

	@Override
	public boolean getResult() {
		return value;
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + " => " + getResult() + "\n");
	}
}
