package interpreter.tree;

import workspace.Console;
import codeblocks.Block;

/**
 * A function that prints a string to the console. (print genus).
 * @author Brandon Oubre
 */
public class Print extends Command {
	public StringResult socket;
	
	/**
	 * Create a new print function.
	 * @param block The associated block.
	 * @param socket The argument to the function.
	 */
	public Print(Block block, StringResult socket) {
		super(block);
		this.socket = socket;
	}

	@Override
	public void execute() {
		Console.getInstance().appendLine(socket.getResult());
	}
	
	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		if (socket instanceof BlockNode) {
			((BlockNode)socket).parseDump(sb, indent + DUMP_INDENT);
		}
	}
}
