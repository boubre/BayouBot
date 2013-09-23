PROJECT=test
SOURCES=test.c

$(PROJECT).hex: $(PROJECT).out
	avr-objcopy -j .text -j .data -O ihex $(PROJECT).out $(PROJECT).hex

$(PROJECT).out: $(SOURCES)
	avr-gcc -mmcu=atmega328p -Wall -O2 -o $(PROJECT).out $(SOURCES)

program: test.hex
	sudo avrdude -c avrispv2 -p m328p -P /dev/ttyACM0 -U flash:w:test.hex

clean:
	rm -rf *.o *out*.hex

fuseset:
	sudo avrdude -c avrispv2 -p m328p -P /dev/ttyACM0 -U lfuse:w:0xe2:m
