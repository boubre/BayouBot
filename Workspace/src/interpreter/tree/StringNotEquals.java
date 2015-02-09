package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * The case sensitive not equals function, with string arguments. (genus string-not-equals)
 * @author Brandon Oubre
 */
public class StringNotEquals extends BinaryOperator<StringResult, StringResult> implements BooleanResult {
	/**
	 * Create a new equals function.
	 * @param block The associated block.
	 * @param arg1 The first argument.
	 * @param arg2 The second argument.
	 */
	public StringNotEquals(Block block, StringResult arg1, StringResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public boolean getResult() throws ProgramExecutionException {
		return !arg1.getResult().equals(arg2.getResult());
	}
}