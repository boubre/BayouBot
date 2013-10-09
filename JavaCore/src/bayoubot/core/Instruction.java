package bayoubot.core;

public enum Instruction {
	SET_DDRB((byte)0x01, 1),
	SET_DDRC((byte)0x02, 1),
	SET_DDRD((byte)0x03, 1),
	SET_PORTB((byte)0x04, 1),
	SET_PORTC((byte)0x05, 1),
	SET_PORTD((byte)0x06, 1),
	CLEAR_DDRB((byte)0x07, 1),
	CLEAR_DDRC((byte)0x08, 1),
	CLEAR_DDRD((byte)0x09, 1),
	CLEAR_PORTB((byte)0x0a, 1),
	CLEAR_PORTC((byte)0x0b, 1),
	CLEAR_PORTD((byte)0x0c, 1),
	GET_PORT((byte)0x0d, 1),
	RESET((byte)0x00, 0);
	
	byte command;
	int nArgs;
	
	private Instruction(byte command, int nArgs) {
		this.command = command;
		this.nArgs = nArgs;
	}
	
	public byte getCommand() {
		return command;
	}
	
	public int getNumArgs() {
		return nArgs;
	}
	
	public static byte[] makeInstruction(Instruction instr, byte[] args) {
		byte[] instrStr = new byte[instr.nArgs + 1];
		instrStr[0] = instr.getCommand();
		for (int i = 1; i < instrStr.length; i++) {
			instrStr[i] = args[i-1];
		}
		return instrStr;
	}
}
