package interpreter.tree;

import codeblocks.Block;

/**
 * A function that takes a two arguments.
 * @param <T> The type of the first, or left, argument.
 * @param <U> The type of the second, or right, argument.
 * @author Brandon Oubre
 */
public abstract class BinaryOperator<T, U> extends Function {
	protected T arg1;
	protected U arg2;
	
	/**
	 * Create a new binary operator.
	 * @param block The associated block.
	 * @param arg1 The first, or left, argument.
	 * @param arg2 The second, or right, argument.
	 */
	protected BinaryOperator(Block block, T arg1, U arg2) {
		super(block);
		this.arg1 = arg1;
		this.arg2 = arg2;
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
