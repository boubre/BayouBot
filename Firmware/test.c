#define F_CPU 8000000L
#include <avr/io.h>
#include <util/delay.h>

int main(void)
{
  // Set Port D pins as all outputs
  DDRD = 0xff;
  
  PORTD = 0b01010101;
  while (1)
  {
      PORTD = ~PORTD;
      _delay_ms(500);
  }

  return 1;
}
