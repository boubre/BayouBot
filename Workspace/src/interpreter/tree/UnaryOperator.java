package interpreter.tree;

import codeblocks.Block;

/**
 * A function that takes a single argument.
 * @param <T> The type of the argument.
 * @author Brandon Oubre
 */
public abstract class UnaryOperator<T extends Result> extends Function {
	protected T arg;
	
	/**
	 * Create a new UnaryOperator.
	 * @param block The associated block.
	 * @param arg The argument to the operator.
	 */
	protected UnaryOperator(Block block, T arg) {
		super(block);
		this.arg = arg;
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
