package bayoubot.core.comms;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;

public class BluetoothDeviceFinder implements Runnable, DiscoveryListener {
	private static final UUID SERIAL_UUID = new UUID(0x1101);
	private Object lock;
	
	private Vector<RemoteDevice> vDevices;
	private int discoveryType;
	private Map<RemoteDevice, List<ServiceRecord>> services;
	
	private List<BluetoothDeviceFinderListener> listeners;

	public BluetoothDeviceFinder() {
		lock = new Object();
		vDevices = new Vector<RemoteDevice>();
		services = new HashMap<RemoteDevice, List<ServiceRecord>>();
		listeners = new LinkedList<BluetoothDeviceFinderListener>();
	}
	
	public void registerListener(BluetoothDeviceFinderListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void run() {
		try {
			LocalDevice localDev = LocalDevice.getLocalDevice();
			
			DiscoveryAgent agent = localDev.getDiscoveryAgent();
			agent.startInquiry(DiscoveryAgent.GIAC, this);
			
			wait(lock);
			
			UUID[] uuids = { SERIAL_UUID };
			for (RemoteDevice rd : vDevices) {
				agent.searchServices(null, uuids, rd, this);
			}
			
			wait(lock);
			
			BluetoothDeviceFinderCallback callback = 
					new BluetoothDeviceFinderCallback(localDev, vDevices, discoveryType, services);
			for (BluetoothDeviceFinderListener l : listeners) {
				l.discoveryComplete(callback);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deviceDiscovered(RemoteDevice btDev, DeviceClass cod) {
		if (!vDevices.contains(btDev)) {
			vDevices.add(btDev);
		}
	}
	
	@Override
	public void servicesDiscovered(int transID, ServiceRecord[] servRec) {
		for (ServiceRecord sr : servRec) {
			RemoteDevice host = sr.getHostDevice();
			if (!services.containsKey(host)) {
				services.put(host, new LinkedList<ServiceRecord>());		
			}
			services.get(host).add(sr);
		}
	}
	
	@Override
	public void serviceSearchCompleted(int transID, int respCode) { 
		synchronized(lock) {
			lock.notify();
		}
	}
	
	@Override
	public void inquiryCompleted(int discType) {
		synchronized(lock) {
			lock.notify();
		}
		
		discoveryType = discType;
	}
	
	private void wait(Object lock) {
		try {
			synchronized(lock) {
				lock.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
