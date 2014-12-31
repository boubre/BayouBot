package interpreter.tree;

/**
 * Represents a block node that returns a string.
 * @author Brandon Oubre
 */
public interface StringResult {
	/**
	 * Calculate or fetch the result of this block.
	 * @return The result.
	 */
	public String getResult();
}
