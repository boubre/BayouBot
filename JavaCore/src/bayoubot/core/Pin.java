package bayoubot.core;

/**
 * A GPIO pin that is mapped to a port and bit on the AVR.
 */
public enum Pin {
	P1(Port.C, 0), //23
	P2(Port.B, 2), //16
	P3(Port.B, 1), //15
	P4(Port.D, 2), //4
	P5(Port.D, 3), //5
	P6(Port.D, 4), //6
	P7(Port.B, 6), //9
	P8(Port.B, 7), //10
	P9(Port.D, 5), //11
	P10(Port.D, 6), //12
	P11(Port.D, 7), //13
	P12(Port.B, 0), //14
	MTR_AIN1(Port.C, 5), //28
	MTR_AIN2(Port.C, 4), //27
	MTR_BIN1(Port.C, 3), //26
	MTR_BIN2(Port.C, 2), //25
	MTR_STBY(Port.C, 1); //24
	
	public static final int NUM_PINS = Pin.values().length;
	
	/**
	 * The port of the GPIO pin.
	 */
	public enum Port {
		B, C, D;
	}
	
	private Port port;
	private int portBit;
	
	private Pin(Port port, int portBit) { 
		this.port = port;
		this.portBit = portBit;
	}
	
	/**
	 * @return The port the pin is on.
	 */
	public Port getPort() {
		return port;
	}
	
	/**
	 * @return The bit of the port the pin is.
	 */
	public int getPortBit() {
		return portBit;
	}
}
