package interpreter.tree;

import codeblocks.Block;

/**
 * A string concatenation function. (string-append genus).
 * @author Brandon Oubre
 */
public class StringConcat extends Function implements StringResult {
	public StringResult arg1, arg2;
	
	/**
	 * Create a new StringConcat function.
	 * @param block The associated block.
	 * @param arg1 The first argument to the function.
	 * @param arg2 The second argument to the function.
	 */
	public StringConcat(Block block, StringResult arg1, StringResult arg2) {
		super(block);
		this.arg1 = arg1;
		this.arg2 = arg2;
	}

	@Override
	public String getResult() {
		return arg1.getResult() + arg2.getResult();
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		if (arg1 instanceof BlockNode) {
			((BlockNode) arg1).parseDump(sb, indent + DUMP_INDENT);
		}
		if (arg2 instanceof BlockNode) {
			((BlockNode) arg2).parseDump(sb, indent + DUMP_INDENT);
		}
	}

}
