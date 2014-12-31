package interpreter.tree;

/**
 * Represents a block node that returns a number.
 * @author Brandon Oubre
 */
public interface NumberResult {
	/**
	 * @return <tt>true</tt> if this number can be represented as an integer.
	 */
	public boolean isInteger();
	
	/**
	 * WARNING: Will have loss of precision if the number is not an integer.
	 * @return The integer value of the result.
	 */
	public int getIntegerResult();
	
	/**
	 * @return The double value of the result.
	 */
	public double getDoubleResult();
}
