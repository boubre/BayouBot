package bayoubot.core.comms;

import java.io.IOException;
import java.util.Vector;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

/**
 * Test device discovery
 */
public class BluetoothDeviceDiscovery implements DiscoveryListener {
	private static Object lock = new Object();
	private static Vector<RemoteDevice> vDevices = new Vector<RemoteDevice>();
	
	public static void main(String[] args) throws IOException {
		BluetoothDeviceDiscovery bDD = new BluetoothDeviceDiscovery();
		
		LocalDevice localDev = LocalDevice.getLocalDevice();
		System.out.println("Address: " + localDev.getBluetoothAddress());
		System.out.println("Name: " + localDev.getFriendlyName());
		
		DiscoveryAgent agent = localDev.getDiscoveryAgent();
		
		System.out.println("Starting Devices Inquiry...");
		agent.startInquiry(DiscoveryAgent.GIAC, bDD);
		
		try {
			synchronized(lock) {
				lock.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Device Inquiry Completed.");
		
		int devCt = vDevices.size();
		if (devCt <= 0){
			System.out.println("No Devices Found :(");
		} else {
			for (int i = 0; i < devCt; i++) {
				RemoteDevice remDev = vDevices.elementAt(i);
				System.out.println((i+i) + ": " + remDev.getBluetoothAddress() + " (" +
						remDev.getFriendlyName(true) + ")");
			}
		}
	}

	@Override
	public void deviceDiscovered(RemoteDevice btDev, DeviceClass cod) {
		System.out.println("Device discovered: " + btDev.getBluetoothAddress());
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
		
		switch (discType) {
		case DiscoveryListener.INQUIRY_COMPLETED:
			System.out.println("Inquiry Completed.");
			break;
		case DiscoveryListener.INQUIRY_TERMINATED:
			System.out.println("Inquiry Terminated.");
			break;
		case DiscoveryListener.INQUIRY_ERROR:
			System.out.println("Inquiry Error");
			break;
			
		default:
			System.out.println("Unknown Response Code.");
			break;
		}
	}
}
