package interpreter.tree;

/**
 * Represents a block node that returns a boolean value.
 * @author Brandon Oubre
 */
public interface BooleanResult {
	/**
	 * Calculate or fetch the result of this block.
	 * @return The result.
	 */
	public boolean getResult();
}
