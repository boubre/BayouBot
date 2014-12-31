package interpreter.tree;

import codeblocks.Block;

/**
 * A String Constant (string genus).
 * @author Brandon Oubre
 */
public class StringConstant extends Constant implements StringResult {
	/**
	 * Create new StringConstant.
	 * @param block The associated block.
	 */
	public StringConstant(Block b) {
		super(b);
	}
	
	@Override
	public String getResult() {
		return block.getBlockLabel();
	}
	
	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + " => \"" + getResult() + "\"\n");
	}
}
