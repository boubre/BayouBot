package interpreter.tree;

import bayoubot.core.Pin;
import codeblocks.Block;

/**
 * 
 * A BayouBot pin constant. (pin# genera)
 * @author Brandon Oubre
 */
public class PinConstant extends Constant implements PinResult {
	private Pin value;
	
	/**
	 * Create a new pin constant.
	 * @param block The block associated with this constant.
	 * @param pin The pin the constant represents.
	 */
	public PinConstant(Block block, Pin pin) {
		super(block);
		value = pin;
	}
	
	@Override
	public Pin getResult() {
		return value;
	}

	@Override
	public void parseDump(StringBuilder sb, int indent) {
		indent(sb, indent);
		sb.append(toString() + " => " + getResult().toString() + "\n");
	}
}
