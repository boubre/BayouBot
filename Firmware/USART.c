#include "USART.h"

/* Initialize USART communications.
 * -Turns on Rx and Tx, sets up USART with 8 bit bytes
 * -Sets baud rate to header-defined value
 * -Enables interrupts
 */
void USART_init(void) {
  UCSR0B |= (1 << RXEN0) | (1 << TXEN0); //Turn on Rx and Tx
  UCSR0C |= (1 << UCSZ00) | (1 << UCSZ01); //8 bit characters
  
  UBRR0H = (BAUD_PRESCALE >> 8); //Set upper 8 bits of baud rate value
  UBRR0L = BAUD_PRESCALE; //Set lower 8 bits of baud rate value
  
  sei();
}