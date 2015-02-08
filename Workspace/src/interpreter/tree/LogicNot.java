package interpreter.tree;

import codeblocks.Block;

/**
 * The Logical Not function. (genus not)
 * @author Brandon Oubre
 */
public class LogicNot extends UnaryOperator<BooleanResult> implements BooleanResult {
	/**
	 * Create a new not function.
	 * @param block The associated block.
	 * @param arg1 The argument.
	 */
	public LogicNot(Block block, BooleanResult arg) {
		super(block, arg);
	}

	@Override
	public boolean getResult() {
		return !arg.getResult();
	}
}
