#include "Main.h"

/* This is the main loop of the AVR's programming
 */
int main(void) {
  USART_init();
  
  //Get ready for trasmission
  //UCSR0B |= (1 << UDRIE0); //Turn on Empty data interrupt
  
  //led bits for D2 and D3
  unsigned char led1 = 1 << 2;
  unsigned char led2 = 1 << 3;
  
  // Set led pins as outputs
  DDRD |= led1 | led2;
  
  PORTD ^= led1;
  while (1) {
      PORTD ^= (led1 | led2);
      _delay_ms(1000);
  }

  return 1;
}