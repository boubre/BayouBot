package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * Superclass for command blocks.
 * @author Brandon Oubre
 */
public abstract class Command extends BlockNode {
	/**
	 * Create a new command.
	 * @param block The associated block.
	 */
	protected Command(Block block) {
		super(block);
	}
	
	/**
	 * Execute the command.
	 * @throws ProgramExecutionException A program error caused by executing this command.
	 */
	public abstract void execute() throws ProgramExecutionException;
}
