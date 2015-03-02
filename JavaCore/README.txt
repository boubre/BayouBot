Insructions for connecting the BayouBot Bluetooth device to a virtual serial port:

=========================================
WINDOWS
=========================================
Turn the device on.
Use the built in Bluetooth manager to add the device (pairing code 1234).
Open the device properties and check the box that creates a virtual serial port.
The menu should tell you that it is /dev/rfcomm#.

=========================================
UBUNTU
=========================================
Turn the device on.
Install the blueman package. (sudo apt-get install blueman)
Use blueman to connect to the device (pairing code 1234).
In blueman setup, you can connect to a serial port. It will tell you it is /dev/rfcomm#.

