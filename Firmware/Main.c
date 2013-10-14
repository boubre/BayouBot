#include "Main.h"

char str[32];

/* This is the main loop of the AVR's programming
 */
int main(void) { 
  init();
  
  while (1) {
    if (USART_available() > 0) {
      Interpreter_execute();
    }
  }

  return 1;
}

/* Perform initial setup on power on.
 */
void init(void) {
  USART_init();
  Interpreter_init();
}