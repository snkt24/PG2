package com.airpacs.pressuregauge;

import java.nio.ByteBuffer;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class ComPortConnection {

	public static SerialPort comPort;

	public static void setupEventsForComPortIfAvailable() {
		comPort = getSerialPort();
		/*
		 * if(comPort!=null) { comPort.openPort(); comPort.addDataListener(new
		 * SerialPortDataListener() {
		 * 
		 * @Override public int getListeningEvents() {
		 * System.out.println("Add port data available listener..."); return
		 * SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
		 * 
		 * @Override public void serialEvent(SerialPortEvent event) {
		 * System.out.println("Data Available....");
		 * 
		 * } });
		 * 
		 * }else { System.out.
		 * println("Com Port is Not available at this moment. Not setting up Listener."
		 * ); }
		 */
		comPort.openPort();
		try {
			while (true) {
				while (comPort.bytesAvailable() == 0)
					Thread.sleep(20);

				byte[] readBuffer = new byte[comPort.bytesAvailable()];
				int numRead = comPort.readBytes(readBuffer, readBuffer.length);
				System.out.println("Read " + numRead + " bytes.");
				System.out.println(new String(readBuffer));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		comPort.closePort();

		/*
		 * comPort.addDataListener(new SerialPortDataListener() {
		 * 
		 * @Override public int getListeningEvents() {
		 * System.out.println("Add port Event Data available Listener ..."); return
		 * SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
		 * 
		 * @Override public void serialEvent(SerialPortEvent event) {
		 * 
		 * 
		 * System.out.println("event"); if (event.getEventType() !=
		 * SerialPort.LISTENING_EVENT_DATA_AVAILABLE) return;
		 * 
		 * 
		 * byte[] newData = new byte[comPort.bytesAvailable()]; int numRead =
		 * comPort.readBytes(newData, newData.length); System.out.println("Read " +
		 * numRead + " bytes.");
		 * 
		 * System.out.println(new String(newData));
		 * 
		 * 
		 * }
		 * 
		 * });
		 */

	}

	public static SerialPort getSerialPort() {
		if (SerialPort.getCommPorts() != null && SerialPort.getCommPorts().length > 0)
			return SerialPort.getCommPorts()[0];
		else
			return null;
	}

}
