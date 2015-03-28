package interpreter;

import bayoubot.core.BayouBot;
import bayoubot.core.Pin;
import bayoubot.core.PinMode;
import bayoubot.core.PinState;

/**
 * A dummy BayouBot that just prints to the command line and thinks that pins are always HIGH/OUTPUT.
 * @author Brandon Oubre
 */
public class DummyBayouBot extends BayouBot {

	public DummyBayouBot() { 
		System.out.println("Dummy BayouBot (DBB) created.");
	}
	
	@Override
	public void close() {
		System.out.println("Closing DBB connection.");
	}

	@Override
	public void moveBackward() {
		System.out.println("DBB: Moving Backward.");
	}

	@Override
	public void moveForward() {
		System.out.println("DBB: Moving Forward.");
	}

	@Override
	public PinMode queryPinMode(Pin pin) {
		System.out.println("DBB: Querying pin " + pin.toString() + " mode: returning OUTPUT");
		return PinMode.OUTPUT;
	}

	@Override
	public PinState queryPinState(Pin pin) {
		System.out.println("DBB: Querying pin " + pin.toString() + " state: returning HIGH");
		return PinState.HIGH;
	}

	@Override
	public void resetPins() {
		System.out.println("DBB: Resetting Pins.");
	}

	@Override
	public void setPinMode(Pin pin, PinMode mode) {
		System.out.println("DBB: Setting pin " + pin.toString() + " to mode " + mode.toString() + ".");
	}

	@Override
	public void setPinState(Pin pin, PinState state) {
		System.out.println("DBB: Setting pin " + pin.toString() + " to state " + state.toString() + ".");
	}

	@Override
	public void stop() {
		System.out.println("DBB: Stopping Movement.");
	}

	@Override
	public void turnLeft() {
		System.out.println("DBB: Turning Left.");
	}

	@Override
	public void turnRight() {
		System.out.println("DBB: Turning Right.");
	}
}
