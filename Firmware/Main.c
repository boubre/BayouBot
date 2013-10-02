#include "Main.h"

char str[32];

/* This is the main loop of the AVR's programming
 */
int main(void) { 
  init();
  
  DDRD |= (1 << 2) | (1 << 3);
  DDRD &= ~(1 << 4);
  while (1) {
    if (USART_available() > 0) {
      USART_sendChar(USART_readChar());
    }
  }
  /*
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
  */

  return 1;
}

/* Perform initial setup on power on.
 */
void init(void) {
  USART_init();
}