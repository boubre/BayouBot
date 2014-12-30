package interpreter.tree;

import codeblocks.Block;

public class Setup extends Procedure {	
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
