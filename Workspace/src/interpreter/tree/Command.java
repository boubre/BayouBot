package interpreter.tree;

import java.util.function.BooleanSupplier;

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
	 * @param testStop Will supply a <tt>true</tt> value if the program should stop execution and return immediately.
	 * @throws ProgramExecutionException A program error caused by executing this command.
	 */
	public abstract void execute(BooleanSupplier testStop) throws ProgramExecutionException;
}
