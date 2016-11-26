package rts.robot.web.controller;

import org.apache.log4j.Logger;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialPortManager {
    Logger LOGGER = Logger.getLogger(SerialPortManager.class);
    private String portName = "/dev/ttyACM0";
    private SerialPort serialPort;

    public void connect() throws Exception {
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
        LOGGER.info("Trying to connect to /dev/ttyACM0");
        LOGGER.info(CommPortIdentifier.getPortIdentifiers().toString());
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            LOGGER.error("Port is currently in use");
        } else {
            LOGGER.info("Trying to connect to /dev/ttyACM0 with timeout");
            int timeout = 2000000;
            CommPort commPort = portIdentifier.open(this.getClass().getName(), timeout);

            LOGGER.info("commPort: ...");
            if (commPort instanceof SerialPort) {
                serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE
                );
            LOGGER.info("commPort, set properties: ...");
            } else {
                LOGGER.error("Error: It's not serial port");
            }
        }
    }

    public void write(int signal) throws IOException {
        OutputStream out = serialPort.getOutputStream();
        LOGGER.info("Before sending");
        out.write(signal);
        LOGGER.info("After sending");
        out.close();
    }

    public void read() throws IOException {
        byte[] buffer = new byte[1024];
        int len = -1;
        InputStream in = serialPort.getInputStream();
        LOGGER.info("Before reading");
        len = in.read(buffer);
        LOGGER.info("Reading: " + new String( buffer, 0, len ));
    }
}
