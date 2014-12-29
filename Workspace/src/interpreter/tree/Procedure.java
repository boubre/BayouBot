package interpreter.tree;

import java.util.LinkedList;
import java.util.List;

import codeblocks.Block;

public abstract class Procedure extends BlockNode {
	public List<Command> commandList;
	
	public Procedure(Block block) {
		super(block);
		commandList = new LinkedList<>();
	}
	
	public void addCommand(Command c) {
		commandList.add(c);
	}
}
