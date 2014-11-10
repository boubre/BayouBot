#include "Name.h"

char str[32];

/* This is the main loop of the AVR's programming
 */
int main(void) { 
  init();
  
  _delay_ms(5000); //Give time for BT device to start up

  USART_sendStr("AT+NAMEBayouBot");

  //Toggle Pin 1. Connect LED to see completion.
  DDRD |= 0b00000100;
  while (1) {
    PORTD |= 0b0000100;
    _delay_ms(200);
    PORTD &= ~(0b0000100);
    _delay_ms(200);
  }

  return 1;
}

/* Perform initial setup on power on.
 */
void init(void) {
  USART_init();
}
