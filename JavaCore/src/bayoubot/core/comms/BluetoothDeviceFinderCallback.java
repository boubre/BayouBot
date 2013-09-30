package bayoubot.core.comms;

import java.util.Vector;

import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;

public class BluetoothDeviceFinderCallback {
	private LocalDevice localDevice;
	private Vector<RemoteDevice> discoveredDevices;
	private int discoveryType;
	
	public BluetoothDeviceFinderCallback(LocalDevice localDevice,
			Vector<RemoteDevice> discoveredDevices, int discoveryType) {
		this.localDevice = localDevice;
		this.discoveredDevices = discoveredDevices;
		this.discoveryType = discoveryType;
	}
	
	public LocalDevice getLocalDevice() {
		return localDevice;
	}
	
	public Vector<RemoteDevice> getDiscoveredDevices() {
		return discoveredDevices;
	}
	
	public int getDiscoveryType() {
		return discoveryType;
	}
}
