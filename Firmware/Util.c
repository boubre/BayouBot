#include "Util.h"

/* Initilizes buffer variables.
 * Can also be used to "clear" the state of the buffer.
 */
void Util_initBuffer(volatile CircularBuffer_Str32* cb) {
  cb->readPos = 0;
  cb->writePos = 0;
  cb->count = 0;
}

/* Determines if a circular buffer is empty.
 * Returns 0 for false, nonzero for true.
 */
unsigned char Util_isEmpty(volatile CircularBuffer_Str32* cb) {
  return cb->count == 0;
}

/* Determines if a circular buffer is full.
 * Returns 0 for false, nonzero for true.
 */
unsigned char Util_isFull(volatile CircularBuffer_Str32* cb) {
  return cb->count == CB_STR32_SIZE;
}

/* Write a character to the circular buffer.
 * Should call Util_isFull() before this or overwrite may happen!
 */
void Util_writeChar(volatile CircularBuffer_Str32* cb, char c) {
  cb->data[cb->writePos] = c;
  cb->writePos = (cb->writePos + 1) % CB_STR32_SIZE;
  cb->count++;
}

/* Read a character from the circuluar buffer.
 * Should call Util_isEmpty() before this or the buffer could break!
 */
char Util_readChar(volatile CircularBuffer_Str32* cb) {
  char c = cb->data[cb->readPos];
  cb->readPos = (cb->readPos + 1) % CB_STR32_SIZE;
  cb->count--;
  return c;
}

/* Puts as much of the string as possible into the buffer.
 * Starts writing at the start character index, and will right up through the size-1 character index. .
 * Returns the number of characters written to the buffer.
 */
unsigned char Util_writeStr(volatile CircularBuffer_Str32* cb, const char* str, int start, int size) {
  unsigned char count = 0;
  while(Util_isFull(cb) == 0 && start < size) {
    Util_writeChar(cb, str[start + count]);
    count++;
  }
  return count;
}