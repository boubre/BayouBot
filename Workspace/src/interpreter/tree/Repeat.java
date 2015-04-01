package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.List;
import java.util.function.BooleanSupplier;

import codeblocks.Block;

/**
 * A loop that repeats the specified number of times. (genus repeat)
 * @author Brandon Oubre
 */
public class Repeat extends Command {
	protected NumberResult nLoops;
	protected List<Command> body;

	/**
	 * Creates a new repeat loop command.
	 * @param block The associated block.
	 * @param nLoops The number of times to repeat the loop. (Will only be evaluated once before loop execution.)
	 * @param body The commands to execute during each iteration.
	 */
	protected Repeat(Block block, NumberResult nLoops, List<Command> body) {
		super(block);
		assert nLoops != null : "Number of loops must not be null.";
		assert body != null : "Loop body must not be null";
		this.nLoops = nLoops;
		this.body = body;
	}

	@Override
	public void execute(BooleanSupplier testStop) throws ProgramExecutionException {
		final int N = (int)nLoops.getResult();
		for (int i = 0; i < N; i++) {
			for (Command cmd : body) {
				if (testStop.getAsBoolean()) {
					return;
				}
				cmd.execute(testStop);
			}
		}
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		
		indent(sb, indent);
		sb.append(">NUM LOOPS: \n");
		if (nLoops instanceof BlockNode) {
			((BlockNode) nLoops).parseDump(sb, indent + DUMP_INDENT);
		}
		
		indent(sb, indent);
		sb.append(">BODY: \n");
		for (BlockNode node : body) {
			node.parseDump(sb, indent + DUMP_INDENT);
		}
	}
}
