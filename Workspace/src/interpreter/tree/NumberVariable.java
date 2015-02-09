package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.Map;

import codeblocks.Block;

/**
 * A number variable reference. (genus var-number)
 * @author Brandon Oubre
 */
public class NumberVariable extends Variable implements NumberResult {
	/**
	 * Create a reference to a numeric variable.
	 * @param b The associated block.
	 * @param lookupTable The global variable lookup table.
	 */
	protected NumberVariable(Block b, Map<String, Result> lookupTable) {
		super(b, lookupTable);
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		if (lookupTable.containsKey(name)) {
			Result r = lookupTable.get(name);
			if (r instanceof NumberResult) {
				return ((NumberResult) r).getResult();
			} else {
				throw new ProgramExecutionException("Attempt to reference varaible of a different type as a numeric type.", block);
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
