package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.Map;

import codeblocks.Block;

/**
 * A Boolean variable reference. (genus var-bool)
 * @author Brandon Oubre
 */
public class BooleanVariable extends Variable implements BooleanResult {
	/**
	 * Create a reference to a Boolean variable.
	 * @param b The associated block.
	 * @param lookupTable The global variable lookup table.
	 */
	protected BooleanVariable(Block b, Map<String, Result> lookupTable) {
		super(b, lookupTable);
	}

	@Override
	public boolean getResult() throws ProgramExecutionException {
		if (lookupTable.containsKey(name)) {
			Result r = lookupTable.get(name);
			if (r instanceof BooleanResult) {
				return ((BooleanResult) r).getResult();
			} else {
				throw new ProgramExecutionException("Attempt to reference varaible of a different type as a Boolean type.", block);
			}
		}
		
		throw new ProgramExecutionException("Attempt to reference undeclared variable.", block);
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + " => " + name + "\n");
	}
}
