package interpreter.tree;

import interpreter.ProgramExecutionException;

/**
 * Represents a block node that returns a string.
 * @author Brandon Oubre
 */
public interface StringResult extends Result {
	/**
	 * Calculate or fetch the result of this block.
	 * @return The result.
	 * @throws ProgramExecutionException A program error caused by executing this command.
	 */
	public String getResult() throws ProgramExecutionException ;
}
