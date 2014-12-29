package interpreter.tree;

import renderable.RenderableBlock;


public class Parser {
	//private Setup tree;
	private Iterable<RenderableBlock> topBlocks = null;
	
	public Parser(Iterable<RenderableBlock> topBlocks) {
		this.topBlocks = topBlocks;
	}
}
