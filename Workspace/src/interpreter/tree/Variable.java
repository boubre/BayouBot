package interpreter.tree;

import java.util.Map;

import codeblocks.Block;

/**
 * Superclass for variable blocks.
 * @author Brandon Oubre
 */
public abstract class Variable extends BlockNode {
	protected Map<String, Result> lookupTable;
	protected String name;
	
	/**
	 * Create a new variable access block.
	 * @param b The associated block.
	 * @param lookupTable The global variable lookup table.
	 */
	protected Variable(Block b, Map<String, Result> lookupTable) {
		super(b);
		this.lookupTable = lookupTable;
		name = b.getBlockLabel();
	}
}
