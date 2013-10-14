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
      break;
  }
}