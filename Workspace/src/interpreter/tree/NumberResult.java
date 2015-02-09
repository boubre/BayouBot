package interpreter.tree;

import interpreter.ProgramExecutionException;

/**
 * Represents a block node that returns a number.
 * @author Brandon Oubre
 */
public interface NumberResult extends Result {
	/**
	 * @return The double value of the result.
	 * @throws ProgramExecutionException A program error caused by executing this command.
	 */
	public double getResult() throws ProgramExecutionException;
}
