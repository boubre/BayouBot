package interpreter.tree;

import codeblocks.Block;

/**
 * A Boolean To String function (bool-to-string genus).
 * @author Brandon Oubre
 */
public class BoolToString extends Function implements StringResult {
	public BooleanResult arg;
	
	/**
	 * Create a new boolean to string function.
	 * @param block The associated block.
	 * @param arg The argument to this function.
	 */
	public BoolToString(Block block, BooleanResult arg) {
		super(block);
		this.arg = arg;
	}

	@Override
	public String getResult() {
		return String.valueOf(arg.getResult());
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		if (arg instanceof BlockNode) {
			((BlockNode) arg).parseDump(sb, indent + DUMP_INDENT);
		}
	}

}
