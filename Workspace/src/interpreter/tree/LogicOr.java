package interpreter.tree;

import codeblocks.Block;

/**
 * The Logical Or function. (genus or)
 * @author Brandon Oubre
 */
public class LogicOr extends BinaryOperator<BooleanResult, BooleanResult> implements BooleanResult {
	/**
	 * Create a new or function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public LogicOr(Block block, BooleanResult arg1, BooleanResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() {
		return arg1.getResult() || arg2.getResult();
	}
}