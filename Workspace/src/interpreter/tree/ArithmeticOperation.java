package interpreter.tree;

import codeblocks.Block;

/**
 * A superclass for basic arithmetic operations.
 * @author Brandon Oubre
 */
public abstract class ArithmeticOperation extends Function implements
		NumberResult {
	protected NumberResult arg1, arg2;
	
	/**
	 * Create a new arithmetic operation.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	protected ArithmeticOperation(Block block, NumberResult arg1, NumberResult arg2) {
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
