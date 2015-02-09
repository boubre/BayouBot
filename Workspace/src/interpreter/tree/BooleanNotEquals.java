package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The boolean not equals function. (genus bool-not-equals)
 * @author Brandon Oubre
 */
public class BooleanNotEquals extends BinaryOperator<BooleanResult, BooleanResult> implements BooleanResult {
	/**
	 * Create a new boolean not equals function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public BooleanNotEquals(Block block, BooleanResult arg1, BooleanResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() throws ProgramExecutionException {
		return arg1.getResult() != arg2.getResult();
	}
}
