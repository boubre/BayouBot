package interpreter.tree;

import codeblocks.Block;

/**
 * A Boolean To String function (bool-to-string genus).
 * @author Brandon Oubre
 */
public class BoolToString extends UnaryOperator<BooleanResult> implements StringResult {	
	/**
	 * Create a new boolean to string function.
	 * @param block The associated block.
	 * @param arg The argument to this function.
	 */
	public BoolToString(Block block, BooleanResult arg) {
		super(block, arg);
	}

	@Override
	public String getResult() {
		return String.valueOf(arg.getResult());
	}
}
