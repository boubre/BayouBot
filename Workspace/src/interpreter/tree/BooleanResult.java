package interpreter.tree;

import interpreter.ProgramExecutionException;

/**
 * Represents a block node that returns a boolean value.
 * @author Brandon Oubre
 */
public interface BooleanResult extends Result {
	/**
	 * Calculate or fetch the result of this block.
	 * @return The result.
	 * @throws ProgramExecutionException A program error caused by executing this command.
	 */
	public boolean getResult() throws ProgramExecutionException;
}
