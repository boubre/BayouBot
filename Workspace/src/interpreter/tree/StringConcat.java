package interpreter.tree;

import codeblocks.Block;

/**
 * A string concatenation function. (string-append genus).
 * @author Brandon Oubre
 */
public class StringConcat extends BinaryOperator<StringResult, StringResult> implements StringResult {	
	/**
	 * Create a new StringConcat function.
	 * @param block The associated block.
	 * @param arg1 The first argument to the function.
	 * @param arg2 The second argument to the function.
	 */
	public StringConcat(Block block, StringResult arg1, StringResult arg2) {
		super(block, arg1, arg2);
	}

	@Override
	public String getResult() {
		return arg1.getResult() + arg2.getResult();
	}
}
