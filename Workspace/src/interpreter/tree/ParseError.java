package interpreter.tree;

import codeblocks.Block;

/**
 * An error or warning that arose during parsing.
 * @author Brandon Oubre
 */
public class ParseError {
	private String message;
	private Block block;
	
	/**
	 * Create a new parse error.
	 * @param b The block this error originates from.
	 * @param msg The error message.
	 */
	public ParseError(Block b, String msg) {
		block = b;
		message = msg;
	}
	
	/**
	 * @return This error's message.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return The block this error originated from.
	 */
	public Block getBlock() {
		return block;
	}
}
