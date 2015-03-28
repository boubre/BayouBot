package bayoubot.core;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class SerialPortBayouBot extends BayouBot {
	private SerialPort serialPort;
	
	/**
	 * Create a new serial port BayouBot.
	 * @param portName The name of the serial port to connect to. (eg COM5 or /dev/rfcomm0)
	 */
	public SerialPortBayouBot(String portName) {
		serialPort = new SerialPort(portName);
		try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, 
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);
        }
        catch (SerialPortException ex) {
        	System.err.println("Could not open port!!!");
			ex.printStackTrace();
        }
		
		init();
	}
	
	private void init() {
		// Set drive ports to outputs.
	}

	/**
	 * This method is here so that client code can enumerate all serial port names without needing to directly reference jssc.
	 * @return An array of all system serial port names.
	 */
	public static String[] getSerialPortNames() {
		return SerialPortList.getPortNames();
	}
	
	@Override
	public void setPinMode(Pin pin, PinMode mode) {
		Instruction instr = null;
		byte[] arg = { (byte)(1 << pin.getPortBit()) };
		if (mode == PinMode.OUTPUT) {
			switch (pin.getPort()) {
			case B:
				instr = Instruction.SET_DDRB;
				break;
			case C:
				instr = Instruction.SET_DDRC;
				break;
			case D:
				instr = Instruction.SET_DDRD;
				break;
			default:
				assert false;
			}
		} else if (mode == PinMode.INPUT) {
			switch (pin.getPort()) {
			case B:
				instr = Instruction.CLEAR_DDRB;
				break;
			case C:
				instr = Instruction.CLEAR_DDRC;
				break;
			case D:
				instr = Instruction.CLEAR_DDRD;
				break;
			default:
				assert false;
			}
		} else {
			throw new NullPointerException("setPinMode() does not accept null mode");
		}
		assert instr != null;
		
		try {
			serialPort.writeBytes(Instruction.makeInstruction(instr, arg));
		} catch (SerialPortException ex) {
			System.err.println("Could not send command!!!");
			ex.printStackTrace();
		}
	}

	@Override
	public void setPinState(Pin pin, PinState state) {
		Instruction instr = null;
		byte[] arg = { (byte)(1 << pin.getPortBit()) };
		if (state == PinState.HIGH) {
			switch (pin.getPort()) {
			case B:
				instr = Instruction.SET_PORTB;
				break;
			case C:
				instr = Instruction.SET_PORTC;
				break;
			case D:
				instr = Instruction.SET_PORTD;
				break;
			default:
				assert false;
			}
		} else if (state == PinState.LOW) {
			switch (pin.getPort()) {
			case B:
				instr = Instruction.CLEAR_PORTB;
				break;
			case C:
				instr = Instruction.CLEAR_PORTC;
				break;
			case D:
				instr = Instruction.CLEAR_PORTD;
				break;
			default:
				assert false;
			}
		} else {
			throw new NullPointerException("setPinState() does not accept null state");
		}
		assert instr != null;
		
		try {
			serialPort.writeBytes(Instruction.makeInstruction(instr, arg));
		} catch (SerialPortException ex) {
			System.err.println("Could not send command!!!");
			ex.printStackTrace();
		}
	}

	@Override
	public void resetPins() {
		try {
			serialPort.writeBytes(Instruction.makeInstruction(Instruction.RESET, null));
		} catch (SerialPortException ex) {
			System.err.println("Could not send command!!!");
			ex.printStackTrace();
		}
	}

	@Override
	public PinMode queryPinMode(Pin pin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PinState queryPinState(Pin pin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void turnLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveForward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveBackward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		stop();
		resetPins();
		try {
			serialPort.closePort();
		} catch (SerialPortException ex) {
			System.err.println("Could not close serial port!");
			ex.printStackTrace();
		}
	}

}
