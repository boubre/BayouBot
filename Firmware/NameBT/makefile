PROJECT=NameBluetooth

SOURCES=Name.c \
	USART.c \
	Util.c

HEADERS=Name.h \
	USART.h \
	Util.h \
	Global.h

$(PROJECT).hex: $(PROJECT).out
	avr-objcopy -j .text -j .data -O ihex $(PROJECT).out $(PROJECT).hex

$(PROJECT).out: $(SOURCES) $(HEADERS)
	avr-gcc --std=c99 -mmcu=atmega328p -Wall -O2 -o $(PROJECT).out $(SOURCES)

program: $(PROJECT).hex
	sudo avrdude -c avrispv2 -p m328p -P /dev/ttyACM0 -U flash:w:$(PROJECT).hex

clean:
	rm -rf *.o *.out *.hex

fuseset:
	sudo avrdude -c avrispv2 -p m328p -P /dev/ttyACM0 -U lfuse:w:0xe2:m
