package interpreter.tree;

import codeblocks.Block;

/**
 * Superclass for function blocks.
 * @author Brandon Oubre
 */
public abstract class Function extends BlockNode {
	/**
	 * Create a new function.
	 * @param block The associated block.
	 */
	protected Function(Block block) {
		super(block);	
	}
}
