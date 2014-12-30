package interpreter.tree;

import codeblocks.Block;

public class Print extends Command {
	public Print(Block block) {
		super(block);
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(sb.append(toString() + "\n"));
	}
}
