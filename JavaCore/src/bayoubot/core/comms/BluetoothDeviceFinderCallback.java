package bayoubot.core.comms;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class BluetoothDeviceFinderCallback {
	private LocalDevice localDevice;
	private Vector<RemoteDevice> discoveredDevices;
	private int discoveryType;
	private Map<RemoteDevice, List<ServiceRecord>> services;
	
	public BluetoothDeviceFinderCallback(LocalDevice localDevice,
			Vector<RemoteDevice> discoveredDevices, int discoveryType,
			Map<RemoteDevice, List<ServiceRecord>> services) {
		this.localDevice = localDevice;
		this.discoveredDevices = discoveredDevices;
		this.discoveryType = discoveryType;
		this.services = services;
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
	
	public Map<RemoteDevice, List<ServiceRecord>> getServices() {
		return services;
	}
}
