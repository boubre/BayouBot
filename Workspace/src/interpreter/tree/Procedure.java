package interpreter.tree;

import java.util.LinkedList;
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
	 */
	protected Procedure(Block block) {
		super(block);
		commandList = new LinkedList<>();
	}
	
	/**
	 * Add a command to the procedure.
	 * @param c The command to add.
	 */
	public void addCommand(Command c) {
		commandList.add(c);
	}
}
