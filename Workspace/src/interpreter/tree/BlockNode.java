package interpreter.tree;

import codeblocks.Block;

/**
 * An element of the parse tree that has an associated block.
 * @author Brandon Oubre
 */
public abstract class BlockNode {
	public static final int DUMP_INDENT = 2;
	
	public Block block = null;
	
	/**
	 * Create a new BlockNode.
	 * @param block The block this node represents.
	 */
	protected BlockNode(Block block) {
		this.block = block;
	}
	
	/**
	 * Append this node's entry to the string being built.
	 * Make sure to call the parseDump of nested tree elements.
	 * @param sb The {@link StringBuilder} constructing the parse dump.
	 * @param indent How many spaces to indent.
	 */
	public abstract void parseDump(StringBuilder sb, int indent);
	
	/**
	 * ParseDump helper:
	 * Indent a line in the {@link StringBuilder}.
	 * @param sb The {@link StringBuilder}.
	 * @param toIndent The number of soace to indent.
	 */
	protected final void indent(StringBuilder sb, int toIndent) {
		for (int i = 0; i < toIndent; i++) {
			sb.append(' ');
		}
	}
	
	@Override
	public String toString() {
		if (block == null)
			return getClass().getSimpleName();
		else 
			return getClass().getSimpleName() + " [#" + block.getBlockID() + " | " + block.getGenusName() + "]";
	}
}
