#ifndef UTIL_H
#define UTIL_H

#include "Global.h"

/* A 32 character circuluar string buffer.
 */
typedef struct {
  unsigned char readPos;
  unsigned char writePos;
  unsigned char count;
  char data[32];
} CircularBuffer_Str32;

#define CB_STR32_SIZE 32

void Util_initBuffer(volatile CircularBuffer_Str32*); //Can also be used to init
unsigned char Util_isEmpty(volatile CircularBuffer_Str32*);
unsigned char Util_isFull(volatile CircularBuffer_Str32*);
void Util_writeChar(volatile CircularBuffer_Str32*, char);
char Util_readChar(volatile CircularBuffer_Str32*);
unsigned char Util_writeStr(volatile CircularBuffer_Str32*, const char*, int, int);

#endif