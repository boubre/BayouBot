package interpreter.tree;

import interpreter.ProgramExecutionException;
import codeblocks.Block;

/**
 * A commnand that causes the thread to sleep for the specified number of mSec. (sleep-ms genus).
 * @author Brandon Oubre
 */
public class Sleep extends Command {
	public NumberResult toSleep;
	
	/**
	 * Create a new print function.
	 * @param block The associated block.
	 * @param toSleep The time to sleep in milliSeconds.
	 */
	public Sleep(Block block, NumberResult toSleep) {
		super(block);
		this.toSleep = toSleep;
	}

	@Override
	public void execute() throws ProgramExecutionException {
		try {
			Thread.sleep((long)toSleep.getResult());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		if (toSleep instanceof BlockNode) {
			((BlockNode)toSleep).parseDump(sb, indent + DUMP_INDENT);
		}
	}
}
