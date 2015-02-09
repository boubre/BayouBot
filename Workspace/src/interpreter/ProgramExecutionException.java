package interpreter;

import codeblocks.Block;

public class ProgramExecutionException extends Exception {
	private static final long serialVersionUID = -3499394093434963294L;
	
	protected boolean isFatal;
	protected Block block;
	
	/**
	 * Create a new program execution error.
	 * @param msg A descriptive error message.
	 * @param b The code block the error originates from. May be <tt>null</tt> if not applicable.
	 */
	public ProgramExecutionException(String msg, Block b) {
		super(msg);
		this.block = b;
	}
	
	/**
	 * @return The block that caused this error, or <tt>null</tt>.
	 */
	public Block getBlock() {
		return block;
	}
}
