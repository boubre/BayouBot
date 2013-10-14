#ifndef INTERPRETER_H
#define INTERPRETER_H

#include "Global.h"
#include <avr/io.h>
#include "USART.h"

#define NUM_INSTRUCTIONS 14

void Interpreter_init(void);
void Interpreter_execute(void);

#endif
