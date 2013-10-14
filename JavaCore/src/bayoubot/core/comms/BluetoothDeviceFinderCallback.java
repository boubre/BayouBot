package bayoubot.core.comms;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

/**
 * Contains information about devices found with the {@link BluetoothDeviceFinder} and
 * about the services those devices offer.
 */
public class BluetoothDeviceFinderCallback {
	private LocalDevice localDevice;
	private Vector<RemoteDevice> discoveredDevices;
	private int discoveryType;
	private Map<RemoteDevice, List<ServiceRecord>> services;
	
	/**
	 * Create a new callback.
	 * @param localDevice The local device.
	 * @param discoveredDevices The discovered remote devices.
	 * @param discoveryType The success or status of the discovery.
	 * @param services The services offered by each remote device.
	 */
	public BluetoothDeviceFinderCallback(LocalDevice localDevice,
			Vector<RemoteDevice> discoveredDevices, int discoveryType,
			Map<RemoteDevice, List<ServiceRecord>> services) {
		this.localDevice = localDevice;
		this.discoveredDevices = discoveredDevices;
		this.discoveryType = discoveryType;
		this.services = services;
	}
	
	/**
	 * @return The local Bluetooth device
	 */
	public LocalDevice getLocalDevice() {
		return localDevice;
	}
	
	/**
	 * @return Discovered remote Bluetooth devices
	 */
	public Vector<RemoteDevice> getDiscoveredDevices() {
		return discoveredDevices;
	}
	
	/**
	 * @return The success, failure, or status of the search
	 */
	public int getDiscoveryType() {
		return discoveryType;
	}
	
	/**
	 * @return A Map of RemoteDevices to the services that they offer.
	 */
	public Map<RemoteDevice, List<ServiceRecord>> getServices() {
		return services;
	}
}
