package bayoubot.core.comms;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Vector;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

public class BluetoothDeviceFinder implements Runnable, DiscoveryListener {
	private Object lock;
	private Vector<RemoteDevice> vDevices;
	private int discoveryType;
	private LinkedList<BluetoothDeviceFinderListener> listeners;

	public BluetoothDeviceFinder() {
		lock = new Object();
		vDevices = new Vector<RemoteDevice>();
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
			
			try {
				synchronized(lock) {
					lock.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			BluetoothDeviceFinderCallback callback = 
					new BluetoothDeviceFinderCallback(localDev, vDevices, discoveryType);
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
	public void servicesDiscovered(int transID, ServiceRecord[] servRec) { }
	
	@Override
	public void serviceSearchCompleted(int transID, int respCode) { }
	
	@Override
	public void inquiryCompleted(int discType) {
		synchronized(lock) {
			lock.notify();
		}
		
		discoveryType = discType;
	}
}
