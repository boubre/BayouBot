package bayoubot.core;

public enum Pin {
	//Make sure to keep this in increasing numerical order
	PIN_0(Port.D, 2),
	PIN_1(Port.D, 3),
	PIN_2(Port.D, 4),
	PIN_3(Port.B, 6),
	PIN_4(Port.B, 7),
	PIN_5(Port.D, 5),
	PIN_6(Port.D, 6),
	PIN_7(Port.D, 7),
	PIN_8(Port.B, 0),
	PIN_9(Port.B, 1),
	PIN_10(Port.B, 2),
	PIN_11(Port.C, 0),
	PIN_12(Port.C, 1),
	PIN_13(Port.C, 2),
	PIN_14(Port.C, 3),
	PIN_15(Port.C, 4),
	PIN_16(Port.C, 5);
	
	public static final int NUM_PINS = Pin.values().length;
	
	public enum Port {
		B, C, D;
	}
	
	private Port port;
	private int portBit;
	
	private Pin(Port port, int portBit) { 
		this.port = port;
		this.portBit = portBit;
	}
	
	public Port getPort() {
		return port;
	}
	
	public int getPortBit() {
		return portBit;
	}
	
	public Pin getPin(int pin) throws IllegalArgumentException {
		if (pin < 0 || pin >= NUM_PINS) {
			throw new IllegalArgumentException("Pin " + pin + " is not valid.");
		}
		return Pin.values()[pin];
	}
}
