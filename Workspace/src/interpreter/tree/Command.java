package interpreter.tree;

import codeblocks.Block;

public abstract class Command extends BlockNode {
	public Command(Block block) {
		super(block);
	}
}
