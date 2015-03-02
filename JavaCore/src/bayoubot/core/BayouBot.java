package bayoubot.core;

/**
 * A class representing the BayoutBot that facilitates communications
 * and interactions with the hardware.
 */
public abstract class BayouBot {	
	/**
	 * Set the I/O mode of a pin.
	 * @param pin The pin modify.
	 * @param mode The desired mode.
	 */
	public abstract void setPinMode(Pin pin, PinMode mode);
	
	/**
	 * Set the IO state of a PIN
	 * @param pin The pin to modify
	 * @param state The desired state
	 */
	public abstract void setPinState(Pin pin, PinState state);
	
	/**
	 * Set all pins to OUTPUT and all voltages to LOW
	 */
	public abstract void resetPins();
	
	/**
	 * Ask the hardware for the current mode of a pin
	 * @param pin The pin to query
	 * @return The IO mode of the pin
	 */
	public abstract PinMode queryPinMode(Pin pin);
	
	/**
	 * Ask the hardware for the current state of a pin
	 * @param pin The pin to query
	 * @return The current voltage of that pin
	 */
	public abstract PinState queryPinState(Pin pin);
	
	/**
	 * Tell the BayouBot to begin a left turn.
	 */
	public abstract void turnLeft();
	
	/**
	 * Tell the BayouBot to begin a right turn.
	 */
	public abstract void turnRight();
	
	/**
	 * Tell the BayouBot to move forward.
	 */
	public abstract void moveForward();
	
	/**
	 * Tell the BayouBot to move backwards
	 */
	public abstract void moveBackward();
	
	/**
	 * Stop the BayouBot's motors.
	 */
	public abstract void stop();
	
	/**
	 * Properly close the given BayouBot connecion.
	 */
	public abstract void close();
}
