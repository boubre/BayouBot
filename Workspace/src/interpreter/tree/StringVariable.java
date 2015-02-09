package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.Map;

import codeblocks.Block;

/**
 * A string variable reference. (genus var-string)
 * @author Brandon Oubre
 */
public class StringVariable extends Variable implements StringResult {
	/**
	 * Create a reference to a string variable.
	 * @param b The associated block.
	 * @param lookupTable The global variable lookup table.
	 */
	protected StringVariable(Block b, Map<String, Result> lookupTable) {
		super(b, lookupTable);
	}

	@Override
	public String getResult() throws ProgramExecutionException {
		if (lookupTable.containsKey(name)) {
			Result r = lookupTable.get(name);
			if (r instanceof StringResult) {
				return ((StringResult) r).getResult();
			} else {
				throw new ProgramExecutionException("Attempt to reference varaible of a different type as a string type.", block);
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
