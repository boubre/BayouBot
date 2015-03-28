package interpreter.tree;

import interpreter.ProgramExecutionException;
import bayoubot.core.Pin;

/**
 * Represents a block node that returns BauouBot pin.
 * @author Brandon Oubre
 */
public interface PinResult extends Result {
		/**
		 * @return A BayouBot Pin
		 * @throws ProgramExecutionException A program error caused by executing this command.
		 */
		public Pin getResult() throws ProgramExecutionException;
}
