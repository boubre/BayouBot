package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.Map;

import codeblocks.Block;

/**
 * Creates a command that declares or sets the value of a variable. (genera setvar-number, setvar-bool, setvar-string)
 * @author Brandon Oubre
 * @param <T>
 */
public class SetVariable <T extends Result> extends Command {
	protected StringResult name;
	protected T value;
	protected Map<String, Result> lookupTable;
	
	/**
	 * Create a new set variable command.
	 * @param b The associated block.
	 * @param lookupTable The global variable lookup table.
	 * @param name The name of the variable.
	 * @param value The value of the variable.
	 */
	public SetVariable(Block b, Map<String, Result> lookupTable, StringResult name, T value) {
		super(b);
		this.name = name;
		this.value = value;
		this.lookupTable = lookupTable;
	}

	@Override
	public void execute() throws ProgramExecutionException {
		lookupTable.put(name.getResult(), value);
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + "\n");
		
		indent(sb, indent);
		sb.append(">NAME: \n");
		if (name instanceof BlockNode) {
			((BlockNode) name).parseDump(sb, indent + DUMP_INDENT);
		}
		
		indent(sb, indent);
		sb.append(">VALUE: \n");
		if (value instanceof BlockNode) {
			((BlockNode) value).parseDump(sb, indent + DUMP_INDENT);
		}
	}
}
