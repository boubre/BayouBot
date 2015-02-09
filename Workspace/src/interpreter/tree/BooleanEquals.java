package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The boolean equals function. (genus bool-equals)
 * @author Brandon Oubre
 */
public class BooleanEquals extends BinaryOperator<BooleanResult, BooleanResult> implements BooleanResult {
	/**
	 * Create a new boolean equals function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public BooleanEquals(Block block, BooleanResult arg1, BooleanResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() throws ProgramExecutionException {
		return arg1.getResult() == arg2.getResult();
	}
}
