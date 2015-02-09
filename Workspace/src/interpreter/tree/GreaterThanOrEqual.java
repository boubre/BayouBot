package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The greater than or equal to (>=) function. (genus greaterthanorequalto)
 * @author Brandon Oubre
 */
public class GreaterThanOrEqual extends BinaryOperator<NumberResult, NumberResult> implements BooleanResult {
	/**
	 * Create a new greater than or equal to function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public GreaterThanOrEqual(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() throws ProgramExecutionException {
		return arg1.getResult() >= arg2.getResult();
	}
}
