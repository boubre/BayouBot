package bayoubot.core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.microedition.io.StreamConnection;

public class BayouBot {
	StreamConnection bbConnection;
	BufferedReader bbRead;
	DataOutputStream bbWrite;
	
	public BayouBot(StreamConnection bbConnection) throws IOException {
		this.bbConnection = bbConnection;
		this.bbRead = new BufferedReader(new InputStreamReader(bbConnection.openInputStream()));
		this.bbWrite = bbConnection.openDataOutputStream();
	}
	
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
	
	public void close() {
		try {
			bbConnection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
