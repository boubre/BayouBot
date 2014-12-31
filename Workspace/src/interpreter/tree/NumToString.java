package interpreter.tree;

import codeblocks.Block;

/**
 * A Numeric To String function (num-to-string genus).
 * @author Brandon Oubre
 */
public class NumToString extends Function implements StringResult {
	public NumberResult arg;
	
	/**
	 * Create a new number to string function.
	 * @param block The associated block.
	 * @param arg The argument to this function.
	 */
	public NumToString(Block block, NumberResult arg) {
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
