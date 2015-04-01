package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.Map;
import java.util.function.BooleanSupplier;

import codeblocks.Block;

/**
 * Call a user-defined procedure. (genus call-proc)
 * @author Brandon Oubre
 */
public class CallProcedure extends Command {
	private Map<String, Procedure> procMap;
	private String procName;
	
	/**
	 * Create a procedure call.
	 * @param block The associated block.
	 * @param bot A map from procedure names to the actual procedures.
	 */
	public CallProcedure(Block block, Map<String, Procedure> procMap) {
		super(block);
		this.procMap = procMap;
		this.procName = block.getBlockLabel();
	}
	
	@Override
	public void execute(BooleanSupplier testStop) throws ProgramExecutionException {
		procMap.get(procName).execute(testStop);
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "(Calling " + procName + ") \n");
	}
}
