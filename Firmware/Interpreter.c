#include "Interpreter.h"

static unsigned char InstructionTable[NUM_INSTRUCTIONS][1];

void Interpreter_init(void) {
  int i;
  for (i = 1; i < 14; i++) {
    InstructionTable[i][0] = 2;
  }
  InstructionTable[0][0] = 1;
}

void Interpreter_execute(void) {
  unsigned char iLen = InstructionTable[(unsigned char)USART_peek()][0];
  if (USART_available() < iLen) return;
  
  unsigned char instr[iLen];
  int i;
  for (i = 0; i < iLen; i++) {
    instr[i] = (unsigned char) USART_readChar();
  }
  
  switch(instr[0]) {
    case 0x00:
      DDRB |= 0xFF;
      DDRC |= 0xFF;
      DDRD |= 0xFF;
      PORTB |= 0x00;
      PORTC |= 0x00;
      PORTD |= 0x00;
      break;
    case 0x01:
      DDRB |= instr[1];
      break;
    case 0x02:
      DDRC |= instr[1];
      break;
    case 0x03:
      DDRD |= instr[1];
      break;
    case 0x04:
      PORTB |= instr[1];
      break;
    case 0x05:
      PORTC |= instr[1];
      break;
    case 0x06:
      PORTD |= instr[1];
      break;
    case 0x07:
      DDRB &= ~instr[1];
      break;
    case 0x08:
      DDRC &= ~instr[1];
      break;
    case 0x09:
      DDRD &= ~instr[1];
      break;
    case 0x0a:
      PORTB &= ~instr[1];
      break;
    case 0x0b:
      PORTC &= ~instr[1];
      break;
    case 0x0c:
      PORTD &= ~instr[1];
      break;
    case 0x0d:
      switch (instr[1])
      {
	case 0x00:
	  USART_sendChar((char)DDRB);
	  break;
	case 0x01:
	  USART_sendChar((char)DDRC);
	  break;
	case 0x02:
	  USART_sendChar((char)DDRD);
	  break;
	case 0x03:
	  USART_sendChar((char)PINB);
	  break;
	case 0x04:
	  USART_sendChar((char)PINC);
	  break;
	case 0x05:
	  USART_sendChar((char)PIND);
	  break;
      }
      break;
  }
}