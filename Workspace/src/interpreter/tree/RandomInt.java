package interpreter.tree;

import interpreter.ProgramExecutionException;

import java.util.Random;

import codeblocks.Block;

/**
 * A random integer function. (genus random)
 * Reports a random int >= 0 and <= the argument.
 * @author Brandon Oubre
 */
public class RandomInt extends UnaryOperator<NumberResult> implements NumberResult {
	private Random randGen;
	
	/**
	 * Create a new random integer function.
	 * @param block The associated block.
	 * @param arg The argument (Will be truncated to an integer).
	 */
	public RandomInt(Block block, NumberResult arg) {
		super(block, arg);
		randGen = new Random();
	}

	@Override
	public double getResult() throws ProgramExecutionException {
		return randGen.nextInt(((int)arg.getResult()) + 1);
	}
}
