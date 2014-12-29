package interpreter.tree;

import codeblocks.Block;

public abstract class BlockNode {
	public Block block = null;
	
	public BlockNode(Block block) {
		this.block = block;
	}
}
