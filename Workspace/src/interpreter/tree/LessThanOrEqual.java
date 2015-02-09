package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The less than or equal to (<=) function. (genus lessthanorequalto)
 * @author Brandon Oubre
 */
public class LessThanOrEqual extends BinaryOperator<NumberResult, NumberResult> implements BooleanResult {
	/**
	 * Create a new less than or equal to function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public LessThanOrEqual(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() throws ProgramExecutionException {
		return arg1.getResult() <= arg2.getResult();
	}
}
