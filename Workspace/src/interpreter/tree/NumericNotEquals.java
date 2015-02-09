package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The not equals function, with numeric arguments. (genus num-not-equals)
 * @author Brandon Oubre
 */
public class NumericNotEquals extends BinaryOperator<NumberResult, NumberResult> implements BooleanResult {
	/**
	 * Create a new not equals function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public NumericNotEquals(Block block, NumberResult arg1, NumberResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() throws ProgramExecutionException {
		return arg1.getResult() != arg2.getResult();
	}
}
