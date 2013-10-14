package bayoubot.core.comms;

/**
 * A class implementing this interface can register itself to be notified
 * upon the completion of a {@link BluetoothDeviceFinder} search.
 */
public interface BluetoothDeviceFinderListener {
	
	/**
	 * The device finder has completed its search.
	 * @param bdfc A callback representing the results of the search.
	 */
	public void discoveryComplete(BluetoothDeviceFinderCallback bdfc);
}
