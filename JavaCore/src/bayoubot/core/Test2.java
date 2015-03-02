package bayoubot.core;

import jssc.SerialPort;
import jssc.SerialPortException;

public class Test2 {
	public static void main(String... args) {
		SerialPort serialPort = new SerialPort("COM5");
        try {
        	System.out.println("Opening...");
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600, 
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
            serialPort.writeBytes("Hubabaloo!".getBytes());//Write data to port
            serialPort.writeBytes("wacka".getBytes());//Write data to port
            System.out.println("Written.");
            Thread.sleep(3000);
            System.out.println("reading.");
            byte[] buffer = serialPort.readBytes();
            System.out.println(new String(buffer));
            serialPort.closePort();//Close serial port
            System.out.println("done.");
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        } catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
