package bayoubot.core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.microedition.io.StreamConnection;

/**
 * A class representing the BayoutBot that facilitates communications
 * and interactions with the hardware.
 */
public class BayouBot {
	StreamConnection bbConnection;
	BufferedReader bbRead;
	DataOutputStream bbWrite;
	
	/**
	 * Create a new BayouBot
	 * @param bbConnection The connection representing the Bluetooth communications with the hardware.
	 * @throws IOException Can come from opening streams from the supplied connection.
	 */
	public BayouBot(StreamConnection bbConnection) throws IOException {
		this.bbConnection = bbConnection;
		this.bbRead = new BufferedReader(new InputStreamReader(bbConnection.openInputStream()));
		this.bbWrite = bbConnection.openDataOutputStream();
	}
	
	/**
	 * Set the I/O mode of a pin.
	 * @param pin The pin modify.
	 * @param mode The desired mode.
	 */
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
			bbWrite.write(Instruction.makeInstruction(instr, arg));
			bbWrite.flush();
		} catch (IOException e) {
			System.err.println("Could not send command!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the IO state of a PIN
	 * @param pin The pin to modify
	 * @param state The desired state
	 */
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
			bbWrite.write(Instruction.makeInstruction(instr, arg));
			bbWrite.flush();
		} catch (IOException e) {
			System.err.println("Could not send command!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Set all pins to OUTPUT and all voltages to LOW
	 */
	public void resetPins()
	{
		try {
			bbWrite.write(Instruction.makeInstruction(Instruction.RESET, null));
			bbWrite.flush();
		} catch (IOException e) {
			System.err.println("Could not send command!!!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Ask the hardware for the current mode of a pin
	 * @param pin The pin to query
	 * @return The IO mode of the pin
	 */
	public PinMode queryPinMode(Pin pin) {
		byte arg = (byte)0xFF;
		switch(pin.getPort())
		{
		case B:
			arg = 0x00;
			break;
		case C:
			arg = 0x01;
			break;
		case D:
			arg = 0x02;
			break;
		default:
			assert false;
		}
		assert arg != (byte)0xFF;
		try {
			bbWrite.write(Instruction.makeInstruction(Instruction.GET_PORT, new byte[] { arg }));
			bbWrite.flush();
			byte response = -1;
			while (response == -1) response = (byte)bbRead.read();
			byte value = (byte) (response & (1 << pin.getPortBit()));
			return value == 0 ? PinMode.INPUT : PinMode.OUTPUT;
		} catch (IOException e) {
			System.err.println("Could not send command!!!");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Ask the hardware for the current state of a pin
	 * @param pin The pin to query
	 * @return The current voltage of that pin
	 */
	public PinState queryPinState(Pin pin) {
		byte arg = (byte)0xFF;
		switch(pin.getPort())
		{
		case B:
			arg = 0x03;
			break;
		case C:
			arg = 0x04;
			break;
		case D:
			arg = 0x05;
			break;
		default:
			assert false;
		}
		assert arg != (byte)0xFF;
		try {
			bbWrite.write(Instruction.makeInstruction(Instruction.GET_PORT, new byte[] { arg }));
			bbWrite.flush();
			byte response = -1;
			while (response == -1) response = (byte)bbRead.read();
			byte value = (byte) (response & (1 << pin.getPortBit()));
			return value == 0 ? PinState.LOW : PinState.HIGH;
		} catch (IOException e) {
			System.err.println("Could not send command!!!");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Properly close the given BayouBot connecion.
	 */
	public void close() {
		try {
			bbConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
