#define F_CPU 8000000L

#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
#include <string.h>

#define USART_BAUDRATE 9600
#define BAUD_PRESCALE (((F_CPU / (USART_BAUDRATE * 16UL))) - 1)

void USART_init(void);

const volatile char* name = "AT+NAMEBayouBot";
volatile int pos = 0;
volatile int size = 15;

int main(void) {
  USART_init();
  
  //Get ready for trasmission
  UCSR0B |= (1 << UDRIE0); //Turn on Empty data interrupt
  
  //led bits for D2 and D3
  unsigned char led1 = 1 << 2;
  unsigned char led2 = 1 << 3;
  
  // Set led pins as outputs
  DDRD |= led1 | led2;
  
  PORTD ^= led1;
  while (1) {
      PORTD ^= (led1 | led2);
      _delay_ms(500);
  }

  return 1;
}

//Initialize USART for the AVR
void USART_init(void) {
  UCSR0B |= (1 << RXEN0) | (1 << TXEN0); //Turn on Rx and Tx
  UCSR0C |= (1 << UCSZ00) | (1 << UCSZ01); //8 bit characters
  
  UBRR0H = (BAUD_PRESCALE >> 8); //Set upper 8 bits of baud rate value
  UBRR0L = BAUD_PRESCALE; //Set lower 8 bits of baud rate value
  
  sei();
}

ISR(USART_UDRE_vect) {
  UDR0 = name[pos];
  pos++;
  if (pos > size) {
    UCSR0B ^= (1 << UDRIE0);
  }
}