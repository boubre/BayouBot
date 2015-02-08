package interpreter.tree;

import java.util.List;

import codeblocks.Block;

/**
 * Superclass for procedures.
 * @author Brandon Oubre
 */
public abstract class Procedure extends BlockNode {
	public List<Command> commandList;
	
	/**
	 * Create a new procedure.
	 * @param block The associated block.
	 * @param commands The commands associated with this procedure.
	 */
	protected Procedure(Block block, List<Command> commands) {
		super(block);
		commandList = commands;
	}
}
