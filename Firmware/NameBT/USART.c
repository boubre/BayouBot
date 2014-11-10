#include "USART.h"

volatile static CircularBuffer_Str32 txBuf;
volatile static CircularBuffer_Str32 rxBuf;

/* Initialize USART communications.
 * -Turns on Rx and Tx, sets up USART with 8 bit bytes
 * -Sets baud rate to header-defined value
 * -Enables interrupts
 */
void USART_init(void) {
  UCSR0B |= (1 << RXEN0) | (1 << TXEN0) | (1 << RXCIE0); //Turn on Rx and Tx, and Rx interrupts
  UCSR0C |= (1 << UCSZ00) | (1 << UCSZ01); //8 bit characters
  
  UBRR0H = (BAUD_PRESCALE >> 8); //Set upper 8 bits of baud rate value
  UBRR0L = BAUD_PRESCALE; //Set lower 8 bits of baud rate value
  
  sei(); //Turn on interrupts
  
  //Initialize data structures
  Util_initBuffer(&txBuf);
  Util_initBuffer(&rxBuf);
}

/* Send a string via USART. Will return a SUCCESS or FAILURE value.
 * The string is placed in a buffer and is transmitted via interrupts.
 * Refuses to commit to sending the string if the buffer does not have space.
 */
unsigned char USART_sendStr(const char* str) {
  unsigned char rv = FAILURE;
  size_t length = strlen(str); 
  ATOMIC_BLOCK(ATOMIC_RESTORESTATE) { 
    if (txBuf.count + length < CB_STR32_SIZE) {
      Util_writeStr(&txBuf, str, 0, length);
      rv = SUCCESS;
      UCSR0B |= (1 << UDRIE0); //Turn on Empty data interrupts
    }
  }
  return rv;
}

/* Send a character via USART. Will return a SUCCESS or FAILURE value.
 * The character is placed in a buffer and is transmitted via interrupts.
 * Refuses to commit to sending the string if the buffer does not have space.
 */
unsigned char USART_sendChar(char c) {
  unsigned char rv = FAILURE;
  ATOMIC_BLOCK(ATOMIC_RESTORESTATE) {
    if (txBuf.count + 1 < CB_STR32_SIZE) {
      Util_writeChar(&txBuf, c);
      rv = SUCCESS;
      UCSR0B |= (1 << UDRIE0); //Turn on Empty data interrupts
    }
  }
  return rv;
}

/* Return the number of characters available to read
 */
unsigned char USART_available(void) {
  unsigned char rv;
  ATOMIC_BLOCK(ATOMIC_RESTORESTATE) {
    rv = rxBuf.count;
  }
  return rv;
}

/* Read a single character from the Rx buffer.
 * Only execute this function if USART_available() returns nonzero.
 */
char USART_readChar(void) {
  char rv;
  ATOMIC_BLOCK(ATOMIC_RESTORESTATE) {
    rv = Util_readChar(&rxBuf);
  }
  return rv;
}

char USART_peek(void) {
  char rv;
  ATOMIC_BLOCK(ATOMIC_RESTORESTATE) {
    rv = Util_peek(&rxBuf);
  }
  return rv;
}

/* ISR for empty tx data. 
 * Will send next byte in tx buffer.
 * Should turn itself off once there is no more data to transmit.
 */
ISR(USART_UDRE_vect) {
  UDR0 = Util_readChar(&txBuf);
  if (Util_isEmpty(&txBuf)) {
    UCSR0B &= ~(1 << UDRIE0); //Turn off Empty data interrupts
  }
}

/* ISR for incoming rx data.
 * Will store byte in rx buffer. If the buffer is full, the byte is lost.
 */
ISR(USART_RX_vect) {
  char c = UDR0;
  if (!Util_isFull(&rxBuf)) {
    Util_writeChar(&rxBuf, c);
  }
}