package bayoubot.core;

import java.io.IOException;

import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

import bayoubot.core.comms.BluetoothDeviceFinder;
import bayoubot.core.comms.BluetoothDeviceFinderCallback;
import bayoubot.core.comms.BluetoothDeviceFinderListener;

public class Test {
	private static boolean complete = false;
	private static String URL;
	
	public static void main(String[] args) throws IOException {
		BluetoothDeviceFinder bdf = new BluetoothDeviceFinder();
		bdf.registerListener(new BluetoothDeviceFinderListener() {
			@Override
			public void discoveryComplete(BluetoothDeviceFinderCallback bdfc) {
				printResults(bdfc);
				getURL(bdfc);
				complete = true;
			}
		});
		Thread finderThread = new Thread(bdf);
		finderThread.start();
		
		while (!complete) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Lookup Completed!");
		System.out.println("Connecting to: " + URL);
		BayouBot bb = new BayouBot((StreamConnection)Connector.open(URL));
		System.out.println("Connected...");
		bb.setPinMode(Pin.PIN_0, PinMode.OUTPUT);
		while (true) {
			try {
				bb.setPinState(Pin.PIN_0, PinState.HIGH);
				Thread.sleep(500);
				bb.setPinState(Pin.PIN_0, PinState.LOW);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// bb.close();
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
			} catch (IOException e) {
				e.printStackTrace();
			}
			for(ServiceRecord sr : bdfc.getServices().get(rd)) {
				System.out.println("\t\tServiceURL: " + 
						sr.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false));
			}
			System.out.println("--------------------");
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
	
	private static void getURL(BluetoothDeviceFinderCallback bdfc) {
		for (RemoteDevice rd : bdfc.getServices().keySet()) {
			try {
				if (rd.getFriendlyName(false).equals("BayouBot")) {
					URL = bdfc.getServices().get(rd).get(0).getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
					break;
				}
			} catch (IOException e) {e.printStackTrace();}
		}
	}
}
