package interpreter.tree;

import codeblocks.Block;

/**
 * The Setup procedure. (setup genus)
 * @author Brandon Oubre
 */
public class Setup extends Procedure {	
	/**
	 * Create a new setup procedure.
	 * @param block The associated block.
	 */
	public Setup(Block block) {
		super(block);
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		for (BlockNode node : commandList) {
			node.parseDump(sb, indent + DUMP_INDENT);
		}
	}
}
