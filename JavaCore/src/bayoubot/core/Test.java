package bayoubot.core;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		BayouBot bb = new SerialPortBayouBot("/dev/rfcomm1");
		System.out.println("Connected...");
		bb.setPinMode(Pin.PIN_2, PinMode.OUTPUT);
		for (int i = 1; i < 10; i++) {
			try {
				bb.setPinState(Pin.PIN_2, PinState.HIGH);
				Thread.sleep(500);
				bb.setPinState(Pin.PIN_2, PinState.LOW);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Closing...");
		bb.close();
	}
}
