package interpreter.tree;

import codeblocks.Block;

/**
 * Superclass for constant blocks.
 * @author Brandon Oubre
 */
public abstract class Constant extends BlockNode {
	/**
	 * Create a new constant.
	 * @param block The associated block.
	 */
	protected Constant(Block block) {
		super(block);
	}
}
