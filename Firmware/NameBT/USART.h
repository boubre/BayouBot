#ifndef USART_H
#define USART_H

#include "Global.h"
#include "Util.h"
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/atomic.h>
#include <string.h>

#define USART_BAUDRATE 9600
#define BAUD_PRESCALE (((F_CPU / (USART_BAUDRATE * 16UL))) - 1)

void USART_init(void);
unsigned char USART_sendStr(const char*);
unsigned char USART_sendChar(char);
unsigned char USART_available(void);
char USART_readChar(void);
char USART_peek(void);

#endif
