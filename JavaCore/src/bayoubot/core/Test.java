package bayoubot.core;

import java.io.IOException;

import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;

import bayoubot.core.comms.BluetoothDeviceFinder;
import bayoubot.core.comms.BluetoothDeviceFinderCallback;
import bayoubot.core.comms.BluetoothDeviceFinderListener;

public class Test {
	private static boolean complete = false;
	
	public static void main(String[] args) {
		BluetoothDeviceFinder bdf = new BluetoothDeviceFinder();
		bdf.registerListener(new BluetoothDeviceFinderListener() {
			@Override
			public void discoveryComplete(BluetoothDeviceFinderCallback bdfc) {
				printResults(bdfc);
				complete = true;
			}
		});
		Thread finderThread = new Thread(bdf);
		finderThread.start();
		
		while(!complete) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
		}
		System.out.println("Program Completed!");
	}
	
	public static void printResults(BluetoothDeviceFinderCallback bdfc) {
		System.out.println("Local Device:");
		System.out.println("\tAddress: " + bdfc.getLocalDevice().getBluetoothAddress());
		System.out.println("\tName: " + bdfc.getLocalDevice().getFriendlyName());
		System.out.println("Remote Devices:");
		for (RemoteDevice rd : bdfc.getDiscoveredDevices()) {
			try {
				System.out.println("\tAddress: " + rd.getBluetoothAddress());
				System.out.println("\tName: " + rd.getFriendlyName(true));
				System.out.println("--------------------");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		switch (bdfc.getDiscoveryType()) {
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
